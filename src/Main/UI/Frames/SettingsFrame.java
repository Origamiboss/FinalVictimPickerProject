package Main.UI.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.UIColors;

public class SettingsFrame extends JFrame {
    final private JFrame self = this;
    private src.Main.Holder holder;
    public SettingsFrame(src.Main.Holder h){
        holder = h;

        //make the holder panel
        self.setTitle("Settings");
        JPanel statHolder = new JPanel();
        self.add(statHolder);
        statHolder.setLayout(new GridLayout(4,1));

        //create each stat and put it inside its own panel
        //Color changer
        String[] optionsToChoose = new String[UIColors.colors.length];
        for(int i = 0; i < UIColors.colors.length; i++){
            optionsToChoose[i] = UIColors.colors[i];
        }

        JPanel backcolorHolder = new JPanel();
        backcolorHolder.add(new JLabel("Background:"));
        // Creating a JComboBox and setting its bounds
        JComboBox<String> backComboBox = new JComboBox<>(optionsToChoose);
        backComboBox.setBounds(80, 50, 140, 20);
        //set the start value
        backComboBox.setSelectedItem(holder.getTheme().getBackgroundString());
        backcolorHolder.add(backComboBox);
        statHolder.add(backcolorHolder);

        //foreground
        JPanel frontcolorHolder = new JPanel();
        frontcolorHolder.add(new JLabel("Foreground:"));
        // Creating a JComboBox and setting its bounds
        JComboBox<String> frontComboBox = new JComboBox<>(optionsToChoose);
        frontComboBox.setBounds(80, 50, 140, 20);
        //set the start value
        frontComboBox.setSelectedItem(holder.getTheme().getForegroundString());
        frontcolorHolder.add(frontComboBox);
        statHolder.add(frontcolorHolder);

        //get the JComboBox String with jComboBox.getItemAt(jComboBox.getSelectedIndex());
        statHolder.add(new JLabel("The changes will occur the next time you open the program."));
        JButton doneButton = new JButton("Save");
        statHolder.add(new JPanel().add(doneButton));
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
