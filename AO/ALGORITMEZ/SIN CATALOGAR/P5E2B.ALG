	ORG    1000
TEXTO      DATA.B "EsTA es la FRASe."

	ORG    0
	LD     .0,#TEXTO
BUC        LD.B   .1,/0[.0]
	CMP    .1,#H'2E
	BZ     FIN
	CMP    .1,#H'20
	BZ     ESPA
	CALL   /CAMBIA
ESPA       ST.B     .1,[.0++]
	BR     BUC
FIN        HALT

	ORG    200
CAMBIA     CMP    .1,#H'41
	BN     NOLETRA
	CMP    .1,#H'5A
	BN     LETRAMA
	CMP    .1,#H'61
	BN     NOLETRA
	CMP    .1,#H'7A
	BN     LETRAMI
NOLETRA    RET
LETRAMA    ADD    .1,#H'20
	RET
LETRAMI    SUB    .1,#H'20
	RET
