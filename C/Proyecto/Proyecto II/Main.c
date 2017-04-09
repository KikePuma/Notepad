#include <stdio.h>
#include "Proyecto.h"
#include "Thread.h"

struct main_struct {
	char demo[5];
}Main;

void args(int argc, char argv[7]);

int main(int argc, char * argv[]) {
	char option[2];
	Language('0');
	Level(100);
	strcpy(Main.demo,"1234");
	
	if(argc != 1) args(argc, argv[1]);
	
	while(1) {
		Interface();
		//SELECCION DE MODO
		fflush(stdin);
		scanf("%[^\n]s",&option);
		if(strlen(option)!=1) option[0] = 'x';
		switch(option[0]) {
			case '0': return 0; break;
			case '1': Game(1,"\0"); getch(); break;
			case '2': Game(0,Main.demo); getch(); break;
			case '3': Level(0); break;
			case '6': SelectLang(); break;
			default: printf("\n%s",Msg('e')); fflush(stdin); getch(); break;
		}
	}
}

void args(int argc, char argv[7]) {
	int i = 0, j = 0;
	
	if(argc > 2) { printf("Uso: ./programa [ --help | c\242digo_prueba ] "); exit(0xDEAD); }
	if(!strcmp(argv,"--help")) { printf("%s",argv); exit(0xDEAD); }
	else {
		if(strlen(argv) != 4) { printf("La longitud del c\242digo de prueba es incorrecta"); exit(0xDEAD); }
		for(i=0;i<4;i++) {
			if(argv[i] < '0' || argv[i] > '9') {	printf("El c\242digo de prueba s\242lo debe contener d\241gitos"); exit(0xDEAD);	}
			for(j=0;j<i;j++) if(argv[i] == argv[j]) {
			printf("Los d\241gitos del c\242digo de prueba deben ser diferentes"); exit(0xDEAD);	}
		}
		strcpy(Main.demo,argv);
	}
}
