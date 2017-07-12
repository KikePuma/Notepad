#include <stdio.h>
 
void funcion1(int x[])
{
    printf("Como parametro de funcion1: %d bytes.\n", sizeof(x));
}
 
void funcion2(int *x[])
{
    printf("Como parametro de funcion2: %d bytes.\n", sizeof(*x));
}
 
int main(int argc, char *argv[])
{
    int x[1];
 
 
    printf("Dentro del ambito en el que se ha declarado: %d bytes.\n", sizeof(x));
 
    funcion1(x);
    funcion2(&x);
 
    return 0;
}
