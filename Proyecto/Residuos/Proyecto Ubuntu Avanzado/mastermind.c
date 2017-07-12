#include <stdio.h>
#include <string.h>
#include "assistant.h"
#include "thread.h"

struct main_struct {
	char demo[5];
}Main;

void args(int argc, char argv[7], char name[100]);

int main(int argc, char * argv[]) {
	char option[2];
	Language('0');
	Level(100);
	strcpy(Main.demo,"1234");
	
	if(argc != 1) args(argc, argv[1], argv[0]);
	system("clear"); //system("cls");
	
	while(1) {
		Interface();
		//SELECCION DE MODO
		fflush(stdin);
		scanf("%[^\n]s",&option);
		if(strlen(option)!=1) option[0] = 'x';
		switch(option[0]) {
			case '0': if(isABuffer()) { if(Exit()) return 0;} else { return 1; } break;
			case '1': Game(1,"\0"); break;
			case '2': Game(0,Main.demo); break;
			case '3': Level(0); break;
			case '4': Read(); break;
			case '5': Save(); break;
			case '6': SelectLang(); break;
			default: printf("\n%s",Msg('e')); break;
		}
		printf("\n");
		//printf("\n%s",Msg('p')); fflush(stdin); getchar();
	}
}

void args(int argc, char argv[7], char name[100]) {
	int i = 0, j = 0;
	
	if(argc > 2) { printf("Uso: ./%s [ --help | c\242digo_prueba ]", name); exit(0xDEAD); }
	if(!strcmp(argv,"--help")) { printf("Uso: ./%s [ --help | c\242digo_prueba ]\n\n--help: este mensaje de ayuda\nc\242digo_prueba: un c\242digo de 4 d\241gitos decimales diferentes para pruebas\nEl programa MASTER MIND permite las siguientes funcionalidades:\n\n1) Jugar partida: descubrir un c\242digo aleatorio de 4 d\241gitos decimales diferentes\n2) Jugar partida de prueba: \"descubrir\" el c\242digo pasado para pruebas\n3) Establecer nivel de dificultad: elegir el m\240ximo n\243mero de intentos admitidos\n4) Listar historial de partidas: mostrar las jugadas anteriormente realizadas\n5) Guardar partida jugada: guardar en un fichero de historial la \243ltima partida\n6) Establecer idioma: elegir en qu\202 idioma se quiere recibir la informaci\242n",name); exit(0xDEAD); }
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
