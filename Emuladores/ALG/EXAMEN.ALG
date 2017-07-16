           ORG    100
MULT25     PUSH   .0
           PUSH   .1
           LD     .0,[/6[.14]]
           LD     .1,[/6[.14]]
           SHL    .0
           SHR    .1
           ST     .0,/6[.14]
           ADD    .1,/6[.14]
           ST     .1,/6[.14]
           POP    .1
           POP    .0
           RET

           ORG    0
           LD     .0,#NUMERO
           PUSH   .0
           CALL   /MULT25
           POP    .1
           HALT
           
           ORG    50 ; PUEDES VARIAR ESTA POSICION (ORG 30, 50, 64..)
NUMERO     DATA   3  ; QUE EL PROGRAMA FUNCIONA IGUAL
