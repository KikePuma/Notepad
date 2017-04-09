#include <stdio.h>
#include "Libraries/pantalla.h"

void hola();

int main() {
	
	int opc = 0;
	while(opc != 1) {
		asteriscos(3,100,'*');
		hola();
		asteriscos(3,50,'*');
		opc = confirmar();
	}
	return 0;
}

void hola() {
	int i;
	for(i = 0; i < 18; i++) printf("*");
	printf(" MASTER MIND ");
	for(i = 0; i < 19; i++) printf("*");
	printf("\n");
	return;
}

