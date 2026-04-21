package estructuras.modelo.estructuras;

import estructuras.modelo.EquipoClasificacion;

public class ArbolClasificacion {
    private NodoClasificacion raiz;
    private String[] equipos;
    private int[] victorias;
    private int cantidad;

    public ArbolClasificacion() {
        equipos = new String[100];
        victorias = new int[100];
        cantidad = 0;
    }

    public void registrarEquipo(String equipo) {
        if (equipo == null || equipo.trim().equals("")) return;
        if (buscarIndice(equipo) != -1) return;
        if (cantidad < equipos.length) {
            equipos[cantidad] = equipo.trim();
            victorias[cantidad] = 0;
            cantidad++;
            reconstruirArbol();
        }
    }

    public void sumarVictoria(String equipo) {
        registrarEquipo(equipo);
        int indice = buscarIndice(equipo);
        if (indice != -1) {
            victorias[indice]++;
            reconstruirArbol();
        }
    }

    private int buscarIndice(String equipo) {
        if (equipo == null) return -1;
        for (int i = 0; i < cantidad; i++) {
            if (equipos[i].equalsIgnoreCase(equipo.trim())) return i;
        }
        return -1;
    }

    private void reconstruirArbol() {
        raiz = null;
        for (int i = 0; i < cantidad; i++) {
            raiz = insertar(raiz, new EquipoClasificacion(equipos[i], victorias[i]));
        }
    }

    private NodoClasificacion insertar(NodoClasificacion nodo, EquipoClasificacion equipo) {
        if (nodo == null) return new NodoClasificacion(equipo);
        if (equipo.getVictorias() < nodo.getEquipo().getVictorias()
                || (equipo.getVictorias() == nodo.getEquipo().getVictorias()
                && equipo.getEquipo().compareToIgnoreCase(nodo.getEquipo().getEquipo()) < 0)) {
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), equipo));
        } else {
            nodo.setDerecho(insertar(nodo.getDerecho(), equipo));
        }
        return nodo;
    }

    public String obtenerTextoDescendente() {
        if (raiz == null) return "No hay equipos en la clasificación.";
        return recorrerDescendente(raiz);
    }

    private String recorrerDescendente(NodoClasificacion nodo) {
        if (nodo == null) return "";
        String texto = "";
        texto += recorrerDescendente(nodo.getDerecho());
        texto += nodo.getEquipo().toString() + "\n";
        texto += recorrerDescendente(nodo.getIzquierdo());
        return texto;
    }
}
