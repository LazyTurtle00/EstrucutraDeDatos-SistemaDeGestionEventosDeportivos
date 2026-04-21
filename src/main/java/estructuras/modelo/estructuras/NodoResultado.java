package estructuras.modelo.estructuras;

import estructuras.modelo.Resultado;

public class NodoResultado {
    private Resultado resultado;
    private NodoResultado siguiente;

    public NodoResultado(Resultado resultado) { this.resultado = resultado; }
    public Resultado getResultado() { return resultado; }
    public void setResultado(Resultado resultado) { this.resultado = resultado; }
    public NodoResultado getSiguiente() { return siguiente; }
    public void setSiguiente(NodoResultado siguiente) { this.siguiente = siguiente; }
}
