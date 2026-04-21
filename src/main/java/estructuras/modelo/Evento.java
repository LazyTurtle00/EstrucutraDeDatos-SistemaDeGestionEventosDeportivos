package estructuras.modelo;

import estructuras.modelo.estructuras.ColaPartidos;
import estructuras.modelo.estructuras.ListaDobleParticipantes;
import estructuras.modelo.estructuras.PilaResultados;

public class Evento {
    private int id;
    private String nombre;
    private String fecha;
    private String ubicacion;
    private ListaDobleParticipantes participantes;
    private ColaPartidos partidosProgramados;
    private PilaResultados resultados;

    public Evento(int id, String nombre, String fecha, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.participantes = new ListaDobleParticipantes();
        this.partidosProgramados = new ColaPartidos();
        this.resultados = new PilaResultados();
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getFecha() { return fecha; }
    public String getUbicacion() { return ubicacion; }
    public ListaDobleParticipantes getParticipantes() { return participantes; }
    public ColaPartidos getPartidosProgramados() { return partidosProgramados; }
    public PilaResultados getResultados() { return resultados; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Fecha: " + fecha + " | Ubicación: " + ubicacion;
    }
}
