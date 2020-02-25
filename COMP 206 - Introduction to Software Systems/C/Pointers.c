#include <stdio.h>

int main(){
	int a = 3;
	int * pointer_to_a = &a; /*is address of a*/
	
	printf("a is %d", *pointer_to_a); /*asterix at beginning gives value, not address*/
	
	return 0;
}