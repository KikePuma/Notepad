/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosdebusqueda;

/**
 *
 * @author Gatete
 */
public class Ejercicio2 {

    public void run() {
        AlmacenExtintores almacen = new AlmacenExtintores(10);

        Extintor ext1 = new Extintor(2514, 'A', 100.50, "Starker A-Series");
        Extintor ext2 = new Extintor(2525, 'B', 125.99, "Starker Bounty Gold");
        Extintor ext3 = new Extintor(2526, 'X', 359.00, "Starker DX Force");

        almacen.insertarExtintor(ext1);
        almacen.insertarExtintor(ext2);
        almacen.insertarExtintor(ext3);
        
        almacen.show();

        almacen.buscarExtintor(1220);
        almacen.buscarExtintor(2525);

        

    }

    public class Extintor {

        private final int numReferencia;
        private final char tipo;
        private final double precio;
        private final String fechaTimbrado;

        public Extintor(int n, char t, double p, String f) {
            numReferencia = n;
            tipo = t;
            precio = p;
            fechaTimbrado = f;
        }

        public int getNumReferencia() {
            return numReferencia;
        }

        public char
                getTipo() {
            return tipo;
        }

        public double
                getPrecio() {
            return precio;
        }

        public String
                getFechaTimbrado() {
            return fechaTimbrado;
        }
    }

    public class AlmacenExtintores {

        private final Extintor[] ALMACEN;

        public AlmacenExtintores(int numExtintores) {
            double factor_de_carga = 0.9; // lamba = numElem / tamañoArray
            ALMACEN = new Extintor[(int) (numExtintores / factor_de_carga)];
            System.out.println("Tamaño del nuevo array: " + ALMACEN.length);
        }

        private int funcionHashing(int numRefer) {
            int key;
            key = numRefer % ALMACEN.length;

            if (ALMACEN[key] != null) {
                int rehash_key = (key + 1) % ALMACEN.length;
                while (ALMACEN[rehash_key] != null && rehash_key != key) {
                    rehash_key = (rehash_key + 1) % ALMACEN.length;
                }

                return ALMACEN[rehash_key] == null ? rehash_key : -1;
            }
            return key;
        }

        public void insertarExtintor(Extintor ext) {
            ALMACEN[funcionHashing(ext.getNumReferencia())] = ext;
        }

        public Extintor buscarExtintor(int numRefer) {
            Extintor toret = null;

            int key = numRefer % ALMACEN.length;

            if (ALMACEN[key] != null) {
                if (ALMACEN[key].getNumReferencia() == numRefer) {
                    toret = ALMACEN[key];
                } else {
                    int rehash_key = (key + 1) % ALMACEN.length;
                    while (ALMACEN[rehash_key].getNumReferencia() != numRefer
                            && ALMACEN[rehash_key] != null && rehash_key != key)
                        rehash_key = (rehash_key + 1) % ALMACEN.length;
                    
                    if (ALMACEN[rehash_key].getNumReferencia() == numRefer) {
                        toret = ALMACEN[rehash_key];
                    }
                }
            }
            
            System.out.println("Respuesta a la búsqueda del Extintor #" + numRefer + ": " + toret);
            return toret;
        }

        public void show() {
            System.out.println("== ALMACEN ==");
            for (Extintor ext : ALMACEN) {
                if (ext == null) {
                    System.out.println("- vacío -");
                } else {
                    System.out.println(ext.getNumReferencia() + " -> " + ext);
                }
            }
            System.out.println("==============");
        }
    }
}
