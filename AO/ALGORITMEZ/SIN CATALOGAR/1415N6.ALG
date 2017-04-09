	ORG    0
	LD     .11,#LISTA1
	LD     .12,#LISTA2
	LD     .13,#LISTA3
BUC        LD     .1,[.11++]
	BZ     FINAL
	LD     .2,[.12++]
	CALL   /OPER
	BR     BUC
FINAL      HALT

	ORG    200
OPER       CMP    .2,#0
	BN     DIV
MULT       BZ     FIN
	SHL    .1
	SUB    .2,#1
	BR     MULT
DIV        BZ     FIN
	SHR    .1
	ADD    .2,#1
	BR     DIV
FIN        ST     .1,[.13++]
	RET

	ORG    300
LISTA1     DATA   20,10,5,0

	ORG    310
LISTA2     DATA   3,0,-1

	ORG    320
LISTA3     RES    10
