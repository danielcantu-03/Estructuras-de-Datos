package proyecto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class OrdenamientoConcurrente {
    static final String[] ALGORITMOS = {"Bubble", "Shell", "Quicksort", "Merge", "Insertion", "Selection"};
    static final String ARREGLO = "Arreglo";
    static final String LISTA = "ArrayList";

    //Resultado de una implementacion
    static class Resultado {
        final String algoritmo;
        final String estructura;
        final double ms;
        final boolean ordenado;

        Resultado(String algoritmo, String estructura, double ms, boolean ordenado) {
            this.algoritmo = algoritmo;
            this.estructura = estructura;
            this.ms = ms;
            this.ordenado = ordenado;
        }
    }

    // MAIN
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String repetir;
        do {
            int n = leerEnteroPositivo(sc, "Cuantos elementos vas a ordenar? ");
            System.out.print("Restringir valores al intervalo 1-5? (s/n): ");
            boolean restringido = sc.next().trim().equalsIgnoreCase("s");

            ejecutar(n, restringido);

            System.out.print("\nRepetir con otra cantidad? (s/n): ");
            repetir = sc.next().trim();
            System.out.println();
        } while (repetir.equalsIgnoreCase("s"));
        sc.close();
    }

    static int leerEnteroPositivo(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.next();
            try {
                int n = Integer.parseInt(linea.trim());
                if (n > 0) return n;
            } catch (NumberFormatException e) {
                // cae al mensaje de error
            }
            System.out.println("Escribe un numero entero positivo.");
        }
    }

    // ------------------------------------------------------------------
    // EJECUCION CONCURRENTE
    // ------------------------------------------------------------------
    static void ejecutar(int n, boolean restringido) throws InterruptedException {
        // 1) Generar los datos originales (fuera del tiempo medido)
        Random rnd = new Random();
        int[] original = new int[n];
        for (int i = 0; i < n; i++) {
            original[i] = restringido ? rnd.nextInt(5) + 1 : rnd.nextInt(1_000_000);
        }

        ConcurrentHashMap<String, Resultado> resultados = new ConcurrentHashMap<>();
        CountDownLatch salida = new CountDownLatch(1); // "pistola de salida" para todos los hilos
        List<Thread> hilos = new ArrayList<>();

        // 2) Preparar copias independientes y los 12 hilos (fuera del tiempo medido)
        for (String alg : ALGORITMOS) {
            // Version con arreglo
            int[] copiaArr = original.clone();
            hilos.add(new Thread(() -> {
                esperar(salida);
                long ini = System.nanoTime();
                ordenarArreglo(alg, copiaArr);
                long fin = System.nanoTime();
                boolean ok = estaOrdenado(copiaArr);
                resultados.put(alg + "|" + ARREGLO,
                        new Resultado(alg, ARREGLO, (fin - ini) / 1_000_000.0, ok));
            }, alg + "-" + ARREGLO));

            // Version con ArrayList
            List<Integer> copiaLista = new ArrayList<>(n);
            for (int v : original) copiaLista.add(v);
            hilos.add(new Thread(() -> {
                esperar(salida);
                long ini = System.nanoTime();
                ordenarLista(alg, copiaLista);
                long fin = System.nanoTime();
                boolean ok = estaOrdenado(copiaLista);
                resultados.put(alg + "|" + LISTA,
                        new Resultado(alg, LISTA, (fin - ini) / 1_000_000.0, ok));
            }, alg + "-" + LISTA));
        }

        System.out.println("Ordenando " + n + " elementos con 12 hilos... (Bubble puede tardar con cantidades grandes)");

        // 3) Iniciar todos los hilos, soltarlos a la vez y esperar a que terminen
        for (Thread t : hilos) t.start();
        salida.countDown();
        for (Thread t : hilos) t.join();

        // 4) Mostrar resultados ordenados de menor a mayor tiempo
        mostrarResultados(n, restringido, resultados);
    }

    static void esperar(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ------------------------------------------------------------------
    // SALIDA
    // ------------------------------------------------------------------
    static void mostrarResultados(int n, boolean restringido, Map<String, Resultado> mapa) {
        List<Resultado> lista = new ArrayList<>(mapa.values());
        lista.sort(Comparator.comparingDouble(r -> r.ms));

        System.out.println();
        System.out.println("RESULTADOS DE ORDENAMIENTO");
        System.out.println("Elementos: " + n + (restringido ? " (valores entre 1 y 5)" : " (valores aleatorios)"));
        System.out.println();
        System.out.printf("%-5s %-11s %-12s %-14s %-9s%n", "Pos.", "Algoritmo", "Estructura", "Tiempo (ms)", "Ordeno?");
        System.out.println("-".repeat(56));
        int pos = 1;
        for (Resultado r : lista) {
            System.out.printf(Locale.US, "%-5d %-11s %-12s %-14.2f %-9s%n",
                    pos++, r.algoritmo, r.estructura, r.ms, r.ordenado ? "Si" : "NO");
        }

        Resultado mejor = lista.get(0);
        System.out.println();
        System.out.println("Implementacion con menor tiempo registrado: "
                + mejor.algoritmo + " (" + mejor.estructura + ")");
        System.out.println("Nota: con 12 hilos compartiendo el procesador, los tiempos varian entre ejecuciones.");

        System.out.println();
        System.out.println("COMPLEJIDAD TEMPORAL (Big O)");
        System.out.printf("%-11s %-12s %-16s %-12s%n", "Algoritmo", "Mejor caso", "Caso promedio", "Peor caso");
        System.out.println("-".repeat(54));
        System.out.printf("%-11s %-12s %-16s %-12s%n", "Bubble", "O(n)*", "O(n^2)", "O(n^2)");
        System.out.printf("%-11s %-12s %-16s %-12s%n", "Shell", "O(n log n)", "~O(n^1.5)**", "O(n^1.5)**");
        System.out.printf("%-11s %-12s %-16s %-12s%n", "Quicksort", "O(n log n)", "O(n log n)", "O(n^2)");
        System.out.printf("%-11s %-12s %-16s %-12s%n", "Merge", "O(n log n)", "O(n log n)", "O(n log n)");
        System.out.printf("%-11s %-12s %-16s %-12s%n", "Insertion", "O(n)", "O(n^2)", "O(n^2)");
        System.out.printf("%-11s %-12s %-16s %-12s%n", "Selection", "O(n^2)", "O(n^2)", "O(n^2)");
        System.out.println("* con bandera de intercambio.  ** con la secuencia de Knuth (h = 3h+1).");
    }

    // DESPACHO POR NOMBRE
    static void ordenarArreglo(String alg, int[] a) {
        switch (alg) {
            case "Bubble":    bubble(a);    break;
            case "Shell":     shell(a);     break;
            case "Quicksort": quick(a, 0, a.length - 1); break;
            case "Merge":     merge(a, new int[a.length], 0, a.length - 1); break;
            case "Insertion": insertion(a); break;
            case "Selection": selection(a); break;
        }
    }

    static void ordenarLista(String alg, List<Integer> l) {
        switch (alg) {
            case "Bubble":    bubble(l);    break;
            case "Shell":     shell(l);     break;
            case "Quicksort": quick(l, 0, l.size() - 1); break;
            case "Merge":     merge(l, new ArrayList<>(l), 0, l.size() - 1); break;
            case "Insertion": insertion(l); break;
            case "Selection": selection(l); break;
        }
    }

    // VERIFICACION
    static boolean estaOrdenado(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i - 1] > a[i]) return false;
        return true;
    }

    static boolean estaOrdenado(List<Integer> l) {
        for (int i = 1; i < l.size(); i++) if (l.get(i - 1) > l.get(i)) return false;
        return true;
    }

    // ALGORITMOS CON ARREGLO (int[])
    static void bubble(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            boolean huboCambio = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                    huboCambio = true;
                }
            }
            if (!huboCambio) break;
        }
    }

    static void shell(int[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;
        for (; h >= 1; h /= 3) {
            for (int i = h; i < n; i++) {
                int t = a[i];
                int j = i;
                while (j >= h && a[j - h] > t) {
                    a[j] = a[j - h];
                    j -= h;
                }
                a[j] = t;
            }
        }
    }

    //Quicksort con particion de Hoare y pivote central; recursa en la parte menor.
    static void quick(int[] a, int lo, int hi) {
        while (lo < hi) {
            int p = a[lo + (hi - lo) / 2];
            int i = lo, j = hi;
            while (i <= j) {
                while (a[i] < p) i++;
                while (a[j] > p) j--;
                if (i <= j) {
                    int t = a[i]; a[i] = a[j]; a[j] = t;
                    i++; j--;
                }
            }
            if (j - lo < hi - i) {
                quick(a, lo, j);
                lo = i;
            } else {
                quick(a, i, hi);
                hi = j;
            }
        }
    }

    static void merge(int[] a, int[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) >>> 1;
        merge(a, aux, lo, mid);
        merge(a, aux, mid + 1, hi);
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) aux[k++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        while (i <= mid) aux[k++] = a[i++];
        while (j <= hi) aux[k++] = a[j++];
        for (k = lo; k <= hi; k++) a[k] = aux[k];
    }

    static void insertion(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int t = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > t) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = t;
        }
    }

    static void selection(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) if (a[j] < a[min]) min = j;
            if (min != i) {
                int t = a[i]; a[i] = a[min]; a[min] = t;
            }
        }
    }

    // ALGORITMOS CON ArrayList<Integer>
    static void bubble(List<Integer> l) {
        int n = l.size();
        for (int i = 0; i < n - 1; i++) {
            boolean huboCambio = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (l.get(j) > l.get(j + 1)) {
                    Integer t = l.get(j);
                    l.set(j, l.get(j + 1));
                    l.set(j + 1, t);
                    huboCambio = true;
                }
            }
            if (!huboCambio) break;
        }
    }

    static void shell(List<Integer> l) {
        int n = l.size();
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;
        for (; h >= 1; h /= 3) {
            for (int i = h; i < n; i++) {
                int t = l.get(i);
                int j = i;
                while (j >= h && l.get(j - h) > t) {
                    l.set(j, l.get(j - h));
                    j -= h;
                }
                l.set(j, t);
            }
        }
    }

    static void quick(List<Integer> l, int lo, int hi) {
        while (lo < hi) {
            int p = l.get(lo + (hi - lo) / 2);
            int i = lo, j = hi;
            while (i <= j) {
                while (l.get(i) < p) i++;
                while (l.get(j) > p) j--;
                if (i <= j) {
                    Integer t = l.get(i);
                    l.set(i, l.get(j));
                    l.set(j, t);
                    i++; j--;
                }
            }
            if (j - lo < hi - i) {
                quick(l, lo, j);
                lo = i;
            } else {
                quick(l, i, hi);
                hi = j;
            }
        }
    }

    static void merge(List<Integer> l, List<Integer> aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) >>> 1;
        merge(l, aux, lo, mid);
        merge(l, aux, mid + 1, hi);
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            if (l.get(i) <= l.get(j)) aux.set(k++, l.get(i++));
            else aux.set(k++, l.get(j++));
        }
        while (i <= mid) aux.set(k++, l.get(i++));
        while (j <= hi) aux.set(k++, l.get(j++));
        for (k = lo; k <= hi; k++) l.set(k, aux.get(k));
    }

    static void insertion(List<Integer> l) {
        for (int i = 1; i < l.size(); i++) {
            int t = l.get(i);
            int j = i - 1;
            while (j >= 0 && l.get(j) > t) {
                l.set(j + 1, l.get(j));
                j--;
            }
            l.set(j + 1, t);
        }
    }

    static void selection(List<Integer> l) {
        int n = l.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) if (l.get(j) < l.get(min)) min = j;
            if (min != i) {
                Integer t = l.get(i);
                l.set(i, l.get(min));
                l.set(min, t);
            }
        }
    }
}