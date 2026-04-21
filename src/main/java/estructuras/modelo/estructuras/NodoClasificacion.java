package estructuras.modelo.estructuras;

import estructuras.modelo.EquipoClasificacion;

public class NodoClasificacion {
    private EquipoClasificacion equipo;
    private NodoClasificacion izquierdo;
    private NodoClasificacion derecho;

    public NodoClasificacion(EquipoClasificacion equipo) { this.equipo = equipo; }
    public EquipoClasificacion getEquipo() { return equipo; }
    public NodoClasificacion getIzquierdo() { return izquierdo; }
    public void setIzquierdo(NodoClasificacion izquierdo) { this.izquierdo = izquierdo; }
    public NodoClasificacion getDerecho() { return derecho; }
    public void setDerecho(NodoClasificacion derecho) { this.derecho = derecho; }
}
