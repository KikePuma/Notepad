	ORG    0
	LD     .1,#52
	CALL   /ESNUM
	HALT

	ORG    200
ESNUM      CMP    .1,#48
	BN     NONUM
	CMP    .1,#58   ;ASCII DE "9" +1
	BN     NUM
NONUM      LD     .2,#0
	RET
NUM        LD     .2,#1
	RET
