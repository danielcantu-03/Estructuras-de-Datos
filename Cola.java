package avance.de.proyecto;

public class Cola<T> {

    private Nodo<T> frente;
    private Nodo<T> fin;

    public Cola() {
        frente = null;
        fin = null;
    }

    public void enqueue(T dato) {

        Nodo<T> nuevo = new Nodo<>(dato);

        if (fin == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public T dequeue() {

        if (frente == null) {
            return null;
        }

        T dato = frente.dato;

        frente = frente.siguiente;

        if (frente == null) {
            fin = null;
        }

        return dato;
    }

    public T front() {

        if (frente == null) {
            return null;
        }

        return frente.dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void mostrar() {

        if (frente == null) {
            System.out.println("La cola esta vacia.");
            return;
        }

        Nodo<T> actual = frente;

        while (actual != null) {

            Tarea tarea = (Tarea) actual.dato;
            tarea.mostrar();

            actual = actual.siguiente;
        }
    }
}
