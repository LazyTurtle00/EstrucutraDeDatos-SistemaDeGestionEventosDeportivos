package estructuras.vistas;

import estructuras.datos.SistemaDatos;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VerResultados extends JFrame {

    private JTextArea areaResultados;
    private boolean volverAdministrador;

    public VerResultados(boolean volverAdministrador) {
        this.volverAdministrador = volverAdministrador;

        setTitle("Ver Resultados");
        setSize(900, 540);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(220, 220, 220));

        JPanel barraSuperior = new JPanel();
        barraSuperior.setLayout(null);
        barraSuperior.setBounds(0, 0, 900, 50);
        barraSuperior.setBackground(new Color(24, 61, 142));
        add(barraSuperior);

        JLabel titulo = new JLabel("Resultados Registrados");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(25, 10, 350, 30);
        barraSuperior.add(titulo);

        JLabel lblLista = new JLabel("Lista de Resultados");
        lblLista.setFont(new Font("Arial", Font.BOLD, 18));
        lblLista.setBounds(60, 80, 250, 30);
        add(lblLista);

        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBounds(60, 120, 760, 250);
        add(scroll);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(470, 410, 120, 35);
        add(btnActualizar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(610, 410, 100, 35);
        add(btnVolver);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(730, 410, 100, 35);
        add(btnSalir);

        actualizarLista();

        btnActualizar.addActionListener(e -> actualizarLista());

        btnVolver.addActionListener(e -> {
            volverMenuAnterior();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void actualizarLista() {
        areaResultados.setText(SistemaDatos.obtenerResultadosTexto());
    }

    private void volverMenuAnterior() {
        if (volverAdministrador) {
            new MenuAdministrador();
        } else {
            new MenuEspectador();
        }

        dispose();
    }
}