import javax.swing.*;
import javax.swing.SwingWorker;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
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
    private JTextField textoUrl;
    private JScrollPane centro;
    private JPanel panelCentro;
    private JProgressBar global;
    private JButton directorioDefecto;
    private File ficheroDestino;
    private SwingWorker hilo;
    private DescargasIndividuales descargas;

    public Vista() throws IOException {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(principal);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,600);
        frame.setVisible(true);
        leerFichero();
        destino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destinoDelFichero();
            }
        });
        descargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descargarFichero();
            }
        });
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarDescargas();
            }
        });
        directorioDefecto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                directorioPorDefecto();
            }
        });
    }

    private void destinoDelFichero() {
        JFileChooser selector = new JFileChooser();
        int respuesta = selector.showOpenDialog(null);
        if(respuesta == JFileChooser.APPROVE_OPTION){
            ficheroDestino = selector.getSelectedFile();
            descargar.setEnabled(true);
        }
    }

    private void descargarFichero() {
        try {
            descargas = new DescargasIndividuales(ficheroDestino, textoUrl.getText());
            panelCentro.add(descargas);
            panelCentro.revalidate();
            pausar.setEnabled(true);
            eliminar.setEnabled(true);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void leerFichero() throws IOException {
        BufferedReader bf = null;
        try {
            File fichero = new File("src\\recursos\\directorio.txt");
            bf = new BufferedReader(new FileReader(fichero));
            String ruta;
            if((ruta = bf.readLine()) != null){
                ficheroDestino = new File(ruta);
                label.setText("El directorio por defecto es " + ruta);
                descargar.setEnabled(true);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            bf.close();
        }
    }

    private void directorioPorDefecto(){
        BufferedWriter bw = null;
        JFileChooser selector = new JFileChooser();
        int respuesta = selector.showOpenDialog(null);
        if(respuesta == JFileChooser.APPROVE_OPTION){
            String rutaAbsoluta = selector.getSelectedFile().getAbsolutePath();
            try {
                bw = new BufferedWriter(new FileWriter("src\\recursos\\directorio.txt"));
                bw.write(rutaAbsoluta);
                label.setText("El directorio por defecto es " + rutaAbsoluta);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            finally {
                try {
                    bw.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            ficheroDestino = selector.getSelectedFile();
            descargar.setEnabled(true);
        }
    }

    private void cancelarDescargas(){
        for (int i = 0; i < panelCentro.getComponentCount(); i++){
            System.out.println("proceso " + SwingUtilities.isEventDispatchThread());
            panelCentro.getComponent(i).setVisible(false);
        }
    }

}
