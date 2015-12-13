/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.Übersichtswerkzeug;

import downloadspeedtest.Werkzeuge.Observable;
import downloadspeedtest.Werkzeuge.Observer;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JPanel;

/**
 *
 * @author Steve
 */
public class ÜbersichtsWekzeug implements Observer{

    ConcurrentLinkedQueue<ÜbersichtsWerkzeugTaskResult> results;
    
    
    ÜbersichtsWekrzeugUI ui;
    ÜbersichtsWerkzeugUIChart uiChart;
    ÜbersichtsKontrollWerkzeug kontrollWerkzeug;
    
    Timer timer;
    
    private final String standartLoopTime = "1";
    private final String standartUrl = "http://www.auby.no/files/video_tests/xvid_480p_as_l5_1mbps_he-aac_foreign_subs_matrix.mkv";
    private final String standartMaxYValue = "20";
    
    public ÜbersichtsWekzeug() {
        
        results = new ConcurrentLinkedQueue<>();
        
        ui = new ÜbersichtsWekrzeugUI();
        
        
        uiChart = new ÜbersichtsWerkzeugUIChart(results);
        ui.setChart(uiChart);
        
        kontrollWerkzeug = new ÜbersichtsKontrollWerkzeug();
        kontrollWerkzeug.getUi().getLoopTime().setText(standartLoopTime);
        kontrollWerkzeug.getUi().getMaxYValue().setText(standartMaxYValue);
        kontrollWerkzeug.getUi().getTargetURL().setText(standartUrl);
        kontrollWerkzeug.getUi().getMaxYValue().addPropertyChangeListener("value",new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                uiChart.setMaxYValue(kontrollWerkzeug.getMaxYValue());
            }
        });
        kontrollWerkzeug.getUi().getTargetURL().addPropertyChangeListener("value",new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                restartTimer(kontrollWerkzeug.getTargetUrl(),kontrollWerkzeug.getLoopTime());
            }
        });
        kontrollWerkzeug.getUi().getLoopTime().addPropertyChangeListener("value", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                restartTimer(kontrollWerkzeug.getTargetUrl(),kontrollWerkzeug.getLoopTime());
            }
        });
        ui.setKontrollWerkzeug(kontrollWerkzeug.getUi().getPanel());
        
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new ÜbersichtsWerkzeugTask(kontrollWerkzeug.getTargetUrl(),this), (long)1000, kontrollWerkzeug.getLoopTime());
        
    }

    
    public JPanel getUI() {
        return ui.getPanel();
    }

    private void restartTimer(String targetURL, int loopTime){
        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new ÜbersichtsWerkzeugTask(targetURL, this), (long) 1000, loopTime);

    }

    
    /**
     * 
     * @param o 
     * @ensure o instanceOf ÜbersichtsWerkzeuTask
     */
    @Override
    public void update(Observable o) {
        if(o instanceof ÜbersichtsWerkzeugTask){
            ÜbersichtsWerkzeugTask task = (ÜbersichtsWerkzeugTask)o;
            if(task.isFinished()){
                task.getResult().setNr(results.size());
                results.add(task.getResult());
                uiChart.update();
                kontrollWerkzeug.setLoading(false);
            }else{
                kontrollWerkzeug.setLoading(true);
                System.out.println("updated");
            }
        }
    }
    
    
    
    
}
