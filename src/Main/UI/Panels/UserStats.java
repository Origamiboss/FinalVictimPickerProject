package src.Main.UI.Panels;

import src.Main.UI.Format.VicFormatter;
import src.Students.Victim;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.RoundedPanel;
import src.UIElements.TextCanvas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserStats extends RoundedPanel {
    private String firstName = "All";
    private String lastName = "Work";
    private String nickName = "No Play";
    private String points = "All Work";
    private String absents = "No Play";
    private String answered = "All Work";
    private String timesPicked = "No Play";
    private String passed = "All Work";
    private String influence = "No Play";

    private Victim victim;

    private ArrayList<TextCanvas> panelHolder;

    private HashMap<String, JComponent> map;

    private String[] stringArray = { firstName + " " + lastName, nickName, points, absents, answered, timesPicked, passed, influence};

    public UserStats(CurrentUITheme theme) {
        super(theme);

        map = new HashMap<>();
        panelHolder = new ArrayList<>();

        JPanel mainHolder = new JPanel();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        CurrentUITheme themeInvert = new CurrentUITheme(theme.getForegroundString(), theme.getBackgroundString());

        Font labelFont = new Font("SansSerif", Font.BOLD, 18);

        // Create labels and text fields for the first column
        String[] labelsTextLeft = {"Name", "Points", "Answered", "Passed"};
        for (int i = 0; i < labelsTextLeft.length; i++) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.5;
            gbc.insets = new Insets(2, 2, 2, 2);
            JLabel labelLeft = new JLabel(labelsTextLeft[i]);
            labelLeft.setForeground(theme.getCurrentForegroundColor().main());
            labelLeft.setFont(labelFont); // Set the font here
            add(labelLeft, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.weightx = 1.5;
            gbc.insets = new Insets(2, 2, 2, 2);
            TextCanvas rightTextField = new TextCanvas(themeInvert, 18, false);
            rightTextField.setColumns(10);
            rightTextField.setBorder(new LineBorder(themeInvert.getCurrentBackgroundColor().main()));
            add(rightTextField, gbc);
            map.put("stat" + (i + 1), rightTextField);
            panelHolder.add(rightTextField);
        }

        // Create labels and text fields for the second column
        String[] labelsTextRight = {"Nickname", "Absents", "Times Picked", "Influence"};
        for (int i = 0; i < labelsTextRight.length; i++) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 2;
            gbc.gridy = i;
            gbc.weightx = 0.5;
            gbc.insets = new Insets(2, 2, 2, 2);
            JLabel labelLeft = new JLabel(labelsTextRight[i]);
            labelLeft.setForeground(theme.getCurrentForegroundColor().main());
            labelLeft.setFont(labelFont); // Set the font here
            add(labelLeft, gbc);

            gbc.gridx = 3;
            gbc.gridy = i;
            gbc.weightx = 1.5;
            gbc.insets = new Insets(2, 2, 2, 2);
            TextCanvas rightTextField = new TextCanvas(themeInvert, 18, false);
            rightTextField.setColumns(10);
            rightTextField.setBorder(new LineBorder(themeInvert.getCurrentBackgroundColor().main()));
            add(rightTextField, gbc);
            map.put("stat" + (i + 5), rightTextField);
            panelHolder.add(rightTextField);
        }
        writeStats();

        map.put("statMain", this);
    }

    public void writeStats(){
        panelHolder.get(0).setText(firstName + " " + lastName);
        panelHolder.get(1).setText(points);
        panelHolder.get(2).setText(answered);
        panelHolder.get(3).setText(passed);
        panelHolder.get(4).setText(nickName);
        panelHolder.get(5).setText(absents);
        panelHolder.get(6).setText(timesPicked);
        panelHolder.get(7).setText(influence);
    }

    public void setVictim(Victim inVictim){
        victim = new Victim(inVictim);
    }

    public Victim getVictim(){
        return victim;
    }

    public void updateStatText(Victim inVictim){
        setVictim(inVictim);

        firstName = victim.getName().getFirstName();
        lastName = victim.getName().getLastName();
        points = String.valueOf(victim.getPoints());
        absents = String.valueOf(victim.getAbsences());
        answered = String.valueOf(victim.getAnswered());
        timesPicked = String.valueOf(victim.getNumPicked());
        passed = String.valueOf(victim.getPassed());
        influence = String.valueOf(victim.getInfluenceScore());

        writeStats();

    }

    public HashMap<String, JComponent> getMap(){
        return map;
    }

}
