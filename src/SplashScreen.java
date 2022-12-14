import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JDialog {
    private JPanel contentPane;
    private JProgressBar splashprogreso;
    private JButton buttonOK;
    Fondo fondo = new Fondo();

    public SplashScreen() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        iniciarHilo();
        setVisible(true);
        System.out.println("hola");


    }

    private void iniciarHilo() {


        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 100; i++){
                    splashprogreso.setValue(i);
                    try{
                        Thread.sleep(100);
                        if(i == 100){
                            dispose();
                            Vista vista = new Vista();
                        }
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

            }
        });
        hilo1.start();
    }
    class Fondo extends JPanel {
        @Override
        public void paint(Graphics g) {
            Image imagen = new ImageIcon(getClass().getResource("imagenes/REBOBIKE.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            super.paint(g);

        }
    }
}
