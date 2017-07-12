#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void Interface(), SelectLang(), Language(char lang), Level(int lvl), TXT(int mode, char *text);
char * Error();

struct game_struct {
	char *title, *error, *currentlevel, *chooselevel, *selectlevel, *errorlevel, *langs, *errorlangs;
	int level;
	FILE * file;
}game;


void Interface() {
	int asterisk = 0;
	//CARATULA DEL JUEGO
	system("cls"); //system("clear");
	for(asterisk = 0; asterisk < 545; asterisk++) {
	//	if(asterisk == 0);
		 if(asterisk == 271) printf("  MASTER MIND  ");
		else if((asterisk % 80 == 0 && asterisk < 271) || asterisk == 305 || ((asterisk-305) %80 == 0 && asterisk > 271)) printf("\n");
		printf("*");
	}
	//game PRINCIPAL
	printf("%s ",game.title);
	
	return;
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

void Language(char lang) {
	switch(lang) {
		case '0':
			game.title = "\n\n1) Jugar partida\n2) Jugar partida de prueba\n3) Establecer nivel de dificultad\n4) Listar historial de partidas\n5) Guardar partida jugada\n6) Establecer idioma\n0) Salir\n\250Siguiente operaci\242n?";
			game.error = "Has realizado una selecci\242n no v\240lida.";
			game.currentlevel = "Nivel actual: ";
			game.chooselevel = "Elige nuevo nivel ";
			game.selectlevel = "Nivel seleccionado: ";
			game.errorlevel = "Nivel seleccionado no v\240lido.";
			game.langs = "0) Espa\244ol\n1) Gallego\n2) Ingl\202s\n\nElige idioma: ";
			game.errorlangs = "Idioma seleccionado no v\240lido.";
			break;
		case '1':
			game.title = "\n\n1) Xogar partida\n2) Xogar partida de proba\n3) Establecer nivel de dificultade\n4) Listar historial de partidas\n5) Gardar partida xogada\n6) Establecer idioma\n0) Sair\n\250Seguinte operaci\242n?";
			game.error = "Realizaches unha selecci\242n no v\240lida.";
			game.currentlevel = "Nivel actual: ";
			game.chooselevel = "Elige nuevo nivel ";
			game.selectlevel = "Nivel seleccionado: ";
			game.errorlevel = "Nivel seleccionado no v\240lido.";
			game.langs = "0) Español\n1) Gallego\n2) Ingl\202s\n\nElige idioma: ";
			game.errorlangs = "Idioma seleccionado no v\240lido.";
			break;
		case '2':
			game.title = "\n\n1) Play game\n2) Play test game\n3) Set skill level\n4) List game history\n5) Save game\n6) Set language\n0) Quit\nNext command?";
			game.error = "You have made an invalid selection.";
			game.currentlevel = "Current level: ";
			game.chooselevel = "Choose new level ";
			game.selectlevel = "Selected level: ";
			game.errorlevel = "Invalid selected level.";
			game.langs = "0) Spanish\n1) Galician\n2) English\n\nChoose language: ";
			game.errorlangs = "Invalid selected language.";
			break;
		default: printf("\n%s",game.error); fflush(stdin); getch(); break;
	}
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
		printf("\n%s%i",game.selectlevel,game.level); fflush(stdin); getch();
	} else {	game.level = lvl;	}
}

void TXT(int mode,char *text) {
	game.file = fopen("partidas.txt","a");
	if(game.file == NULL) {	printf("\nERROR -1.\n"); return; }
	if(mode == 0) for(mode = 0; mode <12; mode++) fprintf(game.file,"-");
	else fprintf(game.file,text);
	fprintf(game.file,"\n");
	fclose(game.file);
}

char * Msg(char i) {
	switch(i) {
		case 'e': return game.error; break;
		default: break;
	}
}
