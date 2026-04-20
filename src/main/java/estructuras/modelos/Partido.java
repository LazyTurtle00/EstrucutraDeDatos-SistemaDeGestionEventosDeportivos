package estructuras.modelos;

public class Partido {
    private int id;
    private String nombreEvento;
    private String equipoUno;
    private String equipoDos;
    private String fecha;
    private String hora;

    public Partido(int id, String nombreEvento, String equipoUno, String equipoDos, String fecha, String hora) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.equipoUno = equipoUno;
        this.equipoDos = equipoDos;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public String getEquipoUno() {
        return equipoUno;
    }

    public String getEquipoDos() {
        return equipoDos;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Evento: " + nombreEvento
                + " | " + equipoUno + " vs " + equipoDos
                + " | Fecha: " + fecha
                + " | Hora: " + hora;
    }
}