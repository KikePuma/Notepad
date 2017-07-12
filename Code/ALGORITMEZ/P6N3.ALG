	ORG    0
	LD     .0,#9000
	CALL   /MUL10GEN
	HALT

	ORG    200
MUL10GEN   CLRV
	SHLA   .0
	BV     NEGA
	ST     .0,AUX
	SHLA   .0
	BV     NEGA
	SHLA   .0
	BV     NEGA
	ADD    .0,AUX
	RET
NEGA       LD     .0,#0
	RET
AUX        RES    2
