package tarea4;

public class Nodo {

    Empleado empleado;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(Empleado empleado) {
        this.empleado = empleado;
        this.izquierdo = null;
        this.derecho = null;
    }
}
