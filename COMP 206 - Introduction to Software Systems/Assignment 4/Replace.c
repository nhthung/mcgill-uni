#include <stdio.h>
#include <stdlib.h>

/*FindRecord() checks each line of phonebook.csv for the name to replace
*/
void FindRecord(char *filename, char *name, char record[]){
	FILE *phoneBook = fopen(filename, "rt");
	if (phoneBook == NULL)
		exit(1);

	//while loop in each iteration copies 1 line of phone book to record[], ready to be searched for name
	int i;
	while(fgets(record, 1000, phoneBook)){
		//This following while loop scans compares each character of name[] to the name in record[]
		i = 0;
		while(name[i] == record[i] && record[i] != ','){
			i++;
		}

		/*if statement checks if record[i] is ',', signifying 2 names are identical up to the first ','
		//but also checks if name[i] is alphabetical, which would mean name[] is different than name in record[].
		//
		//This means if a name in the phone book is only 1 word (first name for example), and the inputted name is 2 words (first and last names), the 2 names would still match.
		//It's not a bug. It's a feature.
		*/
		if(record[i] == ',' && !( (name[i] >= 'a' && name[i] <= 'z') || (name[i] >= 'A' && name[i] <= 'Z') )){
			fclose(phoneBook);
			return; //If name[] is found in record[], the right line of phone book is in record[]. End method here
		}
	}

	//Out of while loop means name[] wasn't found in phone book, asssign '\0' to record[0]. This condition is checked in main()
	fclose(phoneBook);
	record[0] = '\0';
}

/*Replace() first copies newname into new array newRecord[],
//then copies content of record[] from first ',',
//and finally copies newRecord[] back into record[].
*/
void Replace(char *name, char *newname, char record[]){
	//Creating a new array newRecord for the record, with the name replaced and other information intact
	char newRecord[1000];

	//First, copying new name into newRecord
	int i = 0;
	while(*(newname + i) != '\n'){
		*(newRecord + i) = *(newname + i);
		i++;
	}

	//Finding index of first ',' in record
	int j = 0;
	while(*(record + j) != ',')
		j++;

	//Using j, copying information of array record into array newRecord starting from first ','
	while(*(record + j) != '\n'){
		*(newRecord + i) = *(record + j);
		i++; j++;
	}

	//Manually adding '\n' into newRecord
	*(newRecord + i) = '\n';
	*(newRecord + i + 1) = '\0';

	//Copying newRecord[] back into record[]
	i = 0;
  while(*(newRecord + i) != '\n'){
    *(record + i) = *(newRecord + i);
    i++;
  }

  //Manually adding '\n' and '\0' into record[] in case newRecord was shorter or longer in length
  if(i < j || i > j){
    *(record + i) = '\n';
    *(record + i + 1) = '\0';
  }
}

/*SaveRecord() writes each line of phonebook.csv, until the line's that's supposed to be replaced, into temp_phonebook.csv,
//then writes the new record,
//then the rest of phonebook.csv,
//and finally renames temp_phonebook.csv to phonebook.csv with Bash, overwriting the old phonebook.csv.
*/
void SaveRecord(char *filename, char *name, char record[]){
	FILE *phoneBook = fopen(filename, "rt");
  FILE *tempPhoneBook = fopen("temp_phonebook.csv", "wt");
	if(phoneBook == NULL || tempPhoneBook == NULL)
		exit(1);

  //Writing into temp_phonebook.csv but with the new record
  char line[1000];
  while(fgets(line, 1000, phoneBook)){
    int i = 0;
    while(name[i] == line[i] && line[i] != ','){
			i++;
		}

    //Checking if current line in phonebook.csv contains name to replace. If so, write the the new record into temp_phonebook.csv instead
    if(line[i] == ',' && !( (name[i] >= 'a' && name[i] <= 'z') || (name[i] >= 'A' && name[i] <= 'Z') ))
			fputs(record, tempPhoneBook);
		else
      fputs(line, tempPhoneBook);
  }
  fclose(tempPhoneBook); fclose(phoneBook);

  //Bash command essentially renaming temp_phonebook.csv into phonebook.csv
  system("mv -f temp_phonebook.csv phonebook.csv");
}

int main(){
	char name[30];
	char newName[30];
	char record[1000];
  int nameFound = 0;

	printf("Hi :)\nWhich name would you like to replace? (case sensitive): ");

  //If name wasn't found, asks for input again
  while(nameFound == 0){
    fgets(name, 30, stdin);
    FindRecord("phonebook.csv", name, record);

    if(record[0] == '\0'){
      char answer[3];
      printf("\nI couldn't find the name you where looking for :(\nWould you like to try again? ");
      fgets(answer, 3, stdin);

      if(answer[0] != 'y' && answer[0] != 'Y')
        nameFound = -1;
      else
        printf("\nPlease re-enter a name to replace: ");
    }
    else nameFound = 1;
  }

  if(nameFound == -1){
    printf("Okay then. Thanks for using the program :)\n\n");
		exit(2);
	}

  printf("\nGot it here: %sHow do you want to change this name?: ", record);
  fgets(newName, 30, stdin);

	Replace(name, newName, record);
  SaveRecord("phonebook.csv", name, record);

  printf("\nIt's done. Here's how it looks now: %s\nThanks for using the program :)\n\n", record);

	return 0;
}
