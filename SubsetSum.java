
package tarea3;

public class SubsetSum {

    public static boolean existeSubconjunto(int[] numeros, int n, int objetivo) {

        // Caso base: encontramos la suma
        if (objetivo == 0) {
            return true;
        }

        // Caso base: no quedan elementos
        if (n == 0) {
            return false;
        }

        // Si el número es mayor que el objetivo,
        // no lo incluimos
        if (numeros[n - 1] > objetivo) {
            return existeSubconjunto(numeros, n - 1, objetivo);
        }

        // Dos posibilidades:
        // 1. Incluir el elemento
        // 2. No incluir el elemento

        return existeSubconjunto(
                numeros,
                n - 1,
                objetivo - numeros[n - 1]
        )
        ||
        existeSubconjunto(
                numeros,
                n - 1,
                objetivo
        );
    }

    public static void mostrarResultado(int[] numeros, int objetivo) {

        System.out.print("\nConjunto: ");

        for (int i = 0; i < numeros.length; i++) {
            System.out.print(numeros[i] + " ");
        }

        System.out.println("\nObjetivo: " + objetivo);

        if (existeSubconjunto(numeros, numeros.length, objetivo)) {
            System.out.println(
                    "Existe un subconjunto que suma " + objetivo
            );
        } else {
            System.out.println(
                    "No existe un subconjunto que sume " + objetivo
            );
        }
    }
}