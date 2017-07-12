#include <stdio.h>
#include <stdlib.h>

void espacio(int t),Humo(int humo), Rail();

int main() {
	int time = 72, humo = 1;
	char trenA[40] = {"   _||__|  |  ______   ______   \n"};
	char trenM[42] = {"  (        | |      | |      | |      |\n"};
	char trenB[42] = {"  /-()---() ~ ()--() ~ ()--() ~ ()--()\n"};
	
	system("MODE CON COLS=120 LINES=23");
	
	while (time > 0) {
		printf ("\n\n\n\n\n\n\n                                                  El trenecito Chu-Chu\n\n\n\n\n\n\n");
		if (time < 70) {
			espacio(time+6);
			Humo(humo);
			humo++;
		}
		printf("\n");
		espacio(time);
		printf("%s",trenA);
		espacio(time);
		printf("%s",trenM);
		espacio(time);
		printf("%s",trenB);
		Rail();
		time--;
		Sleep(100);
		system("cls");
	}
	return 0;
}

void espacio(int t) {
	int i;
	for (i = 0; i < t; i++) {
		printf(" ");
	}
}

void Humo(int humo) {
	int i;
	for (i = 0; i < humo; i++) {
		if (i%3==0){
			printf("o");
		} else {
			printf("O");			
		}
	}
}

void Rail() {
	int rail;
	for (rail = 0; rail < 120; rail++){
		printf("-");
	}
}
