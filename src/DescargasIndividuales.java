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
    public boolean detener;
    public SwingWorkerr hilo;
    private File destino;

    public DescargasIndividuales(File destino, String url) {
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.CENTER);
        setMaximumSize(new Dimension(23462346, 70));
        this.destino = destino;
        pausar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!detener) {
                    detener = true;
                }
                else if(detener){
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
                eliminarDescarga();
            }
        });
    }


    private void actualizar(PropertyChangeEvent evt) throws InterruptedException {
            if (evt.getPropertyName().equals("progress") && !detener) {
                pausar.setText("Pausar");
                progreso.setValue((int) evt.getNewValue());
            } else if (detener) {
                pausar.setText("Reanudar");
                hilo.pausar = true;
            }
            else if(evt.getPropertyName().equals("esperando") && !detener){
                hilo.pausar = false;
            }
    }

    private void eliminarDescarga() {
        panel1.setVisible(false);
        hilo.cancel(true);
        int opcion = JOptionPane.showConfirmDialog(panel1, "¿Desea eliminar la descarga?", "Eliminar descarga", JOptionPane.YES_NO_OPTION);
        if(opcion == 0){
            destino.delete();
        }
    }
}
