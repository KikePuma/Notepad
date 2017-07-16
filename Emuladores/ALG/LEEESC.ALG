           ORG    100
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
           CALL   /ESCCAR
           HALT
