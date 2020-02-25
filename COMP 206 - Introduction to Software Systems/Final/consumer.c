#include <stdio.h>
#include <stdlib.h>

char removeCharacter(int pos){
  char c;
  FILE *p;
  int x = 0;

  while((p = fopen("shared.txt", "rt")) == NULL);
  c = fgetc(p);
  while(!feof(p) && x < pos){
    x++;
    c = fgetc(p);
  }
  fclose(p);
  return c;
}

int main(){
  char c;
  int pos = 0;

  do{
    c = removeCharacter(pos);
    printf("%c", c);
    pos++;
  } while(c != 'x');

  return 0;
}
