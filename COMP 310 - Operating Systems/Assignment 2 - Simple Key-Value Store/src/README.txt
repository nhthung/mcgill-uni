/*
 * Author: Le Nhat Hung, 260793376
 */

/* Settings */

Bigger sizes for the data structures would only sometime work,
and often gives segmentation faults on my personal laptop.
The chosen settings are the highest that gave no segmentation faults.

a2_lib.h:
    #define STORE_NAME "hle30_260793376"
    #define SEM_READ_NAME "hle30_read_lock"
    #define SEM_WRITE_NAME "hle30_write_lock"
    #define POD_CAPACITY 128
    #define NUM_PODS 32
    #define KEY_SIZE 31
    #define VALUE_SIZE 256

Modifications made in tester header:

comp310_a2_test.h:
    #define __TEST_MAX_POD_ENTRY__ 128
    #define __TEST_MAX_KEY__  64
    #define __TEST_SHARED_MEM_NAME__ "hle30_260793376"

Among the submitted source files, a2_helpers.c
is not a modified version of the file posted by the TA’s, but my own.

The tester (tester 1 at least) ran with no errors.

Because of the modifications above, my own modified copy of
the tester header file is also included in the compressed zip.


/* Compiling instructions */

I use the tester's Makefile. To compile and run:
    1. make
    2. ./os_test1
