	ORG    100
	DATA   CERO,UNO,DOS,TRES,CUATRO,CINCO,SEIS,SIETE,OCHO,NUEVE

	ORG    200
LEECAR     PUSH   .0
	PUSH   .1
	CLR    .0
	LD.B   .1,#1
	OUT    .0,[.0]
	OUT    .0,[.1]
BUC1       IN     .0,[.0]
	AND    .0,#BIT
	BZ     BUC1
	IN     .10,[.1]
;           AND    .10,#CAR
	POP    .1
	POP    .0
	RET

	ORG    300
ESCCAR     PUSH   .1
	PUSH   .2
	PUSH   .3
;           LD.B   .1,#1
	LD.B   .2,#2
	LD.B   .3,#3
;           OUT    .1,[.2]
BUC2       IN     .1,[.2]
	AND    .1,#BIT
	BZ     BUC2
	OUT    .10,[.3]
	POP    .3
	POP    .2
	POP    .1
	RET

	ORG    1000
CERO       DATA.B "CERO",13
UNO        DATA.B "UNO",13
DOS        DATA.B "DOS",13
TRES       DATA.B "TRES",13
CUATRO     DATA.B "CUATRO",13
CINCO      DATA.B "CINCO",13
SEIS       DATA.B "SEIS",13
SIETE      DATA.B "SIETE",13
OCHO       DATA.B "OCHO",13
NUEVE      DATA.B "NUEVE",13

	ORG    0
BIT        EQU    H'01
CAR        EQU    H'FF
SALTO      EQU    H'0D
	CALL   /LEECAR
	SUB    .10,#H'30
	SHL    .10
	LD     .11,/100[.10]
BUC        LD.B   .10,[.11++]
	CALL   /ESCCAR
	CMP    .10,#SALTO
	BZ     FIN
	BR     BUC
FIN        HALT
