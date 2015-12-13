/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.downloadWerkzeug;

import downloadspeedtest.Werkzeuge.Observable;
import downloadspeedtest.Werkzeuge.Observer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JPanel;

/**
 *
 * @author Steve
 */
public class DownloadWerkzeugUI extends JPanel implements Observer{
    
    ConcurrentLinkedQueue<Probe>probes = new ConcurrentLinkedQueue<>();
    
    int cosyX,cosyY; 
    
    int minSpeedLine;
    
    int maxYValue = 20000;
    
    public DownloadWerkzeugUI() {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(400, 300));
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        cosyX = 45;
        cosyY = this.getHeight()-45;
        
        //this.setMinimumSize(new Dimension(400,400));
        
        //System.out.println(this.getPreferredSize().width+" "+this.getPreferredSize().height);
        
        
        g.setColor(Color.BLACK);
        
        g.drawLine(cosyX, cosyY, cosyX, cosyY-this.getHeight());
        g.drawLine(cosyX, cosyY, this.getWidth(), cosyY);
        
        g.setColor(Color.gray);
        for(int i = 0; i < maxYValue;i += 1000){
            g.drawLine(cosyX, cosyY-getScaledValue(i), this.getWidth(), cosyY-getScaledValue(i));
            g.drawString((i/1000)+"mb/s", cosyX -42, cosyY-getScaledValue(i));
        }
        
        for(int i = 0; i < this.getWidth();i += 50){
            g.drawLine(cosyX+i, cosyY, cosyX+i, cosyY-this.getHeight());
            g.drawString((i/10)+"s", cosyX +i, cosyY+30);
        }
        
        g.setColor(Color.BLACK);
        
        long totalBytes =0;
        long totalTime=0;
        for(Probe p : probes){
            g.drawLine(p.nr+cosyX, cosyY, p.nr+cosyX, cosyY-getScaledValue((int)p.getSpeed()));
            totalBytes += p.getReadBytes();
            totalTime = p.getTime();
        }
        double totalkBytes = (double)totalBytes/1000;
        double totalsTime = (double)totalTime/1000;
        int totalSpeed = (int)(totalkBytes/totalsTime);
        //System.out.println("tota"+totalkBytes + " " + totalsTime + " " +totalSpeed);
        
        if(minSpeedLine < 0){
            g.setColor(Color.green);
            g.drawLine(cosyX, cosyY-getScaledValue(minSpeedLine), this.getWidth(), cosyY-getScaledValue(minSpeedLine));
        }
        
        
        g.setColor(Color.red);
        g.drawLine(cosyX, cosyY-getScaledValue(totalSpeed), this.getWidth(), cosyY-getScaledValue(totalSpeed));
        g.drawString(totalSpeed+"kb/s", cosyX+4, cosyY-getScaledValue(totalSpeed)-4);
        
        g.setColor(Color.white);
        g.fillRect(this.getWidth()-200, 0, 200, 150);
        g.setColor(Color.BLACK);
        g.drawString("Average speed: "+ totalSpeed +"kb/s",this.getWidth()-150,40);
        g.drawString("Total traffic: "+ totalkBytes +"kb",this.getWidth()-150,70);
        g.drawString("Total time: "+ totalsTime +"s",this.getWidth()-150,100);
    }

    @Override
    public void update(Observable o) {
        DownloadObserver observer = (DownloadObserver)o;
        probes = observer.getProbes();
        if(isVisible()){
            this.setPreferredSize(new Dimension(Math.max(cosyX+probes.size(),400), 300));
            updateUI();
            repaint();
        }
    }
    
    
    /**
     * Sets a green line for minimum speed. 0 or -1 if no line shall be displayed.
     * @param speed 
     */
    public void setMinimumSpeedLine(int speed){
        minSpeedLine = speed;
    }
    
    public int getScaledValue(int value){
        return (this.getHeight()*value)/maxYValue;
    }
}
