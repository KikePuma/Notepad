#include <stdio.h>

struct animales {
	int patas;
	char comida[20];
};

int main(int argc, char *argv[]) {
		
		struct animales gato; //Creamos la instancia gato para la estructura animales
		
		typedef struct animales animal; //Como poner struct nombre es muy largo le ponemos un alias con typedef
		
		animal perro = {4}; //rellena la primera variable de la estructura con un 4 (patas)
		
		gato.patas = 4;
		strcpy(gato.comida,"pescado");
		strcpy(perro.comida,"carne");
	
		printf("El gato tiene %i patas y come %s. El perro tiene %i patas y come %s",gato.patas,gato.comida,perro.patas,perro.comida);
		
	return 0;
}






















