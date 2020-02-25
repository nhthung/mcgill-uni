#include <stdio.h>
#include <string.h>

int main(){
  int x = 5, y;
  char string[100];
  void *p;
  p = &x;
  //y = *p;
  y = *((int *)p);
  printf("%d\n", y);
  strcpy(string, "fuck");
  printf("%s\n", string);

  return 0;
}
