package estructuras.modelo.estructuras;

import estructuras.modelo.Resultado;

public class PilaResultados {
    private NodoResultado cima;
    private int cantidad;

    public void apilar(Resultado resultado) {
        NodoResultado nuevo = new NodoResultado(resultado);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        cantidad++;
    }

    public Resultado desapilar() {
        if (cima == null) return null;
        Resultado resultado = cima.getResultado();
        cima = cima.getSiguiente();
        cantidad--;
        return resultado;
    }

    public Resultado cima() {
        return cima == null ? null : cima.getResultado();
    }

    public String obtenerTexto() {
        if (cima == null) return "No hay resultados registrados para este evento.";
        String texto = "";
        NodoResultado actual = cima;
        while (actual != null) {
            texto += actual.getResultado().toString() + "\n";
            actual = actual.getSiguiente();
        }
        return texto;
    }

    public int obtenerCantidad() { return cantidad; }

    public Resultado obtenerResultadoEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= cantidad) return null;
        NodoResultado actual = cima;
        int contador = 0;
        while (actual != null) {
            if (contador == posicion) return actual.getResultado();
            contador++;
            actual = actual.getSiguiente();
        }
        return null;
    }
}
