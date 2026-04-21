package estructuras.vista;

import estructuras.controlador.ControladorSistema;
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

public class GestionEventos extends JFrame {
    private JTextField txtId, txtNombre, txtFecha, txtUbicacion;
    private JTextArea area;

    public GestionEventos() {
        setTitle("Gestión de Eventos");
        setSize(900, 560);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(220, 220, 220));
        crearBarra("Gestión de Eventos");

        agregarEtiqueta("ID actualizar/eliminar:", 40, 80); txtId = agregarCampo(190, 80);
        agregarEtiqueta("Nombre:", 40, 120); txtNombre = agregarCampo(190, 120);
        agregarEtiqueta("Fecha:", 40, 160); txtFecha = agregarCampo(190, 160);
        agregarEtiqueta("Ubicación:", 40, 200); txtUbicacion = agregarCampo(190, 200);

        JButton btnGuardar = new JButton("Guardar"); btnGuardar.setBounds(40, 260, 110, 35); add(btnGuardar);
        JButton btnActualizar = new JButton("Actualizar"); btnActualizar.setBounds(165, 260, 120, 35); add(btnActualizar);
        JButton btnEliminar = new JButton("Eliminar"); btnEliminar.setBounds(300, 260, 110, 35); add(btnEliminar);

        JLabel lblLista = new JLabel("Lista de Eventos - Lista Enlazada Simple");
        lblLista.setFont(new Font("Arial", Font.BOLD, 17)); lblLista.setBounds(440, 75, 380, 30); add(lblLista);
        area = new JTextArea(); area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area); scroll.setBounds(430, 115, 410, 260); add(scroll);

        JButton btnVolver = new JButton("Volver"); btnVolver.setBounds(620, 430, 100, 35); add(btnVolver);
        JButton btnSalir = new JButton("Salir"); btnSalir.setBounds(740, 430, 100, 35); add(btnSalir);

        btnGuardar.addActionListener(e -> guardar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnVolver.addActionListener(e -> { new MenuAdministrador(); dispose(); });
        btnSalir.addActionListener(e -> System.exit(0));
        actualizarLista();
        setVisible(true);
    }

    private void guardar() {
        boolean ok = ControladorSistema.registrarEvento(txtNombre.getText(), txtFecha.getText(), txtUbicacion.getText());
        JOptionPane.showMessageDialog(this, ok ? "Evento guardado." : "Revise los datos del evento.");
        limpiar(); actualizarLista();
    }

    private void actualizar() {
        int id = leerEntero(txtId.getText());
        boolean ok = ControladorSistema.actualizarEvento(id, txtNombre.getText(), txtFecha.getText(), txtUbicacion.getText());
        JOptionPane.showMessageDialog(this, ok ? "Evento actualizado." : "No se pudo actualizar. Revise ID y datos.");
        limpiar(); actualizarLista();
    }

    private void eliminar() {
        int id = leerEntero(txtId.getText());
        boolean ok = ControladorSistema.eliminarEvento(id);
        JOptionPane.showMessageDialog(this, ok ? "Evento eliminado." : "No se encontró el evento.");
        limpiar(); actualizarLista();
    }

    private void actualizarLista() { area.setText(ControladorSistema.obtenerEventosTexto()); }
    private void limpiar() { txtId.setText(""); txtNombre.setText(""); txtFecha.setText(""); txtUbicacion.setText(""); }
    private int leerEntero(String texto) { try { return Integer.parseInt(texto.trim()); } catch (Exception e) { return -1; } }
    private void crearBarra(String texto) { JPanel barra = new JPanel(null); barra.setBounds(0,0,900,50); barra.setBackground(new Color(24,61,142)); add(barra); JLabel titulo = new JLabel(texto); titulo.setForeground(Color.WHITE); titulo.setFont(new Font("Arial", Font.BOLD, 24)); titulo.setBounds(25,10,350,30); barra.add(titulo); }
    private void agregarEtiqueta(String texto, int x, int y) { JLabel lbl = new JLabel(texto); lbl.setBounds(x,y,150,25); add(lbl); }
    private JTextField agregarCampo(int x, int y) { JTextField campo = new JTextField(); campo.setBounds(x,y,200,25); add(campo); return campo; }
}
