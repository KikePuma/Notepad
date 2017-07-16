	ORG    0
	LD     .11,#LISTA1
	LD     .12,#LISTA2
	LD     .13,#LISTA3
BUC        LD.B   .1,[.11++]
	CMP    .1,#46
	BZ     FIN
	CALL   /ESNUM
	BR     BUC
FIN        HALT

	ORG    200
ESNUM      CMP    .1,#48
	BN     NONUM
	CMP    .1,#58   ;ASCII DE "9" +1
	BN     NUM
NONUM      ST.B   .1,[.12++]
	RET
NUM        ST.B   .1,[.13++]
	RET

	ORG    300
LISTA1     DATA.B "B10IE0N."

	ORG    310
LISTA2     RES.B  10

	ORG    320
LISTA3     RES.B  10
