/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.datos;

import estructuras.modelos.Evento;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Steven Fallas
 */
public class ManejadorJSON {
    public static void cargarEventosDelJSON(String contenidoJson) {
        
        // 1. Limpiamos espacios y saltos de línea para que sea más fácil buscar
        String textoLimpio = contenidoJson.replace("\n", "").replace("\r", "").trim();
        
        // 2. Buscamos dónde empieza la lista de eventos
        String claveBuscada = "\"eventos\":[";
        int inicioArreglo = textoLimpio.indexOf(claveBuscada);
        
        if (inicioArreglo == -1) {
            System.out.println("No se encontró el arreglo de eventos en el JSON.");
            return; // Nos salimos si no hay eventos
        }
        
        // Calculamos el índice exacto donde empieza el contenido del arreglo
        inicioArreglo = inicioArreglo + claveBuscada.length();
        
        // Buscamos dónde termina el arreglo (el primer ']' después del inicio)
        int finArreglo = textoLimpio.indexOf("]", inicioArreglo);
        
        if (finArreglo == -1) return;
        
        // 3. Extraemos solo lo que está entre [ y ]
        String bloqueEventos = textoLimpio.substring(inicioArreglo, finArreglo);
        
        // Si está vacío, no hacemos nada
        if (bloqueEventos.trim().isEmpty()) {
            return;
        }

        // 4. Separamos cada objeto de evento. 
        // Cada evento termina con "}" y el siguiente empieza con "{", separados por ","
        String[] eventosSeparados = bloqueEventos.split("\\},\\{");
        
        for (String eventoStr : eventosSeparados) {
            // Limpiamos las llaves que sobran en los extremos
            eventoStr = eventoStr.replace("{", "").replace("}", "");
            
            // Variables temporales para guardar los datos antes de enviarlos a SistemaDatos
            String nombre = "";
            String fecha = "";
            String lugar = "";
            
            // 5. Separamos los atributos por coma
            String[] atributos = eventoStr.split(",");
            
            for (String atributo : atributos) {
                // Separamos la clave y el valor por los dos puntos ":"
                String[] par = atributo.split(":");
                
                if (par.length == 2) {
                    // Quitamos las comillas y espacios de la clave y el valor
                    String clave = par[0].replace("\"", "").trim();
                    String valor = par[1].replace("\"", "").trim();
                    
                    if (clave.equals("nombre")) {
                        nombre = valor;
                    } else if (clave.equals("fecha")) {
                        fecha = valor;
                    } else if (clave.equals("lugar")) {
                        lugar = valor;
                    }
                }
            }

            if (!nombre.isEmpty() && !fecha.isEmpty() && !lugar.isEmpty()) {
                SistemaDatos.guardarEvento(nombre, fecha, lugar);
            }
        }
    }
    public static void guardarDatosEnArchivo() {

        StringBuilder json = new StringBuilder();
        
        json.append("{\n");
        json.append("  \"eventos\": [\n");

        // 2. Recorremos los eventos que hay en SistemaDatos
        for (int i = 0; i < SistemaDatos.totalEventos; i++) {
            Evento e = SistemaDatos.eventos[i];
            
            json.append("    {\n");
            json.append("      \"id\": ").append(e.getId()).append(",\n");
            json.append("      \"nombre\": \"").append(e.getNombre()).append("\",\n");
            json.append("      \"fecha\": \"").append(e.getFecha()).append("\",\n");
            json.append("      \"lugar\": \"").append(e.getLugar()).append("\"\n");
            json.append("    }");

            // 3. Regla de la coma: Si no es el último, ponemos coma
            if (i < SistemaDatos.totalEventos - 1) {
                json.append(",");
            }
            json.append("\n");
        }

        json.append("  ]\n");
        json.append("}");

        // 4. Escritura física en el archivo usando clases nativas de Java
        try (FileWriter escritor = new FileWriter("datos_eventos.json")) {
            escritor.write(json.toString());
            System.out.println("¡Datos guardados con éxito en datos_eventos.json!");
        } catch (IOException ex) {
            System.out.println("Error al escribir el archivo: " + ex.getMessage());
        }
    }
}
