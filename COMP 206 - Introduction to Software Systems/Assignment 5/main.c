#include <stdio.h>

int main(){
  newList();

  int value;
  printf("Hi.\nThis program will help you store strictly positive integers (> 0) in a list.\n\nTo stop listing, simply enter a negative number (<= 0) :)\n\nYou can start entering your first number: ");
  scanf(" %d", &value);

  while(value > 0){
    addNode(value);
    printf("\nNext number: ");
    scanf(" %d", &value);
  }
  printf("\nYou entered a negative number (or it was too big, the list doesn't have much space).\n\n");

  prettyPrint();

  printf("Thanks for using the program :)\n");
  
  return 0;
}
