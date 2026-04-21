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

public class ProgramacionPartidos extends JFrame {
    private JComboBox<String> comboEventos, comboUno, comboDos; private JTextField txtFecha, txtHora; private JTextArea area;
    public ProgramacionPartidos(){ setTitle("Programación de Partidos"); setSize(980,580); setLayout(null); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); getContentPane().setBackground(new Color(220,220,220)); crearBarra("Programación de Partidos - Cola Dinámica");
        etiqueta("Evento:",40,80); comboEventos=new JComboBox<>(); comboEventos.setBounds(160,80,280,25); add(comboEventos);
        etiqueta("Equipo 1:",40,125); comboUno=new JComboBox<>(); comboUno.setBounds(160,125,280,25); add(comboUno);
        etiqueta("Equipo 2:",40,170); comboDos=new JComboBox<>(); comboDos.setBounds(160,170,280,25); add(comboDos);
        etiqueta("Fecha:",40,215); txtFecha=campo(160,215); etiqueta("Hora:",40,260); txtHora=campo(160,260);
        JButton btnProgramar=boton("Programar",40,320,130); JButton btnActualizar=boton("Actualizar",190,320,120);
        JLabel lbl=new JLabel("Calendario de partidos por evento"); lbl.setFont(new Font("Arial",Font.BOLD,17)); lbl.setBounds(510,75,380,30); add(lbl); area=new JTextArea(); area.setEditable(false); JScrollPane scroll=new JScrollPane(area); scroll.setBounds(500,115,420,290); add(scroll);
        JButton btnVolver=boton("Volver",680,440,100); JButton btnSalir=boton("Salir",800,440,100);
        cargarEventos(); cargarEquipos(); actualizarLista(); comboEventos.addActionListener(e->{cargarEquipos(); actualizarLista();}); btnProgramar.addActionListener(e->programar()); btnActualizar.addActionListener(e->{cargarEventos(); cargarEquipos(); actualizarLista();}); btnVolver.addActionListener(e->{new MenuAdministrador(); dispose();}); btnSalir.addActionListener(e->System.exit(0)); setVisible(true); }
    private void programar(){ boolean ok=ControladorSistema.programarPartido(idEvento(),(String)comboUno.getSelectedItem(),(String)comboDos.getSelectedItem(),txtFecha.getText(),txtHora.getText()); JOptionPane.showMessageDialog(this,ok?"Partido programado en cola dinámica.":"No se pudo programar. Revise evento, equipos y datos."); txtFecha.setText(""); txtHora.setText(""); actualizarLista(); }
    private void cargarEventos(){ comboEventos.removeAllItems(); for(String i:ControladorSistema.obtenerEventosCombo()) comboEventos.addItem(i); } private void cargarEquipos(){ comboUno.removeAllItems(); comboDos.removeAllItems(); for(String i:ControladorSistema.obtenerParticipantesCombo(idEvento())){ comboUno.addItem(i); comboDos.addItem(i);} }
    private void actualizarLista(){ area.setText(ControladorSistema.obtenerPartidosTexto(idEvento())); } private int idEvento(){ return ControladorSistema.obtenerIdDesdeCombo((String)comboEventos.getSelectedItem()); }
    private void crearBarra(String t){ JPanel b=new JPanel(null); b.setBounds(0,0,980,50); b.setBackground(new Color(24,61,142)); add(b); JLabel l=new JLabel(t); l.setForeground(Color.WHITE); l.setFont(new Font("Arial",Font.BOLD,24)); l.setBounds(25,10,520,30); b.add(l);} private void etiqueta(String t,int x,int y){ JLabel l=new JLabel(t); l.setBounds(x,y,120,25); add(l);} private JTextField campo(int x,int y){ JTextField c=new JTextField(); c.setBounds(x,y,280,25); add(c); return c;} private JButton boton(String t,int x,int y,int w){ JButton b=new JButton(t); b.setBounds(x,y,w,35); add(b); return b;}
}
