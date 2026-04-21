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

public class RegistrarResultados extends JFrame {
    private JComboBox<String> comboEventos, comboPartidos; private JTextField txtUno, txtDos; private JTextArea area;
    public RegistrarResultados(){ setTitle("Registrar Resultados"); setSize(980,580); setLayout(null); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); getContentPane().setBackground(new Color(220,220,220)); crearBarra("Registrar Resultados - Pila Dinámica");
        etiqueta("Evento:",40,80); comboEventos=new JComboBox<>(); comboEventos.setBounds(160,80,300,25); add(comboEventos); etiqueta("Partido:",40,125); comboPartidos=new JComboBox<>(); comboPartidos.setBounds(160,125,300,25); add(comboPartidos);
        etiqueta("Puntos equipo 1:",40,170); txtUno=campo(180,170); etiqueta("Puntos equipo 2:",40,215); txtDos=campo(180,215);
        JButton btnGuardar=boton("Guardar Resultado",40,275,170); JButton btnActualizar=boton("Actualizar",230,275,120);
        JLabel lbl=new JLabel("Historial de resultados más reciente primero"); lbl.setFont(new Font("Arial",Font.BOLD,17)); lbl.setBounds(500,75,400,30); add(lbl); area=new JTextArea(); area.setEditable(false); JScrollPane scroll=new JScrollPane(area); scroll.setBounds(500,115,420,290); add(scroll);
        JButton btnVolver=boton("Volver",680,440,100); JButton btnSalir=boton("Salir",800,440,100);
        cargarEventos(); cargarPartidos(); actualizarResultados(); comboEventos.addActionListener(e->{cargarPartidos(); actualizarResultados();}); btnGuardar.addActionListener(e->guardar()); btnActualizar.addActionListener(e->{cargarEventos(); cargarPartidos(); actualizarResultados();}); btnVolver.addActionListener(e->{new MenuAdministrador(); dispose();}); btnSalir.addActionListener(e->System.exit(0)); setVisible(true); }
    private void guardar(){ int idPartido=ControladorSistema.obtenerIdDesdeCombo((String)comboPartidos.getSelectedItem()); boolean ok=ControladorSistema.registrarResultado(idEvento(),idPartido,leer(txtUno.getText()),leer(txtDos.getText())); JOptionPane.showMessageDialog(this,ok?"Resultado apilado correctamente.":"No se pudo registrar el resultado."); txtUno.setText(""); txtDos.setText(""); actualizarResultados(); }
    private void cargarEventos(){ comboEventos.removeAllItems(); for(String i:ControladorSistema.obtenerEventosCombo()) comboEventos.addItem(i);} private void cargarPartidos(){ comboPartidos.removeAllItems(); for(String i:ControladorSistema.obtenerPartidosCombo(idEvento())) comboPartidos.addItem(i);} private void actualizarResultados(){ area.setText(ControladorSistema.obtenerResultadosTexto(idEvento()));}
    private int idEvento(){ return ControladorSistema.obtenerIdDesdeCombo((String)comboEventos.getSelectedItem()); } private int leer(String t){ try{return Integer.parseInt(t.trim());}catch(Exception e){return -1;} }
    private void crearBarra(String t){ JPanel b=new JPanel(null); b.setBounds(0,0,980,50); b.setBackground(new Color(24,61,142)); add(b); JLabel l=new JLabel(t); l.setForeground(Color.WHITE); l.setFont(new Font("Arial",Font.BOLD,24)); l.setBounds(25,10,520,30); b.add(l);} private void etiqueta(String t,int x,int y){ JLabel l=new JLabel(t); l.setBounds(x,y,130,25); add(l);} private JTextField campo(int x,int y){ JTextField c=new JTextField(); c.setBounds(x,y,100,25); add(c); return c;} private JButton boton(String t,int x,int y,int w){ JButton b=new JButton(t); b.setBounds(x,y,w,35); add(b); return b;}
}
