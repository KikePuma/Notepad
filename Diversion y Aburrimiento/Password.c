#include <conio.h>   
#include <stdio.h>

int main() {
    char c,password[4] = {0};
    int i = 0;
    fprintf(stdout,"\n Escribe un pin de 4 digitos: ");
    while( (c=getch())!= '\n' && i<4){
        password[i] = c;
        printf("*");
        i++;
    }
    printf("\n El pin introducido es: ");
    for(i=0;i<4;i++) printf("%c",password[i]);
    return 0;
}
