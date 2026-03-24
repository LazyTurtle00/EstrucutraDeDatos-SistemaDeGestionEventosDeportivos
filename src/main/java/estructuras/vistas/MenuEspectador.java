package estructuras.vistas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuEspectador extends JFrame {

    public MenuEspectador() {
        setTitle("Espectador");
        setSize(800, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(220, 220, 220));

        JPanel barraSuperior = new JPanel();
        barraSuperior.setLayout(null);
        barraSuperior.setBounds(0, 0, 800, 50);
        barraSuperior.setBackground(new Color(24, 61, 142));
        add(barraSuperior);

        JLabel titulo = new JLabel("Menú Espectadores");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(25, 10, 300, 30);
        barraSuperior.add(titulo);

        JButton btnVerEventos = new JButton("Ver Eventos");
        btnVerEventos.setBounds(60, 120, 180, 35);
        add(btnVerEventos);

        JButton btnVerParticipantes = new JButton("Ver Participantes");
        btnVerParticipantes.setBounds(60, 180, 180, 35);
        add(btnVerParticipantes);

        JButton btnVerResultados = new JButton("Ver Resultados");
        btnVerResultados.setBounds(60, 240, 180, 35);
        add(btnVerResultados);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(560, 380, 100, 35);
        add(btnVolver);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(670, 380, 100, 35);
        add(btnSalir);

        JLabel etiquetaCentro = new JLabel("Menú Espectador");
        etiquetaCentro.setFont(new Font("Arial", Font.BOLD, 24));
        etiquetaCentro.setBounds(400, 170, 260, 40);
        add(etiquetaCentro);

        btnVerEventos.addActionListener(e -> {
            new VerEventos();
            dispose();
        });

        btnVerParticipantes.addActionListener(e -> {
            new VerParticipantes();
            dispose();
        });

        btnVerResultados.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Módulo se desarrollará en el tercer avance.")
        );

        btnVolver.addActionListener(e -> {
            new VentanaPrincipal();
            dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}