package avance.de.proyecto;

public class ListaEnlazada<T> {

    private Nodo<T> cabeza;

    public ListaEnlazada() {
        cabeza = null;
    }

    public void insert(T dato) {

        Nodo<T> nuevo = new Nodo<>(dato);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {

            Nodo<T> actual = cabeza;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;
        }
    }

    public boolean delete(Tarea tarea) {

        Nodo<T> actual = cabeza;
        Nodo<T> anterior = null;

        while (actual != null) {

            Tarea t = (Tarea) actual.dato;

            if (t.getId() == tarea.getId()) {

                if (anterior == null) {
                    cabeza = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }

                return true;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        return false;
    }

    public T find(int id) {

        Nodo<T> actual = cabeza;

        while (actual != null) {

            Tarea tarea = (Tarea) actual.dato;

            if (tarea.getId() == id) {
                return actual.dato;
            }

            actual = actual.siguiente;
        }

        return null;
    }

    public void mostrar() {

        if (cabeza == null) {
            System.out.println("No hay tareas en la lista.");
            return;
        }

        Nodo<T> actual = cabeza;

        while (actual != null) {

            Tarea tarea = (Tarea) actual.dato;
            tarea.mostrar();

            actual = actual.siguiente;
        }
    }
}
