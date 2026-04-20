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

public class RegistrarResultados extends JFrame {

    private JComboBox<String> comboPartidos;
    private JTextField txtPuntosEquipoUno;
    private JTextField txtPuntosEquipoDos;
    private JTextArea areaResultados;

    public RegistrarResultados() {
        setTitle("Registrar Resultados");
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

        JLabel titulo = new JLabel("Registrar Resultados");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(25, 10, 350, 30);
        barraSuperior.add(titulo);

        JLabel lblPartido = new JLabel("Partido:");
        lblPartido.setBounds(40, 90, 120, 25);
        add(lblPartido);

        comboPartidos = new JComboBox<>();
        comboPartidos.setBounds(160, 90, 300, 25);
        add(comboPartidos);

        JLabel lblPuntosUno = new JLabel("Puntos equipo 1:");
        lblPuntosUno.setBounds(40, 140, 130, 25);
        add(lblPuntosUno);

        txtPuntosEquipoUno = new JTextField();
        txtPuntosEquipoUno.setBounds(180, 140, 100, 25);
        add(txtPuntosEquipoUno);

        JLabel lblPuntosDos = new JLabel("Puntos equipo 2:");
        lblPuntosDos.setBounds(40, 180, 130, 25);
        add(lblPuntosDos);

        txtPuntosEquipoDos = new JTextField();
        txtPuntosEquipoDos.setBounds(180, 180, 100, 25);
        add(txtPuntosEquipoDos);

        JButton btnGuardar = new JButton("Guardar Resultado");
        btnGuardar.setBounds(40, 240, 170, 35);
        add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(230, 240, 120, 35);
        add(btnActualizar);

        JLabel lblLista = new JLabel("Resultados Registrados");
        lblLista.setFont(new Font("Arial", Font.BOLD, 18));
        lblLista.setBounds(530, 75, 260, 30);
        add(lblLista);

        areaResultados = new JTextArea();
        areaResultados.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBounds(500, 115, 390, 260);
        add(scroll);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(670, 420, 100, 35);
        add(btnVolver);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(790, 420, 100, 35);
        add(btnSalir);

        cargarPartidos();
        actualizarResultados();

        btnGuardar.addActionListener(e -> guardarResultado());

        btnActualizar.addActionListener(e -> {
            cargarPartidos();
            actualizarResultados();
        });

        btnVolver.addActionListener(e -> {
            new MenuAdministrador();
            dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void cargarPartidos() {
        comboPartidos.removeAllItems();

        for (int i = 0; i < SistemaDatos.totalPartidos; i++) {
            comboPartidos.addItem(
                    "ID " + SistemaDatos.partidos[i].getId()
                            + " - " + SistemaDatos.partidos[i].getEquipoUno()
                            + " vs " + SistemaDatos.partidos[i].getEquipoDos()
            );
        }
    }

    private void guardarResultado() {
        if (SistemaDatos.totalPartidos == 0) {
            JOptionPane.showMessageDialog(this, "Debe programar al menos un partido antes de registrar resultados.");
            return;
        }

        int posicionSeleccionada = comboPartidos.getSelectedIndex();

        if (posicionSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un partido.");
            return;
        }

        int idPartido = SistemaDatos.partidos[posicionSeleccionada].getId();

        int puntosUno;
        int puntosDos;

        try {
            puntosUno = Integer.parseInt(txtPuntosEquipoUno.getText());
            puntosDos = Integer.parseInt(txtPuntosEquipoDos.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los puntos deben ser números enteros.");
            return;
        }

        boolean guardado = SistemaDatos.guardarResultado(idPartido, puntosUno, puntosDos);

        if (guardado) {
            JOptionPane.showMessageDialog(this, "Resultado registrado correctamente.");
            limpiarCampos();
            actualizarResultados();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar el resultado. Verifique los datos.");
        }
    }

    private void actualizarResultados() {
        areaResultados.setText(SistemaDatos.obtenerResultadosTexto());
    }

    private void limpiarCampos() {
        txtPuntosEquipoUno.setText("");
        txtPuntosEquipoDos.setText("");
    }
}