package tarea3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku {

    static final int TAM = 9;

    // =====================================================
    // LEER SUDOKU DESDE UN ARCHIVO TXT
    // =====================================================

    public static int[][] cargarDesdeArchivo(String nombreArchivo)
            throws FileNotFoundException {

        int[][] tablero = new int[TAM][TAM];

        Scanner archivo = new Scanner(new File(nombreArchivo));

        for (int fila = 0; fila < TAM; fila++) {

            if (!archivo.hasNextLine()) {
                archivo.close();
                throw new IllegalArgumentException(
                        "El archivo no contiene 9 filas."
                );
            }

            String linea = archivo.nextLine().trim();

            if (linea.length() != TAM) {
                archivo.close();
                throw new IllegalArgumentException(
                        "Cada fila debe contener 9 numeros."
                );
            }

            for (int columna = 0; columna < TAM; columna++) {

                char caracter = linea.charAt(columna);

                if (caracter < '0' || caracter > '9') {
                    archivo.close();
                    throw new IllegalArgumentException(
                            "El archivo solo debe contener numeros."
                    );
                }

                tablero[fila][columna] = caracter - '0';
            }
        }

        archivo.close();

        return tablero;
    }

    // =====================================================
    // RESOLVER SUDOKU CON BACKTRACKING
    // =====================================================

    public static boolean resolver(int[][] tablero) {

        for (int fila = 0; fila < TAM; fila++) {

            for (int columna = 0; columna < TAM; columna++) {

                if (tablero[fila][columna] == 0) {

                    for (int numero = 1; numero <= 9; numero++) {

                        if (esValido(tablero, fila, columna, numero)) {

                            tablero[fila][columna] = numero;

                            if (resolver(tablero)) {
                                return true;
                            }

                            // Backtracking
                            tablero[fila][columna] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    // =====================================================
    // COMPROBAR SI UN NUMERO ES VALIDO
    // =====================================================

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

    // =====================================================
    // MOSTRAR TABLERO
    // =====================================================

    public static void mostrar(int[][] tablero) {

        for (int fila = 0; fila < TAM; fila++) {

            for (int columna = 0; columna < TAM; columna++) {

                System.out.print(tablero[fila][columna] + " ");
            }

            System.out.println();
        }
    }
}
