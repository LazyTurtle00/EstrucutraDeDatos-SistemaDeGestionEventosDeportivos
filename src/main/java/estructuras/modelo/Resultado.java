package estructuras.modelo;

public class Resultado {
    private int idPartido;
    private String equipoUno;
    private String equipoDos;
    private int puntosEquipoUno;
    private int puntosEquipoDos;

    public Resultado(int idPartido, String equipoUno, String equipoDos, int puntosEquipoUno, int puntosEquipoDos) {
        this.idPartido = idPartido;
        this.equipoUno = equipoUno;
        this.equipoDos = equipoDos;
        this.puntosEquipoUno = puntosEquipoUno;
        this.puntosEquipoDos = puntosEquipoDos;
    }

    public int getIdPartido() { return idPartido; }
    public String getEquipoUno() { return equipoUno; }
    public String getEquipoDos() { return equipoDos; }
    public int getPuntosEquipoUno() { return puntosEquipoUno; }
    public int getPuntosEquipoDos() { return puntosEquipoDos; }

    @Override
    public String toString() {
        return "Partido ID: " + idPartido + " | " + equipoUno + " " + puntosEquipoUno + " - " + puntosEquipoDos + " " + equipoDos;
    }
}
