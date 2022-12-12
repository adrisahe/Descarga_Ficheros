import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class SwingWorkerr extends javax.swing.SwingWorker<Void, Integer> {

    private File destino;
    private String url;
    private JProgressBar progreso;


    public SwingWorkerr(File destino, String url, JProgressBar progreso) {
        this.destino = destino;
        this.url = url;
        this.progreso = progreso;
    }

    @Override
    protected Void doInBackground() throws Exception {
        //System.out.println(SwingUtilities.isEventDispatchThread());
        downloadFile(destino, url);
        return null;
    }

    private void downloadFile(File destino, String url) throws Exception {
        InputStream is = null;
        OutputStream os = null;
        URLConnection conexion = new URL(url).openConnection();
        conexion.connect();
        try {
            is = conexion.getInputStream();
            os = new FileOutputStream(destino);
            byte[] buffer = new byte[1024];
            int length;
            long tamañoTotal = conexion.getContentLength();
            long bytesEscritos = 0;
            System.out.println("tamaño descarga: " + tamañoTotal);
            while ((length = is.read(buffer)) > 0 && !isCancelled()) {
                os.write(buffer, 0, length);
                bytesEscritos += length;
                long porcentaje = bytesEscritos * 100 / tamañoTotal;
                setProgress( (int) porcentaje);
            }
            System.out.println("Fichero origen " + bytesEscritos);
        } finally {
            is.close();
            os.close();
        }
    }
}
