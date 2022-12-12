import javax.swing.*;
import javax.swing.SwingWorker;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Scanner;

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
    private JCheckBox seleccionarDirectorioDescargasCheckBox;
    private JTextField textoUrl;
    private JScrollPane centro;
    private JPanel panelCentro;
    private File ficheroDestino;
    private SwingWorker hilo;
    private DescargasIndividuales descargas;

    public Vista() {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,600);
        frame.setVisible(true);
        destino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selector = new JFileChooser();
                int respuesta = selector.showOpenDialog(null);
                if(respuesta == JFileChooser.APPROVE_OPTION){
                    ficheroDestino = selector.getSelectedFile();
                    descargar.setEnabled(true);
                }
            }
        });
        descargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    /*hilo = new SwingWorkerr(ficheroDestino, textoUrl.getText());
                    hilo.addPropertyChangeListener(new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            cambiosVentana(evt);
                        }
                    });
                    hilo.execute();*/
                    descargas = new DescargasIndividuales(ficheroDestino, textoUrl.getText());
                    panelCentro.add(descargas);
                    panelCentro.revalidate();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    /*private void cambiosVentana(PropertyChangeEvent evt) {
        System.out.println(SwingUtilities.isEventDispatchThread());
        System.out.println(evt.getPropertyName());
        System.out.println(evt.getNewValue());
        if(evt.getPropertyName().equals("state")){
            if(evt.getNewValue().equals(SwingWorker.StateValue.STARTED)){
                descargas = new DescargasIndividuales();
                panelCentro.add(descargas);
                panelCentro.revalidate();
            }
        }
        else if(evt.getPropertyName().equals("progress")){
            descargas.progreso.setValue((int)evt.getNewValue());
        }
        else if(descargas.detener == true){
            hilo.cancel(true);
        }
    }*/
}
