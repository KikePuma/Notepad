#include <stdio.h>
#include <ctype.h>
#include <conio.h>
#include <string.h>

int main () {
	
	int len,i,j;
	char digitos[6];
	
	while(1) { //Ponemos aqui un While que se repite siempre, porque queremos que se repita hasta acabar el programa correctamente
	
//    do{   	Con el while de arriba nos podemos ahorrar esto, si no, nos dará problemas dado que len no se resetea en ningun momento en tu codigo.
	printf ("Escribe 5 d\xA1gitos: "); //Ahora esta bien escrito, con tilde en la i. (A1 es el ASCII Hexadecimal de í)
	scanf (" %s",digitos);

	len=strlen(digitos);
	if (len!=5) { printf ("La longitud de la cadena es incorrecta.\n"); continue; }
	
//	}while(len!=5); 	Esta comprobacion ya la haces con el if de arriba.
	
		for(i=0;i<5;i++){
		if(digitos[i]<'A'||digitos[i]>'Z'){ //Es mejor un if que un While de una sola vuelta,
		printf("Hay un error en la posicion %i\n",i+1); // ya que el break te sale del While y no del For como tu quieres, por eso no te encuentra el error.
		break; }}

	if(i != 5) continue; //Si el bucle anterior encontró un error, i no va a valer 5. Empezamos el programa de nuevo.

	for(i=0;i<5;i++){  for(j=1+i;j<5;j++){
		if(digitos[i]==digitos[j]){printf("Las posiciones %i y %i \n",i+1,j+1);
	i = 10; break;	}}} //Aqui, a parte del Break ponemos tambien i = 10 para cortar el bucle al que está anidado. Asi cortamos el FOR del i y el FOR del j
	
	if(i != 5 || j != 5) continue; //Esto es lo mismo de arriba. Sabemos que si todo es correcto, tanto i como j deberian ser 5. Si no lo son, empezamos de nuevo.
		
		for(i=0;i<5;i++){
	digitos[i]=tolower(digitos[i]); //Esta forma está muy bien, pero es mucho más rapido poner el comando strlwr(digitos) para ahorrarte el bucle y hacerlo todo de una vez.
	}

	printf ("%s\n",digitos);
   while(i>=0)
	{
		printf("%c", digitos[i-1]);  //Lo mismo que arriba. En string.h hay un comando llamado strrev(digitos) que da la vuelta a la cadena, pero está genial de esta forma.
		i--;
	}
			
				



	printf ("La cadena original es: %s\n",digitos);
	
	break; //ESTE BREAK ES IMPORTANTE. Significa que todo ha ido bien y que ya podemos acabar el programa
	
}

	
return 0;}
