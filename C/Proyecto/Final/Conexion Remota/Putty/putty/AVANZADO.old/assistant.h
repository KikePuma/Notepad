#ifndef ASSISTANT
#define ASSISTANT

void Interface(), SelectLang(), Language(char lang), Level(int lvl), TXT(int mode, char *text), Save(), Read(), freeBuffer();
char * Msg(char i);
int getLevel(), isABuffer(), Exit(), isEnglish();

#endif
