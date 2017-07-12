#ifndef ASSISTANT
#define ASSISTANT

#include "assistant.c"

void Interface(), SelectLang(), Language(char lang), Level(int lvl), TXT(int mode, char *text), Save(), Read(), freeBuffer();
char * Error();
int getLevel(), isABuffer(), Exit();

#endif
