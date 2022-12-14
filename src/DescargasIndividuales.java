import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class DescargasIndividuales extends JPanel{
    public JProgressBar progreso;
    private JPanel panel1;
    private JButton pausar;
    private JButton parar;
    private JCheckBox archivo;
    public boolean detener;
    private SwingWorkerr hilo;

    public DescargasIndividuales(File destino, String url) {
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.CENTER);
        setMaximumSize(new Dimension(23462346, 70));
        detener = false;
        pausar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(detener == false) {
                    detener = true;
                }
                else{
                    detener = false;
                }
            }
        });
        hilo = new SwingWorkerr(destino, url, progreso);
        hilo.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    actualizar(evt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        hilo.execute();
        parar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("proceso " + SwingUtilities.isEventDispatchThread());
                hilo.cancel(true);
                int opcion = JOptionPane.showConfirmDialog(panel1, "¿Desea eliminar la descarga?", "Eliminar descarga", JOptionPane.YES_NO_OPTION);
                if(opcion == 0){
                    destino.delete();
                }
            }
        });
    }

    private void actualizar(PropertyChangeEvent evt) throws InterruptedException {
        if(evt.getPropertyName().equals("progress") && !detener){
            progreso.setValue((int)evt.getNewValue());
        }
        if(detener == true){
            pausar.setText("Reanudar");
            //hilo.wait();
        }
        if(detener == false){
           // hilo.notify();
            pausar.setText("Pausar");
        }
    }


}
