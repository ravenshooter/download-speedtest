/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.Übersichtswerkzeug;

import java.util.Date;

/**
 *
 * @author Steve
 */
public class ÜbersichtsWerkzeugTaskResult {

    private long totalTimeMs;
    private long totalDataB;
    private long totalSpeedKbPS;
    private Date startTime;
    private Date endTime;
    private int nr;
    
    public ÜbersichtsWerkzeugTaskResult() {
    }

    public long getTotalTimeMs() {
        return totalTimeMs;
    }

    public void setTotalTimeMs(long totalTimeMs) {
        this.totalTimeMs = totalTimeMs;
    }

    public long getTotalDataB() {
        return totalDataB;
    }

    public void setTotalDataB(long totalDataB) {
        this.totalDataB = totalDataB;
    }

    public long getTotalSpeedKbPS() {
        return totalSpeedKbPS;
    }

    public void setTotalSpeedKbPS(long totalSpeedKbPS) {
        this.totalSpeedKbPS = totalSpeedKbPS;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }
    
    
}
