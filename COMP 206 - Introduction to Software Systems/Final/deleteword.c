#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
  FILE *old_file = fopen("echoes.txt", "rt");
  FILE *new_file = fopen("echoes_without_word.txt", "wt");
  if(old_file == NULL || new_file == NULL) exit(1);
  char word[100];

  printf("Word to remove? ");
  fgets(word, 99, stdin);
  word[strlen(word) - 1] = '\0';

  char c, buffer[100]; int pos = 0, i = 0;
  while(!feof(old_file)){
    /*
    char c = fgetc(old_file);
    if(c = word[0]){
      i = 0;
      for(; i < strlen(word); i++)
        buffer[i] = fgetc(old_file);
      buffer[i] = '\0';
      if(strcmp(buffer, word) == 0){
        c = fgetc(old_file);
        pos = i;
        printf("Word found\n");
      }
      //else
        //fseek(old_file, pos, SEEK_SET);
    }*/
    fputc(c, new_file);
  }
  fclose(old_file); fclose(new_file);
}
