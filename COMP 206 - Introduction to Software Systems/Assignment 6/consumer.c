#include <stdio.h>
#include <stdlib.h>

void consumer(){
  FILE *TURN = fopen("TURN.txt", "rt");
  if(TURN == NULL) exit(1);
  char turn = getc(TURN);
  fclose(TURN);

  if(turn == '1'){
    FILE *DATA = fopen("DATA.txt", "rt");
    if(DATA == NULL) exit(1);
    putchar(getc(DATA));
    fclose(DATA);

    TURN = fopen("TURN.txt", "wt");
    if(TURN == NULL) exit(1);
    putc('0', TURN);
    fclose(TURN);
  }
}
