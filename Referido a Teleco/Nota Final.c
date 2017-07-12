#include <stdio.h>
#include <stdlib.h>

int main()

{
//Declara variables
int k,i;
char salir;

do{
	float nota[11] = { 0 };
	system("cls"); //system("clear");
	printf("*PROGRAMACION*\n");
	for (k=1;k<=3;k++) {
		printf("\nTeoria %i: ",k);
		scanf("%f",&nota[2*k-2]);
		printf("Practica %i: ",k);
		scanf("%f",&nota[2*k-1]);
	}
	printf("\nTeoria final: ");
	scanf("%f",&nota[6]);
	printf("Practica final: ");
	scanf("%f",&nota[7]);
	
	for(i=1;i<4;i++) {
		float per = (i == 3)?0.2:0.1;
		nota[8] += nota[2*i-2]*per;
		nota[9] += nota[2*i-1]*per;
	}
	
	nota[8] += nota[6]*0.6;
	nota[9] += nota[7]*0.6;
	
	nota[10] = 2*nota[8]*nota[9]/(nota[8]+nota[9]);
	
	printf("\n\nNOTA FINAL: %.3f ",nota[10]);
	
	//COMENTA la nota final
	if (nota[10] >= 9) { printf("Sobresaliente"); } else
	if (nota[10] >= 7) { printf("Notable"); } else
	if (nota[10] >= 6) { printf("Bien"); } else
	if (nota[10] >= 5) { printf("Aprobado"); } else {
		printf("Suspenso"); }
	
	//Salir o repetir?
	printf("\n\n\nS) Salir\nANY) Repetir\n");
	scanf(" %c",&salir);

} while(salir!='s' && salir!='S');


return 0;

}

