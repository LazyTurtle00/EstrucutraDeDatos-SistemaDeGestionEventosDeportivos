package estructuras.vista;

import estructuras.controlador.ControladorSistema;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VerEventos extends JFrame {
    private JTextArea area;
    public VerEventos() {
        setTitle("Ver Eventos"); setSize(900,540); setLayout(null); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); getContentPane().setBackground(new Color(220,220,220));
        JPanel barra = new JPanel(null); barra.setBounds(0,0,900,50); barra.setBackground(new Color(24,61,142)); add(barra);
        JLabel titulo = new JLabel("Consulta de Eventos"); titulo.setForeground(Color.WHITE); titulo.setFont(new Font("Arial",Font.BOLD,24)); titulo.setBounds(25,10,350,30); barra.add(titulo);
        JLabel lbl = new JLabel("Eventos registrados"); lbl.setFont(new Font("Arial",Font.BOLD,18)); lbl.setBounds(60,80,250,30); add(lbl);
        area = new JTextArea(); area.setEditable(false); JScrollPane scroll = new JScrollPane(area); scroll.setBounds(60,120,760,250); add(scroll);
        JButton btnActualizar = new JButton("Actualizar"); btnActualizar.setBounds(470,410,120,35); add(btnActualizar);
        JButton btnVolver = new JButton("Volver"); btnVolver.setBounds(610,410,100,35); add(btnVolver);
        JButton btnSalir = new JButton("Salir"); btnSalir.setBounds(730,410,100,35); add(btnSalir);
        btnActualizar.addActionListener(e -> actualizar()); btnVolver.addActionListener(e -> { new MenuEspectador(); dispose(); }); btnSalir.addActionListener(e -> System.exit(0));
        actualizar(); setVisible(true);
    }
    private void actualizar() { area.setText(ControladorSistema.obtenerEventosTexto()); }
}
