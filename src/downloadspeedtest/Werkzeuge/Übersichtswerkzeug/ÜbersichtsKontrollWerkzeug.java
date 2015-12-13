/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.Übersichtswerkzeug;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Steve
 */
public class ÜbersichtsKontrollWerkzeug {
    
    ÜbersichtsKontrollWerkzeugUI ui;

    public ÜbersichtsKontrollWerkzeug() {
    
        ui = new ÜbersichtsKontrollWerkzeugUI();
        
    }

    
    
    public ÜbersichtsKontrollWerkzeugUI getUi() {
        return ui;
    }

    public int getLoopTime(){
        return Integer.parseInt(getUi().getLoopTime().getText())*1000*60;
    }
    
    public int getMaxYValue(){
        return Integer.parseInt(getUi().getMaxYValue().getText()+"000");
    }
    
    public String getTargetUrl(){
        return ui.getTargetURL().getText();
    }
    
    public void setLoading(boolean loading){
        ui.setLoading(loading);
    }
}
