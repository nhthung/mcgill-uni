#include <stdio.h>

int main(){
	int n = 0;
	while(n < 10){
		n++;
	}
	
	/*While loops can also execute infinitely if a condition is given which always evaluates as true (non-zero):*/
	while (1) {
		printf("yea ");
	}
	
	/*the continue directive causes the printf command to be skipped, so that only even numbers are printed out:*/
	n = 0;
	while (n < 10) {
		n++;
		/* check that n is odd */
		if (n % 2 == 1) {
			/* go back to the start of the while block */
			continue;
		}
		/* we reach this code only if n is even */
		printf("The number %d is even.\n", n);
	}
	
	/*there's break too*/
	
	return 0;
}