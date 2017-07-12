package practica2;

public class Ejercicio1 {

	static int[][] puntos = new int[25][2];

    private static void pointsGenerator() {
        int pointsOnX = 0, pointsOnY = 0;
        int xCoord = 0, yCoord = 0, j = 0;
        System.out.println("Impresión del array de 25 puntos");
        for(int i = 0; i < 25; i++) {
            xCoord = (int)(Math.random()*11-5);
            yCoord = (int)(Math.random()*11-5);
            for(j = 0; j < i; j++){
                if(xCoord == puntos[j][0] && yCoord == puntos[j][1]) {
                    i--;
                    j = 100;
                }
            }
            if (j != i) continue;
            puntos[i][0] = xCoord;
            puntos[i][1] = yCoord;
            System.out.print("(" + puntos[i][0] + "," + puntos[i][1] + ") ");
            if((i+1)%5 == 0) {
                System.out.print("\n");
            }
            if(puntos[i][0] == 0) pointsOnX++;
            if(puntos[i][1] == 0) pointsOnY++;
        }
        System.out.println("Número de puntos en el eje Y (X=0) es: " + pointsOnX);
        System.out.println("Número de puntos en el eje X (Y=0) es: " + pointsOnY + "\n");
    }
    
    private static void graphicGenerator() {
        boolean punto = false;
        for (int y = 5; y > -6; y--) {
            for(int x = -5; x < 6; x++) {
                for(int i = 0; i < 25; i++) {
                  if(puntos[i][0] == x && puntos[i][1] == y){
                      punto = true;
                      break;
                  }
                }
                if(punto) System.out.print(" X ");
                else if (x == 0 || y == 0) System.out.print(" . ");
                else System.out.print("   ");
                punto = false;
            }
            System.out.print("\n");
        }
    }
	
	public static void main(String[] args) {
		pointsGenerator();
        graphicGenerator();
	}

}
