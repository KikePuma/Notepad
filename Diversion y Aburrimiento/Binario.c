#include <stdio.h>
#include <time.h>
#include <stdlib.h>


int main() {
	srand(time(NULL));

	int binario[] = {0,1};
	
	system("MODE CON COLS=300 LINES=300");
	system("COLOR 02");
		
		while(1) {
		Sleep(0);
		if(rand()%2 -1) { printf("%i",binario[0]); } else { printf("%i",binario[1]); }
    }
    
    
    return 0;
    
}
