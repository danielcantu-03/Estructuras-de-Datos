package tarea3;

public class Fibonacci {

    static long[] memoria;

    public static long calcular(int n) {

        // Si ya calculamos este valor, lo regresamos
        if (memoria[n] != -1) {
            return memoria[n];
        }

        // Caso base
        if (n == 0 || n == 1) {
            return n;
        }

        // Caso recursivo
        memoria[n] = calcular(n - 1) + calcular(n - 2);

        return memoria[n];
    }

    public static void mostrarSerie(int n) {

        memoria = new long[n + 1];

        // Inicializar memoria
        for (int i = 0; i <= n; i++) {
            memoria[i] = -1;
        }

        System.out.println("\nSerie de Fibonacci:");

        for (int i = 0; i <= n; i++) {
            System.out.print(calcular(i) + " ");
        }

        System.out.println();
    }
}
