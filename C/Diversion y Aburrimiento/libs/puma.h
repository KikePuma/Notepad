#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void matrix(int deelay) {
	srand(time(NULL));	
	int binario[] = {0,1};
	system("COLOR 02");
		
		while(1) {
		Sleep(deelay);
		if(rand()%2 -1) { printf("%i",binario[0]); } else { printf("%i",binario[1]); }
    }
}
void cronometro() {
	int h,min,seg;
	int delay = 1000;

	for (h=0;h<24;h++) {
		for (min=0;min<59;min++) {
			for (seg=0;seg<59;seg++) {
				printf("%02i:%02i:%02i\r",h,min,seg); //Atentos al /r "Salto de carro"
				Sleep(delay);
			}
		}
	}
	return;
}
void secondsToHour(int segundos) {
	int h = segundos/3600;
	int	m = (segundos-3600*h)/60;
	int	s = segundos-3600*h-60*m;
	printf("%02i:%02i:%02i",h,m,s);
	return;
}
void osito() {
	int i,k;
	char teddy[27][46] = { {"..........(___(`___-\"'``-___`)___)...........\n"},
	{"..............)_____-____-___(...............\n"},
	{"............./_____(o___.o)___\\..............\n"},
	{".............\\_____(__0__)____/..............\n"},
	{"..........___`___-______'='____-_`___........\n"},
	{"........./__'#__'#__,___-___,_#'#_'__\\.......\n"},
	{".........\\_____))_____'#'_____((_____/.......\n"},
	{".....xxxxxxxxxxxxxxx________xxxxxxxxxxxxx....\n"},
	{"....xxxxxxxxxxxxxxxxx______xxxxxxxxxxxxxxx...\n"},
	{"...xxxxxxxxxxxxxxxxxxx____xxxxxxxxxxxxxxxxx..\n"},
	{"..xxxxxxxxxxxxxxxxxxxxx__xxxxxxxxxxxxxxxxxxx.\n"},
	{".xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n"},
	{"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n"},
	{"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n"},
	{".xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n"},
	{"..xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.\n"},
	{"....xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx...\n"},
	{"......xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.....\n"},
	{".........xxxxxxxxxxxxxxxxxxxxxxxxxxxxx.......\n"},
	{"............xxxxxxxxxxxxxxxxxxxxxxxx.........\n"},
	{"...............xxxxxxxxxxxxxxxxxxxx..........\n"},
	{"..................xxxxxxxxxxxxxxx............\n"},
	{".....................xxxxxxxxxx..............\n"},
	{"........................xxxxx................\n"},
	{"..........................xx.................\n"},
	{"..........................x..................\n"},};

	system("MODE CON COLS=46 LINES=28");
    system("COLOR 0F");

	for (k=0; k < 27; k++) {
		for (i=0;i<46;i++) {
			printf("%c",teddy[k][i]);
			if (teddy[k][i] == '\n') break;
		}
		Sleep(100);
	}
	return;
}
void tren() {
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
	return;
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


