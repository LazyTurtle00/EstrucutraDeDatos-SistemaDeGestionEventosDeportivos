package estructuras.modelo;

public class Participante {
    private int id;
    private String nombre;
    private int edad;
    private String equipo;

    public Participante(int id, String nombre, int edad, String equipo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.equipo = equipo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getEquipo() { return equipo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setEquipo(String equipo) { this.equipo = equipo; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Edad: " + edad + " | Equipo: " + equipo;
    }
}
