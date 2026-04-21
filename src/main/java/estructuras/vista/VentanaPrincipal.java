package estructuras.vista;

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

        JPanel barra = new JPanel(null);
        barra.setBounds(0, 0, 800, 50);
        barra.setBackground(new Color(24, 61, 142));
        add(barra);

        JLabel titulo = new JLabel("Sistema de Gestión de Eventos Deportivos");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(15, 10, 550, 30);
        barra.add(titulo);

        JButton btnAdmin = new JButton("Administrador");
        btnAdmin.setBounds(80, 130, 170, 40);
        add(btnAdmin);

        JButton btnEspectador = new JButton("Espectador");
        btnEspectador.setBounds(80, 190, 170, 40);
        add(btnEspectador);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(620, 380, 100, 35);
        add(btnSalir);

        JLabel centro = new JLabel("Sistema MVC con estructuras dinámicas");
        centro.setFont(new Font("Arial", Font.BOLD, 20));
        centro.setBounds(310, 180, 420, 30);
        add(centro);

        btnAdmin.addActionListener(e -> { new MenuAdministrador(); dispose(); });
        btnEspectador.addActionListener(e -> { new MenuEspectador(); dispose(); });
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}
