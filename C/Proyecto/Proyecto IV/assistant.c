#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_BUFFER_SIZE 1228

struct game_struct {
	char *title, *titlenosave, *error, *currentlevel, *chooselevel, *selectlevel, *errorlevel, *langs, *errorlangs, *pause, *discard, *exit; //Mensajes mostrados en los menus
	char *game, *glen, *gdigit, *gequal, *gbet; //Mensajes mostrados durante la partida
	char *w1, *w2, *w3, *w4, *w5, *l1, *l2, *l3; //Mensajes mostrados al ganar o al perder la partida
	int level;
	FILE * file;
	char buffer[MAX_BUFFER_SIZE], *readerror, *writerror;
}game;

void freeBuffer() { strcpy(game.buffer,"\0"); return; }

void Interface() {
	int asterisk = 0;
	//CARATULA DEL JUEGO
	system("cls"); //system("clear");
	for(asterisk = 0; asterisk < 545; asterisk++) {
		if(asterisk == 0); // evita un salto de linea inicial porque esta linea no hace nada
		else if(asterisk == 271) printf("  MASTER MIND  "); // else if significa sino, si no se cumple la primera pasa a la segunda y si no la tercera 
		else if((asterisk % 80 == 0 && asterisk < 271) || asterisk == 305 || ((asterisk-305) %80 == 0 && asterisk > 271)) printf("\n");
		//siempre se imprime un asterisco
		printf("*");
	}
	//game PRINCIPAL
	if(strlen(game.buffer) == 0) printf("%s",game.titlenosave);
	else printf("%s ",game.title);
	
	return;
}

void Level(int lvl) {
	//SELECCION DE NIVEL DE DIFICULTAD
	if(lvl == 0) {
		printf("\n\n%s%d\n",game.currentlevel,game.level);
		while(1) {
			printf("%s[1..100]: ",game.chooselevel);
			game.level = -1;
			fflush(stdin); scanf(" %i",&game.level);
			if(game.level < 1 || game.level > 100) { puts(game.errorlevel); continue; }
			break;
		}
		printf("\n%s%i",game.selectlevel,game.level); fflush(stdin); getchar();
	} else {	game.level = lvl;	}
}

void Language(char lang) {
	switch(lang) {
		case '0':
			game.title = "\n\n1) Jugar partida\n2) Jugar partida de prueba\n3) Establecer nivel de dificultad\n4) Listar historial de partidas\n5) Guardar partida jugada\n6) Establecer idioma\n0) Salir\nX?Siguiente operaciXOn?" ;
			game.titlenosave = "\n\n1) Jugar partida\n2) Jugar partida de prueba\n3) Establecer nivel de dificultad\n4) Listar historial de partidas\n6) Establecer idioma\n0) Salir\nX?Siguiente operaciXOn? ";
			game.error = "Has realizado una selecciXOn no vXAlida.";
			game.pause = "Presione ENTER para continuar... ";
			game.currentlevel = "Nivel actual: ";
			game.chooselevel = "Elige nuevo nivel ";
			game.selectlevel = "Nivel seleccionado: ";
			game.errorlevel = "Nivel seleccionado no vXAlido.";
			game.langs = "0) EspaXNol\n1) Gallego\n2) InglXEs\n\nElige idioma: ";
			game.errorlangs = "Idioma seleccionado no vXAlido.";
			game.game = "Escriba un nXUmero de cuatro dXIgitos + ENTER: ";
			game.glen = "La longitud de la apuesta es incorrecta.";
			game.gdigit = "La apuesta sXOlo debe contener dXIgitos.";
			game.gequal = "Los dXIgitos de la apuesta deben ser diferentes.";
			game.gbet = "Su apuesta es: ";
			game.w1 = "Ha descubierto el cXOdigo secreto ("; game.w2 = ") en "; game.w3 = " intentos";
			game.w4 = "Ha obtenido "; game.w5 = " puntos";
			game.l1 = "NO ha descubierto el cXOdigo secreto ("; game.l2 = ") tras "; game.l3 = " intentos";
			
			game.discard = "Hay una partida sin guardar, X?descartarla? (s/n) ";
			game.exit = "Hay una partida sin guardar, X?estXA seguro de que desea salir? (s/n) ";
			game.readerror = "No puedo leer el fichero de historial";
			game.writerror = "No puedo escribir en el fichero de historial";
			
			break;
		case '1':
			game.title = "\n\n1) Xogar partida\n2) Xogar partida de proba\n3) Establecer nivel de dificultade\n4) Listar historial de partidas\n5) Gardar partida xogada\n6) Establecer idioma\n0) SaXIr\nSeguinte operaciXOn? ";
			game.titlenosave = "\n\n1) Xogar partida\n2) Xogar partida de proba\n3) Establecer nivel de dificultade\n4) Listar historial de partidas\n6) Establecer idioma\n0) SaXIr\nSeguinte operaciXOn? ";
			game.error = "Realizaches unha selecciXOn non vXAlida.";
			game.pause = "Presione ENTER para continuar... ";
			game.currentlevel = "Nivel actual: ";
			game.chooselevel = "Elixe novo nivel ";
			game.selectlevel = "Nivel seleccionado: ";
			game.errorlevel = "Nivel seleccionado non vXAlido.";
			game.langs = "0) EspaXNol\n1) Galego\n2) InglXEs\n\nElixe idioma: ";
			game.errorlangs = "Idioma seleccionado non vXAlido.";
			game.game = "Escriba un nXUmero de 4 dXIxitos diferentes + ENTER: ";
			game.glen = "A lonxitude da aposta non XE correcta.";
			game.gdigit = "A aposta sXO debe conter dXIxitos.";
			game.gequal = "Os dXIxitos da apuesta deben ser diferentes.";
			game.gbet = "A sXUa aposta XE: ";
			game.w1 = "DescubrXIu o cXOdigo secreto ("; game.w2 = ") en "; game.w3 = " intentos";
			game.w4 = "Obtivo "; game.w5 = " puntos";
			game.l1 = "NON descubrXIu o cXOdigo secreto ("; game.l2 = ") tras "; game.l3 = " intentos";
			
			game.discard = "Hai unha partida sen gardar, descartala? (s/n) ";
			game.exit = "Hai unha partida sen gardar, estXA seguro de que desexa saír? (s/n) ";
			game.readerror = "Non podo ler o ficheiro de historial";
			game.writerror = "Non podo escribir no ficheiro de historial";
			
			break;
		case '2':
			game.title = "\n\n1) Play game\n2) Play test game\n3) Set skill level\n4) List game history\n5) Save played game\n6) Set language\n0) Quit\nNext command? ";
			game.titlenosave = "\n\n1) Play game\n2) Play test game\n3) Set skill level\n4) List game history\n6) Set language\n0) Quit\nNext command? ";
			game.error = "You have made an invalid selection.";
			game.pause = "Press ENTER to continue... ";
			game.currentlevel = "Current level: ";
			game.chooselevel = "Select new level ";
			game.selectlevel = "Selected level: ";
			game.errorlevel = "You have selected an invalid level.";
			game.langs = "0) Spanish\n1) Galician\n2) English\n\nSelect language: ";
			game.errorlangs = "Selected language is not allowed.";
			game.game = "Type a number with 4 different digits + ENTER: ";
			game.glen = "You have not typed 4 digits.";
			game.gdigit = "The guess can only contain digits.";
			game.gequal = "The guess digits must be different.";
			game.gbet = "Your guess: ";
			game.w1 = "You have discovered the secret code ("; game.w2 = ") in "; game.w3 = " attempts";
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
	char opc = NULL;
	while(1) {
		printf("\n\n%s",game.langs);
		fflush(stdin);
		opc=getchar();
		if(opc == '0' || opc == '1' || opc == '2') {  Language(opc);
		} else { printf("\n%s",game.errorlangs); continue; }
		break;
	}
}

void TXT(int mode, char *text) {
	if(mode == -1) {
		game.file = fopen("partidas.txt","a");
		fprintf(game.file,"%s",game.buffer);
		freeBuffer();
		fclose(game.file);
	} else {
		setbuf(game.file,game.buffer);
		if(!mode) for(mode = 0; mode <12; mode++) strcat(game.buffer,"-");
		else strcat(game.buffer,text);
		if(mode != 2) strcat(game.buffer,"\n");
	}
	
}

void Save() {
	if(!strlen(game.buffer)) { printf("%s",game.error); return;
	}
	TXT(-1,"");
}

void Read() {
	char c = '\0'; printf("\n");
	game.file = fopen("partidas.txt","r");
	while(!feof(game.file)) {
		c = fgetc(game.file);
		printf("%c",c);
	}
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
		case 'p': return game.pause; break;
		case '1': return game.w1; break;
		case '2': return game.w2; break;
		case '3': return game.w3; break;
		case '4': return game.w4; break;
		case '5': return game.w5; break;
		case '6': return game.l1; break;
		case '7': return game.l2; break;
		case '8': return game.l3; break;
		default: break;
	}
}

int getLevel() {   return game.level;	}
int isABuffer() { return strlen(game.buffer); }

int Exit() {
	while(1) {
		char c;
		printf("\n%s",game.exit); fflush(stdin); scanf(" %c",&c);
		if(c == 'N' || c == 'n') return 0;
		else if (c == 'S' || c == 's') return 1;
		else { printf("%s",game.error); continue;} } 
}
