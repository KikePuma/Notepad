#include<stdio.h>
#include<time.h>

void crea_mapa(char c[10][10], char d[10][10]);
void bucle(char c[10][10], char d[10][10]);
void tablero(char c[10][10], char d[10][10]); 
int introduce(char c[10][10], char d[10][10]);
char cuentaminas(char c[10][10], char d[10][10], int x, int y);

int main(){
char c[10][10]; //va a ser el tablero visible
char d[10][10]; //tablero INvisible que contiene donde estan las minas

bucle(c, d);
return 0;
}

void bucle(char c[10][10], char d[10][10]){ //va a ajecutar todo el proceso del programa
int fin = 1, puestos = 0;
crea_mapa(c, d);
tablero(c, d);

do{

fin = introduce(c, d);
if(fin == 0) {
printf("\n\tALLAHU AKBAR! Habia una mina...\n"); return; }
tablero(c, d);
puestos ++;
if(puestos == 89) { printf("HAS GANADO!!!"); return; }
}while(fin == 1 && puestos < 90);
}

 

void crea_mapa(char c[10][10], char d[10][10]){ 
int numero, i, j, k, m;

numero = 0;

for(i=0; i<10; i++){
for(j=0; j<10; j++){
d[i][j] = '0'; 
} } 

srand(time(NULL));
do{ //crea el tablero invisible d: Las minas son rombos ( 4 ), el resto CEROS.

k = rand() % 10;
m = rand() % 10;

for(i=0; i<10; i++){ // pone 10 minas aleatorias
for(j=0; j<10; j++){
if(i==k && j==m) { d[i][j] = 4; } //minas son rombos: caracter ASCII 4

}
}
numero++;
}while(numero<10);

// crea el tablero visible: todo espacios
for(i=0; i<10; i++){
for(j=0; j<10; j++){
c[i][j] = ' '; 
} }


}

void tablero(char c[10][10], char d[10][10]){ //muestra el tablero visible
int i, j, raya, col ;
printf("\n");
for(col=0; col <10; col++) printf(" %i ", col+1);

printf("\n\n"); 

system("COLOR E0"); // EO fondo amarillo y letra en negro
for(i=0; i<10; i++){
for(j=0; j<10; j++){

printf(" %c |", c[i][j]); 
} 
printf(" %i", i+1); printf("\n"); for(raya=0;raya<10; raya ++) printf("----"); printf("\n"); 
}

}


int introduce(char c[10][10], char d[10][10]){ // Si pierdes devuelve un 0
int x, y;
do {

printf("Pulsa en una parcela una parcela (fila, espacio, columna): \t ");
scanf(" %i", &x);
scanf(" %i", &y);
}while( x < 1 || x > 10 || y > 10 || y < 1 );


x--; y--;

if(d[x][y] == 4) { 

return 0; }
else {
c[x][y] = cuentaminas(c, d, x, y); return 1; }
}

char cuentaminas(char c[10][10], char d[10][10], int x, int y){
char cuenta = '0';

if(d[x-1][y-1] == 4 && y>0 && x>0) {cuenta ++;}
if(d[x+1][y+1] == 4 && y<9 && x<9) {cuenta ++;}
if(d[x-1][y] == 4 && x>0) {cuenta ++;}
if(d[x+1][y] == 4 && x<9) {cuenta ++;}
if(d[x][y-1] == 4 && y>0) {cuenta ++;}
if(d[x][y+1] == 4 && y<9) {cuenta ++;}
if(d[x-1][y+1] == 4 && y<9 && x>0) {cuenta ++;}
if(d[x+1][y-1] == 4 && y>0 && x<9) {cuenta ++;}

return cuenta;
}
