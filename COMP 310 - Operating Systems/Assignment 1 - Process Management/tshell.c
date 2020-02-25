#define _GNU_SOURCE

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <errno.h>
#include <sched.h>

#include "profiler.c"

#define SIZE_STACK (1024 * 1024)
#define MAX_LENGTH 1024
#define CD_LENGTH 3

char* get_a_line() {

  static char line[MAX_LENGTH];
  fgets(line, MAX_LENGTH, stdin);
  return line;
}

char* get_cur_dir() {

  static char path[MAX_LENGTH];
  char *cur_dir;
  int len, slash_counter, i;

  getcwd(path, sizeof(path));
  len = strlen(path);
  slash_counter = 0;

  for (
    i = 0;
    i < len && slash_counter < 2;
    i++
  ) {
    if (path[i] == '/')
      slash_counter++;
  }

  for (i = len - 1; path[i] != '/'; i--);

  if (slash_counter == 1)
    cur_dir = &path[i];
  else if (slash_counter == 2)
    cur_dir = &path[i + 1];

  return cur_dir;
}

int is_cd(char *cmd) {
  /* Remove whitespace from beginning of cmd */
  while (*cmd == ' ') cmd = &(*(cmd + 1));

  char prog[] = {*cmd, *(cmd + 1), 0};

  if (strcmp(prog, "cd") == 0)
    return 1;

  return 0;
}

void handle_cd(char *cmd) {

  char *arg, *c;

  errno = 0;
  arg = &(*(cmd + CD_LENGTH));

  /* Remove whitespace from beginning of cd arg */
  while (*arg == ' ') arg = &(*(arg + 1));

  /* Remove \n and spaces from end of cd arg */
  c = &(*(cmd + strlen(cmd) - 1));
  for (
    int i = strlen(cmd) - 1;
    *c < 33 || *c > 126;
    i--, c = &(*(cmd + i))
  ) {
    if (*c == '\n' || *c == ' ') *c = 0;
  }

  chdir(arg);

  if (errno) perror ("Command failed");
}

int clone_function(void *arg) {
  execl("/bin/sh", "sh", "-c", (char *) arg, NULL);
  _exit(errno);
}

int my_system(char *cmd, char *fifo, int io) {
  int status;
  pid_t childPid;


  #if defined(FORK) || defined(VFORK)

  if (is_cd(cmd)) { handle_cd(cmd); }
  else {
    if (!cmd)
      return 0;

    switch ( childPid =
      #ifdef FORK
        fork()
      #elif VFORK
        vfork()
      #endif
    ) {

    case -1: /* fork() failed */
      status = -1;
      break;

    case 0: /* Child: execute cmd */
      execl("/bin/sh", "sh", "-c", cmd, (char *) NULL);
      _exit(errno); /* terminate the child if execl fails */

    default: /* Parent: wait for child to terminate */
      while(waitpid(childPid, &status, 0) == -1) {
        perror("waitpid failed.");

        childPid = -1;

        if (WIFEXITED (status))
          return WEXITSTATUS(status);

        return -1;
      }
      break;
    }
  }

  return status;


  #elif defined(CLONE)

  char *stack, *stackhead;

  stack = (char *) malloc(SIZE_STACK);

  if(!stack) {
    perror("Unable to allocate stack.");
    exit(1);
  }

  stackhead = stack + SIZE_STACK;

  childPid = clone(
    clone_function,
    stackhead,
    CLONE_VFORK|CLONE_FS|SIGCHLD,
    (void *) cmd
  );

  if (childPid == -1) {
    perror("Unable to clone.");
    free(stack);
    exit(1);
  }

  waitpid(childPid, &status, 0);

  #elif defined(FIFO)

  childPid = fork();

  switch (childPid) {
  case -1:
    perror("Unable to fork.");
    exit(EXIT_FAILURE);

  case 0:
    if (io == 0) {
      close(0);
      open(fifo, O_RDONLY);
    }
    else if (io == 1) {
      close(1);
      open(fifo, O_WRONLY);
    }

    execl("/bin/sh", "sh", "-c", cmd, NULL);
    exit(errno);

  default:
    if (waitpid(childPid, &status, 0) == -1)
      perror("waitpid failed.");

    childPid = -1;

    if (WIFEXITED (status))
      return WEXITSTATUS(status);

    return -1;
    break;
  }

  #endif

  return EXIT_SUCCESS;
}

int main(int argc, char *argv[]) {

  const char CMD_DELIMITER[2] = ";";

  char *line, *cur_dir, *cmd, *fifo;
  char temp_line[MAX_LENGTH];

  int io;

  while (1) {
    cur_dir = get_cur_dir();
    printf("[%s]$ ", cur_dir);
    line = get_a_line();

    strncpy(temp_line, line, strlen(line)+1);

    if (strlen(line) > 1) {

      if (strcmp(line, "exit\n") == 0) exit(0);
      /* FOR TIMING PURPOSES */
      //for(int i = 0; i < 100; i++) {

        fifo = NULL;
        io = -1;

        if (argc == 3) {
          fifo = argv[1];
          io = atoi(argv[2]);
        }

        /* Get 1st cmd */
        cmd = strtok(temp_line, CMD_DELIMITER);



        /* Iterate through all commands demilited by ';' */
        for (; cmd != NULL; cmd = strtok(NULL, CMD_DELIMITER)) {
          /* FOR TIMING PURPOSES */
          //start();

          #if defined(CLONE) || defined(FORK) || defined(VFORK) || defined(FIFO)
          my_system(cmd, fifo, io);

          #else
          system(cmd);

          #endif

          /* FOR TIMING PURPOSES */
          //end();
          //c_average(i);
        }

      /* FOR TIMING PURPOSES */
      //}
      //printf("Average time: %f\n", get_average());
      //fflush(stdout);
    }
    else exit(0);
  }

  return 0;
}
