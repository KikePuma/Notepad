#include <stdio.h>


struct game_data {
		char code[5], attempts[5];
	}game;

int main(void) {
	
	puts("Code: "); //printf con salto de linea
	fflush(stdin);
	gets(game.code); //scanf hasta salto de linea

	printf("Attempts: ");
	fflush(stdin);
	scanf("%[^\n]s",&game.attempts);

	FILE * ptr_txt;
	ptr_txt = fopen("escribir en .txt.txt","a");
	if(ptr_txt == NULL) {
		puts("ERROR");
		return -1;
	}
	
	fwrite(&game,sizeof(game),1,ptr_txt); //fwrite(&struct,sizeof(struct),1,file); ESCRIBE EN HEXADECIMAL
	fprintf(ptr_txt,"\n--------------------------\n%s\n%s",game.code,game.attempts); //ESCRIBE EN ASCII
	fclose(ptr_txt);

	return 0;
}
