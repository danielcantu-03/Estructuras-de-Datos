package avance.de.proyecto;

public class Pila<T> {

    private Nodo<T> cima;

    public Pila() {
        cima = null;
    }

    public void push(T dato) {

        Nodo<T> nuevo = new Nodo<>(dato);

        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public T pop() {

        if (cima == null) {
            return null;
        }

        T dato = cima.dato;
        cima = cima.siguiente;

        return dato;
    }

    public T peek() {

        if (cima == null) {
            return null;
        }

        return cima.dato;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public void mostrar() {

        if (cima == null) {
            System.out.println("La pila esta vacia.");
            return;
        }

        Nodo<T> actual = cima;

        while (actual != null) {

            Tarea tarea = (Tarea) actual.dato;
            tarea.mostrar();

            actual = actual.siguiente;
        }
    }
}
