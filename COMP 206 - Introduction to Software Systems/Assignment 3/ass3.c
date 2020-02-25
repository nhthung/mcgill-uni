#include <stdio.h>
#include <ctype.h> //for the isdigit function

//global cipher array
char cipher[101];

//Function prints cipher
void caesar(char message[], int key){
	int i = 0;
	while(message[i] != '\0'){
		if(message[i] >= 'A' && message[i] <= 'Z')
			cipher[i] = (message[i] - 'A' + (26 - key))%26 + 'A';
		else if(message[i] >= 'a' && message[i] <= 'z')
			cipher[i] = (message[i] - 'a' + (26 - key))%26 + 'a';
		else
			cipher[i] = message[i]; //copying spaces, punctuation, special characters like ' or "
		i++;
	}
	cipher[i - 1] = '\0';
	
	printf("Encrypted message: %s\n", cipher);
}

//Function prints deciphered message
void decipher(int key){
	char deciphered_message[101];
	int i = 0;
	while(cipher[i] != '\0'){
		if(cipher[i] >= 'A' && cipher[i] <= 'Z')
			deciphered_message[i] = (cipher[i] - 'A' + key)%26 + 'A';
		else if(cipher[i] >= 'a' && cipher[i] <= 'z')
			deciphered_message[i] = (cipher[i] - 'a' + key)%26 + 'a';
		else
			deciphered_message[i] = cipher[i];
		i++;
	}
	deciphered_message[i] = '\0';
	
	printf("Decrypted message: %s\n", deciphered_message);
}

int main(){
	char message[101];
	int key;
	
	printf("Sentence: ");
	fgets(message, 100, stdin);
	printf("\nKey: ");
	scanf("%d", &key);
	
	
	//Test if key is valid
	if(isdigit(key) || (key < 1 || key > 25))
		printf("The key should be a number between 1 and 25. Try again: ");
	
	//Reprompt for key input
	while(isdigit(key) || (key < 1 || key > 25)){
		fflush(stdin);
		scanf("%d", &key);
		if(isdigit(key) || (key < 1 || key > 25))
			printf("Again: ");
	}
	
	printf("\nOriginal message: %s\n", message);
	
	//prints cipher
	caesar(message, key);
	
	printf("\n");
	
	//prints deciphered message
	decipher(key);
	
	return 0;
}