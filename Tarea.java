package avance.de.proyecto;

public class Tarea {

    private int id;
    private String descripcion;
    private String departamento;
    private int urgencia;

    public Tarea(int id, String descripcion, String departamento, int urgencia) {
        this.id = id;
        this.descripcion = descripcion;
        this.departamento = departamento;
        this.urgencia = urgencia;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public void mostrar() {
        System.out.println(
            "ID: " + id +
            " | Descripcion: " + descripcion +
            " | Departamento: " + departamento +
            " | Urgencia: " + urgencia
        );
    }
}
