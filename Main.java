package tarea4;

import java.util.Scanner;

public class Main {

    static Scanner entrada = new Scanner(System.in);

    // Arreglo para realizar la búsqueda secuencial
    static Empleado[] empleados = new Empleado[100];
    static int cantidadEmpleados = 0;

    public static void main(String[] args) {

        ArbolBinario arbol = new ArbolBinario();

        // EMPLEADOS INICIALES
        agregarEmpleado(arbol,
                new Empleado(50, "Carlos", "Gerente"));

        agregarEmpleado(arbol,
                new Empleado(30, "Ana", "Contadora"));

        agregarEmpleado(arbol,
                new Empleado(70, "Luis", "Supervisor"));

        agregarEmpleado(arbol,
                new Empleado(20, "Maria", "Secretaria"));

        agregarEmpleado(arbol,
                new Empleado(40, "Pedro", "Programador"));

        agregarEmpleado(arbol,
                new Empleado(60, "Sofia", "Diseñadora"));

        agregarEmpleado(arbol,
                new Empleado(80, "Jorge", "Analista"));

        int opcion;

        do {

            System.out.println("--- GESTION DE EMPLEADOS ---");
            System.out.println("1. Mostrar empleados");
            System.out.println("2. Insertar empleado");
            System.out.println("3. Buscar empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Recorrido Preorden");
            System.out.println("6. Recorrido Inorden");
            System.out.println("7. Recorrido Postorden");
            System.out.println("8. Comparar busquedas");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = entrada.nextInt();

            switch (opcion) {

                case 1:
                    mostrarEmpleados();
                    break;

                case 2:
                    insertarEmpleado(arbol);
                    break;

                case 3:
                    buscarEmpleado(arbol);
                    break;

                case 4:
                    eliminarEmpleado(arbol);
                    break;

                case 5:
                    System.out.println("\n--- RECORRIDO PREORDEN ---");
                    arbol.preorden();
                    break;

                case 6:
                    System.out.println("\n--- RECORRIDO INORDEN ---");
                    arbol.inorden();
                    break;

                case 7:
                    System.out.println("\n--- RECORRIDO POSTORDEN ---");
                    arbol.postorden();
                    break;

                case 8:
                    compararBusquedas(arbol);
                    break;

                case 9:
                    System.out.println("\nPrograma finalizado.");
                    break;

                default:
                    System.out.println("\nOpcion no valida.");
            }

        } while (opcion != 9);
    }

    // AGREGAR EMPLEADO
    public static void agregarEmpleado(
            ArbolBinario arbol,
            Empleado empleado) {

        empleados[cantidadEmpleados] = empleado;
        cantidadEmpleados++;

        arbol.insertar(empleado);
    }

    // MOSTRAR EMPLEADOS
    public static void mostrarEmpleados() {

        System.out.println("\n--- EMPLEADOS REGISTRADOS ---");

        for (int i = 0; i < cantidadEmpleados; i++) {
            System.out.println(empleados[i]);
        }
    }

    // INSERTAR
    public static void insertarEmpleado(ArbolBinario arbol) {

        entrada.nextLine();

        System.out.print("\nIngrese el nombre: ");
        String nombre = entrada.nextLine();

        System.out.print("Ingrese el puesto: ");
        String puesto = entrada.nextLine();

        System.out.print("Ingrese el ID: ");
        int id = entrada.nextInt();

        Empleado empleado =
                new Empleado(id, nombre, puesto);

        agregarEmpleado(arbol, empleado);

        System.out.println("\nEmpleado agregado correctamente.");
    }

    // BUSCAR
    public static void buscarEmpleado(ArbolBinario arbol) {

        System.out.print("\nIngrese el ID que desea buscar: ");
        int id = entrada.nextInt();

        Empleado encontrado = arbol.buscar(id);

        if (encontrado != null) {

            System.out.println("\nEmpleado encontrado:");
            System.out.println(encontrado);

            System.out.println(
                    "Comparaciones realizadas por el arbol: "
                    + arbol.getComparacionesBusqueda()
            );

        } else {

            System.out.println("\nEl empleado no existe.");

            System.out.println(
                    "Comparaciones realizadas por el arbol: "
                    + arbol.getComparacionesBusqueda()
            );
        }
    }

    // ELIMINAR
    public static void eliminarEmpleado(ArbolBinario arbol) {

        System.out.print("\nIngrese el ID que desea eliminar: ");
        int id = entrada.nextInt();

        Empleado encontrado = arbol.buscar(id);

        if (encontrado != null) {

            arbol.eliminar(id);

            // Eliminar también del arreglo
            for (int i = 0; i < cantidadEmpleados; i++) {

                if (empleados[i].getId() == id) {

                    for (int j = i; j < cantidadEmpleados - 1; j++) {
                        empleados[j] = empleados[j + 1];
                    }

                    empleados[cantidadEmpleados - 1] = null;
                    cantidadEmpleados--;

                    break;
                }
            }

            System.out.println(
                    "\nEmpleado eliminado correctamente."
            );

        } else {

            System.out.println(
                    "\nNo se encontro un empleado con ese ID."
            );
        }
    }

    // COMPARACION DE BUSQUEDAS
    public static void compararBusquedas(ArbolBinario arbol) {

        System.out.print(
                "\nIngrese el ID que desea buscar: "
        );

        int id = entrada.nextInt();

        // BUSQUEDA SECUENCIAL
        int comparacionesSecuencial = 0;
        Empleado encontradoSecuencial = null;

        for (int i = 0; i < cantidadEmpleados; i++) {

            comparacionesSecuencial++;

            if (empleados[i].getId() == id) {

                encontradoSecuencial = empleados[i];
                break;
            }
        }

        // BUSQUEDA EN ARBOL
        Empleado encontradoArbol = arbol.buscar(id);

        int comparacionesArbol =
                arbol.getComparacionesBusqueda();

        // RESULTADOS
        System.out.println("COMPARACION DE BUSQUEDAS");

        if (encontradoSecuencial != null) {

            System.out.println(
                    "\nEmpleado encontrado:"
            );

            System.out.println(encontradoSecuencial);

        } else {

            System.out.println(
                    "\nEl empleado no fue encontrado."
            );
        }

        System.out.println(
                "\nBusqueda secuencial:"
        );

        System.out.println(
                "Comparaciones: "
                + comparacionesSecuencial
        );

        System.out.println(
                "\nBusqueda mediante arbol:"
        );

        System.out.println(
                "Comparaciones: "
                + comparacionesArbol
        );

        if (comparacionesArbol < comparacionesSecuencial) {

            System.out.println(
                    "\nEl arbol realizo menos comparaciones."
            );

        } else if (comparacionesArbol >
                   comparacionesSecuencial) {

            System.out.println(
                    "\nLa busqueda secuencial realizo menos comparaciones."
            );

        } else {

            System.out.println(
                    "\nAmbas busquedas realizaron la misma cantidad de comparaciones."
            );
        }
    }
}
