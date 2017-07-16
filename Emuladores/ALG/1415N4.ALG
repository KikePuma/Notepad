	ORG    0
	CLR    .1
	LD.B   .1,#H'AA
	CALL   /BITSIGU
	HALT

	ORG    100
BITSIGU    ST.B   .1,DATO
	LD     .13,#4
	LD.B   .2,DATO
BUC        SHR    .1
	SHL    .2
	SHL    .2
	SHL    .2
	SUB    .13,#1
	BZ     SIG
	BR     BUC
SIG        ROL    .2
	ROL    .2
	ROL    .2
	ROL    .2
	ST.B   .1,DATO
	CMP    .2,DATO
	BZ     MISMO
	LD     .2,#0
	RET
MISMO      LD     .2,#1
	RET
DATO       RES    1
