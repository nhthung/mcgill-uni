/*
 * Author: Le Nhat Hung, 260793376
 */
#include "a2_lib.h"
#include "a2_helpers.c"

int kv_store_create(char *name) {

    int fd = shm_open(
        name,
        O_CREAT | O_EXCL | O_RDWR,
        S_IRWXU);

    if (fd < 0 && errno != EEXIST)
        errExit("shm_open");

    if (fd >= 0) {
        if (ftruncate(fd, STORE_SIZE) == -1)
            errExit("ftruncate");

        struct store_t *store = mmap(
            NULL,
            STORE_SIZE,
            PROT_READ | PROT_WRITE,
            MAP_SHARED,
            fd,
            0);

        if (store == MAP_FAILED)
            errExit("mmap");

        memset(store, '\0', STORE_SIZE);

        for (int i = 0; i < NUM_PODS; i++) {
            struct pod_t *pod = &store->pods[i];
            pod->tail = (pod->head + pod->size - 1) % POD_CAPACITY;
        }

        munmap(store, STORE_SIZE);
    }

    close(fd);

    return EXIT_SUCCESS;
}

int kv_store_write(char *key, char *value) {

    struct store_t *store;
    struct pod_t *pod;
    sem_t *WRITE_LOCK;
    char write_lock_name[50] = "";
    int lock_name_offset,
        pod_index,
        fd;

    fd = shm_open(
        STORE_NAME,
        O_RDWR,
        0);

    if (fd == -1)
        errExit("shm_open");

    store = mmap(
        NULL,
        STORE_SIZE,
        PROT_READ | PROT_WRITE,
        MAP_SHARED,
        fd,
        0);

    if (store == MAP_FAILED)
        errExit("mmap");

    /* Get pointer to pod */
    pod_index = hash(key);
    pod = &store->pods[pod_index];

    /* Unique semaphore for pod */
    lock_name_offset = sprintf(
        write_lock_name,
        "pod_%d_",
        pod_index);

    strncpy(
        write_lock_name + lock_name_offset,
        SEM_WRITE_NAME,
        49 - lock_name_offset);

    WRITE_LOCK = sem_open(
        write_lock_name,
        O_CREAT,
        S_IRWXU | S_IRWXG | S_IRWXO,
        1);

    if (WRITE_LOCK == SEM_FAILED)
        errExit("sem_open");

    /*
     * Start critical section
     */

    sem_wait(WRITE_LOCK);

    /* Only write when the entry isn't already in the store */
    if (!is_added_entry(pod, key, value)) {

        /* Begin writing process */

        /* Get pointer to entry to be written in */
        int entry_index = (pod->tail + 1) % POD_CAPACITY;
        struct entry_t *entry = &pod->entries[entry_index];

        /* Check if key is already in pod.
         * If not, then increment number of unique keys in pod
         */
        update_pod(pod, entry, entry_index, key, false);

        /* Writing the key and value of the new entry */
        write_entry(entry, key, value);

        /* Update entry's id, pod's size, head, tail,
         * next array and read array
         */
        update_pod(pod, entry, entry_index, key, true);

        /* End writing process */
    }

    /*
     * End critical section
     */

    sem_post(WRITE_LOCK);
    sem_close(WRITE_LOCK);

    munmap(store, STORE_SIZE);
    close(fd);

    return 0;
}

/* The read function takes in a key, look into the pod's read array's cell
 * whose index is the key's id. The cell contains the entry index in
 * the pod to read.
 */
char *kv_store_read(char *key) {

    struct store_t *store;
    struct pod_t *pod;
    sem_t
        *WRITE_LOCK,
        *READ_LOCK;
    char write_lock_name[50] = "";
    char read_lock_name[50] = "";
    char *value = NULL;
    int lock_name_offset,
        pod_index,
        fd;

    fd = shm_open(
        STORE_NAME,
        O_RDWR,
        0);

    if (fd == -1)
        errExit("shm_open");

    store = mmap(
        NULL,
        STORE_SIZE,
        PROT_READ | PROT_WRITE,
        MAP_SHARED,
        fd,
        0);

    if (store == MAP_FAILED)
        errExit("mmap");

    /* Get pointer to pod */
    pod_index = hash(key);
    pod = &store->pods[pod_index];

    /* Unique semaphore for pod */
    lock_name_offset = sprintf(
        write_lock_name,
        "pod_%d_",
        pod_index);

    strncpy(
        write_lock_name + lock_name_offset,
        SEM_WRITE_NAME,
        49 - lock_name_offset);

    WRITE_LOCK = sem_open(
        write_lock_name,
        O_CREAT,
        S_IRWXU | S_IRWXG | S_IRWXO,
        1);

    if (WRITE_LOCK == SEM_FAILED)
        errExit("sem_open");

    sprintf(
        read_lock_name,
        "pod_%d_",
        pod_index);

    strncpy(
        read_lock_name + lock_name_offset,
        SEM_READ_NAME,
        49 - lock_name_offset);

    READ_LOCK = sem_open(
        read_lock_name,
        O_CREAT,
        S_IRWXU|S_IRWXG|S_IRWXO,
        1);

    if (READ_LOCK == SEM_FAILED) {
        sem_close(WRITE_LOCK);
        errExit("sem_open");
    }

    /*
     * Start critical seection
     */

    sem_wait(READ_LOCK);

    pod->read_count++;

    if (pod->read_count == 1)
        sem_wait(WRITE_LOCK);

    sem_post(READ_LOCK);

    /* Begin reading process */

    int entry_index = is_added_key(pod, key, false);

    if (entry_index != -1) {
        /* Get key's ID */
        int entry_id = pod->entries[entry_index].id;

        /* Look into read array's cell whose index is key's ID.
         * This cell holds the index of the entry to be read
         */
        struct entry_t *entry = &pod->entries[pod->read[entry_id]];

        /* Clone value */
        value = (char *) calloc(1, sizeof(char) * (strlen(entry->value) + 1));
        strcpy(value, entry->value);

        /* Update key's next value to be read */
        pod->read[entry_id] = pod->next[pod->read[entry_id]];
    }

    /* End reading procses */

    sem_wait(READ_LOCK);
    pod->read_count--;

    if (pod->read_count == 0) {
        sem_post(WRITE_LOCK);
        sem_close(WRITE_LOCK);
    }

    sem_post(READ_LOCK);
    sem_close(READ_LOCK);

    /*
     * End critical section
     */

    munmap(store, STORE_SIZE);
    close(fd);

    return value;
}

char **kv_store_read_all(char *key) {

    struct store_t *store;
    struct pod_t *pod;
    sem_t
        *WRITE_LOCK,
        *READ_LOCK;
    char write_lock_name[50] = "";
    char read_lock_name[50] = "";
    char **values = NULL;
    int lock_name_offset,
        pod_index,
        fd;

    fd = shm_open(
        STORE_NAME,
        O_RDWR,
        0);

    if (fd == -1)
        errExit("shm_open");

    store = mmap(
        NULL,
        STORE_SIZE,
        PROT_READ | PROT_WRITE,
        MAP_SHARED,
        fd,
        0);

    if (store == MAP_FAILED)
        errExit("mmap");

    /* Get pointer to pod */
    pod_index = hash(key);
    pod = &store->pods[pod_index];

    /* Unique semaphore for pod */
    lock_name_offset = sprintf(
        write_lock_name,
        "pod_%d_",
        pod_index);

    strncpy(
        write_lock_name + lock_name_offset,
        SEM_WRITE_NAME,
        49 - lock_name_offset);

    WRITE_LOCK = sem_open(
        write_lock_name,
        O_CREAT,
        S_IRWXU | S_IRWXG | S_IRWXO,
        1);

    if (WRITE_LOCK == SEM_FAILED)
        errExit("sem_open");

    sprintf(
        read_lock_name,
        "pod_%d_",
        pod_index);

    strncpy(
        read_lock_name + lock_name_offset,
        SEM_READ_NAME,
        49 - lock_name_offset);

    READ_LOCK = sem_open(
        read_lock_name,
        O_CREAT,
        S_IRWXU|S_IRWXG|S_IRWXO,
        1);

    if (READ_LOCK == SEM_FAILED) {
        sem_close(WRITE_LOCK);
        errExit("sem_open");
    }

    /*
     * Start critical seection
     */

    sem_wait(READ_LOCK);

    pod->read_count++;

    if (pod->read_count == 1)
        sem_wait(WRITE_LOCK);

    sem_post(READ_LOCK);

    /* Begin reading process */

    int entry_index = is_added_key(pod, key, false);

    if (entry_index != -1) {
        int entry_id = pod->entries[entry_index].id;
        int num_values = get_num_values(pod, key);

        values = (char **) calloc(num_values + 1, sizeof(char *));

        struct entry_t *entry = &pod->entries[pod->read[entry_id]];
        entry_index = pod->read[entry_id];

        int i = 0;
        while (num_values > 0) {
            char *value = (char *) calloc(1, sizeof(char) * (VALUE_SIZE + 1));
            strcpy(value, entry->value);
            values[i] = value;

            entry = &pod->entries[pod->next[entry_index]];
            entry_index = pod->next[entry_index];
            i++;
            num_values--;
        }
    }

    /* End reading procses */

    sem_wait(READ_LOCK);
    pod->read_count--;

    if (pod->read_count == 0) {
        sem_post(WRITE_LOCK);
        sem_close(WRITE_LOCK);
    }

    sem_post(READ_LOCK);
    sem_close(READ_LOCK);

    /*
     * End critical section
     */

    munmap(store, STORE_SIZE);
    close(fd);

    return values;
}
