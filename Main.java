package tarea3;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int opcion;

        do {

            System.out.println("EJERCICIOS DE RECURSIVIDAD");
            System.out.println("1. Serie de Fibonacci");
            System.out.println("2. Suma de subconjuntos");
            System.out.println("3. Resolver Sudoku");
            System.out.println("4. Salir");

            System.out.print("Seleccione una opcion: ");
            opcion = entrada.nextInt();

            switch (opcion) {

                case 1:

                    System.out.println("\nSERIE DE FIBONACCI");

                    System.out.print(
                            "Ingrese la posicion hasta donde "
                            + "quiere generar la serie: "
                    );

                    int n = entrada.nextInt();

                    if (n < 0) {

                        System.out.println(
                                "El numero debe ser mayor o igual a 0."
                        );

                    } else {

                        Fibonacci.mostrarSerie(n);
                    }

                    break;

                case 2:

                    System.out.println(
                            "\nSUMA DE SUBCONJUNTOS"
                    );

                    int[] numeros = {
                        3, 34, 4, 12, 5, 2
                    };

                    System.out.print(
                            "Ingrese el valor objetivo: "
                    );

                    int objetivo = entrada.nextInt();

                    SubsetSum.mostrarResultado(
                            numeros,
                            objetivo
                    );

                    break;

                case 3:

                    System.out.println(
                            "\nSOLUCIONADOR DE SUDOKU"
                    );

                    System.out.print(
                            "Ingrese el nombre del archivo: "
                    );

                    String nombreArchivo = entrada.next();

                    try {

                        // Cargar el Sudoku desde el archivo
                        int[][] tablero =
                                Sudoku.cargarDesdeArchivo(nombreArchivo);

                        System.out.println(
                                "\nSudoku original:"
                        );

                        Sudoku.mostrar(tablero);

                        System.out.println(
                                "\nResolviendo Sudoku..."
                        );

                        if (Sudoku.resolver(tablero)) {

                            System.out.println(
                                    "\nSudoku resuelto:"
                            );

                            Sudoku.mostrar(tablero);

                        } else {

                            System.out.println(
                                    "El Sudoku no tiene solucion."
                            );
                        }

                    } catch (FileNotFoundException e) {

                        System.out.println(
                                "No se encontro el archivo."
                        );

                    } catch (IllegalArgumentException e) {

                        System.out.println(
                                "Error en el archivo: "
                                + e.getMessage()
                        );
                    }

                    break;

                case 4:

                    System.out.println(
                            "\nPrograma finalizado."
                    );

                    break;

                default:

                    System.out.println(
                            "\nOpcion no valida."
                    );
            }

        } while (opcion != 4);

        entrada.close();
    }
}