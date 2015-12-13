/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.downloadWerkzeug;

import downloadspeedtest.Werkzeuge.downloadWerkzeug.DownloadWerkzeug;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Steve
 */
public class ControllWerkzeug {

    ControllWerkzeugUI ui;
    DownloadWerkzeug downloadWerkzeug;
    
    public ControllWerkzeug(DownloadWerkzeug downloadWerkzeugn) {
        this.downloadWerkzeug = downloadWerkzeugn;
        
        ui = new ControllWerkzeugUI();
        
        ui.getStartTestButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                downloadWerkzeug.startDownload(getTargetUrl(),getDuration());
            }
        });
    }
    
    
    
    
    public JPanel getUI(){
        return ui.getPanel();
    }
    
    public String getTargetUrl(){
        return ui.getSetURLTextfield().getText();
    }
    
    public int getDuration(){
        String durationString = ui.getSetDurationTextfield().getText();
        String[] strings = durationString.split(":");
        if(strings.length != 3){
            JOptionPane.showMessageDialog(ui.getSetDurationTextfield(), "Not a valid duration!");
            return -1;
        }
        return Integer.parseInt(strings[0])*60*60+Integer.parseInt(strings[1])*60 +Integer.parseInt(strings[2]);
    }
    
}
