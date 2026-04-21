package estructuras.vista;

import estructuras.controlador.ControladorSistema;
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

public class GestionParticipantes extends JFrame {
    private JComboBox<String> comboEventos;
    private JTextField txtNombre, txtEdad, txtEquipo, txtBuscar;
    private JTextArea area;

    public GestionParticipantes() {
        setTitle("Gestión de Participantes"); setSize(980,580); setLayout(null); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); getContentPane().setBackground(new Color(220,220,220));
        crearBarra("Gestión de Participantes por Evento");
        agregarEtiqueta("Evento:",40,80); comboEventos = new JComboBox<>(); comboEventos.setBounds(160,80,270,25); add(comboEventos);
        agregarEtiqueta("Nombre:",40,120); txtNombre = campo(160,120);
        agregarEtiqueta("Edad:",40,160); txtEdad = campo(160,160);
        agregarEtiqueta("Equipo:",40,200); txtEquipo = campo(160,200);
        agregarEtiqueta("Buscar nombre:",40,310); txtBuscar = campo(160,310);
        JButton btnGuardar = boton("Guardar",40,250,110); JButton btnActualizar = boton("Actualizar",165,250,120); JButton btnEliminar = boton("Eliminar",300,250,110); JButton btnBuscar = boton("Buscar Recursivo",300,305,145);
        JLabel lbl = new JLabel("Participantes - Lista Doblemente Enlazada"); lbl.setFont(new Font("Arial",Font.BOLD,17)); lbl.setBounds(490,75,400,30); add(lbl);
        area = new JTextArea(); area.setEditable(false); JScrollPane scroll = new JScrollPane(area); scroll.setBounds(480,115,440,290); add(scroll);
        JButton btnActualizarLista = boton("Actualizar",520,440,120); JButton btnVolver = boton("Volver",660,440,100); JButton btnSalir = boton("Salir",780,440,100);
        cargarEventos(); actualizarLista();
        comboEventos.addActionListener(e -> actualizarLista());
        btnGuardar.addActionListener(e -> guardar()); btnActualizar.addActionListener(e -> actualizar()); btnEliminar.addActionListener(e -> eliminar()); btnBuscar.addActionListener(e -> buscar()); btnActualizarLista.addActionListener(e -> { cargarEventos(); actualizarLista(); });
        btnVolver.addActionListener(e -> { new MenuAdministrador(); dispose(); }); btnSalir.addActionListener(e -> System.exit(0)); setVisible(true);
    }
    private void guardar(){ boolean ok=ControladorSistema.registrarParticipante(idEvento(),txtNombre.getText(),leer(txtEdad.getText()),txtEquipo.getText()); JOptionPane.showMessageDialog(this,ok?"Participante guardado.":"Revise los datos."); limpiar(); actualizarLista(); }
    private void actualizar(){ boolean ok=ControladorSistema.actualizarParticipante(idEvento(),txtBuscar.getText(),txtNombre.getText(),leer(txtEdad.getText()),txtEquipo.getText()); JOptionPane.showMessageDialog(this,ok?"Participante actualizado.":"No se pudo actualizar. Use el campo Buscar nombre para indicar el participante actual."); limpiar(); actualizarLista(); }
    private void eliminar(){ boolean ok=ControladorSistema.eliminarParticipante(idEvento(),txtBuscar.getText()); JOptionPane.showMessageDialog(this,ok?"Participante eliminado.":"No se encontró el participante."); limpiar(); actualizarLista(); }
    private void buscar(){ JOptionPane.showMessageDialog(this,ControladorSistema.buscarParticipanteRecursivoTexto(idEvento(),txtBuscar.getText())); }
    private void cargarEventos(){ comboEventos.removeAllItems(); for(String item: ControladorSistema.obtenerEventosCombo()) comboEventos.addItem(item); }
    private void actualizarLista(){ area.setText(ControladorSistema.obtenerParticipantesTexto(idEvento())); }
    private int idEvento(){ return ControladorSistema.obtenerIdDesdeCombo((String)comboEventos.getSelectedItem()); }
    private int leer(String t){ try{return Integer.parseInt(t.trim());}catch(Exception e){return -1;} }
    private void limpiar(){ txtNombre.setText(""); txtEdad.setText(""); txtEquipo.setText(""); txtBuscar.setText(""); }
    private void crearBarra(String t){ JPanel b=new JPanel(null); b.setBounds(0,0,980,50); b.setBackground(new Color(24,61,142)); add(b); JLabel l=new JLabel(t); l.setForeground(Color.WHITE); l.setFont(new Font("Arial",Font.BOLD,24)); l.setBounds(25,10,420,30); b.add(l);}    
    private void agregarEtiqueta(String t,int x,int y){ JLabel l=new JLabel(t); l.setBounds(x,y,120,25); add(l);} private JTextField campo(int x,int y){ JTextField c=new JTextField(); c.setBounds(x,y,270,25); add(c); return c;} private JButton boton(String t,int x,int y,int w){ JButton b=new JButton(t); b.setBounds(x,y,w,35); add(b); return b;}
}
