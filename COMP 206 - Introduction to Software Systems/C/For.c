#include <stdio.h>
#include <string.h>

int main(){
	int i;
	for(i = 0; i < 3; i++){
		printf("%d\n", i);
	}
	
	printf("%d\n", i);
	/*outputs 3, meaning i stays*/
	
	return 0
}