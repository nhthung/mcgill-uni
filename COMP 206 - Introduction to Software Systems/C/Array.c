#include <stdio.h>

int main(){
	int arr[3][3];
	arr[0][0] = 13;
	arr[3][1] = 31;
	printf("%d, %d", arr[0][0], arr[3][1]);
	return 0;
}