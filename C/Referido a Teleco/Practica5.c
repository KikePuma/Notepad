#include <stdio.h>
#define car 'h'

void hola(), asteriscos(int linea,char crt);
int confirmar();

int main() {
	
	int opc = 0;
	while(opc != 1) {
		asteriscos(3,'*');
		hola();
		asteriscos(3,'*');
		opc = confirmar();
	}
	return 0;
}

int confirmar() {
	char opc;
	fprintf(stdout,"Deseas salir del programa? (s/n)\n");
	fscanf(stdin," %c", &opc);
	switch(opc) {
		case 's': case 'S': return 1; break;
		case 'n': case 'N': return 0; break;
		default: return -1; break;
	}
}

void asteriscos(int linea,char crt) {
	int i,k;
	for(k=0;k<linea;k++) {
		for(i=0;i<50;i++) printf("%c",crt);
		printf("\n");
	}
	return;
}

void hola() {
	int i;
	for(i = 0; i < 18; i++) printf("*");
	printf(" MASTER MIND ");
	for(i = 0; i < 19; i++) printf("*");
	printf("\n");
	return;
}

