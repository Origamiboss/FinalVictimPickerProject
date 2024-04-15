package src.ButtonCommands;

import src.Main.VictimPanelManager;
import src.Students.Victim;
import src.UIElements.Panels.PlayerPanel;

import javax.swing.*;
import java.util.ArrayList;

public class StudentTable {
    private ArrayList<PlayerPanel> vicPanels;
    private ArrayList<Victim> victims;
    private Object[][] data;
    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames;
    private JButton submit;

    public StudentTable(ArrayList<PlayerPanel> inVicPanels){
        vicPanels = inVicPanels;
        for (PlayerPanel vics : vicPanels){
            victims.add(vics.getVictim());
        }



    }


}
