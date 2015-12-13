/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge.Übersichtswerkzeug;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Steve
 */
public class ÜbersichtsKontrollWerkzeugUI {
    JPanel panel;
    
    JFormattedTextField maxYValue;
    JFormattedTextField targetURL;
    JFormattedTextField loopTime;
    JButton resetButton;
    
    JDialog dialog;
    
    JPanel loadingPanel;

    public ÜbersichtsKontrollWerkzeugUI() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        
        JPanel targetUrlPanel = new JPanel();
        targetUrlPanel.add(new JLabel("TargetURL:"));
        DefaultFormatter defaultFormatter = new DefaultFormatter();
        defaultFormatter.setOverwriteMode(false);
        targetURL = new JFormattedTextField(defaultFormatter);
        targetURL.setValue("");
        targetURL.setColumns(30);
        targetUrlPanel.add(targetURL);

        panel.add(targetUrlPanel);

        
        
        maxYValue = new JFormattedTextField(20);
        maxYValue.setToolTipText("Sets the maximal displayed Y-value.");
        maxYValue.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public AbstractFormatter getFormatter(JFormattedTextField tf) {
                return new NumberFormatter();
            }
        });
        maxYValue.setColumns(3);
        
        
        
        loopTime = new JFormattedTextField(new JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public AbstractFormatter getFormatter(JFormattedTextField tf) {
                return new NumberFormatter();
            }
        });
        loopTime.setToolTipText("Sets the time between to Test-Downloads in minutes.");
        loopTime.setColumns(3);
        
        
        
        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(true);
            }
        });
        panel.add(settingsButton);
        
        
        dialog = new JDialog();
        dialog.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 0);
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        dialog.getContentPane().add(new JLabel("Time between downloads: "),c);
        c.gridx = 1;
        dialog.getContentPane().add(loopTime,c);
        
        c.gridx = 0;
        c.gridy = 1;
        dialog.getContentPane().add(new JLabel("Max Y-Value:"),c);
        c.gridx = 1;
        dialog.getContentPane().add(maxYValue,c);
        
        
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        JButton button = new JButton("Ok");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        dialog.add(button,c);
        
        dialog.setPreferredSize(new Dimension(300,200));
        dialog.pack();
        
        
        
        loadingPanel = new JPanel();
        loadingPanel.add(new JLabel(new ImageIcon(getClass().getResource("/ajax-loader.gif"))));
        loadingPanel.add(new JLabel("Download in progress"));
        loadingPanel.setVisible(false);
        
        panel.add(loadingPanel);
        
    }
    

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getMaxYValue() {
        return maxYValue;
    }

    public JTextField getTargetURL() {
        return targetURL;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JFormattedTextField getLoopTime() {
        return loopTime;
    }
    
    public void setLoading(boolean loading){
        loadingPanel.setVisible(loading);
        loadingPanel.updateUI();
        loadingPanel.repaint();
    }
    
    
    
    
}
