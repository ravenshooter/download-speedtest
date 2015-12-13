/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.Übersichtswerkzeug;

import downloadspeedtest.Werkzeuge.downloadWerkzeug.*;
import downloadspeedtest.Werkzeuge.Observable;
import downloadspeedtest.Werkzeuge.Observer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JPanel;

/**
 *
 * @author Steve
 */
public class ÜbersichtsWerkzeugUIChart extends JPanel{
    
    ConcurrentLinkedQueue<ÜbersichtsWerkzeugTaskResult>probes = new ConcurrentLinkedQueue<>();
    
    int cosyX,cosyY; 
    
    int maxYValue = 20000;
    
    public ÜbersichtsWerkzeugUIChart(ConcurrentLinkedQueue<ÜbersichtsWerkzeugTaskResult>probes) {
        this.probes = probes;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(400, 500));
        cosyX = 45;
        cosyY = this.getHeight()-200;
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        recalcCosyY();
        
        //this.setMinimumSize(new Dimension(400,400));
        
        //System.out.println(this.getPreferredSize().width+" "+this.getPreferredSize().height);
        
        
        g.setColor(Color.BLACK);
        
        g.drawLine(cosyX, cosyY, cosyX, cosyY-this.getHeight());
        g.drawLine(cosyX, cosyY, this.getWidth(), cosyY);
        
        //Draw horizontal lines
        g.setColor(Color.gray);
        for(int i = 0; i <= maxYValue;i += 1000){
            g.drawLine(cosyX, cosyY-getScaledValue(i), this.getWidth(), cosyY-getScaledValue(i));
            g.drawString((i/1000)+"mb/s", cosyX -42, cosyY-getScaledValue(i));
        }
        
//        //Draw vertical lines
//        for(int i = 0; i < this.getWidth();i += 50){
//            g.drawLine(cosyX+i, cosyY, cosyX+i, cosyY-this.getHeight());
//            g.drawString((i/10)+"s", cosyX +i, cosyY+30);
//        }
        
        g.setColor(Color.BLACK);
        
        
        
        long totalSpeed =0;
        long totalProbes=probes.size();
        int i = 0;
        for(ÜbersichtsWerkzeugTaskResult p : probes){
            i++;
            if(i%20==0 || i == 1){
                drawVerticalLine(g,p);
            }
            g.drawLine(p.getNr()+cosyX, cosyY, p.getNr()+cosyX, cosyY-getScaledValue((int)p.getTotalSpeedKbPS()));
            totalSpeed += p.getTotalSpeedKbPS();
        }
        int totalAvgSpeed = (totalProbes!=0) ? (int)(totalSpeed/totalProbes) : 0;
        //System.out.println("tota"+totalkBytes + " " + totalsTime + " " +totalSpeed);
        
        g.setColor(Color.red);
        g.drawLine(cosyX, cosyY-getScaledValue(totalAvgSpeed), this.getWidth(), cosyY-getScaledValue(totalAvgSpeed));
        g.drawString(totalAvgSpeed+"kb/s", cosyX+4, cosyY-getScaledValue(totalAvgSpeed)-4);
        
        g.setColor(Color.white);
        g.fillRect(this.getWidth()-210, 10, 200, 70);
        g.setColor(Color.BLACK);
        g.drawString("Average speed: "+ totalAvgSpeed +"kb/s",this.getWidth()-170,40);
        //g.drawString("Total traffic: "+ totalkBytes +"kb",this.getWidth()-150,70);
        //g.drawString("Total time: "+ totalsTime +"s",this.getWidth()-150,100);
    }

    
    public void update(){
        this.setPreferredSize(new Dimension(Math.max(cosyX+probes.size(),400), 500));
        updateUI();
        this.repaint();
    }
    
    
    private void recalcCosyY(){
        cosyY = this.getHeight()-200;
    }
    
    
    private int getScaledValue(int value){
        recalcCosyY();
        return (cosyY*value)/maxYValue;
    }

    public void setMaxYValue(int maxYValue) {
        this.maxYValue = maxYValue;
        repaint();
    }
    
    
    
    
    private void drawVerticalLine(Graphics g, ÜbersichtsWerkzeugTaskResult result){
        g.setColor(Color.GRAY);
        g.drawLine(cosyX + result.getNr(), cosyY, cosyX + result.getNr(), cosyY - this.getHeight());
        
        Font oldFont = g.getFont();
        
        int degrees = 90; 
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(degrees));
        Font f = new Font("Arial", Font.BOLD, 12);
        g.setFont(f.deriveFont(at));
        
        g.drawString(result.getStartTime().toString(), cosyX + result.getNr(), cosyY+5);
        
        g.setColor(Color.BLACK);
        g.setFont(oldFont);
    }
}
