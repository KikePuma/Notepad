#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "thread.h"
#include "assistant.h"

#define MAX_BUFFER_SIZE 1228

struct game_struct {
	char *title, *titlenosave, *error, *currentlevel, *chooselevel, *selectlevel, *errorlevel, *langs, *errorlangs, *pause, *discard, *exit; //char *pause; //Mensajes mostrados en los menus
	char *game, *glen, *gdigit, *gequal, *gbet; //Mensajes mostrados durante la partida
	char *w1, *w2, *w3, *w4, *w5, *w3_, *l1, *l2, *l3; //Mensajes mostrados al ganar o al perder la partida
	int level, english;
	FILE * file;
	char buffer[MAX_BUFFER_SIZE], *readerror, *writerror;
}game;

void freeBuffer() { strcpy(game.buffer,"\0"); return; }

void Interface() {
	int asterisk = 0;
	//CARATULA DEL JUEGO
	for(asterisk = 0; asterisk < 543; asterisk++) {
		if(asterisk == 0); // evita un salto de linea inicial porque esta linea no hace nada
		/*else*/ if(asterisk == 271) printf("*  MASTER MIND  *"); // else if significa sino, si no se cumple la primera pasa a la segunda y si no la tercera
		else if((asterisk % 80 == 0 && asterisk < 271) || asterisk == 303 || ((asterisk-303) %80 == 0 && asterisk > 271)) printf("\n"); //((asterisk-304) %80 == 0 && asterisk > 271)) 
		//siempre se imprime un asterisco
		printf("*");
	}
	
	/*if(strlen(game.buffer) == 0) printf("%s",game.titlenosave);
	else printf("\n\n1) Jugar partida\n2) Jugar partida de prueba\n3) Establecer nivel de dificultad\n4) Listar historial de partidas\n0) Salir\n¿Siguiente operación? "); */
	printf("\n");
	return;
}

void Level(int lvl) {
	//SELECCION DE NIVEL DE DIFICULTAD
	if(lvl == 0) {
		printf("\n\n%s%d\n",game.currentlevel,game.level);
		while(1) {
			printf("%s[1..100]: ",game.chooselevel);
			game.level = -1;
			fflush(stdin); scanf(" %i",&game.level); getchar();
			if(game.level < 1 || game.level > 100) { printf("%s\n",game.errorlevel); continue; }
			break;
		}
		printf("\n%s%i\n",game.selectlevel,game.level);
	} else {	game.level = lvl;	}
}

void Language(char lang) {
	if(lang == 'S') {
		game.title = (char *) malloc(200 * sizeof(char)) ;
		game.titlenosave = (char *) malloc(175 * sizeof(char));
		game.error = (char *) malloc(50 * sizeof(char));
	//	game.pause = (char *) malloc(35 * sizeof(char));
		game.currentlevel = (char *) malloc(15 * sizeof(char));
		game.chooselevel = (char *) malloc(25 * sizeof(char));
		game.selectlevel = (char *) malloc(25 * sizeof(char));
		game.errorlevel = (char *) malloc(35 * sizeof(char));
		game.langs = (char *) malloc(60 * sizeof(char));
		game.errorlangs = (char *) malloc(35 * sizeof(char));
		game.game = (char *) malloc(50 * sizeof(char));
		game.glen = (char *) malloc(45 * sizeof(char));
		game.gdigit = (char *) malloc(45 * sizeof(char));
		game.gequal = (char *) malloc(50 * sizeof(char));
		game.gbet = (char *) malloc(20 * sizeof(char));
		game.w1 = (char *) malloc(40 * sizeof(char)); game.w2 = (char *) malloc(10 * sizeof(char)); game.w3 = (char *) malloc(15 * sizeof(char));
		game.w4 = (char *) malloc(20 * sizeof(char)); game.w5 = (char *) malloc(15 * sizeof(char)); game.w3_ = (char*) malloc(15 * sizeof(char));
		game.l1 = (char *) malloc(45 * sizeof(char)); game.l2 = (char *) malloc(15 * sizeof(char)); game.l3 = (char *) malloc(15 * sizeof(char));
		game.discard =  (char *) malloc(55 * sizeof(char));
		game.exit =  (char *) malloc(75 * sizeof(char));
		game.readerror =  (char *) malloc(40 * sizeof(char));
		game.writerror =  (char *) malloc(47 * sizeof(char));
		return;
	}
	
	switch(lang) {
		case '0':
			game.english = 0;
			game.title = "\n1) Jugar partida\n2) Jugar partida de prueba\n3) Establecer nivel de dificultad\n4) Listar historial de partidas\n5) Guardar partida jugada\n6) Establecer idioma\n0) Salir\n\n¿Siguiente operación?" ;
			game.titlenosave = "\n1) Jugar partida\n2) Jugar partida de prueba\n3) Establecer nivel de dificultad\n4) Listar historial de partidas\n6) Establecer idioma\n0) Salir\n\n¿Siguiente operación? ";
			game.error = "Has realizado una selección no válida.";
//			game.pause = "Presione ENTER para continuar... ";
			game.currentlevel = "Nivel actual: ";
			game.chooselevel = "Elige nuevo nivel ";
			game.selectlevel = "Nivel seleccionado: ";
			game.errorlevel = "Nivel seleccionado no válido.";
			game.langs = "0) Español\n1) Gallego\n2) Inglés\n\nElige idioma: ";
			game.errorlangs = "Idioma seleccionado no válido.";
			game.game = "Escriba un número de 4 dígitos diferentes + ENTER: ";
			game.glen = "La longitud de la apuesta es incorrecta";
			game.gdigit = "La apuesta sólo debe contener dígitos";
			game.gequal = "Los dígitos de la apuesta deben ser diferentes";
			game.gbet = "Su apuesta es: ";
			game.w1 = "Ha descubierto el código secreto ("; game.w2 = ") en "; game.w3 = " intentos"; game.w3_ = " intento";
			game.w4 = "Ha obtenido "; game.w5 = " puntos";
			game.l1 = "NO ha descubierto el código secreto ("; game.l2 = ") tras "; game.l3 = " intentos";
			game.discard = "Hay una partida sin guardar, ¿descartarla? (s/n) ";
			game.exit = "Hay una partida sin guardar, ¿está seguro de que desea salir? (s/n) ";
			game.readerror = "No puedo leer el fichero de historial";
			game.writerror = "No puedo escribir en el fichero de historial";
			
			break;

		case '1':
			game.english = 0;
			game.title = "\n1) Xogar partida\n2) Xogar partida de proba\n3) Establecer nivel de dificultade\n4) Listar historial de partidas\n5) Gardar partida xogada\n6) Establecer idioma\n0) Saír\n\nSeguinte operación? ";
			game.titlenosave = "\n1) Xogar partida\n2) Xogar partida de proba\n3) Establecer nivel de dificultade\n4) Listar historial de partidas\n6) Establecer idioma\n0) Saír\n\nSeguinte operación? ";
			game.error = "Realizaches unha selección non válida.";
//			game.pause = "Presione ENTER para continuar... ";
			game.currentlevel = "Nivel actual: ";
			game.chooselevel = "Elixe novo nivel ";
			game.selectlevel = "Nivel seleccionado: ";
			game.errorlevel = "Nivel seleccionado non válido.";
			game.langs = "0) Español\n1) Galego\n2) Inglés\n\nElixe idioma: ";
			game.errorlangs = "Idioma seleccionado non válido.";
			game.game = "Escriba un número de 4 díxitos diferentes + ENTER: ";
			game.glen = "A lonxitude da aposta non é correcta";
			game.gdigit = "A aposta só debe conter díxitos";
			game.gequal = "Os díxitos da aposta deben ser diferentes";
			game.gbet = "A súa aposta é: ";
			game.w1 = "Descubriu o código secreto ("; game.w2 = ") en "; game.w3 = " intentos"; game.w3_ = " intento";
			game.w4 = "Obtivo "; game.w5 = " puntos";
			game.l1 = "NON descubríu o código secreto ("; game.l2 = ") tras "; game.l3 = " intentos";
			game.discard = "Hai unha partida sen gardar, descartala? (s/n) ";
			game.exit = "Hai unha partida sen gardar, está seguro de que desexa saír? (s/n) ";
			game.readerror = "Non podo ler o ficheiro de historial";
			game.writerror = "Non podo escribir no ficheiro de historial";
			
			break;
			
		case '2':
			game.english = 1;
			game.title = "\n1) Play game\n2) Play test game\n3) Set skill level\n4) List game history\n5) Save played game\n6) Set language\n0) Quit\n\nNext command? ";
			game.titlenosave = "\n1) Play game\n2) Play test game\n3) Set skill level\n4) List game history\n6) Set language\n0) Quit\n\nNext command? ";
			game.error = "You have made an invalid selection.";
//			game.pause = "Press ENTER to continue... ";
			game.currentlevel = "Current level: ";
			game.chooselevel = "Select new level ";
			game.selectlevel = "Selected level: ";
			game.errorlevel = "You have selected an invalid level.";
			game.langs = "0) Spanish\n1) Galician\n2) English\n\nSelect language: ";
			game.errorlangs = "Selected language is not allowed.";
			game.game = "Type a number with 4 different digits + ENTER: ";
			game.glen = "You have not typed 4 digits";
			game.gdigit = "The guess can only contain digits";
			game.gequal = "The guess digits must be different";
			game.gbet = "Your guess: ";
			game.w1 = "You have discovered the secret code ("; game.w2 = ") in "; game.w3 = " attempts"; game.w3_ = " attempt";
			game.w4 = "You have got "; game.w5 = " points";
			game.l1 = "You have FAILED to discover the secret code ("; game.l2 = ") after "; game.l3 = " attempts";
			game.discard = "There is an unsaved game, discard it? (y/n) ";
			game.exit = "There is an unsaved game, are you sure you want to quit? (y/n) ";
			game.writerror = "History file can't be written";
			game.readerror = "History file can't be read";
			
			break;

		default: printf("\n%s",game.error); fflush(stdin); break;
	}
}

void SelectLang() {
	//SELECCION DE LENGUAJE
	char opc[2];
	while(1) {
		printf("\n\n%s",game.langs);
		fflush(stdin);
		scanf(" %s",opc);
		
		if(!(opc[0] == '0' || opc[0] == '1' || opc[0] == '2') || strlen(opc) != 1) { printf("\n%s",game.errorlangs); continue;
		} else {  Language(opc[0]); }
		break;
	}
}

/* AVANZADO */
void TXT(int mode, char *text) {
	if(mode == -1) {
		game.file = fopen("partidas.txt","a");
		if(game.file == NULL) { printf("\n%s",game.writerror); return; }
		fprintf(game.file,"%s",game.buffer);
		freeBuffer();
		fclose(game.file);
	} else {
		//setbuf(game.file,game.buffer);
		if(!mode) for(mode = 0; mode <12; mode++) strcat(game.buffer,"-");
		else strcat(game.buffer,text);
		if(mode != 2) strcat(game.buffer,"\n");
	}
	
}

/* BASICO
void TXT(int mode, char *text) {
	game.file = fopen("partidas.txt","a");
	if(game.file == NULL) { printf("\n%s (partidas.txt)",game.writerror); return; }
	if(!mode) for(mode = 0; mode <12; mode++) fprintf(game.file,"-");
	else fprintf(game.file,"%s",text);
	if(mode != 2) fprintf(game.file,"\n");
	fclose(game.file);
}*/

void Save() {
	if(!strlen(game.buffer)) { printf("%s",game.error); return;
	}
	TXT(-1,"");
}

void Read() {
	char c = '\0';printf("\n");
	game.file = fopen("partidas.txt","r");
	if(game.file == NULL) { printf("%s (partidas.txt)\n",game.readerror); return; }
	/*while(!feof(game.file)) {
		c = fgetc(game.file);
		printf("%c",c);
	}*/
	while((c=fgetc(game.file)) != EOF) putchar(c);
//	printf("\b\b\b\b\b\b\b\b\b%c\n",'\0');
	fclose(game.file);
}

char * Msg(char i) {
	switch(i) {
		case 'a': return game.gbet; break;
		case 'd': return game.gdigit; break;
		case 'e': return game.error; break;
		case 'g': return game.game;  break;
		case 'i': return game.gequal; break;
		case 'k': return game.discard; break;
		case 'l': return game.glen; break;
	//	case 'p': return game.pause; break;
		case 'T': return game.title; break;
		case 't': return game.titlenosave; break;
		case '1': return game.w1; break;
		case '2': return game.w2; break;
		case '3': return game.w3; break;
		case '4': return game.w4; break;
		case '5': return game.w5; break;
		case '6': return game.l1; break;
		case '7': return game.l2; break;
		case '8': return game.l3; break;
		case '9': return game.w3_; break;
		default: return 0; break;
	}
}

int getLevel() {   return game.level;	}
int isABuffer() { return strlen(game.buffer); }
int isEnglish() { return game.english; }

int Exit() {
	while(1) {
		char c;
		printf("\n%s",game.exit); fflush(stdin); scanf(" %c",&c);
		if(isEnglish()) {
		if(c == 'N' || c == 'n') return 0;
		else if (c == 'Y' || c == 'y') return 1;
		else { printf("%s",game.error); continue;	}		
		} else {
		if(c == 'N' || c == 'n') return 0;
		else if (c == 'S' || c == 's') return 1;
		else { printf("%s",game.error); continue;} } }
}
