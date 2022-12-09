import javax.swing.*;

public class Vista {
    private JPanel principal;
    private JPanel Norte;
    private JPanel fila2;
    private JLabel url;
    private JButton destino;
    private JPanel fila4;
    private JLabel label;
    private JButton descargar;
    private JPanel sur;
    private JButton pausar;
    private JButton eliminar;
    private JPanel centro;
    private JCheckBox seleccionarDirectorioDescargasCheckBox;
    private JTextField textoUrl;

    public Vista() {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,600);
        frame.setVisible(true);
    }
}
