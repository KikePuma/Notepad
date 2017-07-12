#include <stdio.h>
#include <time.h>
#define cnt 3

void Slots(int mode), Title(), draw(char drw);
int Slot();

int main(void) {
	
	system("MODE CON COLS=25 LINES=20");
	
	Title(1);
	Slots(0);
	
	
	return 0;
}

void Title(int mode) {
	system("cls");
	printf("\n\n\n\n\n\n\n\n");
	draw(' ');	draw('=');
	draw(' ');	draw('|');	draw(' ');
	if(mode) {	printf("SLOT!"); } else {	printf("WIN!!");	}
	draw(' ');	draw('|'); printf("\n");
	draw(' ');	draw('=');
	getch();
	system("cls");
	
}

void Slots(int mode) {
	int coins = 100;
	char slot[3];
	while(1) {
		int i;
		if (!mode || coins) {
			srand(time(NULL));
			printf("\n\n");
			draw(' '); draw('='); draw(' '); draw('='); 
			draw(' '); draw('=');
			draw(' '); draw('|'); printf("   "); draw('|'); printf("   "); draw('|');  printf("   "); draw('|'); printf("\n");
			draw(' '); draw('|');		
			for(i=0; i<3;i++) { slot[i] = Slot(); }
			draw(slot[0]); draw('|'); draw(slot[1]); draw('|'); draw(slot[2]); draw('|');
			printf("\n"); draw(' '); draw('|'); printf("   "); draw('|'); printf("   "); draw('|');  printf("   "); draw('|'); printf("\n");
			draw(' '); draw('=');
			draw(' '); draw('='); draw(' '); draw('=');
			draw(' '); draw('|'); draw(' '); draw(' '); draw(' '); printf(" "); draw('|'); printf("\n");
			draw(' '); draw('|'); draw(' '); draw(' '); draw(' '); printf(" "); draw('|'); printf("\n");
			draw(' '); draw('|'); draw(' '); draw(' '); draw(' '); printf(" "); draw('|'); printf("\n");
			draw(' '); draw('|'); draw(' '); draw(' '); draw(' '); printf(" "); draw('|'); printf("\n");
			printf("\n      Insert coin!\n   (Current coins: %02i)",coins);	
		}
		Sleep(1000);
		fflush(stdin);
		getch();
		if(slot[1] == 0); else if(slot[1]==slot[2] && slot [1]==slot[0]) {	fflush(stdin);	getch();	Title(0);	coins +=20;	}
		coins--;
		system("cls");
	}
}

void draw(char drw) { 
	int i;
	switch(drw) {
		case ' ': for(i=0;i<4;i++) printf(" "); break;
		case '|': printf("||"); break;
		case '=': for(i=0; i<17; i++) printf("="); printf("\n"); break;
		default: printf(" %c ",drw); break;
		}
}

int Slot() {
	static int start = 0;
	start++;
	if(start < 4) return 0; 
	int  figuras[] = { '$','%', '&', '@', '?', '#', '+', '~'}, slct = -1;
	
	slct = rand()%3; //8
	return figuras[slct];
}
