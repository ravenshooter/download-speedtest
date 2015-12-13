/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.downloadWerkzeug;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Steve
 */
public class ControllWerkzeugUI {
    
    private JPanel panel;
    
    private JTextField setURLTextfield;
    private JTextField setDurationTextfield;
    private JButton startTestButton;

    public ControllWerkzeugUI() {
    
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,40));
        
        JPanel urlPanel = new JPanel();
        urlPanel.add(new JLabel("Video URL:"));
        setURLTextfield = new JTextField();
        setURLTextfield.setColumns(20);
        urlPanel.add(setURLTextfield);
        panel.add(urlPanel);
        
        
        JPanel durationPanel = new JPanel();
        durationPanel.add(new JLabel("Duration:"));
        setDurationTextfield = new JTextField("00:00:00");
        setDurationTextfield.setColumns(8);
        durationPanel.add(setDurationTextfield);
        panel.add(durationPanel);
        
        
        startTestButton = new JButton("Start Test");
        panel.add(startTestButton);
    }

    
    /**
     * Returns the main Panel of this GUI Element.
     * @return 
     */
    public JPanel getPanel() {
        return panel;
    }
    
    
    public JTextField getSetURLTextfield() {
        return setURLTextfield;
    }

    public JButton getStartTestButton() {
        return startTestButton;
    }

    public JTextField getSetDurationTextfield() {
        return setDurationTextfield;
    }
    
    
    
    
    
    
    
    
    
    
}
