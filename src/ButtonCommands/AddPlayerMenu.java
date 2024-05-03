package ButtonCommands;

import Interfaces.Instructions;
import Main.UI.Format.VicFormatter;
import Students.StudentFunctions.Names;
import Students.Victim;
import UIElements.Colors.CurrentUITheme;
import UIElements.TextCanvas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AddPlayerMenu {
    private JFrame frame;
    private JPopupMenu popupMenu;
    private Victim inVictim;
    private ArrayList<Victim> vics;

    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField nicknameTextField;


    public AddPlayerMenu(ArrayList<Victim> inVicz, CurrentUITheme theme) {
        vics = inVicz;
        frame = new JFrame("Add Victim");
        popupMenu = new JPopupMenu();


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(8, 8, 8, 8);

        // First Name label and text field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameTextField = new TextCanvas(theme, 20, true);
        firstNameTextField.setPreferredSize(new Dimension(200, 25));
        firstNameTextField.setBorder(new LineBorder(theme.getCurrentForegroundColor().main()));
        VicFormatter firstFormat = new VicFormatter(firstNameTextField, 5);
        panel.add(firstNameLabel, constraints);
        constraints.gridx = 1;
        panel.add(firstNameTextField, constraints);

        // Reset gridx for the next row
        constraints.gridx = 0;

        // Last Name label and text field
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameTextField = new TextCanvas(theme, 20, true);
        lastNameTextField.setPreferredSize(new Dimension(200, 25));
        lastNameTextField.setBorder(new LineBorder(theme.getCurrentForegroundColor().main()));
        panel.add(lastNameLabel, constraints);
        constraints.gridx = 1;
        panel.add(lastNameTextField, constraints);

        // Reset gridx for the next row
        constraints.gridx = 0;

        // Nickname label and text field
        JLabel nicknameLabel = new JLabel("Nickname:");
        nicknameTextField = new TextCanvas(theme, 20, true);
        nicknameTextField.setPreferredSize(new Dimension(200, 25));
        nicknameTextField.setBorder(new LineBorder(theme.getCurrentForegroundColor().main()));
        panel.add(nicknameLabel, constraints);
        constraints.gridx = 1;
        panel.add(nicknameTextField, constraints);

        JPanel buttonPanel = new JPanel();
        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> addObject());
        buttonPanel.add(submit);

        JPanel combined = new JPanel();
        combined.setLayout(new BoxLayout(combined, BoxLayout.Y_AXIS));
        combined.add(panel);
        combined.add(buttonPanel);

        frame.add(combined);
        frame.pack();  // Adjusts window to fit all components
        frame.setVisible(true);
    }

    private void addObject() {
        Names studentName = new Names();

        studentName.setFirstName(firstNameTextField.getText());
        studentName.setLastName(lastNameTextField.getText());
        studentName.setNickName(nicknameTextField.getText());

        Victim newVictim = new Victim(studentName, 0, 0, 0, 0, 0, 2, 2);

        if (newVictim != null) {
            Victim obj = newVictim;
            vics.add(obj);
            System.out.println("Object added: " + obj);
        }

        for (Victim victimz : vics){
            System.out.println(victimz.getName().getFirstName());
        }

        firstNameTextField.setText("");
        lastNameTextField.setText("");
        nicknameTextField.setText("");

    }
}