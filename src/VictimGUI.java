package src;

import src.Victim.VictimPicker;

import javax.swing.*;
import java.awt.*;

public class VictimGUI extends JFrame  {
    public VictimGUI(VictimPicker v) {
        super("VictimGUI");
        setLayout(new BorderLayout());
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        VictimPanel victimPanel = new VictimPanel(v);
        add(victimPanel);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
        setResizable(false);
    }
}
