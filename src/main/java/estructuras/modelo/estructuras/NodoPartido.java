package estructuras.modelo.estructuras;

import estructuras.modelo.Partido;

public class NodoPartido {
    private Partido partido;
    private NodoPartido siguiente;

    public NodoPartido(Partido partido) { this.partido = partido; }
    public Partido getPartido() { return partido; }
    public void setPartido(Partido partido) { this.partido = partido; }
    public NodoPartido getSiguiente() { return siguiente; }
    public void setSiguiente(NodoPartido siguiente) { this.siguiente = siguiente; }
}
