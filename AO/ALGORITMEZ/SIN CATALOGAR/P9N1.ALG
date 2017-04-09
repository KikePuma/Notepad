	ORG    0
BIT        EQU    H'01
CAR        EQU    H'FF
	CALL   /LEECAR
	CALL   /ESCCAR
	HALT

	ORG    100
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

	ORG    200
ESCCAR     PUSH   .1
	PUSH   .2
	PUSH   .3
;           LD.B   .1,#1
;           LD.B   .2,#2
	LD.B   .3,#3
;           OUT    .1,[.2]
	OUT    .10,[.3]
	POP    .3
	POP    .2
	POP    .1
	RET
