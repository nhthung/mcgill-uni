#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(){
	typedef struct{
		char * bassist;
		char * guitarist;
	} band;
	
	band pfloyd;
	pfloyd.bassist = "Waters";
	pfloyd.guitarist = "Gilmour";
	
	printf("%s\n", pfloyd.bassist);
	
	char firstname[] = "Roger ";
	
	void add_first_name(band * b){ /*how to function with structures*/
		(*b).bassist = strncat(firstname, (*b).bassist, 20);
		(*b).guitarist = "poo 2";
	}

	add_first_name(&pfloyd);
	
	printf("%s\n", pfloyd.bassist);
	printf("%s\n", pfloyd.guitarist);
	
	/*Dynamic allocation*/
	band *gnroses = malloc(sizeof(band));
	gnroses->bassist = "Duff McKagan"; /*shitty syntax*/	
	gnroses->guitarist = "Slash";
	printf("Guns' bassist is %s and guitarist is %s", gnroses->bassist, gnroses->guitarist);
	
	return 0;
}

