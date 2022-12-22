import javax.swing.*;

public class SplashScreen extends JDialog {
    private JPanel contentPane;
    private JProgressBar splashprogreso;



    public SplashScreen() {
        setContentPane(contentPane);
        setModal(false);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        iniciarHilo();
        setVisible(true);


    }

    private void iniciarHilo() {


        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 100; i++){
                    splashprogreso.setValue(i);
                    try{
                        Thread.sleep(10);
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
}
