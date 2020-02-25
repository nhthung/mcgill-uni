#include <stdio.h>

int add(int a, int b){
  return a + b;
}

int main(){
  int (*p)(int, int) = &add;
  int sum = (*p)(2, 3);
  printf("%d", sum);

  return 0;
}
