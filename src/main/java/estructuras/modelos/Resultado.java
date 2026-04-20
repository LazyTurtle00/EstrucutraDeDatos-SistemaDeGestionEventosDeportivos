package estructuras.modelos;

public class Resultado {
    private int idPartido;
    private String nombreEvento;
    private String equipoUno;
    private String equipoDos;
    private int puntosEquipoUno;
    private int puntosEquipoDos;

    public Resultado(int idPartido, String nombreEvento, String equipoUno, String equipoDos,
                     int puntosEquipoUno, int puntosEquipoDos) {
        this.idPartido = idPartido;
        this.nombreEvento = nombreEvento;
        this.equipoUno = equipoUno;
        this.equipoDos = equipoDos;
        this.puntosEquipoUno = puntosEquipoUno;
        this.puntosEquipoDos = puntosEquipoDos;
    }

    public int getIdPartido() {
        return idPartido;
    }

    @Override
    public String toString() {
        return "Partido ID: " + idPartido
                + " | Evento: " + nombreEvento
                + " | " + equipoUno + " " + puntosEquipoUno
                + " - " + puntosEquipoDos + " " + equipoDos;
    }
}