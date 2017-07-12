#include <stdio.h>
#include <string.h>

int main() {
	//DECLARACION DE VARIABLES
	char cad[40],cpy[40],*pnt;
	//LECTURA DE CADENAS
	printf("Cadena: "); fflush(stdin); scanf("%[^\n]s",cad);
	printf("Copy: "); fflush(stdin); gets(cpy);
	//CALCULO DE LONGITUDES
	printf("Cadena.Len = %i :: Copy.Len = %i\n",strlen(cad),strlen(cpy));
	//COMPARACION DE CADENAS
	printf("%i\n",strcmp(cad,cpy));
	//CONCATENADO DE CADENAS
	puts(strcat(cad,cpy));
	//COPIA DE CADENAS
	puts(strcpy(cpy,cad));
	//CAMBIO DE MAYUS A MINUS	
	puts(strupr(cad));
	puts(strlwr(cpy));
			
	return 0;
}
