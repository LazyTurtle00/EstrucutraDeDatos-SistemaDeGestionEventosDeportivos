package estructuras.datos;

import estructuras.modelos.Evento;
import estructuras.modelos.Participante;

public class SistemaDatos {

    public static Evento[] eventos = new Evento[50];
    public static int totalEventos = 0;
    public static int siguienteIdEvento = 1;

    public static Participante[] participantes = new Participante[50];
    public static int totalParticipantes = 0;
    public static int siguienteIdParticipante = 1;

    public static boolean guardarEvento(String nombre, String fecha, String lugar) {
        if (nombre == null || nombre.trim().equals("")
                || fecha == null || fecha.trim().equals("")
                || lugar == null || lugar.trim().equals("")) {
            return false;
        }

        if (totalEventos >= eventos.length) {
            return false;
        }

        eventos[totalEventos] = new Evento(siguienteIdEvento, nombre.trim(), fecha.trim(), lugar.trim());
        totalEventos++;
        siguienteIdEvento++;
        return true;
    }

    public static String obtenerEventosTexto() {
        if (totalEventos == 0) {
            return "No hay eventos registrados.";
        }

        String texto = "";
        for (int i = 0; i < totalEventos; i++) {
            texto += eventos[i].toString() + "\n";
        }
        return texto;
    }

    public static boolean eliminarEventoPorNombre(String nombre) {
        if (nombre == null || nombre.trim().equals("")) {
            return false;
        }

        int posicion = -1;

        for (int i = 0; i < totalEventos; i++) {
            if (eventos[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            return false;
        }

        for (int i = posicion; i < totalEventos - 1; i++) {
            eventos[i] = eventos[i + 1];
        }

        eventos[totalEventos - 1] = null;
        totalEventos--;
        return true;
    }

    public static boolean guardarParticipante(String nombreEquipo, String capitan, int cantidadJugadores) {
        if (nombreEquipo == null || nombreEquipo.trim().equals("")
                || capitan == null || capitan.trim().equals("")
                || cantidadJugadores <= 0) {
            return false;
        }

        if (totalParticipantes >= participantes.length) {
            return false;
        }

        participantes[totalParticipantes] = new Participante(
                siguienteIdParticipante,
                nombreEquipo.trim(),
                capitan.trim(),
                cantidadJugadores
        );

        totalParticipantes++;
        siguienteIdParticipante++;
        return true;
    }

    public static String obtenerParticipantesTexto() {
        if (totalParticipantes == 0) {
            return "No hay participantes registrados.";
        }

        String texto = "";
        for (int i = 0; i < totalParticipantes; i++) {
            texto += participantes[i].toString() + "\n";
        }
        return texto;
    }

    public static boolean eliminarParticipantePorNombre(String nombreEquipo) {
        if (nombreEquipo == null || nombreEquipo.trim().equals("")) {
            return false;
        }

        int posicion = -1;

        for (int i = 0; i < totalParticipantes; i++) {
            if (participantes[i].getNombreEquipo().equalsIgnoreCase(nombreEquipo.trim())) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            return false;
        }

        for (int i = posicion; i < totalParticipantes - 1; i++) {
            participantes[i] = participantes[i + 1];
        }

        participantes[totalParticipantes - 1] = null;
        totalParticipantes--;
        return true;
    }
}