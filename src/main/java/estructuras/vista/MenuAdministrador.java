package estructuras.vista;

import estructuras.controlador.ControladorSistema;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuAdministrador extends JFrame {
    public MenuAdministrador() {
        setTitle("Administrador");
        setSize(850, 560);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(220, 220, 220));

        JPanel barra = new JPanel(null);
        barra.setBounds(0, 0, 850, 50);
        barra.setBackground(new Color(24, 61, 142));
        add(barra);

        JLabel titulo = new JLabel("Administrador");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(20, 10, 300, 30);
        barra.add(titulo);

        JButton btnEventos = crearBoton("Gestión de Eventos", 70);
        JButton btnParticipantes = crearBoton("Gestión de Participantes", 120);
        JButton btnPartidos = crearBoton("Programar Partido", 170);
        JButton btnRegistrarResultados = crearBoton("Registrar Resultados", 220);
        JButton btnVerResultados = crearBoton("Ver Resultados", 270);
        JButton btnGrafo = crearBoton("Ver Grafo", 320);
        JButton btnClasificacion = crearBoton("Ver Clasificación", 370);
        JButton btnGuardar = crearBoton("Guardar Datos", 420);
        JButton btnCargar = crearBoton("Cargar Datos", 470);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(610, 460, 100, 35);
        add(btnVolver);
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(720, 460, 100, 35);
        add(btnSalir);

        JLabel centro = new JLabel("Menú del Administrador");
        centro.setFont(new Font("Arial", Font.BOLD, 24));
        centro.setBounds(430, 210, 320, 30);
        add(centro);

        btnEventos.addActionListener(e -> { new GestionEventos(); dispose(); });
        btnParticipantes.addActionListener(e -> { new GestionParticipantes(); dispose(); });
        btnPartidos.addActionListener(e -> { new ProgramacionPartidos(); dispose(); });
        btnRegistrarResultados.addActionListener(e -> { new RegistrarResultados(); dispose(); });
        btnVerResultados.addActionListener(e -> { new VerResultados(true); dispose(); });
        btnGrafo.addActionListener(e -> { new VerGrafo(true); dispose(); });
        btnClasificacion.addActionListener(e -> { new VerClasificacion(true); dispose(); });
        btnGuardar.addActionListener(e -> JOptionPane.showMessageDialog(this, ControladorSistema.guardarDatos() ? "Datos guardados correctamente." : "No se pudieron guardar los datos."));
        btnCargar.addActionListener(e -> JOptionPane.showMessageDialog(this, ControladorSistema.cargarDatos() ? "Datos cargados correctamente." : "No se encontraron datos guardados."));
        btnVolver.addActionListener(e -> { new VentanaPrincipal(); dispose(); });
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private JButton crearBoton(String texto, int y) {
        JButton boton = new JButton(texto);
        boton.setBounds(60, y, 220, 35);
        add(boton);
        return boton;
    }
}
