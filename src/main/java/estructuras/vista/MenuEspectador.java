package estructuras.vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuEspectador extends JFrame {
    public MenuEspectador() {
        setTitle("Espectador");
        setSize(850, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(220, 220, 220));

        JPanel barra = new JPanel(null);
        barra.setBounds(0, 0, 850, 50);
        barra.setBackground(new Color(24, 61, 142));
        add(barra);

        JLabel titulo = new JLabel("Menú Espectador");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(20, 10, 300, 30);
        barra.add(titulo);

        JButton btnEventos = crearBoton("Ver Eventos", 110);
        JButton btnParticipantes = crearBoton("Ver Participantes", 165);
        JButton btnResultados = crearBoton("Ver Resultados", 220);
        JButton btnGrafo = crearBoton("Ver Grafo", 275);
        JButton btnClasificacion = crearBoton("Ver Clasificación", 330);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(610, 400, 100, 35);
        add(btnVolver);
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(720, 400, 100, 35);
        add(btnSalir);

        JLabel centro = new JLabel("Consultas para espectadores");
        centro.setFont(new Font("Arial", Font.BOLD, 24));
        centro.setBounds(410, 190, 340, 30);
        add(centro);

        btnEventos.addActionListener(e -> { new VerEventos(); dispose(); });
        btnParticipantes.addActionListener(e -> { new VerParticipantes(false); dispose(); });
        btnResultados.addActionListener(e -> { new VerResultados(false); dispose(); });
        btnGrafo.addActionListener(e -> { new VerGrafo(false); dispose(); });
        btnClasificacion.addActionListener(e -> { new VerClasificacion(false); dispose(); });
        btnVolver.addActionListener(e -> { new VentanaPrincipal(); dispose(); });
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private JButton crearBoton(String texto, int y) {
        JButton boton = new JButton(texto);
        boton.setBounds(60, y, 210, 35);
        add(boton);
        return boton;
    }
}
