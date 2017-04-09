#include <stdio.h>

void cronometro();

int main() {
	
	cronometro();

	return 0;
}

void cronometro() {
	int h,min,seg;
	int deelay = 1000;

	for (h=0;h<24;h++) {
		for (min=0;min<59;min++) {
			for (seg=0;seg<59;seg++) {
				printf("%02i:%02i:%02i\r",h,min,seg); //Atentos al /r "Salto de carro"
				Sleep(deelay);
			}
		}
	}
	return;
}

