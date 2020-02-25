#define _GNU_SOURCE
#define STACK_SIZE 65536

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <errno.h>
#include <sched.h>

// If TIMING_ON is set, we record the execution time
#include "timing.h"
//#define TIMING_ON

#ifdef TIMING_ON
#define TIMING_RES
#include "timing.c"
#endif

// Signal handling not required anymore
//#include <signal.h>

// Initialized values
unsigned int size = 0;
char *line = NULL;
pid_t pid = -1;

// Free memory when program exits
void at_exit() {
    free(line);
}

char* get_a_line() {
    int c;
    unsigned int i = 0;
    char *new_line;
    
    // Initialize the at_exit callback on the first call
    if(size == 0) atexit(at_exit);

    // Initialize size on the first call
    // Reinitialize the default size if we extended it on the previous call
    if(size != 512) {
        size = 512;

        // Check if realloc succeeded
        if((new_line = (char*)realloc(line, size*sizeof(char*))) == NULL) {
            perror( "Error with realloc" );
            // The system can't allocate memory to get the user input, kill the program
            exit(EXIT_FAILURE);
        }
        line = new_line; 
    } 
        
    while ((c = getchar()) != '\n' && c != EOF) {
        line[i++] = (char)c;
        
        // T command exceed the line size, let's double it
        if(i == size-1) {
            // Double the size
            size *= 2;

            // Check if realloc succeeded
            if((new_line = (char*)realloc(line, size)) == NULL) {
                perror( "Error with realloc" );
                // The system can't allocate memory to get the user input, kill the program
                exit(EXIT_FAILURE);
            }
            line = new_line; 
        }
    }

    // Add the end of line character
    line[i] = '\0';
    return line;
}

unsigned int length(const char* str) {
    int i = 0;
	for(; *str != '\0'; str++) i++;
	return i;
}

static int clone_function(void *line) {    
    execl("/bin/sh", "sh", "-c", (char*)line, NULL);
    _exit(errno);
}

int my_system(char* line, char* pipe, int io) {
    #ifdef FORK
        int status;
        pid = fork();
        
        // failure
        if(pid == -1) {
            perror( "Error with fork" );
            exit(EXIT_FAILURE);
        // child
        } else if(pid == 0) {
            execl("/bin/sh", "sh", "-c", line, NULL);
            exit(errno); 
        // parent
        } else {
            if(waitpid(pid, &status, 0) == -1) perror("Error with waitpid");
            // Reset the pid  
            pid = -1;            
            if (WIFEXITED (status)) return WEXITSTATUS(status);
            // Default value if status is not available
            return -1;
        }
    #elif VFORK
        int status;
        pid = vfork();
        
        // failure
        if(pid == -1) {
            perror( "Error with vfork" );
            exit(EXIT_FAILURE);
        // child
        } else if(pid == 0) {
            execl("/bin/sh", "sh", "-c", line, NULL);
            _exit(errno);
        // parent
        } else {
            if(waitpid(pid, &status, 0) == -1) perror("Error with waitpid");   
            // Reset the pid  
            pid = -1;            
            if (WIFEXITED (status)) return WEXITSTATUS(status);
            // Default value if status is not available
            return -1;
        }
    #elif CLONE
        int status;

        // Create the stack
        char *stack = (char*)malloc(STACK_SIZE);
        if(stack == NULL) {
            perror("Error with malloc");
            exit(EXIT_FAILURE);
        }

        pid = clone(clone_function, stack + STACK_SIZE, CLONE_VFORK|CLONE_FS|SIGCHLD, (void*)line);

        // failure
        if(pid == -1) {
            perror( "Error with clone" );
            free(stack);
            exit(EXIT_FAILURE);
        }

        if(waitpid(pid, &status, 0) == -1) perror("Error with waitpid");
        // Reset the pid  
        pid = -1;
        free(stack);               
        if (WIFEXITED (status)) return WEXITSTATUS(status);
        // Default value if status is not available
        return -1;
        
    #elif PIPE
        int status;
        pid = fork();
        
        // failure
        if(pid == -1) {
            perror( "Error with fork" );
            exit(EXIT_FAILURE);
        // child
        } else if(pid == 0) {
            // If the child read
            if(io == 0)  {
                close(0);
                open(pipe, O_RDONLY);
            // If the child write
            } else if (io == 1) {
                close(1);
                open(pipe, O_WRONLY);
            }

            execl("/bin/sh", "sh", "-c", line, NULL);
            exit(errno);
        // parent
        } else {
            if(waitpid(pid, &status, 0) == -1) perror("Error with waitpid"); 
            // Reset the pid  
            pid = -1;
            if (WIFEXITED (status)) return WEXITSTATUS(status);
            // Default value if status is not available
            return -1;
        }
    #endif
    return EXIT_SUCCESS;
}

// Signal handling not required anymore
/*void sighandler(int sig) {
    if(pid > 0) kill(pid, SIGKILL);
}*/

int main(int argc, char *argv[]) {

    #if defined(TIMING_ON) 
    // Get the clock resolution
    get_res();
    
    // Timing purpose - huge malloc
    #if defined(TIMING_RES)
    allocate_resources();
    #endif

    #endif
    
    // Signal handling not required anymore
    // signal(SIGINT, sighandler);

    while (1) {
        printf("tiny_shell > "); 
        fflush(stdout);
        
        char* line = get_a_line(); 

        // Read until we exit the program using an enter or exit
        if (length(line) > 1 && strcmp(line, "exit") != 0) {
    
            #if defined(TIMING_ON) 
            for(int i = 0; i < get_it(); i++) {
            #endif
                            
                /* Check if a multi command line was given using ; */
                /* Get the first command */
                char *command = strtok(line, ";");
                while( command != NULL ) {
                    // Initialize pipe arguments 
                    char *pipe = NULL;
                    int io = -1;

                    #if defined(TIMING_ON) 
                    start_timing();            
                    #endif
                    
                    #if defined(FORK) || defined(VFORK) || defined(CLONE)
                        my_system(command, pipe, io);
                    #elif defined(PIPE)
                        // If we have a pipe
                        // Get the arguments values
                        if (argc == 3) {
                            pipe = argv[1];
                            io = atoi(argv[2]);
                        }
                        my_system(command, pipe, io);
                    #else 
                        system(command);
                    #endif
       
                    /* Get the next command */
                    command = strtok(NULL, ";");  
                }
            
                #if defined(TIMING_ON) 
                stop_timing();
                get_timing(i);
                #endif

            #if defined(TIMING_ON) 
            }            
            #endif
            
            #if defined(TIMING_ON) 
            print_timing();
            #endif

        } else {
            return EXIT_SUCCESS;
        }
    }
}