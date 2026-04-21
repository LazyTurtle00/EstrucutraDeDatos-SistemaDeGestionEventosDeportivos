package estructuras.estructuras;

import estructuras.modelos.Evento;

public class ListaSimpleEventos {
    private NodoEvento cabeza;
    private int cantidad;

    public ListaSimpleEventos() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    public void agregar(Evento evento) {
        NodoEvento nuevoNodo = new NodoEvento(evento);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoEvento actual = cabeza;

            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(nuevoNodo);
        }

        cantidad++;
    }

    public Evento buscarPorId(int id) {
        NodoEvento actual = cabeza;

        while (actual != null) {
            if (actual.getEvento().getId() == id) {
                return actual.getEvento();
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    public Evento buscarPorNombre(String nombre) {
        NodoEvento actual = cabeza;

        while (actual != null) {
            if (actual.getEvento().getNombre().equalsIgnoreCase(nombre.trim())) {
                return actual.getEvento();
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    public boolean actualizar(int id, String nombre, String fecha, String ubicacion) {
        Evento evento = buscarPorId(id);

        if (evento == null) {
            return false;
        }

        evento.setNombre(nombre);
        evento.setFecha(fecha);
        evento.setUbicacion(ubicacion);

        return true;
    }

    public boolean eliminarPorId(int id) {
        if (cabeza == null) {
            return false;
        }

        if (cabeza.getEvento().getId() == id) {
            cabeza = cabeza.getSiguiente();
            cantidad--;
            return true;
        }

        NodoEvento actual = cabeza;

        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getEvento().getId() == id) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                cantidad--;
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public String obtenerTexto() {
        if (cabeza == null) {
            return "No hay eventos registrados.";
        }

        String texto = "";
        NodoEvento actual = cabeza;

        while (actual != null) {
            texto += actual.getEvento().toString() + "\n";
            actual = actual.getSiguiente();
        }

        return texto;
    }

    public int obtenerCantidad() {
        return cantidad;
    }

    public Evento obtenerEventoEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= cantidad) {
            return null;
        }

        NodoEvento actual = cabeza;
        int contador = 0;

        while (actual != null) {
            if (contador == posicion) {
                return actual.getEvento();
            }

            contador++;
            actual = actual.getSiguiente();
        }

        return null;
    }
}