#include <stdio.h>
#include <time.h>
#define MIN_TIRADAS 1000



int main() {
	
	int dado[6] = {0}, i, tiradas = 10000;
	for(i=0;i<6;i++) {
		dado[i] = lanzar(dado,tiradas);
		printf("%i%% ",dado[i]);
	}
	return 0;
}

void lanzar(int *dado,int tiradas) {
	srand(time(NULL));
	int result,i;
	
	tiradas=(tiradas<MIN_TIRADAS)?MIN_TIRADAS:tiradas;
	
	for (i=0;i<tiradas;i++) {
		result = rand()%6;
		*(dado+result) += 1;
	}
	for (i=0;i<6;i++) {
		*(dado+i) = *(dado+i) * 100 / tiradas;
		return *(dado+i);
	}
}
