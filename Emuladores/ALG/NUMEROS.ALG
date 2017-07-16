           ORG    200
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
           CALL   /LEECAR
           SUB    .10,#H'30
           SHL    .10
           ADD    .10,#500
           LD     .9,/0[.10]
BUC        LD.B   .10,[.9++]
           CALL   /ESCCAR
            CMP.B  .10,#H'0D
           BNZ    BUC
           HALT

           ORG    500
           DATA   CERO,UNO,DOS

           ORG    1000
CERO       DATA.B "CERO",13
UNO        DATA.B "UNO",13
DOS        DATA.B "DOS",13

