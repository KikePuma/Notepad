	ORG    0
	LD     .1,NUM
	CLR    .2
	CALL   /UNOS
FIN        HALT
NUM        DATA   15

	ORG    200
UNOS       CMP    .1,#0
	BZ     FINAL
	SHRA   .1
	BC     SUMA
	BR     UNOS
SUMA       ADD    .2,#1
	BR     UNOS
FINAL      RET
