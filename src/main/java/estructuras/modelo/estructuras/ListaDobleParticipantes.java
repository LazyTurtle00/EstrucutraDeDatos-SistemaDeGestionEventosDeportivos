package estructuras.modelo.estructuras;

import estructuras.modelo.Participante;

public class ListaDobleParticipantes {
    private NodoParticipante cabeza;
    private NodoParticipante cola;
    private int cantidad;

    public void agregar(Participante participante) {
        NodoParticipante nuevo = new NodoParticipante(participante);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
        cantidad++;
    }

    public Participante buscarPorNombre(String nombre) {
        NodoParticipante actual = cabeza;
        while (actual != null) {
            if (actual.getParticipante().getNombre().equalsIgnoreCase(nombre.trim())) return actual.getParticipante();
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Participante buscarRecursivo(String nombre) {
        return buscarRecursivoDesde(cabeza, nombre == null ? "" : nombre.trim());
    }

    private Participante buscarRecursivoDesde(NodoParticipante nodo, String nombre) {
        if (nodo == null) return null;
        if (nodo.getParticipante().getNombre().equalsIgnoreCase(nombre)) return nodo.getParticipante();
        return buscarRecursivoDesde(nodo.getSiguiente(), nombre);
    }

    public boolean actualizar(String nombreActual, String nuevoNombre, int edad, String equipo) {
        Participante participante = buscarPorNombre(nombreActual);
        if (participante == null) return false;
        participante.setNombre(nuevoNombre);
        participante.setEdad(edad);
        participante.setEquipo(equipo);
        return true;
    }

    public boolean eliminarPorNombre(String nombre) {
        NodoParticipante actual = cabeza;
        while (actual != null) {
            if (actual.getParticipante().getNombre().equalsIgnoreCase(nombre.trim())) {
                if (actual == cabeza && actual == cola) {
                    cabeza = null;
                    cola = null;
                } else if (actual == cabeza) {
                    cabeza = actual.getSiguiente();
                    cabeza.setAnterior(null);
                } else if (actual == cola) {
                    cola = actual.getAnterior();
                    cola.setSiguiente(null);
                } else {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                }
                cantidad--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public String obtenerTexto() {
        if (cabeza == null) return "No hay participantes registrados para este evento.";
        String texto = "";
        NodoParticipante actual = cabeza;
        while (actual != null) {
            texto += actual.getParticipante().toString() + "\n";
            actual = actual.getSiguiente();
        }
        return texto;
    }

    public int obtenerCantidad() { return cantidad; }

    public Participante obtenerParticipanteEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= cantidad) return null;
        NodoParticipante actual = cabeza;
        int contador = 0;
        while (actual != null) {
            if (contador == posicion) return actual.getParticipante();
            contador++;
            actual = actual.getSiguiente();
        }
        return null;
    }
}
