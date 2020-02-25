#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(){
  char a[256];
  char address[5];

  strcpy(a, "a");
  printf("%s\n", a);

  strcpy(a, "b");
  printf("%s\n", a);

  strcat(a, "itch");
  printf("%s\n", a);

  strcpy(a, "c");
  printf("%s\n", a);

  sprintf(address, "%d", 52);
  strcat(a, address);
  printf("%s\n", a);

  return 0;
}
