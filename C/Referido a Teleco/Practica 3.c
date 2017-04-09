#include <stdio.h>
#include <time.h>

int main() {
	
	int opc = 0;
	
	do {
		system("cls");
		printf("\n -------------------- PRACTICA 3 -------------------- \n Que ejercicio quieres realizar? (Escribe 0 para salir):");
		fscanf(stdin," %i",&opc);
		switch(opc) {
			case 0: printf("Has seleccionado salir.\n"); break;
			case 1: case 2: P1(); break;
			case 3:	case 4:	case 5: P5(); break;
			default: printf("Seleccione una opcion correcta"); break;
		}
		printf("\n");
		system("pause");
		
	} while(opc!=0);	
	
	return 0;
}


void P1() {
	int num = 0, i,k;
	
	printf(" Cuantas lineas de asteriscos quieres?: ");
	scanf(" %i",&num);
	system("cls");
	for(i = 0; i < num; i++) {
		for(k=0;k<80;k++) {
			printf("*");
		}
		printf("\n");
	}
}

void P5() {
	char opc;
	
	printf(" Aleatorio o manual? (a/m): ");
	scanf(" %c",&opc);
	if (opc == 'a' || opc == 'A') {
		Aleatorio();
	} else {
		Manual();		
	}
}

void Aleatorio() {
	double nums[5];
	int i,random;
	srand(time(NULL));
	for(i = 0; i<5; i++) {
		random = rand()%20000 -10000;
		nums[i] = random * 0.001;
		printf("%.3lf ",nums[i]);
	}
}

void Manual() {
	double nums[5] = { 0 };
	int i;
	srand(time(NULL));
	while (i < 5) {
		scanf (" %lf",&nums[i]);
		if(!(nums[i] >= -10 && nums[i] <= 10)) {
			continue;
		}
		i++;
	}
	for(i=0;i<5;i++) printf("%.3lf ",nums[i]);
}
