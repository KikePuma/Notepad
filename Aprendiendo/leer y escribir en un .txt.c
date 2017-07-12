#include <stdio.h>


struct game_str {
	char code[5];
	char attempts[5];
}game;

void write() {
	printf("Escribe el c\242digo: ");
	scanf("%[^\n]s",&game.code);
	puts("Escribe el n\243mero de intentos realizados: ");
	fflush(stdin);
	gets(game.attempts);
	
	FILE * txt;
	txt = fopen("leer y escribir en un .txt.txt","w");
	if (txt == NULL) return;
	fwrite(&game,sizeof(game),1,txt);
	fclose(txt);
}

void read() {
	FILE * txt;
	txt = fopen("leer y escribir en un .txt.txt","r");
	if (txt == NULL) return;
	while(!feof(txt)) {
		fread(&game,sizeof(game),1,txt);
		printf("El c\242digo es: %s y el n\243mero de intentos fueron %s",game.code,game.attempts);
		break; //CHUZAS
	}
}

int main(void) {
	write();
	read();
	return 0;	
}
