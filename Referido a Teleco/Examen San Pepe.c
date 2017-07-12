#include <stdio.h>
#include <string.h>

int main() {	
	while(1) {
		
		//DECLARACION DE VARIABLES
		char txt[6] = "\0";
		int i = 0, j = 0;
		//SOLICITUD DANDO INSTRUCCIONES AL USUARIO
		printf("Introduce una cadena de caracteres formada por 5 mayusculas diferentes: ");
		//LECTURA DE LA CADENA INTRODUCIDA
		fflush(stdin);
		scanf("%[^\n]s",&txt);
		//COMPROBACION DE LONGITUD
		if(strlen(txt) != 5) { printf("La longitud de la cadena es incorrecta.\n\n"); continue; }
		//COMPROBACION DE CONTENIDO: MAYUSCULAS
		for(j=0;j<5;j++) {	if(txt[j] < 'A' || txt[j] > 'Z') {
				printf("El caracter en la posicion %i no es una letra mayuscula.\n\n",j+1);	break;
		}	}
		if(j != 5) continue;
		//COMPROBACION DE CONTENIDO: REPETICIONES
		for(j=0;j<5;j++) {	for(i=j+1;i<5;i++){
				if(txt[j] == txt[i]) { printf("Las letras en las posiciones %i y %i son iguales.\n\n",j+1,i+1);  j = 10; break; } } }
		if(j !=5 || i != 5) continue;
		//TRANSFORMACION
		printf("La cadena transformada es: %s",strlwr(strrev(txt)));
				
		break; }
	return 0;
}
