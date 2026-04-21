package estructuras.modelo.estructuras;

public class GrafoEnfrentamientos {
    private String[] equipos;
    private int[][] matrizAdyacencia;
    private int cantidadEquipos;

    public GrafoEnfrentamientos() {
        equipos = new String[100];
        matrizAdyacencia = new int[100][100];
        cantidadEquipos = 0;
    }

    public void agregarEquipo(String equipo) {
        if (equipo == null || equipo.trim().equals("") || obtenerIndice(equipo) != -1) return;
        if (cantidadEquipos < equipos.length) {
            equipos[cantidadEquipos] = equipo.trim();
            cantidadEquipos++;
        }
    }

    public void agregarEnfrentamiento(String equipoA, String equipoB) {
        if (equipoA == null || equipoB == null || equipoA.equalsIgnoreCase(equipoB)) return;
        agregarEquipo(equipoA);
        agregarEquipo(equipoB);
        int indiceA = obtenerIndice(equipoA);
        int indiceB = obtenerIndice(equipoB);
        if (indiceA != -1 && indiceB != -1) {
            matrizAdyacencia[indiceA][indiceB] = 1;
            matrizAdyacencia[indiceB][indiceA] = 1;
        }
    }

    public boolean existeEnfrentamiento(String equipoA, String equipoB) {
        int indiceA = obtenerIndice(equipoA);
        int indiceB = obtenerIndice(equipoB);
        return indiceA != -1 && indiceB != -1 && matrizAdyacencia[indiceA][indiceB] == 1;
    }

    private int obtenerIndice(String equipo) {
        if (equipo == null) return -1;
        for (int i = 0; i < cantidadEquipos; i++) {
            if (equipos[i].equalsIgnoreCase(equipo.trim())) return i;
        }
        return -1;
    }

    public String obtenerTexto() {
        if (cantidadEquipos == 0) return "No hay enfrentamientos registrados.";
        String texto = "Grafo no dirigido de enfrentamientos:\n";
        for (int i = 0; i < cantidadEquipos; i++) {
            texto += equipos[i] + " -> ";
            boolean tieneRelacion = false;
            for (int j = 0; j < cantidadEquipos; j++) {
                if (matrizAdyacencia[i][j] == 1) {
                    texto += equipos[j] + "  ";
                    tieneRelacion = true;
                }
            }
            if (!tieneRelacion) texto += "Sin enfrentamientos";
            texto += "\n";
        }
        return texto;
    }
}
