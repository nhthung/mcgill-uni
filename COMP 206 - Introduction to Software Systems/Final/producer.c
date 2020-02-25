#include <stdio.h>
#include <stdlib.h>

int main(){
  char c = ' ';
  FILE *p;

  while(c != 'x'){
    c = getchar();
    while((p = fopen("shared.txt", "at")) == NULL);
    fprintf(p, "%c\n", c);
    fclose(p);
  }

  return 0;
}
