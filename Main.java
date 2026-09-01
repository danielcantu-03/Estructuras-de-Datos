package avance.de.proyecto;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static Pila<Tarea> pilaUrgentes = new Pila<>();
    static Cola<Tarea> colaProgramadas = new Cola<>();
    static ListaEnlazada<Tarea> listaPendientes = new ListaEnlazada<>();

    static int siguienteId = 1;

    public static void main(String[] args) {

        int opcion;

        do {

            mostrarMenu();

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    agregarUrgente();
                    break;

                case 2:
                    atenderUrgente();
                    break;

                case 3:
                    verUrgente();
                    break;

                case 4:
                    agregarProgramada();
                    break;

                case 5:
                    atenderProgramada();
                    break;

                case 6:
                    verProgramada();
                    break;

                case 7:
                    agregarPendiente();
                    break;

                case 8:
                    eliminarPendiente();
                    break;

                case 9:
                    buscarPendiente();
                    break;

                case 10:
                    listaPendientes.mostrar();
                    break;

                case 11:
                    mostrarTodas();
                    break;

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }

    public static void mostrarMenu() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("       SISTEMA DE GESTION DE TAREAS");
        System.out.println("========================================");
        System.out.println("1. Agregar tarea urgente");
        System.out.println("2. Atender tarea urgente");
        System.out.println("3. Ver tarea urgente");
        System.out.println();
        System.out.println("4. Agregar tarea programada");
        System.out.println("5. Atender tarea programada");
        System.out.println("6. Ver siguiente tarea programada");
        System.out.println();
        System.out.println("7. Agregar tarea pendiente");
        System.out.println("8. Eliminar tarea pendiente");
        System.out.println("9. Buscar tarea pendiente");
        System.out.println("10. Ver tareas pendientes");
        System.out.println();
        System.out.println("11. Ver todas las tareas");
        System.out.println("0. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
    }

    public static Tarea crearTarea() {

        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();

        int urgencia;

        do {

            System.out.print("Urgencia (1-Baja, 2-Media, 3-Alta): ");
            urgencia = scanner.nextInt();
            scanner.nextLine();

            if (urgencia < 1 || urgencia > 3) {
                System.out.println("La urgencia debe estar entre 1 y 3.");
            }

        } while (urgencia < 1 || urgencia > 3);

        Tarea tarea = new Tarea(
            siguienteId,
            descripcion,
            departamento,
            urgencia
        );

        siguienteId++;

        return tarea;
    }

    public static void agregarUrgente() {

        System.out.println();
        System.out.println("--- AGREGAR TAREA URGENTE ---");

        Tarea tarea = crearTarea();

        pilaUrgentes.push(tarea);

        System.out.println("Tarea agregada a la pila de urgentes.");
        System.out.println("ID asignado: " + tarea.getId());
    }

    public static void atenderUrgente() {

        Tarea tarea = pilaUrgentes.pop();

        if (tarea == null) {
            System.out.println("No hay tareas urgentes.");
        } else {
            System.out.println("Tarea atendida:");
            tarea.mostrar();
        }
    }

    public static void verUrgente() {

        Tarea tarea = pilaUrgentes.peek();

        if (tarea == null) {
            System.out.println("No hay tareas urgentes.");
        } else {
            System.out.println("Tarea en la cima:");
            tarea.mostrar();
        }
    }

    public static void agregarProgramada() {

        System.out.println();
        System.out.println("--- AGREGAR TAREA PROGRAMADA ---");

        Tarea tarea = crearTarea();

        colaProgramadas.enqueue(tarea);

        System.out.println("Tarea agregada a la cola.");
        System.out.println("ID asignado: " + tarea.getId());
    }

    public static void atenderProgramada() {

        Tarea tarea = colaProgramadas.dequeue();

        if (tarea == null) {
            System.out.println("No hay tareas programadas.");
        } else {
            System.out.println("Tarea atendida:");
            tarea.mostrar();
        }
    }

    public static void verProgramada() {

        Tarea tarea = colaProgramadas.front();

        if (tarea == null) {
            System.out.println("No hay tareas programadas.");
        } else {
            System.out.println("Siguiente tarea:");
            tarea.mostrar();
        }
    }

    public static void agregarPendiente() {

        System.out.println();
        System.out.println("--- AGREGAR TAREA PENDIENTE ---");

        Tarea tarea = crearTarea();

        listaPendientes.insert(tarea);

        System.out.println("Tarea agregada a la lista.");
        System.out.println("ID asignado: " + tarea.getId());
    }

    public static void eliminarPendiente() {

        System.out.print("Ingrese el ID de la tarea a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Tarea tarea = listaPendientes.find(id);

        if (tarea == null) {
            System.out.println("No se encontro la tarea.");
            return;
        }

        listaPendientes.delete(tarea);

        System.out.println("Tarea eliminada correctamente.");
    }

    public static void buscarPendiente() {

        System.out.print("Ingrese el ID de la tarea a buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Tarea tarea = listaPendientes.find(id);

        if (tarea == null) {
            System.out.println("No se encontro la tarea.");
        } else {
            System.out.println("Tarea encontrada:");
            tarea.mostrar();
        }
    }

    public static void mostrarTodas() {

        System.out.println();
        System.out.println("========== TAREAS URGENTES ==========");
        pilaUrgentes.mostrar();

        System.out.println();
        System.out.println("======= TAREAS PROGRAMADAS =========");
        colaProgramadas.mostrar();

        System.out.println();
        System.out.println("========= TAREAS PENDIENTES =========");
        listaPendientes.mostrar();
    }
}
