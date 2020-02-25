#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <errno.h>
#include <sched.h>

#define SIZE_STACK (1024 * 1024)

int child(void *arg) {
  printf("child: %s\n", (char *) arg);

  return 0;
}

int main () {
  char str[80] = "This is;www.tutorialspoint.com;website";
  const char s[2] = ";";
  char *token;

  printf("str: %s\n", str);

  /* get the first token */
  token = strtok(str, s);

  printf("str: %s\n", str);

  /* walk through other tokens */
  while( token != NULL ) {
    printf( " %s\n", token );
    token = strtok(NULL, s);
  }

  int status;
  pid_t childPid;
  char *stack, *stackhead;
  char *str1 = "hi\n";

  if (!stack) {
    write(2, "Unable to allocate stack.\n", 26);
    exit(1);
  }

  stackhead = stack + SIZE_STACK - 1;
  printf("Parent pid: %d\n", getpid());

  childPid = clone();

  if (childPid == -1) {
    free(stack);
    exit(1);
  }

  waitpid(childPid, &status, 0);

  printf("done\n");

  return(0);
}
