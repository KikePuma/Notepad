	ORG    0
	LD     .1,#H'1A1A   ;NUMERO A COMPARAR (¨1B=1A?)
	CALL   /BYTESIGU
	HALT

	ORG    100
BYTESIGU   ST     .1,DATO
	LD     .0,#DATO
	LD.B   .12,[.0++]
	CMP    .12,/0[.0]
	BZ     IGUALES
	LD     .2,#0
	RET
IGUALES    LD     .2,#1
	RET
DATO       RES    1
