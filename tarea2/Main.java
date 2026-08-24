package tarea2;

import java.io.IOException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        int opcion;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("   CONVERSOR DE INFIJA A POSTFIJA");
            System.out.println("======================================");

            System.out.println();
            System.out.println("Operadores permitidos:");
            System.out.println("^  *  /  +  -");
            System.out.println("Tambien se permiten parentesis.");
            System.out.println();

            System.out.println("Ejemplo de expresion:");
            System.out.println("2 + 3 * 4");
            System.out.println();

            System.out.println("Ingrese la expresion infija:");

            char[] expresion = leerExpresion();

            // Verificar que la expresión sea válida
            if (!esExpresionValida(expresion)) {

                System.out.println();
                System.out.println(
                        "La expresion contiene caracteres no permitidos."
                );

            } else {

                // Convertir la expresión
                LinkedList<Character> postfija =
                        convertirPostfija(expresion);

                System.out.println();
                System.out.println("Expresion postfija:");

                mostrarPostfija(postfija);
            }

            // Preguntar si desea realizar otra prueba
            System.out.println();
            System.out.println("======================================");
            System.out.println("       ¿QUE DESEA HACER?");
            System.out.println("======================================");
            System.out.println("1. Convertir otra expresion");
            System.out.println("2. Salir");
            System.out.println("======================================");
            System.out.print("Seleccione una opcion: ");

            opcion = leerOpcion();

            System.out.println();

        } while (opcion == 1);

        System.out.println("Programa finalizado.");
    }

    // =====================================================
    // LEER EXPRESION COMO CHAR[]
    // =====================================================

    public static char[] leerExpresion()
            throws IOException {

        char[] expresion = new char[100];

        int posicion = 0;
        int caracter;

        while ((caracter = System.in.read()) != -1) {

            if (caracter == '\n') {
                break;
            }

            if (caracter == '\r') {
                continue;
            }

            // Ignorar espacios
            if (caracter == ' ') {
                continue;
            }

            if (posicion < expresion.length) {

                expresion[posicion] =
                        (char) caracter;

                posicion++;
            }
        }

        // Crear un char[] del tamaño exacto
        char[] resultado =
                new char[posicion];

        for (int i = 0; i < posicion; i++) {

            resultado[i] =
                    expresion[i];
        }

        return resultado;
    }

    // =====================================================
    // LEER OPCION DEL MENU
    // =====================================================

    public static int leerOpcion()
        throws IOException {

    int caracter;
    int opcion = 0;

    while ((caracter = System.in.read()) != -1) {

        if (caracter == '1' && opcion == 0) {

            opcion = 1;
        }

        else if (caracter == '2' && opcion == 0) {

            opcion = 2;
        }

        // Cuando encuentra Enter,
        // termina de leer la opción.
        if (caracter == '\n') {

            break;
        }
    }

    if (opcion == 1) {

        return 1;
    }

    return 2;
}

    // =====================================================
    // CONVERSIÓN DE INFIJA A POSTFIJA
    // =====================================================

    public static LinkedList<Character> convertirPostfija(
            char[] expresion) {

        Pila<Character> pila =
                new Pila<>();

        LinkedList<Character> postfija =
                new LinkedList<>();

        for (int i = 0;
                i < expresion.length;
                i++) {

            char caracter =
                    expresion[i];

            // ---------------------------------------------
            // OPERANDO
            // ---------------------------------------------

            if (esOperando(caracter)) {

                postfija.addLast(caracter);
            }

            // ---------------------------------------------
            // PARENTESIS ABIERTO
            // ---------------------------------------------

            else if (caracter == '(') {

                pila.push(caracter);
            }

            // ---------------------------------------------
            // PARENTESIS CERRADO
            // ---------------------------------------------

            else if (caracter == ')') {

                while (
                        !pila.isEmpty()
                        && pila.peek() != '('
                ) {

                    postfija.addLast(
                            pila.pop()
                    );
                }

                // Sacar '(' de la pila
                // pero no agregarlo a postfija
                if (
                        !pila.isEmpty()
                        && pila.peek() == '('
                ) {

                    pila.pop();
                }
            }

            // ---------------------------------------------
            // OPERADOR
            // ---------------------------------------------

            else if (esOperador(caracter)) {

                while (
                        !pila.isEmpty()
                        && pila.peek() != '('
                        && pesoPila(pila.peek())
                        >= pesoExpresion(caracter)
                ) {

                    postfija.addLast(
                            pila.pop()
                    );
                }

                pila.push(caracter);
            }
        }

        // ---------------------------------------------
        // VACIAR LA PILA
        // ---------------------------------------------

        while (!pila.isEmpty()) {

            postfija.addLast(
                    pila.pop()
            );
        }

        return postfija;
    }

    // =====================================================
    // VERIFICAR OPERANDO
    // =====================================================

    public static boolean esOperando(
            char caracter) {

        return caracter >= '0'
                && caracter <= '9';
    }

    // =====================================================
    // VERIFICAR OPERADOR
    // =====================================================

    public static boolean esOperador(
            char caracter) {

        return caracter == '^'
                || caracter == '*'
                || caracter == '/'
                || caracter == '+'
                || caracter == '-';
    }

    // =====================================================
    // PESO EN EXPRESION
    // =====================================================

    public static int pesoExpresion(
            char operador) {

        switch (operador) {

            case '^':
                return 4;

            case '*':
            case '/':
                return 2;

            case '+':
            case '-':
                return 1;

            case '(':
                return 5;

            default:
                return -1;
        }
    }

    // =====================================================
    // PESO EN PILA
    // =====================================================

    public static int pesoPila(
            char operador) {

        switch (operador) {

            case '^':
                return 3;

            case '*':
            case '/':
                return 2;

            case '+':
            case '-':
                return 1;

            case '(':
                return 0;

            default:
                return -1;
        }
    }

    // =====================================================
    // VALIDAR EXPRESION
    // =====================================================

    public static boolean esExpresionValida(
            char[] expresion) {

        if (expresion.length == 0) {

            return false;
        }

        for (int i = 0;
                i < expresion.length;
                i++) {

            char caracter =
                    expresion[i];

            if (
                    !esOperando(caracter)
                    && !esOperador(caracter)
                    && caracter != '('
                    && caracter != ')'
            ) {

                return false;
            }
        }

        return true;
    }

    // =====================================================
    // MOSTRAR POSTFIJA
    // =====================================================

    public static void mostrarPostfija(
            LinkedList<Character> postfija) {

        Node<Character> actual =
                postfija.getHead();

        while (actual != null) {

            System.out.print(
                    actual.data
            );

            if (actual.next != null) {

                System.out.print(" ");
            }

            actual = actual.next;
        }

        System.out.println();
    }
}