//Practica 1 : Anexo II

#include <stdio.h>
#include <math.h>

int main () {
	
	void marmo(),trirect(),suma(),multxsum(),divxrst(),pares();
	int prog;
	fprintf(stdout, "Introduzca que programa desea abrir: ");
	scanf("%i",&prog);
	system("cls");
	switch (prog) {
		case 1: marmo(); break;
		case 2: trirect(); break;
		case 3: suma(); break;
		case 4: multxsum(); break;
		case 5: divxrst(); break;
		case 6: pares(); break;
	//	case 7: exp();
		default: fprintf(stdout,"Introduzca un valor correcto.\n"); break;
	}
	
	system("pause");
	return 0;
}

void marmo() {
	float nota[2];
	float media;
	printf("Introduzca la primera nota: ");
	scanf("%f",&nota[0]);
	printf("Introduzca la segunda nota: ");
	scanf("%f",&nota[1]);
	media = 2 * nota[0] * nota[1] / (nota[0]+nota[1]);
	printf ("Tu nota es: %0.2f\n",media);
}
void trirect() {
	float h,c1,c2,area,perimetro;
	printf("Introduce el valor del cateto: ");
	scanf("%f",&c1);
	printf("Introduce el valor de la hipotenusa: ");
	scanf("%f",&h);
	c2 = sqrt((h*h)-(c1*c1));
	perimetro = h + c1 + c2;
	area = c1 * c2;
	printf("El perimetro del triangulo es de %0.2f y su area es de %0.2f.\n",perimetro,area);
}
void suma() {
	int num,resultado,i;
	for(i=1;i<11;i++) {
		printf("Introduzca el numero deseado: ");
		scanf("%i",&num);
		resultado += num;
	}
	printf("La suma de los 10 numeros introducidos es: %i.\n",resultado);
}
void multxsum() {
	int a,b,i,result;
	result = 0;
	printf("Introduce el primer numero: ");
	scanf("%i",&a);
	printf("Introduce el segundo numero: ");
	scanf("%i",&b);
	for (i=0;i<b;i++) {
		result += a;
	}
	printf("El resultado es: %i.\n",result);
}
void divxrst() {
	int a,b,i,result;
	result = 0;
	printf("Introduce el primer numero: ");
	scanf("%i",&a);
	printf("Introduce el segundo numero: ");
	scanf("%i",&b);
	for (a=a;a>=b;a-=b) {
		result ++;
	}
	printf("El resultado es %i y el resto es %i.\n",result,a);
}
void pares() {
	int i,max;
	printf("Introduzca el numero de valores pares que desee: ");
	scanf("%i",&max);
	max *= 2;
	printf("Los numeros pares son: ");
	for (i=0;i<max;i += 2) {
		printf("%i ",i);
	}
}





