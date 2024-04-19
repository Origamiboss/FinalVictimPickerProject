package Main.UI.Panels;

import Main.Holder;
import Main.UI.Format.VicFormatter;
import Main.UI.Frames.*;
import UIElements.Buttons.RoundButton;
import UIElements.Colors.CurrentUITheme;
import UIElements.Colors.Images;
import UIElements.Panels.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private VicFormatter reset;
    private VicFormatter topPanel;
    private HashMap<String, JComponent> map;
    private Holder holder;

    public ControlPanel(CurrentUITheme theme, Holder holder) {
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

        //Reset Button
        imageGetter = new Images("dice", theme, "UIimage");
        RoundButton res = new RoundButton(imageGetter.getImage(), theme);
        ext.setToolTipText("Exit");
        map.put("csButton9", res);
        reset = new VicFormatter(res, buffDistance);

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
        controlPanel.add(reset.getPanel());
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
                new AddVictimFrame(holder);
            }
        });
        //Call the functions for the Delete victim button
        deleteVictim.getComponent().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                new DeleteVictimFrame(holder);
            }
        });
        //Call the functions for the Delete victim button
        editVictim.getComponent().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                new EditVictimFrame(holder);
            }
        });
        //Call the edit class function
        editClass.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new EditClassFrame(holder);
            }
        });
        settings.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SettingsFrame(holder);
            }
        });
        exit.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ExitFrame(holder);
            }
        });
        save.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                holder.saveStudents();
            }
        });
        reset.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                holder.resetStudents();
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
}