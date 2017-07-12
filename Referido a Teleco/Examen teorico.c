#include <stdio.h>


int main() {
	
	int spc,tot = 0; char letra; float pc;
	
	printf("\nEscribe menos de 30 caracteres.\n");
	while((letra=getchar()) != '\n') {
		if(tot < 30) {
			if(letra == ' ') { spc++; }
			tot++;
		} else { break; }
	}
	
	pc= spc * 100. / tot;
	printf("Hay un %.2f%% de espacios",pc);
	
	return 0;
}
