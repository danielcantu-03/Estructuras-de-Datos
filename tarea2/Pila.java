package tarea2;

public class Pila<T> {

    private LinkedList<T> lista;

    public Pila() {
        lista = new LinkedList<>();
    }

    // Agregar elemento a la pila
    public void push(T data) {

        lista.addLast(data);
    }

    // Sacar elemento del tope
    public T pop() {

        if (lista.isEmpty()) {
            return null;
        }

        return lista.removeLast();
    }

    // Ver el elemento del tope sin eliminarlo
    public T peek() {

        if (lista.isEmpty()) {
            return null;
        }

        return lista.getLast();
    }

    // Verificar si está vacía
    public boolean isEmpty() {

        return lista.isEmpty();
    }

    // Obtener tamaño
    public int size() {

        return lista.size();
    }

    // Mostrar pila
    public void display() {

        lista.display();
    }
}