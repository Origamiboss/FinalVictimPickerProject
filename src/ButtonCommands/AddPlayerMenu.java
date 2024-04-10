package src.ButtonCommands;

import src.Interfaces.Instructions;
import src.Students.StudentFunctions.Names;
import src.Students.Victim;

import javax.swing.*;
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


    public AddPlayerMenu(ArrayList<Victim> inVicz) {
        vics = inVicz;
        frame = new JFrame("Add Victim");
        popupMenu = new JPopupMenu();


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(4, 4, 4, 4);

        // First Name label and text field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameTextField = new JTextField(15);
        panel.add(firstNameLabel, constraints);
        constraints.gridx = 1;
        panel.add(firstNameTextField, constraints);

        // Reset gridx for the next row
        constraints.gridx = 0;

        // Last Name label and text field
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameTextField = new JTextField(15);
        panel.add(lastNameLabel, constraints);
        constraints.gridx = 1;
        panel.add(lastNameTextField, constraints);

        // Reset gridx for the next row
        constraints.gridx = 0;

        // Nickname label and text field
        JLabel nicknameLabel = new JLabel("Nickname:");
        nicknameTextField = new JTextField(15);
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