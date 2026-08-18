package tarea1;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    private boolean doublyLinked;

    public LinkedList(boolean doublyLinked) 
    {

        this.doublyLinked = doublyLinked;

        head = null;
        tail = null;
    }

    // ==========================================
    // VERIFICAR SI ESTÁ VACÍA
    // ==========================================

    public boolean isEmpty() 
    {

        return head == null;
    }

    // ==========================================
    // INSERTAR AL INICIO
    // ==========================================

    public void insertAtBeginning(T data) 
    {

        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {

            head = newNode;
            tail = newNode;

            return;
        }

        newNode.next = head;

        if (doublyLinked) 
        {

            head.prev = newNode;
        }

        head = newNode;
    }

    // ==========================================
    // INSERTAR AL FINAL
    // ==========================================

    public void insertAtEnd(T data) 
    {

        Node<T> newNode = new Node<>(data);

        if (isEmpty()) 
        {

            head = newNode;
            tail = newNode;

            return;
        }

        tail.next = newNode;

        if (doublyLinked) 
        {

            newNode.prev = tail;
        }

        tail = newNode;
    }

    // ==========================================
    // BUSCAR
    // ==========================================

    public boolean search(T data) 
    {

        Node<T> current = head;

        while (current != null) 
        {

            if (current.data.equals(data)) 
            {

                return true;
            }

            current = current.next;
        }

        return false;
    }

    // ==========================================
    // ELIMINAR
    // ==========================================

    public boolean delete(T data) {

        if (isEmpty()) 
        {

            return false;
        }

        Node<T> current = head;

        // El elemento está en el primer nodo
        if (current.data.equals(data)) 
        {

            head = current.next;

            if (doublyLinked && head != null) 
            {

                head.prev = null;
            }

            if (head == null) 
            {

                tail = null;
            }

            return true;
        }

        // Buscar el nodo
        while (current != null && !current.data.equals(data)) 
        {

            current = current.next;
        }

        // No encontrado
        if (current == null) 
        {

            return false;
        }

        // LISTA DOBLE
        if (doublyLinked) 
        {

            if (current.prev != null) 
            {

                current.prev.next = current.next;
            }

            if (current.next != null) 
            {

                current.next.prev = current.prev;
            }

        }

        // LISTA SIMPLE
        else 
        {

            Node<T> previous = head;

            while (previous.next != current) 
            {

                previous = previous.next;
            }

            previous.next = current.next;
        }

        // Actualizar tail
        if (current == tail) 
        {

            if (doublyLinked) 
            {

                tail = current.prev;

            } 
            else 
            {

                Node<T> temp = head;

                while (temp.next != current) 
                {

                    temp = temp.next;
                }

                tail = temp;
            }
        }

        return true;
    }

    // ==========================================
    // MOSTRAR LISTA
    // ==========================================

    public void display() 
    {

        if (isEmpty()) 
        {

            System.out.println("La lista está vacía.");

            return;
        }

        Node<T> current = head;

        System.out.println("\nElementos de la lista:");

        while (current != null) 
        {

            System.out.print(current.data);

            if (current.next != null) 
            {

                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    // ==========================================
    // MOSTRAR INVERSA
    // ==========================================

    public void displayReverse() 
    {

        if (!doublyLinked) 
        {

            System.out.println("Esta operación solamente funciona " + "con listas doblemente enlazadas.");

            return;
        }

        if (isEmpty()) 
        {

            System.out.println("La lista está vacía.");

            return;
        }

        Node<T> current = tail;

        System.out.println("\nLista en orden inverso:");

        while (current != null) 
        {

            System.out.print(current.data);

            if (current.prev != null) 
            {

                System.out.print(" <- ");
            }

            current = current.prev;
        }

        System.out.println();
    }

    // ==========================================
    // VACIAR
    // ==========================================

    public void clear() 
    {

        head = null;
        tail = null;
    }
    
    public void displayWithAge(
    int currentDay,
    int currentMonth,
    int currentYear
) {

    if (isEmpty()) {

        System.out.println(
            "La lista está vacía."
        );

        return;
    }

    Node<T> current = head;

    while (current != null) {

        if (
            current.data instanceof
            DataTypeExamples.Contact
        ) {

            DataTypeExamples.Contact contact =
                (DataTypeExamples.Contact)
                current.data;

            System.out.println(
                contact.getInformation(
                    currentDay,
                    currentMonth,
                    currentYear
                )
            );

        } else {

            System.out.println(
                current.data
            );
        }

        current = current.next;
    }
}
}

