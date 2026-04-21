package estructuras.controlador;

import estructuras.modelos.Evento;
import estructuras.modelos.Participante;
import estructuras.modelos.Partido;
import estructuras.modelos.Resultado;

public class SistemaDatos {

    private static ListaSimpleEventos listaEventos = new ListaSimpleEventos();
    public static int totalEventos = 0;
    public static int siguienteIdEvento = 1;

    public static Participante[] participantes = new Participante[50];
    public static int totalParticipantes = 0;
    public static int siguienteIdParticipante = 1;

    public static Partido[] partidos = new Partido[100];
    public static int totalPartidos = 0;
    public static int siguienteIdPartido = 1;

    public static Resultado[] resultados = new Resultado[100];
    public static int totalResultados = 0;

    private static ListaSimpleEventos listaEventos = new ListaSimpleEventos();
    private static int siguienteIdEvento = 1;

    public static boolean guardarEvento(String nombre, String fecha, String ubicacion) {
        if (nombre == null || nombre.trim().equals("")
                || fecha == null || fecha.trim().equals("")
                || ubicacion == null || ubicacion.trim().equals("")) {
            return false;
        }

        Evento evento = new Evento(
                siguienteIdEvento,
                nombre.trim(),
                fecha.trim(),
                ubicacion.trim()
        );

        listaEventos.agregar(evento);
        siguienteIdEvento++;

        return true;
    }

    public static String obtenerEventosTexto() {
        return listaEventos.obtenerTexto();
    }

    public static boolean actualizarEvento(int id, String nombre, String fecha, String ubicacion) {
        if (nombre == null || nombre.trim().equals("")
                || fecha == null || fecha.trim().equals("")
                || ubicacion == null || ubicacion.trim().equals("")) {
            return false;
        }

        return listaEventos.actualizar(id, nombre.trim(), fecha.trim(), ubicacion.trim());
    }

    public static boolean eliminarEventoPorId(int id) {
        return listaEventos.eliminarPorId(id);
    }

    public static Evento buscarEventoPorId(int id) {
        return listaEventos.buscarPorId(id);
    }

    public static Evento obtenerEventoEnPosicion(int posicion) {
        return listaEventos.obtenerEventoEnPosicion(posicion);
    }

    public static int obtenerCantidadEventos() {
        return listaEventos.obtenerCantidad();
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

    public static boolean guardarPartido(String nombreEvento, String equipoUno, String equipoDos, String fecha, String hora) {
        if (nombreEvento == null || nombreEvento.trim().equals("")
                || equipoUno == null || equipoUno.trim().equals("")
                || equipoDos == null || equipoDos.trim().equals("")
                || fecha == null || fecha.trim().equals("")
                || hora == null || hora.trim().equals("")) {
            return false;
        }

        if (equipoUno.equalsIgnoreCase(equipoDos)) {
            return false;
        }

        if (totalPartidos >= partidos.length) {
            return false;
        }

        partidos[totalPartidos] = new Partido(
                siguienteIdPartido,
                nombreEvento.trim(),
                equipoUno.trim(),
                equipoDos.trim(),
                fecha.trim(),
                hora.trim()
        );

        totalPartidos++;
        siguienteIdPartido++;
        return true;
    }

    public static String obtenerPartidosTexto() {
        if (totalPartidos == 0) {
            return "No hay partidos programados.";
        }

        String texto = "";
        for (int i = 0; i < totalPartidos; i++) {
            texto += partidos[i].toString() + "\n";
        }

        return texto;
    }

    public static boolean eliminarPartidoPorId(int id) {
        int posicion = -1;

        for (int i = 0; i < totalPartidos; i++) {
            if (partidos[i].getId() == id) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            return false;
        }

        for (int i = posicion; i < totalPartidos - 1; i++) {
            partidos[i] = partidos[i + 1];
        }

        partidos[totalPartidos - 1] = null;
        totalPartidos--;
        return true;
    }

    public static Partido buscarPartidoPorId(int id) {
    for (int i = 0; i < totalPartidos; i++) {
        if (partidos[i].getId() == id) {
            return partidos[i];
        }
    }

    return null;
    }

    public static boolean guardarResultado(int idPartido, int puntosEquipoUno, int puntosEquipoDos) {
        if (puntosEquipoUno < 0 || puntosEquipoDos < 0) {
            return false;
        }

        Partido partido = buscarPartidoPorId(idPartido);

        if (partido == null) {
            return false;
        }

        Resultado nuevoResultado = new Resultado(
                partido.getId(),
                partido.getNombreEvento(),
                partido.getEquipoUno(),
                partido.getEquipoDos(),
                puntosEquipoUno,
                puntosEquipoDos
        );

        for (int i = 0; i < totalResultados; i++) {
            if (resultados[i].getIdPartido() == idPartido) {
                resultados[i] = nuevoResultado;
                return true;
            }
        }

        if (totalResultados >= resultados.length) {
            return false;
        }

        resultados[totalResultados] = nuevoResultado;
        totalResultados++;
        return true;
    }

    public static String obtenerResultadosTexto() {
        if (totalResultados == 0) {
            return "No hay resultados registrados.";
        }

        String texto = "";

        for (int i = 0; i < totalResultados; i++) {
            texto += resultados[i].toString() + "\n";
        }

        return texto;
    }
}