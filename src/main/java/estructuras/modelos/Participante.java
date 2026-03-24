package estructuras.modelos;

public class Participante {
    private int id;
    private String nombreEquipo;
    private String capitan;
    private int cantidadJugadores;

    public Participante(int id, String nombreEquipo, String capitan, int cantidadJugadores) {
        this.id = id;
        this.nombreEquipo = nombreEquipo;
        this.capitan = capitan;
        this.cantidadJugadores = cantidadJugadores;
    }

    public int getId() {
        return id;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public String getCapitan() {
        return capitan;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Equipo: " + nombreEquipo
                + " | Capitán: " + capitan
                + " | Jugadores: " + cantidadJugadores;
    }
}