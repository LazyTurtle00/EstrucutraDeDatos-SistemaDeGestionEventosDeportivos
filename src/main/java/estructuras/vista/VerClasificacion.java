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

public class VerClasificacion extends JFrame {
    private JTextArea area; private boolean administrador;
    public VerClasificacion(boolean administrador){ this.administrador=administrador; setTitle("Árbol de Clasificación"); setSize(900,540); setLayout(null); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); getContentPane().setBackground(new Color(220,220,220));
        JPanel barra=new JPanel(null); barra.setBounds(0,0,900,50); barra.setBackground(new Color(24,61,142)); add(barra); JLabel titulo=new JLabel("Árbol Binario de Búsqueda - Clasificación"); titulo.setForeground(Color.WHITE); titulo.setFont(new Font("Arial",Font.BOLD,24)); titulo.setBounds(25,10,550,30); barra.add(titulo);
        JLabel lbl=new JLabel("Clasificación ordenada por victorias usando recorrido descendente del BST."); lbl.setFont(new Font("Arial",Font.PLAIN,15)); lbl.setBounds(60,80,650,30); add(lbl);
        area=new JTextArea(); area.setEditable(false); JScrollPane scroll=new JScrollPane(area); scroll.setBounds(60,125,760,250); add(scroll);
        JButton btnActualizar=new JButton("Actualizar"); btnActualizar.setBounds(470,410,120,35); add(btnActualizar); JButton btnVolver=new JButton("Volver"); btnVolver.setBounds(610,410,100,35); add(btnVolver); JButton btnSalir=new JButton("Salir"); btnSalir.setBounds(730,410,100,35); add(btnSalir);
        btnActualizar.addActionListener(e->actualizar()); btnVolver.addActionListener(e->{ if(administrador)new MenuAdministrador(); else new MenuEspectador(); dispose();}); btnSalir.addActionListener(e->System.exit(0)); actualizar(); setVisible(true); }
    private void actualizar(){ area.setText(ControladorSistema.obtenerClasificacionTexto()); }
}
