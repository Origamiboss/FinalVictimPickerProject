package src.Main.UI.Panels;

import src.Main.UI.Format.VicFormatter;
import src.Students.Victim;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;
import src.UIElements.Panels.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class ControlPanel {
    private RoundedPanel controlPanel;
    private VicFormatter addVictim;
    private VicFormatter deleteVictim;
    private VicFormatter editVictim;
    private VicFormatter editClass;
    private VicFormatter settings;
    private VicFormatter save;
    private VicFormatter exit;
    private VicFormatter topPanel;
    private HashMap<String, JComponent> map;
    private src.Main.Holder holder;

    public ControlPanel(CurrentUITheme theme, src.Main.Holder holder) {
        map = new HashMap<>();

        //assign the main holder
        this.holder = holder;

        controlPanel = new RoundedPanel(theme);
        map.put("csButton1", controlPanel);

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        int buffDistance = 5;
        Images imageGetter;

        imageGetter = new Images("plus", theme, "UIimage");
        RoundButton addVic = new RoundButton(imageGetter.getImage(), theme);
        addVic.setToolTipText("Add Victim");
        map.put("csButton2", addVic);
        addVictim = new VicFormatter(addVic, buffDistance);

        imageGetter = new Images("floppy", theme, "UIimage");
        RoundButton saveV = new RoundButton(imageGetter.getImage(), theme);
        saveV.setToolTipText("Save");
        map.put("csButton7", saveV);
        save = new VicFormatter(saveV, buffDistance);

        imageGetter = new Images("X", theme, "UIimage");
        RoundButton delVic = new RoundButton(imageGetter.getImage(), theme);
        delVic.setToolTipText("Delete Victim");
        map.put("csButton3", delVic);
        deleteVictim = new VicFormatter(delVic, buffDistance);

        imageGetter = new Images("person", theme, "UIimage");
        RoundButton edtVic = new RoundButton(imageGetter.getImage(), theme);
        edtVic.setToolTipText("Edit Victim");
        map.put("csButton4", edtVic);
        editVictim = new VicFormatter(edtVic, buffDistance);

        imageGetter = new Images("people", theme, "UIimage");
        RoundButton edtPpl = new RoundButton(imageGetter.getImage(), theme);
        edtPpl.setToolTipText("Edit Class");
        map.put("csButton5", edtPpl);
        editClass = new VicFormatter(edtPpl, buffDistance);

        imageGetter = new Images("gear", theme, "UIimage");
        RoundButton settingsBt = new RoundButton(imageGetter.getImage(), theme);
        settingsBt.setToolTipText("Settings");
        map.put("csButton6", settingsBt);
        settings = new VicFormatter(settingsBt, buffDistance);

        imageGetter = new Images("door", theme, "UIimage");
        RoundButton ext = new RoundButton(imageGetter.getImage(), theme);
        ext.setToolTipText("Exit");
        map.put("csButton8", ext);
        exit = new VicFormatter(ext, buffDistance);

        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension(0, 500));
        //test.setMinimumSize(new Dimension(1, 200));
        //test.setMaximumSize(new Dimension(1, 800));
        VicFormatter testBox = new VicFormatter(test, buffDistance);

        controlPanel.add(addVictim.getPanel());
        controlPanel.add(deleteVictim.getPanel());
        controlPanel.add(editVictim.getPanel());
        controlPanel.add(editClass.getPanel());
        controlPanel.add(settings.getPanel());
        controlPanel.add(test);
        controlPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        //controlPanel.add(testBox.getPanel());
        //controlPanel.add(test);

        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.Y_AXIS));
        savePanel.add(save.getPanel());
        savePanel.add(exit.getPanel());
        controlPanel.add(save.getPanel());
        controlPanel.add(exit.getPanel());

        topPanel = new VicFormatter(controlPanel, buffDistance);
        //topPanel.getPanel().setMinimumSize(new Dimension(300, 300));
        map.put("csTopPanel", topPanel.getPanel());

        //Call the functions for the Add victim button
        addVictim.getComponent().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                AddVictimPopUp();
            }
        });
        //Call the functions for the Delete victim button
        deleteVictim.getComponent().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                DeleteVictimPopUp();
            }
        });
        //Call the functions for the Delete victim button
        editVictim.getComponent().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                EditVictimPopUp();
            }
        });
    }

    public JPanel getFormat() {
        return topPanel.getPanel();
    }

    public HashMap<String, JComponent> getMap(){
        return map;
    }


    public void updateColors(CurrentUITheme currentTheme) {
        // Update the main control panel
        controlPanel.setBackground(currentTheme.getCurrentBackgroundColor().main());
        controlPanel.setForeground(currentTheme.getCurrentForegroundColor().main());

        // Iterate through all components in the panel and update their colors
        for (Map.Entry<String, JComponent> entry : map.entrySet()) {
            JComponent component = entry.getValue();
            if (component instanceof RoundButton) { // Check if it's a RoundButton to update
                component.setBackground(currentTheme.getCurrentBackgroundColor().main());
                component.repaint();
                component.setForeground(currentTheme.getCurrentForegroundColor().main());
                component.repaint();
                // if RoundButton has specific methods to update its appearance, call them here
            }
            // Add more conditions if there are different types of components with distinct update methods
        }

        controlPanel.repaint(); // Repaint the control panel to reflect the theme changes
    }
    //Add Victim's Popup
    private void AddVictimPopUp(){
        //Experiment
        final JFrame parent = new JFrame();
        String VictimName = JOptionPane.showInputDialog(parent,
                "Add the Victims Name", null);
        if(VictimName != null) {
            holder.addVictim(VictimName);
        }

    }
    private void DeleteVictimPopUp(){
        //Experiment
        final JFrame parent = new JFrame();
        //create the options panel
        JPanel optionMenu = new JPanel();
        optionMenu.setPreferredSize(new Dimension(200,holder.getVictims().size() * 31));
        for (src.Students.Victim v : holder.getVictims()) {
            String name = v.getName().getFirstName() + " " + v.getName().getLastName();
            JButton newButton = new JButton(name);
            optionMenu.add(newButton);
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //call delete for the button
                    holder.deleteVictim(v);

                    //Kill the frame
                    parent.dispatchEvent(new WindowEvent(parent, WindowEvent.WINDOW_CLOSING));
                }
            });

        }
        JScrollPane scrollPane = new JScrollPane(optionMenu);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 30, 200, 300);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        parent.setContentPane(contentPane);
        parent.pack();
        parent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        parent.setVisible(true);
    }
    private void EditVictimPopUp(){
        //Experiment
        final JFrame parent = new JFrame();
        //create the Victim Select Panel
        JPanel optionMenu = new JPanel();
        optionMenu.setPreferredSize(new Dimension(200,holder.getVictims().size() * 31));
        for (src.Students.Victim v : holder.getVictims()) {
            String name = v.getName().getFirstName() + " " + v.getName().getLastName();
            JButton newButton = new JButton(name);
            optionMenu.add(newButton);
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //create the Panel to Edit that specific victim
                    final JFrame victimEditor = new JFrame();
                    JPanel statHolder = new JPanel();
                    statHolder.setPreferredSize(new Dimension(300,300));
                    victimEditor.add(statHolder);
                    //Create the name Holder
                    statHolder.add(new JLabel(name));

                    //create the stat holders and editors
                    //Put them into their own panel so they stay together by using a pointer
                    JPanel tempHolder = new JPanel();
                    tempHolder.setPreferredSize(new Dimension(300,25));
                    tempHolder.add(new JLabel("Nickname:"));
                    JTextField Nickname = new JTextField();
                    Nickname.setPreferredSize(new Dimension(100, 25));
                    Nickname.setText(v.getName().getNickName());
                    tempHolder.add(Nickname);
                    statHolder.add(tempHolder);

                    tempHolder = new JPanel();
                    tempHolder.setPreferredSize(new Dimension(300,25));
                    tempHolder.add(new JLabel("Times Picked:"));
                    JTextField TimesPicked = new JTextField();
                    TimesPicked.setPreferredSize(new Dimension(100,25));
                    TimesPicked.setText(Integer.toString(v.getNumPicked()));
                    tempHolder.add(TimesPicked);
                    statHolder.add(tempHolder);

                    tempHolder = new JPanel();
                    tempHolder.setPreferredSize(new Dimension(300,25));
                    tempHolder.add(new JLabel("Points:"));
                    JTextField Points = new JTextField();
                    Points.setPreferredSize(new Dimension(100,25));
                    Points.setText(Integer.toString(v.getPoints()));
                    tempHolder.add(Points);
                    statHolder.add(tempHolder);

                    tempHolder = new JPanel();
                    tempHolder.setPreferredSize(new Dimension(300,25));
                    tempHolder.add(new JLabel("Absents:"));
                    JTextField Absents = new JTextField();
                    Absents.setPreferredSize(new Dimension(100,25));
                    Absents.setText(Integer.toString(v.getAbsences()));
                    tempHolder.add(Absents);
                    statHolder.add(tempHolder);

                    tempHolder = new JPanel();
                    tempHolder.setPreferredSize(new Dimension(300,25));
                    tempHolder.add(new JLabel("Answered:"));
                    JTextField Answered = new JTextField();
                    Answered.setPreferredSize(new Dimension(100,25));
                    Answered.setText(Integer.toString(v.getAnswered()));
                    tempHolder.add(Answered);
                    statHolder.add(tempHolder);

                    tempHolder = new JPanel();
                    tempHolder.setPreferredSize(new Dimension(300,25));
                    tempHolder.add(new JLabel("Passed:"));
                    JTextField Passed = new JTextField();
                    Passed.setPreferredSize(new Dimension(100,25));
                    Passed.setText(Integer.toString(v.getPassed()));
                    tempHolder.add(Passed);
                    statHolder.add(tempHolder);

                    tempHolder = new JPanel();
                    tempHolder.setPreferredSize(new Dimension(300,25));
                    tempHolder.add(new JLabel("Phone:"));
                    JTextField Phone = new JTextField();
                    Phone.setPreferredSize(new Dimension(100,25));
                    Phone.setText(Integer.toString(v.getPhone()));
                    tempHolder.add(Phone);
                    statHolder.add(tempHolder);

                    tempHolder = new JPanel();
                    tempHolder.setPreferredSize(new Dimension(300,25));
                    tempHolder.add(new JLabel("Jail:"));
                    JTextField Jail = new JTextField();
                    Jail.setPreferredSize(new Dimension(100,25));
                    Jail.setText(Integer.toString(v.getJail()));
                    tempHolder.add(Jail);
                    statHolder.add(tempHolder);


                    JButton saveButton = new JButton("Save Stats");
                    statHolder.add(saveButton);
                    saveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //save stats
                            v.getName().setNickName(Nickname.getText());
                            Victim newGuy = new Victim(v.getName(),
                                    Integer.parseInt(Points.getText()),
                                    Integer.parseInt(Absents.getText()),
                                    Integer.parseInt(TimesPicked.getText()),
                                    Integer.parseInt(Passed.getText()),
                                    Integer.parseInt(Answered.getText()),
                                    Integer.parseInt(Phone.getText()),
                                    Integer.parseInt(Jail.getText()));
                            holder.editVictim(newGuy);

                            //kill the frame
                            victimEditor.dispatchEvent(new WindowEvent(victimEditor, WindowEvent.WINDOW_CLOSING));
                            parent.dispatchEvent(new WindowEvent(parent, WindowEvent.WINDOW_CLOSING));
                        }
                    });


                    victimEditor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    victimEditor.pack();
                    victimEditor.setVisible(true);
                }
            });

        }
        JScrollPane scrollPane = new JScrollPane(optionMenu);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 30, 200, 300);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        parent.setContentPane(contentPane);
        parent.pack();
        parent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        parent.setVisible(true);
    }
}
