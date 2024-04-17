package Main.UI.Frames;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Holder;
import Main.UI.Format.VicFormatter;
import UIElements.Buttons.RoundButton;
import UIElements.Colors.CurrentUITheme;
import UIElements.Colors.UIColors;
import UIElements.Panels.RoundedPanel;
import UIElements.TextCanvas;

public class SettingsFrame extends JFrame {
    final private JFrame self = this;
    private Holder holder;
    public SettingsFrame(Holder h){
        holder = h;

        //make the holder panel
        self.setTitle("Settings");
        RoundedPanel statHolder = new RoundedPanel(h.getTheme()); // UI adjustment
        self.add(statHolder);
        statHolder.setLayout(new BoxLayout(statHolder,BoxLayout.Y_AXIS)); // UI adjustment

        //create each stat and put it inside its own panel
        //Color changer
        String[] optionsToChoose = new String[UIColors.colors.length];
        for(int i = 0; i < UIColors.colors.length; i++){
            optionsToChoose[i] = UIColors.colors[i];
        }

        RoundedPanel backcolorHolder = new RoundedPanel(h.getTheme()); // UI adjustment
        backcolorHolder.add(new JLabel("Background:"));
        // Creating a JComboBox and setting its bounds
        JComboBox<String> backComboBox = new JComboBox<>(optionsToChoose);
        backComboBox.setBounds(80, 50, 140, 20);
        //set the start value
        backComboBox.setSelectedItem(holder.getTheme().getBackgroundString());
        backcolorHolder.add(backComboBox);
        statHolder.add(backcolorHolder);

        //foreground
        RoundedPanel frontColorHolder = new RoundedPanel(h.getTheme()); // UI adjustment
        frontColorHolder.add(new JLabel("Foreground:"));
        // Creating a JComboBox and setting its bounds
        JComboBox<String> frontComboBox = new JComboBox<>(optionsToChoose);
        frontComboBox.setBounds(80, 50, 140, 20);
        //set the start value
        frontComboBox.setSelectedItem(holder.getTheme().getForegroundString());
        frontColorHolder.add(frontComboBox);
        statHolder.add(frontColorHolder);

        //get the JComboBox String with jComboBox.getItemAt(jComboBox.getSelectedIndex());
        // UI adjustments
        TextCanvas displayCanvas = new TextCanvas(h.getTheme(), 15, false);
        displayCanvas.setText("To save your changes restart the program!");
        displayCanvas.setSize(new Dimension(500, 15));
        displayCanvas.setBorder(new LineBorder(h.getTheme().getCurrentForegroundColor().main(), 2));
        VicFormatter canvasFormat = new VicFormatter(displayCanvas, 5);
        statHolder.add(canvasFormat.getPanel()); // UI adjustments
        RoundButton doneButton = new RoundButton("Save", h.getTheme());
        doneButton.setSize(new Dimension(300, 50));
        VicFormatter buttonFormat = new VicFormatter(doneButton, 5);
        statHolder.add(buttonFormat.getPanel());
        // UI adjustments end.

        //Save stats
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveInformation(backComboBox.getItemAt(backComboBox.getSelectedIndex()),
                        frontComboBox.getItemAt(frontComboBox.getSelectedIndex()));
            }
        });

        self.pack();
        self.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        self.setVisible(true);
    }
    //save all the information
    private void saveInformation(String background, String foreground){
        holder.setTheme(new CurrentUITheme(background, foreground));
    }
}