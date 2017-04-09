#*************************************************************#	
#	   MASTERMIND: DIEGO ENRIQUE FONTAN LORENZO	      #
#*************************************************************#

CC	= gcc -Wall

all:mastermind

thread.o: thread.c thread.h
	$(CC) -c thread.c

assistant.o: assistant.c assistant.h
	$(CC) -c assistant.c

mastermind : mastermind.c thread.o assistant.o
	$(CC) thread.o assistant.o mastermind.c -o mastermind
	
clean:
	rm -f partidas.txt mastermind *.bak *.o *~

developerClean:
	rm -f *.c *.h *.o partidas.txt mastermind
