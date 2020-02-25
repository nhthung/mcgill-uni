#include <stdio.h>
#include <string.h>

int main(){
	/*Defining immutable strings*/
	char * name1 = "die";
	
	/*Defining mutable strings*/
	char name2[] = "undie";
	/*same as*/
	char name3[7] = "undies"; /*length +1, cause need to mark end of string*/
	
	printf("Names are %s, %s, %s\n", name1, name2, name3);
	
	printf("Length of name1 is %d\n", strlen(name1));
	
	/*compare strings. 2 strings, and max comparison langth. Returns 0 if equal*/
	if (strncmp(name1, "die", 3) == 0){
		printf("die then\n");
	} else{
		printf("live\n");
	}
	
	/*concatenation: destination string, source string, max nb of characters to be appended*/
	char dest[20]="Hello";
    char src[20]="World";
    strncat(dest,src,3);
    printf("%s\n",dest); /*outputs HelloWor*/
    strncat(dest,src,20);
    printf("%s\n",dest); /*outputs HelloWorWorld*/
	
	return 0;
}