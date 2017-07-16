	ORG    0
	LD     .1,#17
	LD     .2,#-2
	CALL   /OPER
	HALT

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
FIN        ST     .1,DATO
	LD     .3,DATO
	RET
DATO       RES    1

