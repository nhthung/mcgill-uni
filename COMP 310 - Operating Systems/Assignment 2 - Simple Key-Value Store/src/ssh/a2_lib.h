#include <fcntl.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <semaphore.h>
#include <stdlib.h> /* exit() */
#include <errno.h>

#define STORE_NAME "hle30_260793376"
#define SEM_READ_NAME "hle30_read_lock"
#define SEM_WRITE_NAME "hle30_write_lock"

#define POD_CAPACITY 256

#define NUM_PODS 128

#define KEY_SIZE 31
#define VALUE_SIZE 256
#define ENTRY_SIZE (KEY_SIZE + VALUE_SIZE + 4)
#define POD_DATA_SIZE (20 + (sizeof(int) * POD_CAPACITY) * 2)
#define POD_SIZE (ENTRY_SIZE * POD_CAPACITY + POD_DATA_SIZE)
#define STORE_SIZE (POD_SIZE * NUM_PODS)

#define min(a,b) (((a)<(b))?(a):(b))
#define errExit(msg) do {perror(msg); exit(EXIT_FAILURE); } while (0);

typedef enum { false, true } bool;

struct entry_t {
    int id;
    char key[KEY_SIZE];
    char value[VALUE_SIZE];
};

struct pod_t {
    int size;
    int head;
    int tail;
    int read_count;
    int num_unique_keys;
    int read[POD_CAPACITY];
    int next[POD_CAPACITY];
    struct entry_t entries[POD_CAPACITY];
};

struct store_t {
    struct pod_t pods[NUM_PODS];
} *store;

int kv_store_create(char *name);
int kv_store_write(char *key, char *value);
char *kv_store_read(char *key);
char **kv_store_read_all(char *key);

int hash(char *key);
void print_pod(struct pod_t *pod, char *key, int index);
void print_store(struct store_t *store);
bool is_added_entry(struct pod_t *pod, char *key, char *value);
int is_added_key(struct pod_t *pod, char *key, bool after_write);
void write_entry(struct entry_t *entry, char *key, char *value);
void update_pod(struct pod_t *pod, struct entry_t *entry, int entry_index, char *key, bool after_write);
int get_num_values(struct pod_t *pod, char *key);
