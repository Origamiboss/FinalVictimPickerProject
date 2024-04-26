package Main.UI.Frames;

import Main.Holder;
import Main.UI.Format.VicFormatter;
import Students.Victim;
import UIElements.Buttons.RoundButton;
import UIElements.Buttons.RoundedButton;
import UIElements.Panels.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class EditVictimFrame extends JFrame {
    private Holder holder;
    final private JFrame self = this;
    public EditVictimFrame(Holder h) {
        holder = h;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Edit Victim");

        // Use a custom panel with BoxLayout for vertical stacking of buttons
        RoundedPanel optionMenu = new RoundedPanel(holder.getTheme());
        optionMenu.setLayout(new BoxLayout(optionMenu, BoxLayout.Y_AXIS));
        optionMenu.setPreferredSize(new Dimension(250, holder.getVictims().size() * 60)); // Increase initial width

        for (int i = 0; i < holder.getVictims().size(); i++) {
            Victim v = holder.getVictims().get(i);
            String name = v.getName().getFirstName() + " " + v.getName().getLastName();
            RoundButton newButton = new RoundButton(name, holder.getTheme());
            newButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Align buttons
            optionMenu.add(newButton);
            newButton.addActionListener(e -> MakeEditScreen(v));

            if (i < holder.getVictims().size() - 1) {
                // Adds space between buttons
                optionMenu.add(Box.createRigidArea(new Dimension(0, 10))); // Adds 10 pixels space between buttons
            }
        }

        // JScrollPane setup
        JScrollPane scrollPane = new JScrollPane(optionMenu);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Content Pane setup with a custom rounded panel
        RoundedPanel contentPane = new RoundedPanel(holder.getTheme());
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);

        this.setContentPane(contentPane);
        this.pack();
        this.setVisible(true);
    }


    private void MakeEditScreen(Victim v) {
        final JFrame victimEditor = new JFrame("Edit Victim Stats");
        victimEditor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        RoundedPanel statHolder = new RoundedPanel(holder.getTheme());
        statHolder.setLayout(new BoxLayout(statHolder, BoxLayout.Y_AXIS));
        statHolder.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the outer panel

        JLabel nameLabel = new JLabel(v.getName().getFirstName() + " " + v.getName().getLastName(), SwingConstants.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); // Padding below the name label
        statHolder.add(nameLabel);

        RoundedPanel tempHolder;
        JLabel label;
        JTextField textField;

        // Nickname
        tempHolder = new RoundedPanel(holder.getTheme());
        label = new JLabel("Nickname:");
        textField = new JTextField(v.getName().getNickName(), 10);
        tempHolder.add(label);
        tempHolder.add(textField);
        statHolder.add(tempHolder);

        // Times Picked
        tempHolder = new RoundedPanel(holder.getTheme());
        label = new JLabel("Times Picked:");
        textField = new JTextField(Integer.toString(v.getNumPicked()), 10);
        tempHolder.add(label);
        tempHolder.add(textField);
        statHolder.add(tempHolder);

        // Points
        tempHolder = new RoundedPanel(holder.getTheme());
        label = new JLabel("Points:");
        textField = new JTextField(Integer.toString(v.getPoints()), 10);
        tempHolder.add(label);
        tempHolder.add(textField);
        statHolder.add(tempHolder);

        // Absents
        tempHolder = new RoundedPanel(holder.getTheme());
        label = new JLabel("Absents:");
        textField = new JTextField(Integer.toString(v.getAbsences()), 10);
        tempHolder.add(label);
        tempHolder.add(textField);
        statHolder.add(tempHolder);

        // Answered
        tempHolder = new RoundedPanel(holder.getTheme());
        label = new JLabel("Answered:");
        textField = new JTextField(Integer.toString(v.getAnswered()), 10);
        tempHolder.add(label);
        tempHolder.add(textField);
        statHolder.add(tempHolder);

        // Passed
        tempHolder = new RoundedPanel(holder.getTheme());
        label = new JLabel("Passed:");
        textField = new JTextField(Integer.toString(v.getPassed()), 10);
        tempHolder.add(label);
        tempHolder.add(textField);
        statHolder.add(tempHolder);

        // Phone
        tempHolder = new RoundedPanel(holder.getTheme());
        label = new JLabel("Phone:");
        textField = new JTextField(Integer.toString(v.getPhone()), 10);
        tempHolder.add(label);
        tempHolder.add(textField);
        statHolder.add(tempHolder);

        // Jail
        tempHolder = new RoundedPanel(holder.getTheme());
        label = new JLabel("Jail:");
        textField = new JTextField(Integer.toString(v.getJail()), 10);
        tempHolder.add(label);
        tempHolder.add(textField);
        statHolder.add(tempHolder);

        // Save button
        RoundButton saveButton = new RoundButton("Save Stats", holder.getTheme());
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Insert save logic here
                victimEditor.dispose();
            }
        });
        statHolder.add(Box.createRigidArea(new Dimension(0, 10))); // Space before the save button
        statHolder.add(saveButton);

        victimEditor.add(statHolder);
        victimEditor.pack();
        victimEditor.setLocationRelativeTo(null);
        victimEditor.setVisible(true);
    }

}