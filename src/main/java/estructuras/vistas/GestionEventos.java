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

public class GestionEventos extends JFrame {

    private JTextField txtNombre;
    private JTextField txtFecha;
    private JTextField txtLugar;
    private JTextArea areaEventos;

    public GestionEventos() {
        setTitle("Gestión de Eventos");
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

        JLabel titulo = new JLabel("Gestión de Eventos");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(25, 10, 300, 30);
        barraSuperior.add(titulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(40, 90, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 90, 180, 25);
        add(txtNombre);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(40, 130, 100, 25);
        add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(130, 130, 180, 25);
        add(txtFecha);

        JLabel lblLugar = new JLabel("Lugar:");
        lblLugar.setBounds(40, 170, 100, 25);
        add(lblLugar);

        txtLugar = new JTextField();
        txtLugar.setBounds(130, 170, 180, 25);
        add(txtLugar);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(40, 230, 110, 35);
        add(btnGuardar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(170, 230, 110, 35);
        add(btnEliminar);

        JLabel lblLista = new JLabel("Lista de Eventos:");
        lblLista.setFont(new Font("Arial", Font.BOLD, 18));
        lblLista.setBounds(450, 70, 200, 30);
        add(lblLista);

        areaEventos = new JTextArea();
        areaEventos.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaEventos);
        scroll.setBounds(380, 110, 400, 220);
        add(scroll);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(560, 390, 100, 35);
        add(btnVolver);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(680, 390, 100, 35);
        add(btnSalir);

        actualizarLista();

        btnGuardar.addActionListener(e -> guardarEvento());
        btnEliminar.addActionListener(e -> eliminarEvento());

        btnVolver.addActionListener(e -> {
            new MenuAdministrador();
            dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void guardarEvento() {
        String nombre = txtNombre.getText();
        String fecha = txtFecha.getText();
        String lugar = txtLugar.getText();

        boolean guardado = SistemaDatos.guardarEvento(nombre, fecha, lugar);

        if (guardado) {
            JOptionPane.showMessageDialog(this, "El evento ha sido guardado correctamente.");
            limpiarCampos();
            actualizarLista();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha podido guardar el evento. Por favor verifique los datos.");
        }
    }

    private void eliminarEvento() {
        String nombre = txtNombre.getText();

        boolean eliminado = SistemaDatos.eliminarEventoPorNombre(nombre);

        if (eliminado) {
            JOptionPane.showMessageDialog(this, "El evento ha sido eliminado correctamente.");
            limpiarCampos();
            actualizarLista();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha encontrado un evento con ese nombre.");
        }
    }

    private void actualizarLista() {
        areaEventos.setText(SistemaDatos.obtenerEventosTexto());
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtFecha.setText("");
        txtLugar.setText("");
    }
}