/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.Übersichtswerkzeug;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Steve
 */
public class ÜbersichtsWekrzeugUI {
    
    JPanel panel;
    JPanel kontrollWerkzeug;
    JPanel chart;
    
    public ÜbersichtsWekrzeugUI() {
        panel = new JPanel(new BorderLayout(10,10));
            }

    public JPanel getPanel() {
        JPanel returnPanel =  new JPanel(new BorderLayout());
        returnPanel.add(new JScrollPane(panel),BorderLayout.CENTER);
        return panel;
    }

    public void setKontrollWerkzeug(JPanel kontrollWerkzeug) {
        this.kontrollWerkzeug = kontrollWerkzeug;
        panel.add(kontrollWerkzeug, BorderLayout.NORTH);
    }

    public void setChart(JPanel chart) {
        this.chart = chart;
        panel.add(new JScrollPane(chart),BorderLayout.CENTER);
    }
    
    
    
    
}
