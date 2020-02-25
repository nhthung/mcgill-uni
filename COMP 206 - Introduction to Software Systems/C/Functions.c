#include <stdio.h>

int square(int num){
	return num*num;
}

int poo(){
	static int poo = 0; /*static variable, or else resets*/
	poo++;
	return poo;
} /*can be declared separately*/


/*By default, functions are global in C. If we declare a function with static, the scope of that function is reduced to the file containing it.*/
static int poo2(){
		printf("I am a big and smelly poo, and I'm gonna throw my shit at you\n");
	}

int main(){
	void increment(int * i){ /*syntax for actually practical functions*/
		(*i)++;
	}
	
	printf("2 squared is %d\n", square(2));
	int i;
	for(i = 0; i < 3; i++){
		printf("%d ", poo());
	}
	/*variable names can't match function names*/
	
	poo2();
	
	increment(&i);
	printf("%d",i);
	
	return 0;
}