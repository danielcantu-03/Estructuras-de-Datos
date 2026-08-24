package tarea2;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Verifica si la lista está vacía
    public boolean isEmpty() {
        return head == null;
    }

    // Regresa el tamaño de la lista
    public int size() {
        return size;
    }

    // Agrega un elemento al inicio
    public void addFirst(T data) {

        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {

            head = newNode;
            tail = newNode;

        } else {

            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        size++;
    }

    // Agrega un elemento al final
    public void addLast(T data) {

        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {

            head = newNode;
            tail = newNode;

        } else {

            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
    }

    // Elimina el primer elemento
    public T removeFirst() {

        if (isEmpty()) {
            return null;
        }

        T data = head.data;

        if (head == tail) {

            head = null;
            tail = null;

        } else {

            head = head.next;
            head.prev = null;
        }

        size--;

        return data;
    }

    // Elimina el último elemento
    public T removeLast() {

        if (isEmpty()) {
            return null;
        }

        T data = tail.data;

        if (head == tail) {

            head = null;
            tail = null;

        } else {

            tail = tail.prev;
            tail.next = null;
        }

        size--;

        return data;
    }

    // Obtiene el primer elemento
    public T getFirst() {

        if (isEmpty()) {
            return null;
        }

        return head.data;
    }

    // Obtiene el último elemento
    public T getLast() {

        if (isEmpty()) {
            return null;
        }

        return tail.data;
    }

    // Muestra los elementos de la lista
    public void display() {

        Node<T> current = head;

        while (current != null) {

            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    // Vacía la lista
    public void clear() {

        head = null;
        tail = null;
        size = 0;
    }
    
    //Obtener el primer nodo
    public Node<T> getHead() {
        
    return head;
    }
}