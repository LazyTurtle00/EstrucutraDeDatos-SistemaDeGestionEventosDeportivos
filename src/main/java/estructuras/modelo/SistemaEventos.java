package estructuras.modelo;

import estructuras.modelo.estructuras.ArbolClasificacion;
import estructuras.modelo.estructuras.GrafoEnfrentamientos;
import estructuras.modelo.estructuras.ListaSimpleEventos;

public class SistemaEventos {
    private ListaSimpleEventos eventos;
    private GrafoEnfrentamientos grafo;
    private ArbolClasificacion clasificacion;
    private int siguienteIdEvento;
    private int siguienteIdParticipante;
    private int siguienteIdPartido;

    public SistemaEventos() {
        eventos = new ListaSimpleEventos();
        grafo = new GrafoEnfrentamientos();
        clasificacion = new ArbolClasificacion();
        siguienteIdEvento = 1;
        siguienteIdParticipante = 1;
        siguienteIdPartido = 1;
    }

    public boolean registrarEvento(String nombre, String fecha, String ubicacion) {
        if (textoVacio(nombre) || textoVacio(fecha) || textoVacio(ubicacion)) return false;
        eventos.agregar(new Evento(siguienteIdEvento++, nombre.trim(), fecha.trim(), ubicacion.trim()));
        return true;
    }

    public void registrarEventoConId(int id, String nombre, String fecha, String ubicacion) {
        eventos.agregar(new Evento(id, nombre, fecha, ubicacion));
        if (id >= siguienteIdEvento) siguienteIdEvento = id + 1;
    }

    public boolean actualizarEvento(int id, String nombre, String fecha, String ubicacion) {
        if (textoVacio(nombre) || textoVacio(fecha) || textoVacio(ubicacion)) return false;
        return eventos.actualizar(id, nombre.trim(), fecha.trim(), ubicacion.trim());
    }

    public boolean eliminarEvento(int id) { return eventos.eliminarPorId(id); }
    public Evento buscarEvento(int id) { return eventos.buscarPorId(id); }
    public String obtenerEventosTexto() { return eventos.obtenerTexto(); }
    public int obtenerCantidadEventos() { return eventos.obtenerCantidad(); }
    public Evento obtenerEventoEnPosicion(int posicion) { return eventos.obtenerEventoEnPosicion(posicion); }

    public String[] obtenerEventosCombo() {
        String[] datos = new String[eventos.obtenerCantidad()];
        for (int i = 0; i < datos.length; i++) {
            Evento evento = eventos.obtenerEventoEnPosicion(i);
            datos[i] = evento.getId() + " - " + evento.getNombre();
        }
        return datos;
    }

    public boolean registrarParticipante(int idEvento, String nombre, int edad, String equipo) {
        if (textoVacio(nombre) || textoVacio(equipo) || edad <= 0) return false;
        Evento evento = buscarEvento(idEvento);
        if (evento == null) return false;
        evento.getParticipantes().agregar(new Participante(siguienteIdParticipante++, nombre.trim(), edad, equipo.trim()));
        clasificacion.registrarEquipo(equipo.trim());
        grafo.agregarEquipo(equipo.trim());
        return true;
    }

    public void registrarParticipanteConId(int idEvento, int idParticipante, String nombre, int edad, String equipo) {
        Evento evento = buscarEvento(idEvento);
        if (evento != null) {
            evento.getParticipantes().agregar(new Participante(idParticipante, nombre, edad, equipo));
            clasificacion.registrarEquipo(equipo);
            grafo.agregarEquipo(equipo);
            if (idParticipante >= siguienteIdParticipante) siguienteIdParticipante = idParticipante + 1;
        }
    }

    public boolean actualizarParticipante(int idEvento, String nombreActual, String nuevoNombre, int edad, String equipo) {
        if (textoVacio(nombreActual) || textoVacio(nuevoNombre) || textoVacio(equipo) || edad <= 0) return false;
        Evento evento = buscarEvento(idEvento);
        if (evento == null) return false;
        boolean actualizado = evento.getParticipantes().actualizar(nombreActual.trim(), nuevoNombre.trim(), edad, equipo.trim());
        if (actualizado) {
            clasificacion.registrarEquipo(equipo.trim());
            grafo.agregarEquipo(equipo.trim());
        }
        return actualizado;
    }

    public boolean eliminarParticipante(int idEvento, String nombre) {
        Evento evento = buscarEvento(idEvento);
        if (evento == null || textoVacio(nombre)) return false;
        return evento.getParticipantes().eliminarPorNombre(nombre.trim());
    }

    public Participante buscarParticipanteRecursivo(int idEvento, String nombre) {
        Evento evento = buscarEvento(idEvento);
        if (evento == null || textoVacio(nombre)) return null;
        return evento.getParticipantes().buscarRecursivo(nombre.trim());
    }

    public String obtenerParticipantesTexto(int idEvento) {
        Evento evento = buscarEvento(idEvento);
        if (evento == null) return "Debe seleccionar un evento válido.";
        return evento.getParticipantes().obtenerTexto();
    }

    public String[] obtenerParticipantesCombo(int idEvento) {
        Evento evento = buscarEvento(idEvento);
        if (evento == null) return new String[0];
        String[] datos = new String[evento.getParticipantes().obtenerCantidad()];
        for (int i = 0; i < datos.length; i++) {
            Participante participante = evento.getParticipantes().obtenerParticipanteEnPosicion(i);
            datos[i] = participante.getEquipo();
        }
        return datos;
    }

    public boolean programarPartido(int idEvento, String equipoUno, String equipoDos, String fecha, String hora) {
        if (textoVacio(equipoUno) || textoVacio(equipoDos) || textoVacio(fecha) || textoVacio(hora)) return false;
        if (equipoUno.equalsIgnoreCase(equipoDos)) return false;
        Evento evento = buscarEvento(idEvento);
        if (evento == null) return false;
        Partido partido = new Partido(siguienteIdPartido++, equipoUno.trim(), equipoDos.trim(), fecha.trim(), hora.trim());
        evento.getPartidosProgramados().encolar(partido);
        clasificacion.registrarEquipo(equipoUno.trim());
        clasificacion.registrarEquipo(equipoDos.trim());
        return true;
    }

    public void programarPartidoConId(int idEvento, int idPartido, String equipoUno, String equipoDos, String fecha, String hora, boolean jugado) {
        Evento evento = buscarEvento(idEvento);
        if (evento != null) {
            Partido partido = new Partido(idPartido, equipoUno, equipoDos, fecha, hora);
            partido.setJugado(jugado);
            evento.getPartidosProgramados().encolar(partido);
            clasificacion.registrarEquipo(equipoUno);
            clasificacion.registrarEquipo(equipoDos);
            if (idPartido >= siguienteIdPartido) siguienteIdPartido = idPartido + 1;
        }
    }

    public String obtenerPartidosTexto(int idEvento) {
        Evento evento = buscarEvento(idEvento);
        if (evento == null) return "Debe seleccionar un evento válido.";
        return evento.getPartidosProgramados().obtenerTexto();
    }

    public String[] obtenerPartidosCombo(int idEvento) {
        Evento evento = buscarEvento(idEvento);
        if (evento == null) return new String[0];
        String[] datos = new String[evento.getPartidosProgramados().obtenerCantidad()];
        for (int i = 0; i < datos.length; i++) {
            Partido partido = evento.getPartidosProgramados().obtenerPartidoEnPosicion(i);
            datos[i] = partido.getId() + " - " + partido.getEquipoUno() + " vs " + partido.getEquipoDos();
        }
        return datos;
    }

    public boolean registrarResultado(int idEvento, int idPartido, int puntosUno, int puntosDos) {
        if (puntosUno < 0 || puntosDos < 0) return false;
        Evento evento = buscarEvento(idEvento);
        if (evento == null) return false;
        Partido partido = evento.getPartidosProgramados().buscarPorId(idPartido);
        if (partido == null) return false;
        Resultado resultado = new Resultado(idPartido, partido.getEquipoUno(), partido.getEquipoDos(), puntosUno, puntosDos);
        evento.getResultados().apilar(resultado);
        partido.setJugado(true);
        grafo.agregarEnfrentamiento(partido.getEquipoUno(), partido.getEquipoDos());
        if (puntosUno > puntosDos) clasificacion.sumarVictoria(partido.getEquipoUno());
        if (puntosDos > puntosUno) clasificacion.sumarVictoria(partido.getEquipoDos());
        return true;
    }

    public void registrarResultadoCargado(int idEvento, int idPartido, String equipoUno, String equipoDos, int puntosUno, int puntosDos) {
        Evento evento = buscarEvento(idEvento);
        if (evento != null) {
            evento.getResultados().apilar(new Resultado(idPartido, equipoUno, equipoDos, puntosUno, puntosDos));
            grafo.agregarEnfrentamiento(equipoUno, equipoDos);
            if (puntosUno > puntosDos) clasificacion.sumarVictoria(equipoUno);
            if (puntosDos > puntosUno) clasificacion.sumarVictoria(equipoDos);
        }
    }

    public String obtenerResultadosTexto(int idEvento) {
        Evento evento = buscarEvento(idEvento);
        if (evento == null) return "Debe seleccionar un evento válido.";
        return evento.getResultados().obtenerTexto();
    }

    public String obtenerGrafoTexto() { return grafo.obtenerTexto(); }
    public String obtenerClasificacionTexto() { return clasificacion.obtenerTextoDescendente(); }

    public GrafoEnfrentamientos getGrafo() { return grafo; }
    public ListaSimpleEventos getEventos() { return eventos; }

    public void limpiar() {
        eventos = new ListaSimpleEventos();
        grafo = new GrafoEnfrentamientos();
        clasificacion = new ArbolClasificacion();
        siguienteIdEvento = 1;
        siguienteIdParticipante = 1;
        siguienteIdPartido = 1;
    }

    private boolean textoVacio(String texto) {
        return texto == null || texto.trim().equals("");
    }
}
