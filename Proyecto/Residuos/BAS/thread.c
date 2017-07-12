#include <stdio.h>
#include <string.h>
#include <time.h>

struct thread_struct {
	char code[5], inserted[5], demo[5];
	int level;
}thread;

void generator(int mode) {
	if(mode) {
		char numAleatorio[5] = "xxxx";
		int i = 0, j=0, num = 0;
		srand(time(NULL));
		
		for(i=0;i<4;i++) {
			num = (rand()%10);
			for(j=0;j<i;j++) if((num + '0') == numAleatorio[j]) { i--; break; }
			if(j == i) numAleatorio[i] = num + '0';
		}
		
		strcpy(thread.code,numAleatorio);
	} else {
		strcpy(thread.code,thread.demo);
	}
	TXT(0,""); TXT(1,thread.code);
}

void insert() {
	char trying[5] = "\0";
	int i,j;
	while(1) {
		printf("\n\nEscriba un n\243mero de cuatro d\241gitos + ENTER: ");
		fflush(stdin);
		scanf(" %s",&trying);
		
		if(strlen(trying) != 4) { printf("\nLa longitud de la apuesta es incorrecta.\n"); continue; }
		
		for(i=0;i<4;i++) { if(trying[i] < '0' || trying[i] > '9') {	printf("\nLa apuesta s\242lo debe contener d\241gitos.\n"); break;	} }
		if (i != 4) continue;
		for(i=0;i<4;i++) { for(j=0;j<i;j++) if(trying[i] == trying[j]) {
			printf("\nLos d\241gitos de la apuesta deben ser diferentes.\n"); i = 10; break;	} }
		if(i != 4) continue;
		break;
	}
	strcpy(thread.inserted,trying);
	TXT(2,trying);
}

void win() {
	float score  = 0;
	char temp[5];
	
	sprintf(temp, "%d", getLevel() - thread.level);
	printf("\n\nHa descubierto el c\242digo secreto (%s) en %s intentos",thread.code,temp);
	if(!strcmp(temp,"1")) printf("\b%c",'\0');
	TXT(1,temp);
	
	score = (getLevel()-thread.level <= 3)?10:((getLevel()-thread.level > 12)?0:10 - 10 * (getLevel() - thread.level - 3) / 9.);
	printf("\nHa obtenido %0.2f puntos",score);
	sprintf(temp, "%0.2f", score);
	TXT(1,temp);
}

void lose() {
	
	char temp[5];
	
	sprintf(temp, "%d", getLevel());
	printf("\n\nNO ha descubierto el c\242digo secreto (%s) tras %s intentos",thread.code,temp);
	if(!strcmp(temp,"1")) printf("\b%c",'\0');
	printf("\nHa obtenido %0.2f puntos",0.00);;
	TXT(1,temp);	TXT(1,"0.00");
}

void Game(int mode, char demo[5]) {
	/*char opc = '\0';
	if(isABuffer()) { while (1) {	
		printf("\n%s",Msg('k')); fflush(stdin); scanf(" %c",&opc);
		if(opc == 'n' || opc == 'N') { return; } else if (opc != 's' && opc != 'S') { printf("%s",Msg('e')); continue; } else { break; } } }
	freeBuffer();*/
	strcpy(thread.demo, demo);
	thread.level = getLevel();
	generator(mode);
	do { insert();
	} while(check());
	if(thread.level) win();
	else lose();
}

int check() {
	int i = 0, j = 0, cap = 4, excellent = 0, correct = 0, bad = 0;
	printf("\nSu apuesta es: %s <", thread.inserted); TXT(2," <");
	thread.level--;
	for(i = 0; i < 4; i++) {
		for(j = 0; j < 4; j++) {
			if(thread.inserted[i] == thread.code[j]) {
				if(i != j) { correct++; break;	}
				else {	excellent++; break;	}
		}	}
		if(j == 4) bad++;
	}
	
	for(i = 0; i < excellent; i++) {	printf("%c",'*'); TXT(2,"*"); cap--;	}
	for(i = 0; i < correct; i++) {	printf("%c",'|'); TXT(2,"|");	}
	for(i = 0; i < bad; i++) {	printf("%c",'_'); TXT(2,"_");	}
	
	
	printf("%s",">\n"); TXT(1,">");
	if(!cap) return 0;
	return thread.level;
}
