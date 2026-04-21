package estructuras.modelo.estructuras;

import estructuras.modelo.Evento;

public class NodoEvento {
    private Evento evento;
    private NodoEvento siguiente;

    public NodoEvento(Evento evento) {
        this.evento = evento;
    }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }
    public NodoEvento getSiguiente() { return siguiente; }
    public void setSiguiente(NodoEvento siguiente) { this.siguiente = siguiente; }
}
