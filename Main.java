package tarea1;

import java.util.Scanner;

public class Main {

    static Scanner scanner =
        new Scanner(System.in);

    static LinkedList<Object> list = null;

    // Fecha actual utilizada para calcular edades
    static int currentDay;
    static int currentMonth;
    static int currentYear;

    public static void main(String[] args) {

        // ======================================
        // OBTENER FECHA ACTUAL
        // ======================================

        System.out.println(
            "===================================="
        );

        System.out.println(
            "       FECHA ACTUAL"
        );

        System.out.println(
            "===================================="
        );

        System.out.print(
            "Ingrese el día actual: "
        );

        currentDay = readInt();

        System.out.print(
            "Ingrese el mes actual: "
        );

        currentMonth = readInt();

        System.out.print(
            "Ingrese el año actual: "
        );

        currentYear = readInt();

        int option;

        do {

            System.out.println(
                "\n===================================="
            );

            System.out.println(
                "       PROGRAMA DE LISTAS"
            );

            System.out.println(
                "===================================="
            );

            System.out.println(
                "1. Seleccionar tipo de lista"
            );

            System.out.println(
                "2. Insertar elemento"
            );

            System.out.println(
                "3. Eliminar elemento"
            );

            System.out.println(
                "4. Buscar elemento"
            );

            System.out.println(
                "5. Mostrar lista"
            );

            System.out.println(
                "6. Mostrar lista inversa"
            );

            System.out.println(
                "7. Ejemplos de tipos de datos"
            );

            System.out.println(
                "8. Sistema de contactos"
            );

            System.out.println(
                "9. Vaciar lista"
            );

            System.out.println(
                "0. Salir"
            );

            System.out.println(
                "===================================="
            );

            System.out.print(
                "Seleccione una opción: "
            );

            option = readInt();

            switch (option) {

                case 1:

                    selectList();

                    break;

                case 2:

                    insertElement();

                    break;

                case 3:

                    deleteElement();

                    break;

                case 4:

                    searchElement();

                    break;

                case 5:

                    displayList();

                    break;

                case 6:

                    displayReverse();

                    break;

                case 7:

                    dataTypeMenu();

                    break;

                case 8:

                    contactManager();

                    break;

                case 9:

                    clearList();

                    break;

                case 0:

                    System.out.println(
                        "\nPrograma finalizado."
                    );

                    break;

                default:

                    System.out.println(
                        "Opción no válida."
                    );
            }

        } while (option != 0);

        scanner.close();
    }

    // ==========================================
    // SELECCIONAR LISTA
    // ==========================================

    public static void selectList() {

        System.out.println(
            "\n--- TIPO DE LISTA ---"
        );

        System.out.println(
            "1. Simplemente enlazada"
        );

        System.out.println(
            "2. Doblemente enlazada"
        );

        System.out.print(
            "Seleccione: "
        );

        int option = readInt();

        if (option == 1) {

            list =
                new LinkedList<Object>(false);

            System.out.println(
                "Lista simplemente enlazada seleccionada."
            );

        } else if (option == 2) {

            list =
                new LinkedList<Object>(true);

            System.out.println(
                "Lista doblemente enlazada seleccionada."
            );

        } else {

            System.out.println(
                "Opción no válida."
            );
        }
    }

    // ==========================================
    // INSERTAR
    // ==========================================

    public static void insertElement() {

        if (!checkList()) {

            return;
        }

        System.out.println(
            "\n--- INSERTAR ELEMENTO ---"
        );

        System.out.println(
            "1. Entero"
        );

        System.out.println(
            "2. Decimal"
        );

        System.out.println(
            "3. Texto"
        );

        System.out.print(
            "Seleccione el tipo: "
        );

        int type = readInt();

        Object data;

        switch (type) {

            case 1:

                System.out.print(
                    "Ingrese un entero: "
                );

                data = readInt();

                break;

            case 2:

                System.out.print(
                    "Ingrese un decimal: "
                );

                data = readDouble();

                break;

            case 3:

                System.out.print(
                    "Ingrese un texto: "
                );

                data =
                    scanner.nextLine();

                break;

            default:

                System.out.println(
                    "Tipo inválido."
                );

                return;
        }

        System.out.println(
            "\n¿Dónde desea insertar?"
        );

        System.out.println(
            "1. Al inicio"
        );

        System.out.println(
            "2. Al final"
        );

        System.out.print(
            "Seleccione: "
        );

        int position = readInt();

        if (position == 1) {

            list.insertAtBeginning(
                data
            );

        } else if (position == 2) {

            list.insertAtEnd(
                data
            );

        } else {

            System.out.println(
                "Posición inválida."
            );

            return;
        }

        System.out.println(
            "Elemento insertado correctamente."
        );
    }

    // ==========================================
    // ELIMINAR
    // ==========================================

    public static void deleteElement() {

        if (!checkList()) {

            return;
        }

        System.out.print(
            "\nIngrese el elemento que desea eliminar: "
        );

        String value =
            scanner.nextLine();

        boolean deleted = false;

        // Intentar como String
        deleted =
            list.delete(value);

        // Intentar como Integer
        if (!deleted) {

            try {

                Integer number =
                    Integer.valueOf(value);

                deleted =
                    list.delete(number);

            } catch (
                NumberFormatException e
            ) {
            }
        }

        // Intentar como Double
        if (!deleted) {

            try {

                Double number =
                    Double.valueOf(value);

                deleted =
                    list.delete(number);

            } catch (
                NumberFormatException e
            ) {
            }
        }

        if (deleted) {

            System.out.println(
                "Elemento eliminado correctamente."
            );

        } else {

            System.out.println(
                "Elemento no encontrado."
            );
        }
    }

    // ==========================================
    // BUSCAR
    // ==========================================

    public static void searchElement() {

        if (!checkList()) {

            return;
        }

        System.out.print(
            "\nIngrese el elemento que desea buscar: "
        );

        String value =
            scanner.nextLine();

        boolean found =
            list.search(value);

        if (!found) {

            try {

                Integer number =
                    Integer.valueOf(value);

                found =
                    list.search(number);

            } catch (
                NumberFormatException e
            ) {
            }
        }

        if (!found) {

            try {

                Double number =
                    Double.valueOf(value);

                found =
                    list.search(number);

            } catch (
                NumberFormatException e
            ) {
            }
        }

        if (found) {

            System.out.println(
                "El elemento SI se encuentra."
            );

        } else {

            System.out.println(
                "El elemento NO se encuentra."
            );
        }
    }

    // ==========================================
    // MOSTRAR
    // ==========================================

    public static void displayList() {

        if (!checkList()) {

            return;
        }

        list.display();
    }

    // ==========================================
    // MOSTRAR INVERSO
    // ==========================================

    public static void displayReverse() {

        if (!checkList()) {

            return;
        }

        list.displayReverse();
    }

    // ==========================================
    // MENÚ DE TIPOS DE DATOS
    // ==========================================

    public static void dataTypeMenu() {

        int option;

        do {

            System.out.println(
                "\n--- TIPOS DE DATOS ---"
            );

            System.out.println(
                "1. Tipos primitivos"
            );

            System.out.println(
                "2. Tipo complejo"
            );

            System.out.println(
                "3. Tipo abstracto"
            );

            System.out.println(
                "0. Regresar"
            );

            System.out.print(
                "Seleccione: "
            );

            option = readInt();

            switch (option) {

                case 1:

                    DataTypeExamples
                        .primitiveExample();

                    break;

                case 2:

                    DataTypeExamples
                        .complexExample();

                    break;

                case 3:

                    DataTypeExamples
                        .abstractExample();

                    break;

                case 0:

                    break;

                default:

                    System.out.println(
                        "Opción no válida."
                    );
            }

        } while (option != 0);
    }

    // ==========================================
    // SISTEMA DE CONTACTOS
    // ==========================================

    public static void contactManager() {

        int option;

        LinkedList<
            DataTypeExamples.Contact
        > contacts =
            new LinkedList<>(true);

        do {

            System.out.println(
                "\n===================================="
            );

            System.out.println(
                "       SISTEMA DE CONTACTOS"
            );

            System.out.println(
                "===================================="
            );

            System.out.println(
                "1. Agregar contacto"
            );

            System.out.println(
                "2. Mostrar contactos"
            );

            System.out.println(
                "3. Buscar contacto"
            );

            System.out.println(
                "4. Eliminar contacto"
            );

            System.out.println(
                "5. Mostrar contactos en reversa"
            );

            System.out.println(
                "0. Regresar"
            );

            System.out.println(
                "===================================="
            );

            System.out.print(
                "Seleccione: "
            );

            option = readInt();

            switch (option) {

                case 1:

                    addContact(
                        contacts
                    );

                    break;

                case 2:

                    displayContacts(
                        contacts
                    );

                    break;

                case 3:

                    searchContact(
                        contacts
                    );

                    break;

                case 4:

                    deleteContact(
                        contacts
                    );

                    break;

                case 5:

                    contacts.displayReverse();

                    break;

                case 0:

                    break;

                default:

                    System.out.println(
                        "Opción no válida."
                    );
            }

        } while (option != 0);
    }

    // ==========================================
    // AGREGAR CONTACTO
    // ==========================================

    public static void addContact(
        LinkedList<
            DataTypeExamples.Contact
        > contacts
    ) {

        System.out.println(
            "\n--- NUEVO CONTACTO ---"
        );

        System.out.print(
            "Nombre: "
        );

        String name =
            scanner.nextLine();

        System.out.print(
            "Dirección: "
        );

        String address =
            scanner.nextLine();

        System.out.print(
            "Número de teléfono: "
        );

        String phone =
            scanner.nextLine();

        System.out.println(
            "\nFecha de nacimiento"
        );

        System.out.print(
            "Día: "
        );

        int day =
            readInt();

        System.out.print(
            "Mes: "
        );

        int month =
            readInt();

        System.out.print(
            "Año: "
        );

        int year =
            readInt();

        DataTypeExamples.Date birthDate =
            new DataTypeExamples.Date(
                day,
                month,
                year
            );

        DataTypeExamples.Contact contact =
            new DataTypeExamples.Contact(
                name,
                address,
                phone,
                birthDate
            );

        contacts.insertAtEnd(
            contact
        );

        System.out.println(
            "\nContacto agregado correctamente."
        );

        System.out.println(
            "Edad calculada: "
            + contact.getAge(
                currentDay,
                currentMonth,
                currentYear
            )
            + " años."
        );
    }

    // ==========================================
    // MOSTRAR CONTACTOS
    // ==========================================

    public static void displayContacts(
        LinkedList<
            DataTypeExamples.Contact
        > contacts
    ) {

        System.out.println(
            "\n--- CONTACTOS ---"
        );

        contacts.displayWithAge(
            currentDay,
            currentMonth,
            currentYear
        );
    }

    // ==========================================
    // BUSCAR CONTACTO
    // ==========================================

    public static void searchContact(
        LinkedList<
            DataTypeExamples.Contact
        > contacts
    ) {

        System.out.print(
            "\nIngrese el teléfono del contacto: "
        );

        String phone =
            scanner.nextLine();

        DataTypeExamples.Date date =
            new DataTypeExamples.Date(
                1,
                1,
                2000
            );

        DataTypeExamples.Contact contact =
            new DataTypeExamples.Contact(
                "",
                "",
                phone,
                date
            );

        if (contacts.search(contact)) {

            System.out.println(
                "El contacto existe en la lista."
            );

        } else {

            System.out.println(
                "El contacto no fue encontrado."
            );
        }
    }

    // ==========================================
    // ELIMINAR CONTACTO
    // ==========================================

    public static void deleteContact(
        LinkedList<
            DataTypeExamples.Contact
        > contacts
    ) {

        System.out.print(
            "\nIngrese el teléfono del contacto: "
        );

        String phone =
            scanner.nextLine();

        DataTypeExamples.Date date =
            new DataTypeExamples.Date(
                1,
                1,
                2000
            );

        DataTypeExamples.Contact contact =
            new DataTypeExamples.Contact(
                "",
                "",
                phone,
                date
            );

        if (contacts.delete(contact)) {

            System.out.println(
                "Contacto eliminado correctamente."
            );

        } else {

            System.out.println(
                "Contacto no encontrado."
            );
        }
    }

    // ==========================================
    // VACIAR LISTA PRINCIPAL
    // ==========================================

    public static void clearList() {

        if (!checkList()) {

            return;
        }

        list.clear();

        System.out.println(
            "Lista vaciada correctamente."
        );
    }

    // ==========================================
    // COMPROBAR LISTA
    // ==========================================

    public static boolean checkList() {

        if (list == null) {

            System.out.println(
                "Primero seleccione un tipo de lista."
            );

            return false;
        }

        return true;
    }

    // ==========================================
    // LEER ENTERO
    // ==========================================

    public static int readInt() {

        while (true) {

            try {

                return Integer.parseInt(
                    scanner.nextLine()
                );

            } catch (
                NumberFormatException e
            ) {

                System.out.print(
                    "Ingrese un número entero válido: "
                );
            }
        }
    }

    // ==========================================
    // LEER DECIMAL
    // ==========================================

    public static double readDouble() {

        while (true) {

            try {

                return Double.parseDouble(
                    scanner.nextLine()
                );

            } catch (
                NumberFormatException e
            ) {

                System.out.print(
                    "Ingrese un número decimal válido: "
                );
            }
        }
    }
}