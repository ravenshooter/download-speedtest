/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.GUI;

import downloadspeedtest.Werkzeuge.downloadWerkzeug.ControllWerkzeug;
import downloadspeedtest.Werkzeuge.downloadWerkzeug.DownloadWerkzeug;
import downloadspeedtest.Werkzeuge.Übersichtswerkzeug.ÜbersichtsWekzeug;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Steve
 */
public class Window extends JFrame{

    JTabbedPane tabPane;
    
    public Window() {
        this.setPreferredSize(new Dimension(800,600));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        tabPane = new JTabbedPane();
        this.setContentPane(tabPane);
        
        
        
        ÜbersichtsWekzeug übersichtsWekzeug = new ÜbersichtsWekzeug();
        //this.add(übersichtsWekzeug.getUI(),BorderLayout.SOUTH);
        tabPane.addTab("Übersicht", new ImageIcon(getClass().getResource("/line_chart.png")), übersichtsWekzeug.getUI());
        
        
        JPanel panel = new JPanel(new BorderLayout());
        DownloadWerkzeug downloadWerkzeug = new DownloadWerkzeug();
        panel.add(downloadWerkzeug.getUI(),BorderLayout.CENTER);
        
        tabPane.addTab("Videodownload",new ImageIcon(getClass().getResource("/go_down_blue.png")), panel);
        
        
        
        
        this.pack();
        this.setVisible(true);
    }
    
}
