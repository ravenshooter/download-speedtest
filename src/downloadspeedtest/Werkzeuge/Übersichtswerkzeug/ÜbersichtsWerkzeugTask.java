/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.Übersichtswerkzeug;

import downloadspeedtest.Werkzeuge.Observable;
import downloadspeedtest.Werkzeuge.Observer;
import downloadspeedtest.Werkzeuge.downloadWerkzeug.DownloadWerkzeug;
import java.util.Date;
import java.util.HashSet;
import java.util.TimerTask;

/**
 *
 * @author Steve
 */
public class ÜbersichtsWerkzeugTask extends TimerTask implements Observable,Observer{

    private HashSet<Observer>observers;
    
    private boolean finished = true;
    
    DownloadWerkzeug downloadWerkzeug;
    
    String targetFile;
    
    private ÜbersichtsWerkzeugTaskResult result;

    public ÜbersichtsWerkzeugTask(String targetFile, Observer ... observer) {
        observers = new HashSet<>();
        for(Observer o : observer){
            addObserver(o);
        }
        this.targetFile = targetFile;
        downloadWerkzeug = new DownloadWerkzeug();
        downloadWerkzeug.addObserver(this);
    }
    
    
    
    
    @Override
    public synchronized void run() {
        if(finished){
            finished = false;
            result = new ÜbersichtsWerkzeugTaskResult();
            result.setStartTime(new Date());
            notifyObservers();
            downloadWerkzeug.startDownload(targetFile,-1);
        }else{
            System.err.println("Test skipped, old test not finished yet!");
        }
        
    }

    
    
    @Override
    public void notifyObservers() {
        for(Observer o : observers){
            o.update(this);
        }
    }

    @Override
    public final void addObserver(Observer o) {
        observers.add(o);
    }

    public HashSet<Observer> getObservers() {
        return observers;
    }

    public long getTotalTimeMs() {
        return result.getTotalTimeMs();
    }

    public long getTotalDataB() {
        return result.getTotalDataB();
    }

    public long getTotalSpeedKbPS() {
        return result.getTotalSpeedKbPS();
    }

    public Date getStartTime() {
        return result.getStartTime();
    }

    public Date getEndTime() {
        return result.getEndTime();
    }

    
    public ÜbersichtsWerkzeugTaskResult getResult(){
        return result;
    }

    public boolean isFinished() {
        return finished;
    }
    
    
    
    
    @Override
    public synchronized void update(Observable o) {
        if(o instanceof DownloadWerkzeug){
            result.setEndTime(new Date());
            DownloadWerkzeug downloadWerkzeug = (DownloadWerkzeug)o;
            result.setTotalDataB(downloadWerkzeug.getLastResultTotalBytes());
            result.setTotalTimeMs(downloadWerkzeug.getLastResultTotalMs());
            result.setTotalSpeedKbPS(downloadWerkzeug.getLastResultTotalSpeedKbPS());
            finished = true;
            notifyObservers();
        }
    }
    
    
    
    
}
