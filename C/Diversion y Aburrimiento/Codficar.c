#include <stdio.h>

void codifica();

int main() {
	
	char nombre[80];
	int i;
	
	printf ("Escribe tu nombre: ");
	scanf("%s",&nombre);
	for (i=0;i<80;i++) {
		if (nombre[i] == 0) { break; }
		codifica(nombre,i);
		Sleep(1000);
		printf("#");
	}
	system("cls");
	printf("\nBienvenido %s.\n\n",nombre);
	
	system("pause");
	
	return 0;
}

void codifica(char *nombre,int i) {
	int dif = 'a' - 'A';
	if (*(nombre+i) >= 'A' && +*(nombre+i) <= 'Z') {
		*(nombre+i) += dif;
	}
	switch (*(nombre+i)) {
		case 'a': *(nombre+i) = '@'; break;
		case 'b': *(nombre+i) = '3'; break;
		case 'c': *(nombre+i) = 's'; break;
		case 'd': *(nombre+i) = 'd'; break;
		case 'e': *(nombre+i) = '€'; break;
		case 'f': *(nombre+i) = '='; break;
		case 'g': *(nombre+i) = '&'; break;
		case 'h': *(nombre+i) = ' '; break;
		case 'i': *(nombre+i) = '1'; break;
		case 'j': *(nombre+i) = 'j'; break;
		case 'k': *(nombre+i) = 'k'; break;
		case 'l': *(nombre+i) = '|'; break;
		case 'm': *(nombre+i) = 'm'; break;
		case 'n': *(nombre+i) = 'n'; break;
		case 'o': *(nombre+i) = '0'; break;
		case 'p': *(nombre+i) = 'p'; break;
		case 'q': *(nombre+i) = 'q'; break;
		case 'r': *(nombre+i) = 'r'; break;
		case 's': *(nombre+i) = '$'; break;
		case 't': *(nombre+i) = 't'; break;
		case 'u': *(nombre+i) = '4'; break;
		case 'v': *(nombre+i) = 'v'; break;
		case 'w': *(nombre+i) = 'w'; break;
		case 'x': *(nombre+i) = 'x'; break;
		case 'y': *(nombre+i) = 'y'; break;
		case 'z': *(nombre+i) = '2'; break;
		default: break;
	}
}
