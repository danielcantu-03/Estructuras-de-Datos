package tarea3;

public class Sudoku {

    static final int TAM = 9;

    public static boolean resolver(int[][] tablero) {

        // Buscar una celda vacía
        for (int fila = 0; fila < TAM; fila++) {

            for (int columna = 0; columna < TAM; columna++) {

                if (tablero[fila][columna] == 0) {

                    // Probar números del 1 al 9
                    for (int numero = 1; numero <= 9; numero++) {

                        if (esValido(tablero, fila, columna, numero)) {

                            // Colocar el número
                            tablero[fila][columna] = numero;

                            // Llamada recursiva
                            if (resolver(tablero)) {
                                return true;
                            }

                            // Backtracking:
                            // quitar el número y probar otro
                            tablero[fila][columna] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        // No quedan espacios vacíos
        return true;
    }

    public static boolean esValido(
            int[][] tablero,
            int fila,
            int columna,
            int numero) {

        // Comprobar fila
        for (int i = 0; i < TAM; i++) {

            if (tablero[fila][i] == numero) {
                return false;
            }
        }

        // Comprobar columna
        for (int i = 0; i < TAM; i++) {

            if (tablero[i][columna] == numero) {
                return false;
            }
        }

        // Comprobar bloque 3x3
        int inicioFila = fila - fila % 3;
        int inicioColumna = columna - columna % 3;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (tablero[inicioFila + i]
                        [inicioColumna + j] == numero) {

                    return false;
                }
            }
        }

        return true;
    }

    public static void mostrar(int[][] tablero) {

        for (int fila = 0; fila < TAM; fila++) {

            for (int columna = 0; columna < TAM; columna++) {

                System.out.print(tablero[fila][columna] + " ");
            }

            System.out.println();
        }
    }

    public static int[][] crearTablero() {

        return new int[][]{

            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},

            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},

            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
    }
}
