/* Student: Le Nhat Hung
 * Partner: Raymond Diamonds
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(){
  char turn;
  FILE *mydata = fopen("mydata.txt", "rt");

  // Writing 0 into TURN.txt
  FILE *TURN = fopen("TURN.txt", "wt");
  if(TURN == NULL) exit(1);

  putc('0', TURN);
  fclose(TURN);

  int pid = fork();

  // Program done when turn == 'x'
  while(turn != 'x'){
    TURN = fopen("TURN.txt", "rt");
    turn = getc(TURN);
    fclose(TURN);

    // producer() uses pointer to mydata.txt, to avoid having to reopen in producer.c each time
    if(pid == 0){ producer(mydata); wait(); }
    if(pid != -1){ consumer(); wait(); }
    if(pid == -1){ exit(1); }
  }

  fclose(mydata);

  return 0;
}
