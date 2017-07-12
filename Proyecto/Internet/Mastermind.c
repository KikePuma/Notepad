/*
	********  Mastermind en C  *********
	Autor   : Andrés Oller Serrano
	Twitter : aos_26
	Web     : andresoller.es
	licencia: Cualquier uso sin plagio.
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <time.h>
#define N 4 //Numero de valores que tendrá el numero

//CABECERAS de funciones
void llenarVectorNumAleatorios(int adivinar[],int n);
void impresionPantalla(int opcion);
int comprobarDigitosNum(int numero, int n);
void comprobarNumeroMastermind(int adivinar[], char resultadoComprobacion[], int numero, int n);
int todosIguales(int adivinar[], char resultadoComprobacion[], int n);
void imprimirCaracteres(char resultadoComprobacion[], int n);

int main(){

	int adivinar[N], numero, salida=0, digitos;
	char resultadoComprobacion[N];
	
	llenarVectorNumAleatorios(adivinar, N);
	impresionPantalla(1);
	getchar();
	do{
		impresionPantalla(2);
		scanf("%d", &numero);
		digitos = comprobarDigitosNum(numero, N);
		if(digitos!=0) impresionPantalla(3);
		else {
			comprobarNumeroMastermind(adivinar, resultadoComprobacion, numero, N);
			salida = todosIguales(adivinar, resultadoComprobacion, N);
			impresionPantalla(4);
			imprimirCaracteres(resultadoComprobacion, N);
		}
	}while(salida==0);
	
	
	return 0;
}

void llenarVectorNumAleatorios(int adivinar[],int n){

	int cont;
	srand (time(NULL));
	for(cont=0; cont<n; cont++) adivinar[cont]=(rand() %9)+1; //del 1 al 9
	
}

void impresionPantalla(int opcion){

	switch (opcion){
		case 1: printf("\t\t\t MASTERMIND");
				printf("\n\t\t\t------------\n\n\tINSTRUCCIONES:");
				printf("\n\t\tSe genererará un numero de 4 digitos y se tendrá que adivinar.");
				printf("\n\t\t*=Adivinado en el sitio correcto");
				printf("\n\t\t?=Existe el numero pero no esta en la posición");
				printf("\n\t\t0=No esta");
				printf("\n\n\tPRESIONA [ENTER] PARA CONTINUAR CON EL JUEGO");
			break;
		case 2: printf("\n\n\t -Inserta numero: ");
			break;
		case 3: printf("\n\t Error al introducir el numero");
			break;
		case 4: printf("\n\t ");
			break;
	}
	
}

int comprobarDigitosNum(int numero, int n){
	
	if (numero/(int)pow(10, n)!=0) return 1;
	else if (numero/(int)pow(10, n-1)==0) return 1;
	else{
		return 0;
	}

}

//Mira si coincide y pone los simbolos correspondientes al vector 
void comprobarNumeroMastermind(int adivinar[], char resultadoComprobacion[], int numero, int n){

	int cont, cont2, esta;
	for (cont=0; cont<n; cont++){
		if ((int)(numero/pow(10,n-1-cont))%10== adivinar[cont]) resultadoComprobacion[cont] = '*';
		else{
			esta = 0;
			for (cont2=0; cont2<n; cont2++){
				if ((int)(numero/pow(10,n-1-cont))%10== adivinar[cont2])
				{
					resultadoComprobacion[cont] = '?';
					cont2 = n;
					esta = 1;
				}
				if (cont2==n-1 && esta==0){
					resultadoComprobacion[cont] = 'O';
				}
			}
		}	
	}
}

//Retorna 1 si todos són iguales, y 0 si hay alguno que no lo és.
int todosIguales(int adivinar[], char resultadoComprobacion[], int n){

	int x;
	
	for(x=0; x<n; x++){
		if(resultadoComprobacion[x]!='*') return 0;
	}
	return 1;
}

void imprimirCaracteres(char resultadoComprobacion[], int n){

	int x;
	for(x=0; x<n; x++) printf("%c", resultadoComprobacion[x]);
	
}










