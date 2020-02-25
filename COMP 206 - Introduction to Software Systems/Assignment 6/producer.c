#include <stdio.h>
#include <stdlib.h>

void producer(FILE *mydata){
  FILE *DATA;

  // Writing 1 character from mydata.txt to DATA.txt
  DATA = fopen("DATA.txt", "wt");
  if(DATA == NULL) exit(1);
  fprintf(DATA, "%c", getc(mydata));
  fclose(DATA);

  // Writing '1' into TURN.txt for consumer, or 'x' if done reading mydata.txt
  FILE *TURN = fopen("TURN.txt", "wt");
  if(TURN == NULL) exit(1);
  if(!feof(mydata))
    putc('1', TURN);
  else
    putc('x', TURN);
  fclose(TURN);
}
