package estructuras.sistemadegestioneventosdeportivos;

import estructuras.vista.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class SistemaDeGestionEventosDeportivos {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal();
            }
        });
    }
}
