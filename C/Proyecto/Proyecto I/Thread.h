#include <stdio.h>
#include <time.h>
#include <string.h>

void Game(char mode);

struct thread_struct {
		char *codes;
}thread;

void Game(char mode) {
	
	srand(time(NULL));
	thread.codes = malloc(5*sizeof(char));
	
	if(mode == 'p') thread.codes = "1234";		
	else {
		int i = 0, j = 0, flag = 0;
		char numRandom = 'x';
		
		free(thread.codes);
		
		for(i = 0; i < 4; i++) {
			do {
				numRandom = rand()%9 + '0';
				for(j = 0; (j < 4 && flag == 0) ; j++) {
					if(numRandom == thread.codes[j]) flag = 1;
				}
			} while(flag);
			thread.codes[i] = numRandom;
		}
		
	}
	puts(thread.codes);
	getch();
	return;
}
