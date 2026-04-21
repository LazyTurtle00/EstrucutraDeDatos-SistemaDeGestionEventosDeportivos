package estructuras.modelo.estructuras;

import estructuras.modelo.Participante;

public class NodoParticipante {
    private Participante participante;
    private NodoParticipante anterior;
    private NodoParticipante siguiente;

    public NodoParticipante(Participante participante) { this.participante = participante; }
    public Participante getParticipante() { return participante; }
    public void setParticipante(Participante participante) { this.participante = participante; }
    public NodoParticipante getAnterior() { return anterior; }
    public void setAnterior(NodoParticipante anterior) { this.anterior = anterior; }
    public NodoParticipante getSiguiente() { return siguiente; }
    public void setSiguiente(NodoParticipante siguiente) { this.siguiente = siguiente; }
}
