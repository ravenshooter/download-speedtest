/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.downloadWerkzeug;


import downloadspeedtest.Werkzeuge.Observable;
import downloadspeedtest.Werkzeuge.Observer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steve
 */
public class DownloadObserver extends Thread implements Observable{
    
    private HashSet<Observer> observers;
    private ConcurrentLinkedQueue<Probe> probes;
    private Downloader downloader;
    private long lastReadBytes;
    private int probeID;
    
    private final int probeduration = 100; 

    public DownloadObserver(ConcurrentLinkedQueue<Probe> probes, Downloader downloader) {
        this.probes = probes;
        this.downloader = downloader;
        observers = new HashSet<>();
    }
    
    
    @Override
    public void run(){
        while(!isInterrupted()){
            try {
                Thread.sleep(probeduration);
            } catch (InterruptedException ex) {
                this.interrupt();
            }
            if(!downloader.isDownloadFinished()){
                long readBytes = downloader.getReadBytes();
                probes.add(new Probe(System.currentTimeMillis()-downloader.getStartTime(), probeduration, readBytes-lastReadBytes,probeID));
                //System.out.println(""+probeID+"  "+ (System.currentTimeMillis()-downloader.getStartTime())+"  "+ (readBytes-lastReadBytes));
                lastReadBytes = readBytes;
                probeID++;
                notifyObservers();
            }
        }
    }

    public ConcurrentLinkedQueue<Probe> getProbes() {
        return probes;
    }



    
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
}
