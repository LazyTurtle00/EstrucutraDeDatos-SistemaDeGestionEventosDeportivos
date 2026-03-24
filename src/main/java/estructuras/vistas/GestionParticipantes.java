package estructuras.vistas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import estructuras.datos.SistemaDatos;

public class GestionParticipantes extends JFrame {

    private JTextField txtNombreEquipo;
    private JTextField txtCapitan;
    private JTextField txtCantidadJugadores;
    private JTextArea areaParticipantes;

    public GestionParticipantes() {
        setTitle("Gestión de Participantes");
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

        JLabel titulo = new JLabel("Gestión de Participantes");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(25, 10, 350, 30);
        barraSuperior.add(titulo);

        JLabel lblNombreEquipo = new JLabel("Nombre del equipo:");
        lblNombreEquipo.setBounds(40, 90, 140, 25);
        add(lblNombreEquipo);

        txtNombreEquipo = new JTextField();
        txtNombreEquipo.setBounds(180, 90, 180, 25);
        add(txtNombreEquipo);

        JLabel lblCapitan = new JLabel("Capitán:");
        lblCapitan.setBounds(40, 130, 140, 25);
        add(lblCapitan);

        txtCapitan = new JTextField();
        txtCapitan.setBounds(180, 130, 180, 25);
        add(txtCapitan);

        JLabel lblCantidadJugadores = new JLabel("Cantidad jugadores:");
        lblCantidadJugadores.setBounds(40, 170, 140, 25);
        add(lblCantidadJugadores);

        txtCantidadJugadores = new JTextField();
        txtCantidadJugadores.setBounds(180, 170, 180, 25);
        add(txtCantidadJugadores);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(40, 230, 120, 35);
        add(btnGuardar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(180, 230, 120, 35);
        add(btnEliminar);

        JLabel lblLista = new JLabel("Lista de Participantes:");
        lblLista.setFont(new Font("Arial", Font.BOLD, 18));
        lblLista.setBounds(480, 70, 220, 30);
        add(lblLista);

        areaParticipantes = new JTextArea();
        areaParticipantes.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaParticipantes);
        scroll.setBounds(400, 110, 430, 240);
        add(scroll);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(610, 410, 100, 35);
        add(btnVolver);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(730, 410, 100, 35);
        add(btnSalir);

        actualizarLista();

        btnGuardar.addActionListener(e -> guardarParticipante());
        btnEliminar.addActionListener(e -> eliminarParticipante());

        btnVolver.addActionListener(e -> {
            new MenuAdministrador();
            dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void guardarParticipante() {
        String nombreEquipo = txtNombreEquipo.getText();
        String capitan = txtCapitan.getText();
        String cantidadTexto = txtCantidadJugadores.getText();

        int cantidadJugadores;

        try {
            cantidadJugadores = Integer.parseInt(cantidadTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad de los jugadores debe ser un número entero, por favor verifique el dato.");
            return;
        }

        boolean guardado = SistemaDatos.guardarParticipante(nombreEquipo, capitan, cantidadJugadores);

        if (guardado) {
            JOptionPane.showMessageDialog(this, "Participante guardado correctamente.");
            limpiarCampos();
            actualizarLista();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el participante. Verifique los datos.");
        }
    }

    private void eliminarParticipante() {
        String nombreEquipo = txtNombreEquipo.getText();

        boolean eliminado = SistemaDatos.eliminarParticipantePorNombre(nombreEquipo);

        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Participante eliminado correctamente.");
            limpiarCampos();
            actualizarLista();
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un participante con ese nombre.");
        }
    }

    private void actualizarLista() {
        areaParticipantes.setText(SistemaDatos.obtenerParticipantesTexto());
    }

    private void limpiarCampos() {
        txtNombreEquipo.setText("");
        txtCapitan.setText("");
        txtCantidadJugadores.setText("");
    }
}