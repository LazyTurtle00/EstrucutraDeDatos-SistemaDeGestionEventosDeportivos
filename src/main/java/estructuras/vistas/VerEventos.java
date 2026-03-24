package estructuras.vistas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import estructuras.datos.SistemaDatos;

public class VerEventos extends JFrame {

    private JTextArea areaEventos;

    public VerEventos() {
        setTitle("Ver Eventos");
        setSize(850, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(220, 220, 220));

        JPanel barraSuperior = new JPanel();
        barraSuperior.setLayout(null);
        barraSuperior.setBounds(0, 0, 850, 50);
        barraSuperior.setBackground(new Color(24, 61, 142));
        add(barraSuperior);

        JLabel titulo = new JLabel("Eventos Registrados");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(25, 10, 300, 30);
        barraSuperior.add(titulo);

        JLabel lblLista = new JLabel("Lista de Eventos:");
        lblLista.setFont(new Font("Arial", Font.BOLD, 18));
        lblLista.setBounds(60, 80, 200, 30);
        add(lblLista);

        areaEventos = new JTextArea();
        areaEventos.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaEventos);
        scroll.setBounds(60, 120, 700, 250);
        add(scroll);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(450, 400, 120, 35);
        add(btnActualizar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(590, 400, 100, 35);
        add(btnVolver);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(710, 400, 100, 35);
        add(btnSalir);

        actualizarLista();

        btnActualizar.addActionListener(e -> actualizarLista());

        btnVolver.addActionListener(e -> {
            new MenuEspectador();
            dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void actualizarLista() {
        areaEventos.setText(SistemaDatos.obtenerEventosTexto());
    }
}