#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	
	double peso, altura;
	
	if(argc!=2) {
		printf("\nMALOS PARAMETROS\n");
		getch();
		exit(1);
	}

	
	printf("\n%s\n",argv[1]);
	return 0;
	
	
	
	return;
}
