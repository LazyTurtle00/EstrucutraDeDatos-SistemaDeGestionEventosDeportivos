package estructuras.vista;

import estructuras.controlador.ControladorSistema;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VerResultados extends JFrame {
    private JComboBox<String> comboEventos; private JTextArea area; private boolean administrador;
    public VerResultados(boolean administrador){ this.administrador=administrador; setTitle("Ver Resultados"); setSize(900,540); setLayout(null); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); getContentPane().setBackground(new Color(220,220,220));
        JPanel barra=new JPanel(null); barra.setBounds(0,0,900,50); barra.setBackground(new Color(24,61,142)); add(barra); JLabel titulo=new JLabel("Consulta de Resultados"); titulo.setForeground(Color.WHITE); titulo.setFont(new Font("Arial",Font.BOLD,24)); titulo.setBounds(25,10,380,30); barra.add(titulo);
        JLabel lblEvento=new JLabel("Evento:"); lblEvento.setBounds(60,80,100,25); add(lblEvento); comboEventos=new JComboBox<>(); comboEventos.setBounds(130,80,320,25); add(comboEventos);
        area=new JTextArea(); area.setEditable(false); JScrollPane scroll=new JScrollPane(area); scroll.setBounds(60,125,760,250); add(scroll);
        JButton btnActualizar=new JButton("Actualizar"); btnActualizar.setBounds(470,410,120,35); add(btnActualizar); JButton btnVolver=new JButton("Volver"); btnVolver.setBounds(610,410,100,35); add(btnVolver); JButton btnSalir=new JButton("Salir"); btnSalir.setBounds(730,410,100,35); add(btnSalir);
        cargarEventos(); actualizar(); comboEventos.addActionListener(e->actualizar()); btnActualizar.addActionListener(e->{cargarEventos();actualizar();}); btnVolver.addActionListener(e->{ if(administrador)new MenuAdministrador(); else new MenuEspectador(); dispose();}); btnSalir.addActionListener(e->System.exit(0)); setVisible(true); }
    private void cargarEventos(){ comboEventos.removeAllItems(); for(String i:ControladorSistema.obtenerEventosCombo()) comboEventos.addItem(i);} private void actualizar(){ area.setText(ControladorSistema.obtenerResultadosTexto(ControladorSistema.obtenerIdDesdeCombo((String)comboEventos.getSelectedItem())));}
}
