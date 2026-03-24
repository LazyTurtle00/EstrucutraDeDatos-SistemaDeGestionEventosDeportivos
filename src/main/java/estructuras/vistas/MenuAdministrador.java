package estructuras.vistas;

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

        JLabel titulo = new JLabel("Administrador");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(25, 10, 250, 30);
        barraSuperior.add(titulo);

        JButton btnGestionEventos = new JButton("Gestión de Eventos");
        btnGestionEventos.setBounds(60, 90, 180, 35);
        add(btnGestionEventos);

        JButton btnGestionParticipantes = new JButton("Gestión de Participantes");
        btnGestionParticipantes.setBounds(60, 140, 180, 35);
        add(btnGestionParticipantes);

        JButton btnProgramar = new JButton("Programar Partido");
        btnProgramar.setBounds(60, 190, 180, 35);
        add(btnProgramar);

        JButton btnVerResultados = new JButton("Ver Resultados");
        btnVerResultados.setBounds(60, 240, 180, 35);
        add(btnVerResultados);

        JButton btnRegistrarResultados = new JButton("Registrar Resultados");
        btnRegistrarResultados.setBounds(60, 290, 180, 35);
        add(btnRegistrarResultados);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(560, 380, 100, 35);
        add(btnVolver);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(670, 380, 100, 35);
        add(btnSalir);

        JLabel etiquetaCentro = new JLabel("Menú Administrador");
        etiquetaCentro.setFont(new Font("Arial", Font.BOLD, 24));
        etiquetaCentro.setBounds(380, 160, 300, 40);
        add(etiquetaCentro);

        btnGestionEventos.addActionListener(e -> {
            new GestionEventos();
            dispose();
        });

        btnGestionParticipantes.addActionListener(e -> {
            new GestionParticipantes();
            dispose();
        });

        btnProgramar.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Módulo se desarrollará en tercer avance.")
        );

        btnVerResultados.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Módulo se desarrollará en tercer avance.")
        );

        btnRegistrarResultados.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Módulo se desarrollará en tercer avance.")
        );

        btnVolver.addActionListener(e -> {
            new VentanaPrincipal();
            dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}