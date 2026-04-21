package estructuras.modelo;

public class EquipoClasificacion {
    private String equipo;
    private int victorias;

    public EquipoClasificacion(String equipo, int victorias) {
        this.equipo = equipo;
        this.victorias = victorias;
    }

    public String getEquipo() { return equipo; }
    public int getVictorias() { return victorias; }
    public void setVictorias(int victorias) { this.victorias = victorias; }

    @Override
    public String toString() {
        return equipo + " | Victorias: " + victorias;
    }
}
