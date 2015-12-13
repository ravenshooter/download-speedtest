/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.downloadWerkzeug;

/**
 *
 * @author Steve
 */
public class Probe {

    long time;
    long readBytes;
    double speed;
    int nr;

    /**
     *
     * @param time time of this measurement
     * @param readBytes bytes read in the time of measurement
     * @param duration duration of this probe
     */
    public Probe(long time, int duration, long readBytes, int nr) {
        this.time = time;
        this.readBytes = readBytes;
        this.nr = nr;
        speed = readBytes/duration;
    }

    public long getTime() {
        return time;
    }

    public long getReadBytes() {
        return readBytes;
    }

    /**
     * in b/ms
     * @return 
     */
    public double getSpeed() {
        return speed;
    }
    
    
    
}