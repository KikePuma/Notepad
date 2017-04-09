#include <stdio.h>
#include "Proyecto.h"
#include "Thread.h"

int main(int argc, char * argv[]) {
	char option;
	Level(100);
	Language('0');
	while(1) {
		Interface();
		//SELECCION DE MODO
		fflush(stdin);
		switch(option = getchar()) {
			case '0': TXT(0," "); TXT(1,"Mi mama en bisicleta"); return 1; break;
			case '1': Game('a'); break;
			case '2': Game('p'); break;
			case '3': Level(0); break;
			case '6': SelectLang(); break;
			default: printf("\n%s",Msg('e')); fflush(stdin); getch(); break;
		}
	}
}
