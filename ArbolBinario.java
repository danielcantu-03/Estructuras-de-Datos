package tarea4;

public class ArbolBinario {

    private Nodo raiz;
    private int comparacionesBusqueda;

    public ArbolBinario() {
        raiz = null;
    }

    // INSERTAR

    public void insertar(Empleado empleado) {
        raiz = insertarRecursivo(raiz, empleado);
    }

    private Nodo insertarRecursivo(Nodo nodo, Empleado empleado) {

        if (nodo == null) {
            return new Nodo(empleado);
        }

        if (empleado.getId() < nodo.empleado.getId()) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, empleado);
        } else if (empleado.getId() > nodo.empleado.getId()) {
            nodo.derecho = insertarRecursivo(nodo.derecho, empleado);
        } else {
            System.out.println("El ID " + empleado.getId()
                    + " ya existe en el árbol.");
        }

        return nodo;
    }

    // BUSCAR

    public Empleado buscar(int id) {

        comparacionesBusqueda = 0;

        Nodo actual = raiz;

        while (actual != null) {

            comparacionesBusqueda++;

            if (id == actual.empleado.getId()) {
                return actual.empleado;
            }

            if (id < actual.empleado.getId()) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }

        return null;
    }

    public int getComparacionesBusqueda() {
        return comparacionesBusqueda;
    }

    // ELIMINAR

    public void eliminar(int id) {
        raiz = eliminarRecursivo(raiz, id);
    }

    private Nodo eliminarRecursivo(Nodo nodo, int id) {

        if (nodo == null) {
            return null;
        }

        if (id < nodo.empleado.getId()) {

            nodo.izquierdo =
                    eliminarRecursivo(nodo.izquierdo, id);

        } else if (id > nodo.empleado.getId()) {

            nodo.derecho =
                    eliminarRecursivo(nodo.derecho, id);

        } else {

            // Caso 1: no tiene hijos
            if (nodo.izquierdo == null && nodo.derecho == null) {
                return null;
            }

            // Caso 2: solamente tiene hijo derecho
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            }

            // Caso 3: solamente tiene hijo izquierdo
            if (nodo.derecho == null) {
                return nodo.izquierdo;
            }

            // Caso 4: tiene dos hijos
            Nodo sucesor = encontrarMinimo(nodo.derecho);

            nodo.empleado = sucesor.empleado;

            nodo.derecho =
                    eliminarRecursivo(
                            nodo.derecho,
                            sucesor.empleado.getId()
                    );
        }

        return nodo;
    }

    private Nodo encontrarMinimo(Nodo nodo) {

        Nodo actual = nodo;

        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }

        return actual;
    }

    // PREORDEN

    public void preorden() {
        preordenRecursivo(raiz);
        System.out.println();
    }

    private void preordenRecursivo(Nodo nodo) {

        if (nodo != null) {

            System.out.println(nodo.empleado);

            preordenRecursivo(nodo.izquierdo);

            preordenRecursivo(nodo.derecho);
        }
    }

    // INORDEN

    public void inorden() {
        inordenRecursivo(raiz);
        System.out.println();
    }

    private void inordenRecursivo(Nodo nodo) {

        if (nodo != null) {

            inordenRecursivo(nodo.izquierdo);

            System.out.println(nodo.empleado);

            inordenRecursivo(nodo.derecho);
        }
    }

    // POSTORDEN

    public void postorden() {
        postordenRecursivo(raiz);
        System.out.println();
    }

    private void postordenRecursivo(Nodo nodo) {

        if (nodo != null) {

            postordenRecursivo(nodo.izquierdo);

            postordenRecursivo(nodo.derecho);

            System.out.println(nodo.empleado);
        }
    }
}
