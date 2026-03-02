# 1. Grupo e Integrantes

Grupo: E

Integrantes:

- Luis Daniel Jaen Vargas  
- José Valentín Medrano Páramo  
- Steven Alonso Fallas Madrigal  
- Ireany Sevilla Araya

---

# 2. Plan de Proyecto con Responsables por Tarea

El proyecto se estará trabajando en grupo, de manera organizada, asignando diferentes responsabilidades a cada compañero (a), para así de esta forma, poder tener una buena gestión del proyecto, que todo sea equitativo y que se pueda realizar un buen trabajo en conjunto.

---

# 3. Introducción

Este proyecto consiste en el desarrollo de un sistema para la gestión de eventos deportivos, utilizando las estructuras vistas en clase.

El sistema será desarrollado en Java usando el patrón Modelo–Vista–Controlador (MVC), lo que permitirá poner en práctica los conceptos teóricos y prácticos vistos en clases, mediante una aplicación funcional.

¿Qué es MVC?
El patrón Modelo-Vista-Controlador (MVC) separa la lógica de negocio (Modelo), la presentación (Vista) y la interacción del usuario (Controlador), lo cual facilita el mantenimiento y la escalabilidad de las aplicaciones (Arquitectura Java, 2026).

---

# 4. Roles del Equipo

| Integrante | Rol |
|------------|------|
| Luis Daniel Jaén Vargas | Documentación y apoyo en estructuras de datos |
| José Valentín Medrano Páramo | Componentes visuales y librerías |
| Steven Alonso Fallas Madrigal | Diagrama de clases y funcionalidad de colas y pilas |
| Ireany Sevilla Araya | Diseño de menú e interfaces de usuario |

**Responsabilidad compartida:**  
Todos los integrantes participarán en la integración del sistema, pruebas, corrección de errores y también, en el trabajo en equipo para los entregables del presente proyecto.

---
``` mermaid
---
config:
  theme: dark
---
classDiagram
    direction TB
    namespace vista {
        class FrmLogin {
            - btnAdmin: JButton
            - btnEspectador: JButton
            + getSeleccion() : String
        }
        class FrmMenuAdmin {
            - btnGestEventos: JButton
            - btnGestParticipantes: JButton
            - btnProgPartido: JButton
            - btnRegResultados: JButton
            - btnVerFiltros: JButton
        }
        class FrmEvento {
            - txtNombre: JTextField
            - txtFecha: JTextField
            - txtLugar: JTextField
            + getDatosEvento() : String[]
        }
        class FrmParticipante {
            - txtNombre: JTextField
            - txtEdad: JTextField
            - txtEquipo: JTextField
            - btnAgregarJugador: JButton
        }
        class FrmProgramarPartido {
            - cmbEquipo1: JComboBox
            - cmbEquipo2: JComboBox
            + getEquiposSeleccionados() : String[]
        }
        class FrmRegistrarResultado {
            - cmbPartidoPendiente: JComboBox
            - txtGolesEq1: JTextField
            - txtGolesEq2: JTextField
            + getMarcador() : int[]
        }
        class FrmFiltros {
            - cmbTipoFiltro: JComboBox
            - tblResultados: JTable
            + mostrarEventos(lista: ListaSimpleEventos) : void
        }
    }

    namespace controlador {
        class CtrPrincipal {
            - modeloSistema: Sistema
            - vistaLogin: FrmLogin
            - vistaMenu: FrmMenuAdmin
            + iniciar() : void
        }
        class CtrEventos {
            - modeloSistema: Sistema
            - vistaEvento: FrmEvento
            - vistaParticipante: FrmParticipante
        }
        class CtrPartidos {
            - eventoActual: Evento
            - vistaProgramar: FrmProgramarPartido
            - vistaResultados: FrmRegistrarResultado
        }
    }

    namespace modelo {
        class Sistema {
            - eventos: ListaSimpleEventos
            - registroAcciones: PilaEstatica
            - colaImpresion: ColaEstatica
            + validarLogin(rol: String) : boolean
            + crearEvento(nombre: String, fecha: String, ubicacion: String) : void
            + buscarEvento(nombre: String) : Evento
            + getEventosPasados() : ListaSimpleEventos
            + getEventosFuturos() : ListaSimpleEventos
        }
        class Evento {
            - nombre: String
            - fecha: String
            - ubicacion: String
            - participantes: ListaDobleParticipantes
            - partidosProgramados: ColaDinamicaPartidos
            - historialResultados: PilaDinamicaResultados
            + agregarParticipante(p: Participante) : void
            + programarPartido(local: String, visitante: String) : void
            + registrarResultado(local: String, visitante: String, gL: int, gV: int) : void
        }
        class Participante {
            - nombre: String
            - edad: int
            - nombreEquipo: String
            - esCapitan: boolean
        }
        class Partido {
            - equipoLocal: String
            - equipoVisitante: String
            - golesLocal: int
            - golesVisitante: int
            - estado: String
            + finalizar(gL: int, gV: int) : void
        }
        class ListaSimpleEventos {
            - cabeza: NodoEvento
            + insertar(e: Evento) : void
        }
        class NodoEvento {
            - dato: Evento
            - siguiente: NodoEvento
        }
        class NodoParticipante {
            - dato: Participante
            - anterior: NodoParticipante
            - siguiente: NodoParticipante
        }
        class ColaDinamicaPartidos {
            - frente: NodoPartido
            - fin: NodoPartido
            + encolar(p: Partido) : void
            + desencolar() : Partido
            + frente() : Partido
            + estaVacia() : boolean
        }
        class NodoPartido {
            - dato: Partido
            - siguiente: NodoPartido
        }
        class PilaDinamicaResultados {
            - cima: NodoResultado
            - tamano: int
            + estaVacia() : boolean
            + apilar(p: Partido) : void
            + desapilar() : Partido
            + mostrarPila() : String
        }
        class NodoResultado {
            - dato: Partido
            - siguiente: NodoResultado
        }
        class PilaEstatica {
            - pila: String[]
            - top: int
            - maxSize: int
            + PilaEstatica(tamano: int)
            + push(accion: String) : void
            + pop() : String
            + toString() : String
        }
        class ColaEstatica {
            - cola: String[]
            - frente: int
            - fin: int
            - maxSize: int
            + ColaEstatica(tamano: int)
            + encolar(reporte: String) : void
            + desencolar() : String
            + estaVacia() : boolean
        }
        class ListaDobleParticipantes {
            - cabeza: NodoParticipante
            - cola: NodoParticipante
            + insertar(p: Participante) : void
            + buscarRecursivo(nombre: String) : Participante
        }
    }

    CtrPrincipal --> Sistema
    CtrPrincipal --> FrmLogin
    CtrPrincipal --> FrmMenuAdmin
    CtrEventos --> Sistema
    CtrEventos --> FrmEvento
    CtrEventos --> FrmParticipante
    CtrPartidos --> Evento
    CtrPartidos --> FrmProgramarPartido
    CtrPartidos --> FrmRegistrarResultado
    Sistema *-- ListaSimpleEventos
    Sistema *-- PilaEstatica : usa para registro
    Sistema *-- ColaEstatica : usa para impresion
    ListaSimpleEventos *-- NodoEvento
    NodoEvento --> Evento
    Evento *-- ListaDobleParticipantes
    Evento *-- ColaDinamicaPartidos
    Evento *-- PilaDinamicaResultados
    ListaDobleParticipantes *-- NodoParticipante
    NodoParticipante --> Participante
    ColaDinamicaPartidos *-- NodoPartido
    NodoPartido --> Partido
    PilaDinamicaResultados *-- NodoResultado
    NodoResultado --> Partido 


```

# 5. Imágenes del diseño
1-
![alt text](image.png)
2-
![alt text](Imagen2.jpeg)
3-
![alt text](Imagen3.jpeg)
4-
![alt text](Imagen4.jpeg)
5-
![alt text](Imagen5.jpeg)
6-
![alt text](Imagen6.jpeg)
7-
![alt text](Imagen7.jpeg)
8-
![alt text](Imagen8.jpeg)
9-
![alt text](Imagen9.jpeg)
10-
![alt text](Imagen10.jpeg)
11-
![alt text](Imagen11.jpeg)
12-
![alt text](Imagen12.jpeg)

## Referencias
Arquitectura Java. (2026). ¿Qué es el Patrón MVC? Recuperado de https://www.arquitecturajava.com/el-modelo-vista-controlador-y-sus-responsabilidades/#%C2%BFQue_es_el_Patron_MVC