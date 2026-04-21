package estructuras.modelo;

public class Partido {
    private int id;
    private String equipoUno;
    private String equipoDos;
    private String fecha;
    private String hora;
    private boolean jugado;

    public Partido(int id, String equipoUno, String equipoDos, String fecha, String hora) {
        this.id = id;
        this.equipoUno = equipoUno;
        this.equipoDos = equipoDos;
        this.fecha = fecha;
        this.hora = hora;
        this.jugado = false;
    }

    public int getId() { return id; }
    public String getEquipoUno() { return equipoUno; }
    public String getEquipoDos() { return equipoDos; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public boolean isJugado() { return jugado; }
    public void setJugado(boolean jugado) { this.jugado = jugado; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + equipoUno + " vs " + equipoDos + " | Fecha: " + fecha + " | Hora: " + hora + (jugado ? " | Jugado" : " | Pendiente");
    }
}
