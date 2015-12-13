/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.downloadWerkzeug;

import downloadspeedtest.Werkzeuge.Observable;
import downloadspeedtest.Werkzeuge.Observer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Steve
 */
public class DownloadWerkzeug implements Observable{

    private DownloadWerkzeugUI ui;
    private Downloader downloader;
    DownloadObserver downloadObserver;
    private boolean downloadRunning;
    ConcurrentLinkedQueue<Probe> measurements;
    private HashSet<Observer> observers;
    
    long lastResultTotalBytes;
    long lastResultTotalMs;
    long lastResultTotalSpeedKbPS;
    
    
    ControllWerkzeug controllWerkzeug;
    
    public DownloadWerkzeug() {
        controllWerkzeug = new ControllWerkzeug(this);
        ui = new DownloadWerkzeugUI();
        observers = new HashSet<>();
    }

    


    
    
    
    /**
     * 
     * @param targetFile Url of the target file
     * @param duration Duration of the video file in s, -1 if no duration shall be displayed
     */
    public void startDownload(String targetFile, int duration) {
        if(downloader != null){
            downloader.interrupt();
        }
        ui.setMinimumSpeedLine(duration);
        measurements = new ConcurrentLinkedQueue<>();
        downloader = new Downloader(targetFile,this);
        downloader.start();
        downloadRunning = true;
        downloadObserver = new DownloadObserver(measurements,downloader);
        downloadObserver.addObserver(ui);
        downloadObserver.start();
    }

    
    
    public JPanel getUI() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(controllWerkzeug.getUI(),BorderLayout.NORTH);
        panel.add(new JScrollPane(ui),BorderLayout.CENTER);
        return panel;
    }
    
    

    
    protected void downloadFinished(){
        //double kbytes = (double)(downloader.getReadBytes())/1000;
        //double seconds = (double)(downloader.getDuration())/1000;
        //double kbps = kbytes/seconds;
        //JOptionPane.showMessageDialog(null, "Downloaded "+ kbytes +"kby in " + downloader.getDuration() + "ms. "+ System.getProperty("line.separator")+ "Average downloadspeed:" + kbps +"kbyte/s");
        downloadObserver.interrupt();
        lastResultTotalBytes = downloader.getReadBytes();
        lastResultTotalMs = downloader.getDuration();
        lastResultTotalSpeedKbPS = (lastResultTotalMs!=0) ? lastResultTotalBytes/lastResultTotalMs : lastResultTotalBytes;
        downloadRunning = false;
        notifyObservers();
    }

    
    
    /**
     * Called when a download is finsihed.
     */
    @Override
    public void notifyObservers() {
        for(Observer o : observers){
            o.update(this);
        }
    }
    
    @Override
    public void addObserver(Observer o){
        observers.add(o);
    }

    public long getLastResultTotalBytes() {
        return lastResultTotalBytes;
    }

    public long getLastResultTotalMs() {
        return lastResultTotalMs;
    }

    public long getLastResultTotalSpeedKbPS() {
        return lastResultTotalSpeedKbPS;
    }
    

    
    
    
}
