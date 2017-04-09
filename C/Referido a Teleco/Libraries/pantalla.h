#include <stdio.h>
#define MAX_CHAR 50

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

void asteriscos(int linea, int col, char crt) {
	int i,k;
	col = (col<MAX_CHAR)?col:MAX_CHAR;
	for(k=0;k<linea;k++) {
		for(i=0;i<col;i++) printf("%c",crt);
		printf("\n");
	}
	return;
}
