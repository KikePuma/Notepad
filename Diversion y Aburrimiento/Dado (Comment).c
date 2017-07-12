#include <stdio.h>
#include <time.h>

void tirada(float *dado);

int main() {
	srand(time(NULL)); //Semilla
	
	int RATIO = 10000000;
	float dado[6] = {0}; //Recoge las tiradas del dado
	int i; //Contador
	
	for (i=0;i<RATIO;i++) {
		tirada(dado); //Lanza un dado
	}
	for (i=0;i<6;i++) {
		dado[i] = dado[i] * 100 / RATIO; //Hace una regla de 3 para la proporcion
		printf("%0.2f%% ",dado[i]);
	}
	return 0;
}

void tirada (float *dado) {
	int i = rand()%6; //Numero aleatorio del 0 al 5 (Que es lo mismo que del 1 al 6)
	*(dado+i) += 1.; //Suma 1 al numero que haya salido
}
