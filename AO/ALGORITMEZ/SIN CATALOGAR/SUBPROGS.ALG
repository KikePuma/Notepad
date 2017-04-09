	ORG    500
LEECAR     PUSH   .0
	PUSH   .1
	LD.B   .0,#0
BUC1       IN     .1,[.0]
	AND    .1,#1
	BZ     BUC1
	LD.B   .0,#1
	IN     .10,[.0]
	POP    .1
	POP    .0
	RET

ESCCAR     PUSH   .0
	PUSH   .1
	LD.B   .0,#2
BUC2       IN     .1,[.0]
	AND    .1,#1
	BZ     BUC2
	LD.B   .0,#3
	OUT    .10,[.0]
	POP    .1
	POP    .0
	RET

	ORG    0
INI        CALL   /LEECAR
	CMP    .10,#H'30
	BN     ECO
	CMP    .10,#H'35
	BNN    ECO
	SUB    .10,#H'30
	SHL    .10
	CALL   [/SUBPROG[.10]]
	BR     /INI
ECO        CALL   /ESCCAR
	BR     /INI

	ORG    100
SUBPROG    DATA   2000,3000,4000,5000,6000

	ORG    2000
SALTO      EQU    H'0D
	LD     .10,#SALTO
	PUSH   .11
	LD     .11,#25
BUC3       CALL   /ESCCAR
	SUB    .11,#1
	BNZ    BUC3
	POP    .11
	RET

	ORG    3000
ROJO       EQU    H'04
	LD     .10,#15
	CALL   /ESCCAR
	LD     .10,#ROJO
	CALL   /ESCCAR
	RET

	ORG    4000
BLANCO     EQU    15
	LD     .10,#BLANCO
	CALL   /ESCCAR
	CALL   /ESCCAR
	RET

	ORG    5000
QUITAR     EQU    1
	LD     .10,#18
	CALL   /ESCCAR
	LD     .10,#QUITAR
	CALL   /ESCCAR
	RET

	ORG    6000
PONER      EQU    0
	LD     .10,#18
	CALL   /ESCCAR
	LD     .10,#PONER
	CALL   /ESCCAR
	RET
