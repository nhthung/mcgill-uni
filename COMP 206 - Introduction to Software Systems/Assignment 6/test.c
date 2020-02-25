#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(){
  char c = ' ';
  FILE *p = fopen("mydata.txt", "rt");
  //while(!feof(p)){
  c = getc(p);
  printf("%c\n", c);

  fseek(p, 2, SEEK_SET);
  c = getc(p);
  printf("%c\n", c);

  //}

  fprintf(stdout, "%c\n", getc(p));
  return 0;
}
