/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.downloadWerkzeug;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Steve
 */
public class Downloader extends Thread {

    private String targetFile;
    private long startTime;
    private long readBytes;
    private long duration = -1;
    private DownloadWerkzeug downloadWerkzeug;
    boolean downloadFinished;

    public Downloader(String targetFile, DownloadWerkzeug downloadWerkzeug) {
        this.targetFile = targetFile;
        this.downloadWerkzeug = downloadWerkzeug;
    }

    @Override
    public void run() {
        InputStream is = null;
        try {
            URL url = new URL(targetFile);
            URLConnection conn;
            try {
                conn = url.openConnection();
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "File not found");
                return;
            }
            is = new BufferedInputStream(conn.getInputStream());
            byte[] chunk = new byte[1024];
            long start = startTime = System.currentTimeMillis();
            while (is.read(chunk) != -1 && !isInterrupted()) {
                //os.write(chunk, 0, chunkSize);
                readBytes += 1024;
                //System.out.println(""+readBytes);
            }
            long end = System.currentTimeMillis();
            downloadFinished = true;
            duration = end - start;
            is.close();
        } catch (MalformedURLException ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Not a valid URL");
        } catch (FileNotFoundException ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "File not found");
        } catch (IOException ex) {
            Logger.getLogger(DownloadWerkzeug.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(is!=null){
                    is.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(DownloadWerkzeug.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!isInterrupted()) {
            downloadWerkzeug.downloadFinished();
        }
    }

    public long getReadBytes() {
        return readBytes;
    }

    
    /**
     * Duration of the whole transmission.
     * @return 
     */
    public long getDuration() {
        return duration;
    }

    public long getStartTime() {
        return startTime;
    }

    public boolean isDownloadFinished() {
        return downloadFinished;
    }
    
    
}