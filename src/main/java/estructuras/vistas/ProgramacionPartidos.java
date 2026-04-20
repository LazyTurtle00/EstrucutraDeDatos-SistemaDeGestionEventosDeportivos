package estructuras.vistas;

import estructuras.datos.SistemaDatos;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProgramacionPartidos extends JFrame {

    private JComboBox<String> comboEventos;
    private JComboBox<String> comboEquipoUno;
    private JComboBox<String> comboEquipoDos;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtIdEliminar;
    private JTextArea areaPartidos;

    public ProgramacionPartidos() {
        setTitle("Programación de Partidos");
        setSize(950, 560);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(220, 220, 220));

        JPanel barraSuperior = new JPanel();
        barraSuperior.setLayout(null);
        barraSuperior.setBounds(0, 0, 950, 50);
        barraSuperior.setBackground(new Color(24, 61, 142));
        add(barraSuperior);

        JLabel titulo = new JLabel("Programación de Partidos");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(25, 10, 380, 30);
        barraSuperior.add(titulo);

        JLabel lblEvento = new JLabel("Evento:");
        lblEvento.setBounds(40, 90, 120, 25);
        add(lblEvento);

        comboEventos = new JComboBox<>();
        comboEventos.setBounds(160, 90, 220, 25);
        add(comboEventos);

        JLabel lblEquipoUno = new JLabel("Equipo 1:");
        lblEquipoUno.setBounds(40, 130, 120, 25);
        add(lblEquipoUno);

        comboEquipoUno = new JComboBox<>();
        comboEquipoUno.setBounds(160, 130, 220, 25);
        add(comboEquipoUno);

        JLabel lblEquipoDos = new JLabel("Equipo 2:");
        lblEquipoDos.setBounds(40, 170, 120, 25);
        add(lblEquipoDos);

        comboEquipoDos = new JComboBox<>();
        comboEquipoDos.setBounds(160, 170, 220, 25);
        add(comboEquipoDos);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(40, 210, 120, 25);
        add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(160, 210, 220, 25);
        add(txtFecha);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(40, 250, 120, 25);
        add(lblHora);

        txtHora = new JTextField();
        txtHora.setBounds(160, 250, 220, 25);
        add(txtHora);

        JButton btnGuardar = new JButton("Programar");
        btnGuardar.setBounds(40, 310, 130, 35);
        add(btnGuardar);

        JLabel lblIdEliminar = new JLabel("ID a eliminar:");
        lblIdEliminar.setBounds(40, 370, 120, 25);
        add(lblIdEliminar);

        txtIdEliminar = new JTextField();
        txtIdEliminar.setBounds(160, 370, 80, 25);
        add(txtIdEliminar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(260, 365, 120, 35);
        add(btnEliminar);

        JLabel lblLista = new JLabel("Partidos Programados");
        lblLista.setFont(new Font("Arial", Font.BOLD, 18));
        lblLista.setBounds(500, 75, 250, 30);
        add(lblLista);

        areaPartidos = new JTextArea();
        areaPartidos.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaPartidos);
        scroll.setBounds(430, 115, 460, 260);
        add(scroll);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(530, 420, 120, 35);
        add(btnActualizar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(670, 420, 100, 35);
        add(btnVolver);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(790, 420, 100, 35);
        add(btnSalir);

        cargarCombos();
        actualizarLista();

        btnGuardar.addActionListener(e -> programarPartido());
        btnEliminar.addActionListener(e -> eliminarPartido());
        btnActualizar.addActionListener(e -> {
            cargarCombos();
            actualizarLista();
        });

        btnVolver.addActionListener(e -> {
            new MenuAdministrador();
            dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void cargarCombos() {
        comboEventos.removeAllItems();
        comboEquipoUno.removeAllItems();
        comboEquipoDos.removeAllItems();

        for (int i = 0; i < SistemaDatos.totalEventos; i++) {
            comboEventos.addItem(SistemaDatos.eventos[i].getNombre());
        }

        for (int i = 0; i < SistemaDatos.totalParticipantes; i++) {
            comboEquipoUno.addItem(SistemaDatos.participantes[i].getNombreEquipo());
            comboEquipoDos.addItem(SistemaDatos.participantes[i].getNombreEquipo());
        }
    }

    private void programarPartido() {
        if (SistemaDatos.totalEventos == 0) {
            JOptionPane.showMessageDialog(this, "Debe registrar al menos un evento antes de programar partidos.");
            return;
        }

        if (SistemaDatos.totalParticipantes < 2) {
            JOptionPane.showMessageDialog(this, "Debe registrar al menos dos participantes antes de programar partidos.");
            return;
        }

        String evento = (String) comboEventos.getSelectedItem();
        String equipoUno = (String) comboEquipoUno.getSelectedItem();
        String equipoDos = (String) comboEquipoDos.getSelectedItem();
        String fecha = txtFecha.getText();
        String hora = txtHora.getText();

        if (equipoUno != null && equipoDos != null && equipoUno.equalsIgnoreCase(equipoDos)) {
            JOptionPane.showMessageDialog(this, "No se puede programar un partido entre el mismo equipo.");
            return;
        }

        boolean guardado = SistemaDatos.guardarPartido(evento, equipoUno, equipoDos, fecha, hora);

        if (guardado) {
            JOptionPane.showMessageDialog(this, "Partido programado correctamente.");
            limpiarCampos();
            actualizarLista();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo programar el partido. Verifique los datos.");
        }
    }

    private void eliminarPartido() {
        String idTexto = txtIdEliminar.getText();

        int id;

        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un ID válido.");
            return;
        }

        boolean eliminado = SistemaDatos.eliminarPartidoPorId(id);

        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Partido eliminado correctamente.");
            txtIdEliminar.setText("");
            actualizarLista();
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un partido con ese ID.");
        }
    }

    private void actualizarLista() {
        areaPartidos.setText(SistemaDatos.obtenerPartidosTexto());
    }

    private void limpiarCampos() {
        txtFecha.setText("");
        txtHora.setText("");
    }
}