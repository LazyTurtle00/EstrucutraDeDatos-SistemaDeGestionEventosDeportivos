/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.controlador;

import estructuras.modelos.Evento;
import estructuras.modelos.Participante;
import estructuras.modelos.Partido;
import estructuras.modelos.Resultado;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Steven Fallas
 */
public class ManejadorJSON {

    private static final String FILE_NAME = "datos_sistema.json";

    private static void procesarSeccionEventos(String json) {
        String clave = "\"eventos\":[";
        int inicio = json.indexOf(clave);
        if (inicio == -1) {
            return;
        }
        inicio += clave.length();
        int fin = json.indexOf("]", inicio);
        String bloque = json.substring(inicio, fin);
        if (bloque.trim().isEmpty()) {
            return;
        }

        String[] objetos = bloque.split("\\},\\{");
        for (String obj : objetos) {
            obj = obj.replace("{", "").replace("}", "");
            String[] atributos = obj.split(",");
            String nombre = "", fecha = "", lugar = "";
            for (String at : atributos) {
                String[] par = at.split(":");
                String claveAt = par[0].replace("\"", "").trim();
                String valorAt = par[1].replace("\"", "").trim();
                if (claveAt.equals("nombre")) {
                    nombre = valorAt;
                } else if (claveAt.equals("fecha")) {
                    fecha = valorAt;
                } else if (claveAt.equals("lugar")) {
                    lugar = valorAt;
                }
            }
            if (!nombre.isEmpty()) {
                SistemaDatos.guardarEvento(nombre, fecha, lugar);
            }
        }
    }

    private static void procesarSeccionParticipantes(String json) {
        String clave = "\"participantes\":[";
        int inicio = json.indexOf(clave);
        if (inicio == -1) {
            return;
        }
        inicio += clave.length();
        int fin = json.indexOf("]", inicio);
        String bloque = json.substring(inicio, fin);
        if (bloque.trim().isEmpty()) {
            return;
        }

        String[] objetos = bloque.split("\\},\\{");
        for (String obj : objetos) {
            obj = obj.replace("{", "").replace("}", "");
            String[] atributos = obj.split(",");
            String equipo = "", capitan = "";
            int jugadores = 0;
            for (String at : atributos) {
                String[] par = at.split(":");
                String claveAt = par[0].replace("\"", "").trim();
                String valorAt = par[1].replace("\"", "").trim();
                if (claveAt.equals("equipo")) {
                    equipo = valorAt;
                } else if (claveAt.equals("capitan")) {
                    capitan = valorAt;
                } else if (claveAt.equals("jugadores")) {
                    jugadores = Integer.parseInt(valorAt);
                }
            }
            if (!equipo.isEmpty()) {
                SistemaDatos.guardarParticipante(equipo, capitan, jugadores);
            }
        }
    }

    private static void procesarSeccionPartidos(String json) {
        String clave = "\"partidos\":[";
        int inicio = json.indexOf(clave);
        if (inicio == -1) {
            return;
        }
        inicio += clave.length();
        int fin = json.indexOf("]", inicio);
        String bloque = json.substring(inicio, fin);
        if (bloque.trim().isEmpty()) {
            return;
        }

        String[] objetos = bloque.split("\\},\\{");
        for (String obj : objetos) {
            obj = obj.replace("{", "").replace("}", "");
            String[] atributos = obj.split(",");
            String ev = "", e1 = "", e2 = "", f = "", h = "";
            for (String at : atributos) {
                String[] par = at.split(":");
                String c = par[0].replace("\"", "").trim();
                String v = par[1].replace("\"", "").trim();
                if (c.equals("evento")) {
                    ev = v;
                } else if (c.equals("e1")) {
                    e1 = v;
                } else if (c.equals("e2")) {
                    e2 = v;
                } else if (c.equals("f")) {
                    f = v;
                } else if (c.equals("h")) {
                    h = v;
                }
            }
            SistemaDatos.guardarPartido(ev, e1, e2, f, h);
        }
    }

    private static void procesarSeccionResultados(String json) {
        String clave = "\"resultados\":[";
        int inicio = json.indexOf(clave);
        if (inicio == -1) {
            return;
        }
        inicio += clave.length();
        int fin = json.indexOf("]", inicio);
        String bloque = json.substring(inicio, fin);
        if (bloque.trim().isEmpty()) {
            return;
        }

        String[] objetos = bloque.split("\\},\\{");
        for (String obj : objetos) {
            obj = obj.replace("{", "").replace("}", "");
            String[] atributos = obj.split(",");
            int id = 0, p1 = 0, p2 = 0;
            for (String at : atributos) {
                String[] par = at.split(":");
                String c = par[0].replace("\"", "").trim();
                String v = par[1].replace("\"", "").trim();
                if (c.equals("id")) {
                    id = Integer.parseInt(v);
                } else if (c.equals("p1")) {
                    p1 = Integer.parseInt(v);
                } else if (c.equals("p2")) {
                    p2 = Integer.parseInt(v);
                }
            }
            SistemaDatos.guardarResultado(id, p1, p2);
        }
    }

    public static void guardarTodo() {
        StringBuilder json = new StringBuilder();
        json.append("{\n");

        // Eventos
        json.append("  \"eventos\": [\n");
        for (int i = 0; i < SistemaDatos.totalEventos; i++) {
            Evento e = SistemaDatos.eventos[i];
            json.append("    {\"id\":").append(e.getId())
                    .append(", \"nombre\":\"").append(e.getNombre())
                    .append("\", \"fecha\":\"").append(e.getFecha())
                    .append("\", \"lugar\":\"").append(e.getLugar()).append("\"}");
            if (i < SistemaDatos.totalEventos - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("  ],\n");

        // Participantes
        json.append("  \"participantes\": [\n");
        for (int i = 0; i < SistemaDatos.totalParticipantes; i++) {
            Participante p = SistemaDatos.participantes[i];
            json.append("    {\"id\":").append(p.getId())
                    .append(", \"equipo\":\"").append(p.getNombreEquipo())
                    .append("\", \"capitan\":\"").append(p.getCapitan())
                    .append("\", \"jugadores\":").append(p.getCantidadJugadores()).append("}");
            if (i < SistemaDatos.totalParticipantes - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("  ],\n");

        // Partidos
        json.append("  \"partidos\": [\n");
        for (int i = 0; i < SistemaDatos.totalPartidos; i++) {
            Partido pa = SistemaDatos.partidos[i];
            json.append("    {\"id\":").append(pa.getId())
                    .append(", \"evento\":\"").append(pa.getNombreEvento())
                    .append("\", \"e1\":\"").append(pa.getEquipoUno())
                    .append("\", \"e2\":\"").append(pa.getEquipoDos())
                    .append("\", \"f\":\"").append(pa.getFecha())
                    .append("\", \"h\":\"").append(pa.getHora()).append("\"}");
            if (i < SistemaDatos.totalPartidos - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("  ],\n");

        // Resultados
        json.append("  \"resultados\": [\n");
        for (int i = 0; i < SistemaDatos.totalResultados; i++) {
            Resultado r = SistemaDatos.resultados[i];
            json.append("    {\"id\":").append(r.getIdPartido())
                    .append(", \"p1\": ").append(r.getPuntosEquipoUno())
                    .append(", \"p2\": ").append(r.getPuntosEquipoDos())
                    .append("}");
            if (i < SistemaDatos.totalResultados - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("  ]\n");

        json.append("}");

        try (FileWriter escritor = new FileWriter(FILE_NAME)) {
            escritor.write(json.toString());
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException ex) {
            System.out.println("Error al escribir: " + ex.getMessage());
        }
    }

    public static void cargarTodo() {
        java.io.File archivo = new java.io.File(FILE_NAME);
        if (!archivo.exists()) {
            System.out.println("Archivo no encontrado. Se iniciará con datos vacíos.");
            return;
        }

        try (java.io.BufferedReader lector = new java.io.BufferedReader(new java.io.FileReader(archivo))) {
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea);
            }

            String json = contenido.toString().replace("\n", "").replace("\r", "").trim();

            procesarSeccionEventos(json);
            procesarSeccionParticipantes(json);
            procesarSeccionPartidos(json);
            procesarSeccionResultados(json);

        } catch (java.io.IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }
}
