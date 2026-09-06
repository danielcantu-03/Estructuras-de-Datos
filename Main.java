package tarea3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int opcion;

        do {

            System.out.println("\n======================================");
            System.out.println("       EJERCICIOS DE RECURSIVIDAD");
            System.out.println("======================================");
            System.out.println("1. Serie de Fibonacci");
            System.out.println("2. Suma de subconjuntos");
            System.out.println("3. Resolver Sudoku");
            System.out.println("4. Salir");
            System.out.println("======================================");

            System.out.print("Seleccione una opcion: ");
            opcion = entrada.nextInt();

            switch (opcion) {

                case 1:

                    System.out.println("\n--- SERIE DE FIBONACCI ---");

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
                            "\n--- SUMA DE SUBCONJUNTOS ---"
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
                            "\n--- SOLUCIONADOR DE SUDOKU ---"
                    );

                    int[][] tablero = Sudoku.crearTablero();

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