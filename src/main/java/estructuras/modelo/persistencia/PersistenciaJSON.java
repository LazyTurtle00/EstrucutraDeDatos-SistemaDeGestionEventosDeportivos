package estructuras.modelo.persistencia;

import estructuras.modelo.Evento;
import estructuras.modelo.Participante;
import estructuras.modelo.Partido;
import estructuras.modelo.Resultado;
import estructuras.modelo.SistemaEventos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PersistenciaJSON {
    private static final String RUTA_ARCHIVO = "datos_eventos.jsonl";

    public static boolean guardar(SistemaEventos sistema) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RUTA_ARCHIVO))) {
            writer.println("{\"formato\":\"json-por-lineas\",\"descripcion\":\"Estado completo del sistema\"}");
            for (int i = 0; i < sistema.obtenerCantidadEventos(); i++) {
                Evento evento = sistema.obtenerEventoEnPosicion(i);
                writer.println("{\"tipo\":\"EVENTO\",\"id\":" + evento.getId()
                        + ",\"nombre\":\"" + escapar(evento.getNombre())
                        + "\",\"fecha\":\"" + escapar(evento.getFecha())
                        + "\",\"ubicacion\":\"" + escapar(evento.getUbicacion()) + "\"}");

                for (int p = 0; p < evento.getParticipantes().obtenerCantidad(); p++) {
                    Participante participante = evento.getParticipantes().obtenerParticipanteEnPosicion(p);
                    writer.println("{\"tipo\":\"PARTICIPANTE\",\"idEvento\":" + evento.getId()
                            + ",\"id\":" + participante.getId()
                            + ",\"nombre\":\"" + escapar(participante.getNombre())
                            + "\",\"edad\":" + participante.getEdad()
                            + ",\"equipo\":\"" + escapar(participante.getEquipo()) + "\"}");
                }

                for (int pa = 0; pa < evento.getPartidosProgramados().obtenerCantidad(); pa++) {
                    Partido partido = evento.getPartidosProgramados().obtenerPartidoEnPosicion(pa);
                    writer.println("{\"tipo\":\"PARTIDO\",\"idEvento\":" + evento.getId()
                            + ",\"id\":" + partido.getId()
                            + ",\"equipoUno\":\"" + escapar(partido.getEquipoUno())
                            + "\",\"equipoDos\":\"" + escapar(partido.getEquipoDos())
                            + "\",\"fecha\":\"" + escapar(partido.getFecha())
                            + "\",\"hora\":\"" + escapar(partido.getHora())
                            + "\",\"jugado\":" + partido.isJugado() + "}");
                }

                // La pila se guarda en el orden visible, del más reciente al más antiguo.
                for (int r = evento.getResultados().obtenerCantidad() - 1; r >= 0; r--) {
                    Resultado resultado = evento.getResultados().obtenerResultadoEnPosicion(r);
                    writer.println("{\"tipo\":\"RESULTADO\",\"idEvento\":" + evento.getId()
                            + ",\"idPartido\":" + resultado.getIdPartido()
                            + ",\"equipoUno\":\"" + escapar(resultado.getEquipoUno())
                            + "\",\"equipoDos\":\"" + escapar(resultado.getEquipoDos())
                            + "\",\"puntosUno\":" + resultado.getPuntosEquipoUno()
                            + ",\"puntosDos\":" + resultado.getPuntosEquipoDos() + "}");
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean cargar(SistemaEventos sistema) {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) return false;
        sistema.limpiar();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String tipo = obtenerTexto(linea, "tipo");
                if (tipo.equals("EVENTO")) {
                    sistema.registrarEventoConId(
                            obtenerEntero(linea, "id"),
                            obtenerTexto(linea, "nombre"),
                            obtenerTexto(linea, "fecha"),
                            obtenerTexto(linea, "ubicacion")
                    );
                } else if (tipo.equals("PARTICIPANTE")) {
                    sistema.registrarParticipanteConId(
                            obtenerEntero(linea, "idEvento"),
                            obtenerEntero(linea, "id"),
                            obtenerTexto(linea, "nombre"),
                            obtenerEntero(linea, "edad"),
                            obtenerTexto(linea, "equipo")
                    );
                } else if (tipo.equals("PARTIDO")) {
                    sistema.programarPartidoConId(
                            obtenerEntero(linea, "idEvento"),
                            obtenerEntero(linea, "id"),
                            obtenerTexto(linea, "equipoUno"),
                            obtenerTexto(linea, "equipoDos"),
                            obtenerTexto(linea, "fecha"),
                            obtenerTexto(linea, "hora"),
                            obtenerBooleano(linea, "jugado")
                    );
                } else if (tipo.equals("RESULTADO")) {
                    sistema.registrarResultadoCargado(
                            obtenerEntero(linea, "idEvento"),
                            obtenerEntero(linea, "idPartido"),
                            obtenerTexto(linea, "equipoUno"),
                            obtenerTexto(linea, "equipoDos"),
                            obtenerEntero(linea, "puntosUno"),
                            obtenerEntero(linea, "puntosDos")
                    );
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static String escapar(String texto) {
        if (texto == null) return "";
        return texto.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static String obtenerTexto(String linea, String clave) {
        String patron = "\"" + clave + "\":\"";
        int inicio = linea.indexOf(patron);
        if (inicio == -1) return "";
        inicio += patron.length();
        String texto = "";
        boolean escape = false;
        for (int i = inicio; i < linea.length(); i++) {
            char c = linea.charAt(i);
            if (escape) {
                texto += c;
                escape = false;
            } else if (c == '\\') {
                escape = true;
            } else if (c == '"') {
                break;
            } else {
                texto += c;
            }
        }
        return texto;
    }

    private static int obtenerEntero(String linea, String clave) {
        String valor = obtenerValorSimple(linea, clave);
        try { return Integer.parseInt(valor); } catch (NumberFormatException e) { return 0; }
    }

    private static boolean obtenerBooleano(String linea, String clave) {
        return obtenerValorSimple(linea, clave).equalsIgnoreCase("true");
    }

    private static String obtenerValorSimple(String linea, String clave) {
        String patron = "\"" + clave + "\":";
        int inicio = linea.indexOf(patron);
        if (inicio == -1) return "";
        inicio += patron.length();
        int fin = inicio;
        while (fin < linea.length() && linea.charAt(fin) != ',' && linea.charAt(fin) != '}') fin++;
        return linea.substring(inicio, fin).trim();
    }
}
