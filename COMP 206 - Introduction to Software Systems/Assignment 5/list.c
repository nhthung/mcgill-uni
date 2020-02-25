#include <stdio.h>
#include <stdlib.h>
#include "list.h"

struct NODE *head;

void newList(){
  head = NULL;
}

int addNode(int value){
  if(value <= 0)
    return -1;

  struct NODE *newNode = (struct NODE *) malloc(sizeof(struct NODE));
  if(newNode == NULL)
    exit(1);

  newNode->data = value;
  newNode->next = head;

  head = newNode;

  return 0;
}

void prettyPrint(){
  printf("Your list in reversed order is: ");
  while(head != NULL){
    printf("%d", head->data);

    if(head->next != NULL)
      printf(", ");

    head = head->next;
  }
  printf(".\n");
  
}
