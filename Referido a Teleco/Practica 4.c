#include <stdio.h>

int main() {
	
	int cant = 0, i = 0,opc = 0;
	double *nums;
	
	while(1) {
		do {
			printf("Escoge el n\243mero de elementos que deseas introducir: ");
			fflush(stdin);
			scanf(" %i",&cant);
		} while(cant < 1 || cant > 10);
		
		nums = (int *) malloc (cant*sizeof(int));
		i = 0;
		
		while (i<cant) {
			scanf(" %d",&nums[i]);
			if(nums[i] < -10 || nums[i] > 10) {
				continue;
			}
			i++;
		}
	
		for(i=0;i<cant;i++) printf("%d ",nums[i]);
		//free(nums); //DA ERROR
		printf("\n\nDesea salir?: ");
		fflush(stdin);
		if(((opc = getchar()) == 's') || ((opc = getchar()) == 'S')) exit(1);
	}
	
	
	return 0;
}
