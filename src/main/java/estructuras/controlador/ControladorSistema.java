package estructuras.controlador;

import estructuras.modelo.Participante;
import estructuras.modelo.SistemaEventos;
import estructuras.modelo.persistencia.PersistenciaJSON;

public class ControladorSistema {
    private static SistemaEventos sistema = new SistemaEventos();

    public static boolean registrarEvento(String nombre, String fecha, String ubicacion) {
        return sistema.registrarEvento(nombre, fecha, ubicacion);
    }

    public static boolean actualizarEvento(int id, String nombre, String fecha, String ubicacion) {
        return sistema.actualizarEvento(id, nombre, fecha, ubicacion);
    }

    public static boolean eliminarEvento(int id) { return sistema.eliminarEvento(id); }
    public static String obtenerEventosTexto() { return sistema.obtenerEventosTexto(); }
    public static String[] obtenerEventosCombo() { return sistema.obtenerEventosCombo(); }

    public static boolean registrarParticipante(int idEvento, String nombre, int edad, String equipo) {
        return sistema.registrarParticipante(idEvento, nombre, edad, equipo);
    }

    public static boolean actualizarParticipante(int idEvento, String nombreActual, String nuevoNombre, int edad, String equipo) {
        return sistema.actualizarParticipante(idEvento, nombreActual, nuevoNombre, edad, equipo);
    }

    public static boolean eliminarParticipante(int idEvento, String nombre) {
        return sistema.eliminarParticipante(idEvento, nombre);
    }

    public static String buscarParticipanteRecursivoTexto(int idEvento, String nombre) {
        Participante participante = sistema.buscarParticipanteRecursivo(idEvento, nombre);
        return participante == null ? "No se encontró el participante." : participante.toString();
    }

    public static String obtenerParticipantesTexto(int idEvento) { return sistema.obtenerParticipantesTexto(idEvento); }
    public static String[] obtenerParticipantesCombo(int idEvento) { return sistema.obtenerParticipantesCombo(idEvento); }

    public static boolean programarPartido(int idEvento, String equipoUno, String equipoDos, String fecha, String hora) {
        return sistema.programarPartido(idEvento, equipoUno, equipoDos, fecha, hora);
    }

    public static String obtenerPartidosTexto(int idEvento) { return sistema.obtenerPartidosTexto(idEvento); }
    public static String[] obtenerPartidosCombo(int idEvento) { return sistema.obtenerPartidosCombo(idEvento); }

    public static boolean registrarResultado(int idEvento, int idPartido, int puntosUno, int puntosDos) {
        return sistema.registrarResultado(idEvento, idPartido, puntosUno, puntosDos);
    }

    public static String obtenerResultadosTexto(int idEvento) { return sistema.obtenerResultadosTexto(idEvento); }
    public static String obtenerGrafoTexto() { return sistema.obtenerGrafoTexto(); }
    public static String obtenerClasificacionTexto() { return sistema.obtenerClasificacionTexto(); }

    public static boolean guardarDatos() { return PersistenciaJSON.guardar(sistema); }
    public static boolean cargarDatos() { return PersistenciaJSON.cargar(sistema); }

    public static int obtenerIdDesdeCombo(String item) {
        if (item == null || !item.contains(" - ")) return -1;
        try {
            return Integer.parseInt(item.substring(0, item.indexOf(" - ")).trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
