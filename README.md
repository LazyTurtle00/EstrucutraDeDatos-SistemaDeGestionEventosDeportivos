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

# 5. Avance Plan de Proyecto
# 5.1 Avance  2 Semana 11

Durante esta semana, el enfoque principal consistió en la evolución del diseño estático a un prototipo totalmente interactivo en Figma. Se pensó mucho la experiencia de usuario y la arquitectura de navegación.
 
**Detalles del Trabajo Realizado**
 
Interactividad y Prototipado Funcional:
Se trabajó sobre el archivo original, y se cambió a un prototipo navegable. Esto incluyó la configuración de botones y la vinculación de marcos (frames) para simular el comportamiento real de la aplicación.
 
Desarrollo del Módulo de Autenticación:
Se diseñó y configuró el flujo completo de Inicio de Sesión. Esto hace que el inicio de sesión sea un proceso fácil de entender para el usuario, ya que es muy intuitivo. Además se hizo la opción de recuperar contraseña.
 
Arquitectura de Menús e Interacción:
Se trabajó en la operatividad de todos los componentes de navegación. Los menús ahora cuentan con clics y botones funcionales, permitiendo que el usuario explore las diferentes secciones del proyecto de manera intuitiva.
 
Pruebas de Usabilidad del Prototipo:
Se realizaron pruebas para asegurar que los flujos de usuario no presentaran problemas al navegar en el prototipo, optimizando la respuesta de los botones y la claridad de los menús interactivos.
 
**Estado Actual:**
El proyecto ha pasado de una fase de diseño visual a una fase de validación funcional, permitiendo a los interesados experimentar la navegación del producto final de forma cercana a la realidad.

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
	        - modeloSistema: SistemaDeEventos
	        - vistaLogin: FrmLogin
	        - vistaMenu: FrmMenuAdmin
	        + iniciar() : void
        }

        class CtrEventos {
	        - modeloSistema: SistemaDeEventos
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
        class SistemaDeEventos {
	        - eventos: ListaSimpleEventos
	        - registroAcciones: PilaEstatica
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

        

        class ListaDobleParticipantes {
	        - cabeza: NodoParticipante
	        - cola: NodoParticipante
	        + insertar(p: Participante) : void
	        + buscarRecursivo(nombre: String) : Participante
        }

	}

    CtrPrincipal --> SistemaDeEventos
    CtrPrincipal --> FrmLogin
    CtrPrincipal --> FrmMenuAdmin
    CtrEventos --> SistemaDeEventos
    CtrEventos --> FrmEvento
    CtrEventos --> FrmParticipante
    CtrPartidos --> Evento
    CtrPartidos --> FrmProgramarPartido
    CtrPartidos --> FrmRegistrarResultado
    SistemaDeEventos *-- ListaSimpleEventos
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

### Funcionamiento de Pilas y Colas en el Sistema

Para gestionar los datos, dividimos el uso de estructuras en dos tipos según la necesidad de memoria: dinámicas  y estáticas.

#### 1. Estructuras Dinámicas (Basadas en Nodos)
Son usadas para los partidos del evento porque es no se sabe o se puede predecir cuántos partidos habrá. Si usáramos un arreglo fijo de 20 espacios y se juegan 21, el programa fallaría. Al ser dinamica puede aumentar o decrementar la cantidad.

* **ColaDinamicaPartidos (Calendario):** Funciona con lógica **FIFO** (Primero en entrar, primero en salir). Garantiza un orden cronológico estricto: el primer partido que programamos es el primero en la lista para jugarse.
* **PilaDinamicaResultados (Historial):** Funciona con lógica **LIFO** (Último en entrar, primero en salir). Al usar una pila, el último resultado registrado queda en la cima, permitiéndonos mostrar los datos más nuevos de forma instantánea sin tener que programar algoritmos pesados para ordenarlos.

#### 2. Estructuras Estáticas (Basadas en Arreglos)
Las usamos exclusivamente para tareas de control interno del sistema donde **sí** necesitamos poner un límite estricto de memoria para que la aplicación no se sature.

* **ColaEstatica (Cola de reportes/impresión):** Se encarga de encolar tareas cortas del sistema. Al ponerle un tamaño máximo, prevenimos que el sistema se pegue o sature si el usuario hace demasiadas peticiones al mismo tiempo.

# 5. Imágenes del diseño
1-
![alt text](/img/Imagen1.jpeg)
2-
![alt text](/img/Imagen2.jpeg)
3-
![alt text](/img/Imagen3.jpeg)
4-
![alt text](/img/Imagen4.jpeg)
5-
![alt text](/img/Imagen5.jpeg)
6-
![alt text](/img/Imagen6.jpeg)
7-
![alt text](/img/Imagen7.jpeg)
8-
![alt text](/img/Imagen8.jpeg)
9-
![alt text](/img/Imagen9.jpeg)
10-
![alt text](/img/Imagen10.jpeg)
11-
![alt text](/img/Imagen11.jpeg)
12-
![alt text](/img/Imagen12.jpeg)

## Referencias
Arquitectura Java. (2026). ¿Qué es el Patrón MVC? Recuperado de https://www.arquitecturajava.com/el-modelo-vista-controlador-y-sus-responsabilidades/#%C2%BFQue_es_el_Patron_MVC
