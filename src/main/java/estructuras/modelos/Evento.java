package estructuras.modelos;

public class Evento {
    private int id;
    private String nombre;
    private String fecha;
    private String lugar;

    public Evento(int id, String nombre, String fecha, String lugar) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getLugar() {
        return lugar;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Fecha: " + fecha + " | Lugar: " + lugar;
    }
}