package estructuras.modelo.estructuras;

import estructuras.modelo.Partido;

public class ColaPartidos {
    private NodoPartido frente;
    private NodoPartido fin;
    private int cantidad;

    public void encolar(Partido partido) {
        NodoPartido nuevo = new NodoPartido(partido);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        cantidad++;
    }

    public Partido desencolar() {
        if (frente == null) return null;
        Partido partido = frente.getPartido();
        frente = frente.getSiguiente();
        if (frente == null) fin = null;
        cantidad--;
        return partido;
    }

    public Partido buscarPorId(int id) {
        NodoPartido actual = frente;
        while (actual != null) {
            if (actual.getPartido().getId() == id) return actual.getPartido();
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Partido obtenerPartidoEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= cantidad) return null;
        NodoPartido actual = frente;
        int contador = 0;
        while (actual != null) {
            if (contador == posicion) return actual.getPartido();
            contador++;
            actual = actual.getSiguiente();
        }
        return null;
    }

    public String obtenerTexto() {
        if (frente == null) return "No hay partidos programados para este evento.";
        String texto = "";
        NodoPartido actual = frente;
        while (actual != null) {
            texto += actual.getPartido().toString() + "\n";
            actual = actual.getSiguiente();
        }
        return texto;
    }

    public int obtenerCantidad() { return cantidad; }
}
