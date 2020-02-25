
int hash(char *key) {

    int hash = 5381;
    int c;

    while (c = *key++)
        hash = ((hash << 5) + hash) + c;

    return (hash > 0) ? hash % NUM_PODS : -(hash) % NUM_PODS;
}

void print_pod(struct pod_t *pod, char *key, int index) {

    int fd;
    bool pod_was_null = false;
    struct entry_t *entry;

    if (pod == NULL) {
        pod_was_null = true;

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

        index = hash(key);
        pod = &store->pods[hash(key)];
    }

    printf("Pod: %d\n", index);
    for (int i = 0; i < pod->size; i++) {
        entry = &pod->entries[i];

        printf(
            "%d: %s, %s, id: %d\n",
            i,
            entry->key,
            entry->value,
            entry->id);
    }

    printf("read: [");
    for (int i = 0; i < POD_CAPACITY; i++) {
        printf("%d", pod->read[i]);
        if (i < POD_CAPACITY - 1)
            printf(", ");
    }
    printf("]\n");

    printf("next: [");
    for (int i = 0; i < POD_CAPACITY; i++) {
        printf("%d", pod->next[i]);
        if (i < POD_CAPACITY - 1)
            printf(", ");
    }
    printf("]\n\n");

    if (pod_was_null) {
        munmap(store, STORE_SIZE);
        close(fd);
    }
}

void print_store(struct store_t *store) {

    int fd;
    bool store_was_null = false;

    if (store == NULL) {
        store_was_null = true;

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
    }

    struct pod_t *pod;

    for (int i = 0; i < NUM_PODS; i++) {
        pod = &store->pods[i];

        if (pod->size > 0) {
            printf("Pod %d:\n", i);
            print_pod(pod, NULL, i);
        }
    }

    if (store_was_null) {
        munmap(store, STORE_SIZE);
        close(fd);
    }
}

bool is_added_entry(struct pod_t *pod, char *key, char *value) {

    for (int i = 0; i < pod->size; i++) {
        if (
            strncmp(pod->entries[i].key, key, KEY_SIZE) == 0 &&
            strncmp(pod->entries[i].value, value, VALUE_SIZE) == 0) {

            return true;
        }
    }

    return false;
}

/* Search from tail to head of pod for key.
 * If found return entry index of found key. Else return -1.
 *
 * Has boolean after_write.
 * If set to true, this means the an entry with the searched key
 * has just been added to the tail.
 * The search will then ignore the pod's tail,
 * Looking for another entry containing the searched key.
 */
int is_added_key(struct pod_t *pod, char *key, bool after_write) {

    int i, search_count;

    if (after_write) {
        i = (pod->tail - 1 + POD_CAPACITY) % POD_CAPACITY;
        search_count = 1;
    }

    else {
        i = pod->tail;
        search_count = 0;
    }

    for (
        ; search_count < pod->size;
        i = (i - 1 + POD_CAPACITY) % POD_CAPACITY,
        search_count++) {

        if (strcmp(pod->entries[i].key, key) == 0)
            return i;
    }

    return -1;
}

void write_entry(struct entry_t *entry, char *key, char *value) {

    memcpy(
        entry->key,
        key,
        min(strlen(key), KEY_SIZE - 1));

    memcpy(
        entry->value,
        value,
        min(strlen(value), VALUE_SIZE - 1));
}

void update_pod(
    struct pod_t *pod,
    struct entry_t *entry,
    int entry_index,
    char *key,
    bool after_write) {

    /* Before the entry is written, check if key is already in pod.
     * If not, then increment number of unique keys in pod
     */
    if (!after_write) {
        if (is_added_key(pod, key, false) == -1) {
            pod->num_unique_keys = (pod->num_unique_keys + 1) % POD_CAPACITY;
        }
    }

    /* After the entry is written,
     * Update entry's id, pod's size, head, tail, next array and read array.
     */
    else {
        if (pod->size < POD_CAPACITY) { /* Pod isn't full */
            pod->size++;
            pod->tail = (pod->head + pod->size - 1) % POD_CAPACITY;
        }

        else { /* Pod is full */
            pod->tail = pod->head;
            pod->head = (pod->head + 1) % POD_CAPACITY;
        }

        /* Set id of key */
        entry->id = pod->num_unique_keys - 1;

        /* Set key's next entry for the read function to be itself */
        pod->next[entry_index] = entry_index;

        /* Modify pod's read array.
         * The index of the read array represents
         * a unique key id (0 to POD_CAPACITY - 1).
         * The read function takes in a key, look into the pod's read array's cell
         * whose index is the key's id. The cell contains the entry index in
         * the pod to read.
         *
         * After writing the new entry,
         * we set the cell corresponding to the new entry's key in the
         * read array to be the net entry itself.
         * This means the newest entry will be the first to be read
         * by the read function.
         */
        pod->read[entry->id] = entry_index;


        /* Check if there are other entries of same key in pod.
         * If ues, build pod's next array (which emulates a linked list)
         * which connects all entries with the same key.
         */
        int next_index = is_added_key(pod, key, true);
        if (next_index > -1) {
            pod->next[entry_index] = pod->next[next_index];
            pod->next[next_index] = entry_index;
        }
    }
}

int get_num_values(struct pod_t *pod, char *key) {

    int num_values = 0;

    for (
        int i = pod->tail;;
        i = (i - 1 + POD_CAPACITY) % POD_CAPACITY) {

        if (strncmp(pod->entries[i].key, key, KEY_SIZE) == 0)
            num_values++;

        if (i == pod->head)
            break;
    }

    return num_values;
}
