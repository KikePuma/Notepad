#include <stdio.h>
#include <stdlib.h>
#include <time.h>



/*

……….(…(`…-“’´´-…´)…)……….
…………..)……–…….–….(………..
…………./……(o…_…o)….\……….
………….\………(..0..)……./……….
……….__.`.-._…’=’.._.-.´.__…….
……./…….’#.’#.,.–.,.#’.#.’….\…..
…….\__))……….’#’……… ((__/…..
__xxxxxxxxxxx______xxxxxxxxxx
_xxxxxxxxxxxxxx___xxxxxxxxxxxxx
xxxxxxxxxxxxxxxx_xxxxxxxxxxxxxx
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
_xxxxxxxxxxxxxxxxxxxxxxxxxxxxx
__xxxxxxxxxxxxxxxxxxxxxxxxxxx
____xxxxxxxxxxxxxxxxxxxxxx
_______xxxxxxxxxxxxxxxxx
________xxxxxxxxxxxx
__________xxxxxxxxx
____________xxxxx
_____________xxx
_____________xx
_____________*

Con amor, cariño y muuucha paciencia
de tu amigo y vecino, Spiderm... PUMA.

*/



int main() {

	int binario[] = {0,1};
	int i,k;
	char teddy[30][46] = { {"..........(___(`___-\"'``-___`)___)...........\n"},
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
	{"..........................x..................\n"},
	{".............................................\n"},
	{"................. TE QUIEROOO ...............\n"},
	{"............................................."}};

	srand(time(NULL));

	system("MODE CON COLS=46 LINES=31");
	system("COLOR 02");
	
	for (i=0;i < 46*30.96;i++) {
		Sleep(5);
		if(rand()%2 -1) { printf("%i",binario[0]); } else { printf("%i",binario[1]); }
    }
    
    Sleep(1500);
    system("COLOR FC");
    Sleep(750);
    system("COLOR 0F");
    Sleep(1500);

    system("cls");

	for (k=0; k < 30; k++) {
		for (i=0;i<46;i++) {
			printf("%c",teddy[k][i]);
			if (teddy[k][i] == '\n') break;
		}
		Sleep(100);
	}

	
	system("pause");
	
	
	return 0;
}

