package estructuras.vistas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Sistema de Gestión de Eventos Deportivos");
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

        JLabel titulo = new JLabel("Sistema de Gestión de Eventos Deportivos");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(15, 10, 500, 30);
        barraSuperior.add(titulo);

        JButton btnAdministrador = new JButton("Administrador");
        btnAdministrador.setBounds(80, 140, 150, 40);
        add(btnAdministrador);

        JButton btnEspectador = new JButton("Espectador");
        btnEspectador.setBounds(80, 200, 150, 40);
        add(btnEspectador);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(620, 380, 100, 35);
        add(btnSalir);

        JLabel etiquetaCentro = new JLabel("Menú Principal");
        etiquetaCentro.setFont(new Font("Arial", Font.BOLD, 26));
        etiquetaCentro.setBounds(290, 170, 250, 40);
        add(etiquetaCentro);

        btnAdministrador.addActionListener(e -> {
            new MenuAdministrador();
            dispose();
        });

        btnEspectador.addActionListener(e -> {
            new MenuEspectador();
            dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}