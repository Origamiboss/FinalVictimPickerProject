package Main.UI.Frames;

import Main.Holder;
import Students.Victim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class EditClassFrame extends JFrame {
    private Holder holder;
    final private JFrame self = this;
    private HashMap<String, Integer> victimSelect;
    public EditClassFrame(Holder h){
        //save data thats needed
        holder = h;


        //create the Panel to Edit those victims
        JPanel mainpanel = new JPanel();
        self.add(mainpanel);
        //constrain layout
        mainpanel.setLayout(new BoxLayout(mainpanel,BoxLayout.X_AXIS));
        mainpanel.setPreferredSize(new Dimension(600,500));

        JPanel statHolder = new JPanel();
        statHolder.setPreferredSize(new Dimension(400,500));
        mainpanel.add(statHolder);

        //Create the Victim Holder
        JPanel victimHolder = new JPanel();
        victimHolder.setLayout(new BoxLayout(victimHolder,BoxLayout.Y_AXIS));
        mainpanel.add(victimHolder);

        //Add the victims to the Victim Holder
        victimSelect = new HashMap<String, Integer>();
        for(Victim v : holder.getVictims()){
            //set the hashmap to false for everyone
            String id = v.getName().getFirstName() + v.getName().getNickName() + v.getName().getLastName();
            victimSelect.put(id, 0);
            JPanel temp = new JPanel();
            temp.setPreferredSize(new Dimension(150, 50));
            JCheckBox checked = new JCheckBox();
            temp.add(checked);

            //reset id so it can be a name
            String name = v.getName().getFirstName() + " " + v.getName().getLastName();
            temp.add(new JLabel(name));

            checked.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //make sure it switches
                    String id = v.getName().getFirstName() + v.getName().getNickName() + v.getName().getLastName();
                    if(victimSelect.get(id) == 0)
                        victimSelect.put(id,1);
                    else
                        victimSelect.put(id,0);
                }
            });
            victimHolder.add(temp);
        }
        //set up a scroll wheel for the victimHolder
        JScrollPane pane = new JScrollPane(victimHolder, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainpanel.add(pane);



        //create the stat holders and editors on the left side
        //Put them into their own panel so they stay together by using a pointer
        JPanel tempHolder = new JPanel();
        tempHolder.setPreferredSize(new Dimension(300,50));
        tempHolder.add(new JLabel("Times Picked:"));
        JTextField TimesPicked = new JTextField();
        TimesPicked.setText("");
        TimesPicked.setPreferredSize(new Dimension(100,25));
        tempHolder.add(TimesPicked);
        statHolder.add(tempHolder);

        tempHolder = new JPanel();
        tempHolder.setPreferredSize(new Dimension(300,50));
        tempHolder.add(new JLabel("Points:"));
        JTextField Points = new JTextField();
        Points.setText("");
        Points.setPreferredSize(new Dimension(100,25));
        tempHolder.add(Points);
        statHolder.add(tempHolder);

        tempHolder = new JPanel();
        tempHolder.setPreferredSize(new Dimension(300,50));
        tempHolder.add(new JLabel("Absents:"));
        JTextField Absents = new JTextField();
        Absents.setText("");
        Absents.setPreferredSize(new Dimension(100,25));
        tempHolder.add(Absents);
        statHolder.add(tempHolder);

        tempHolder = new JPanel();
        tempHolder.setPreferredSize(new Dimension(300,50));
        tempHolder.add(new JLabel("Answered:"));
        JTextField Answered = new JTextField();
        Answered.setText("");
        Answered.setPreferredSize(new Dimension(100,25));
        tempHolder.add(Answered);
        statHolder.add(tempHolder);

        tempHolder = new JPanel();
        tempHolder.setPreferredSize(new Dimension(300,50));
        tempHolder.add(new JLabel("Passed:"));
        JTextField Passed = new JTextField();
        Passed.setText("");
        Passed.setPreferredSize(new Dimension(100,25));
        tempHolder.add(Passed);
        statHolder.add(tempHolder);

        tempHolder = new JPanel();
        tempHolder.setPreferredSize(new Dimension(300,50));
        tempHolder.add(new JLabel("Phone:"));
        JTextField Phone = new JTextField();
        Phone.setText("");
        Phone.setPreferredSize(new Dimension(100,25));
        tempHolder.add(Phone);
        statHolder.add(tempHolder);

        tempHolder = new JPanel();
        tempHolder.setPreferredSize(new Dimension(300,50));
        tempHolder.add(new JLabel("Jail:"));
        JTextField Jail = new JTextField();
        Jail.setText("");
        Jail.setPreferredSize(new Dimension(100,25));
        tempHolder.add(Jail);
        statHolder.add(tempHolder);


        JButton saveButton = new JButton("Save Stats");
        statHolder.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save stats for all victims in hashmap
                int points = -1;
                if(Points.getText().chars().allMatch(Character::isDigit) && !Points.getText().isEmpty())
                    points = Integer.parseInt(Points.getText());
                int absents = -1;
                if(Absents.getText().chars().allMatch(Character::isDigit) && !Absents.getText().isEmpty())
                    absents = Integer.parseInt(Absents.getText());
                int timespicked = -1;
                if(TimesPicked.getText().chars().allMatch(Character::isDigit) && !TimesPicked.getText().isEmpty())
                    timespicked = Integer.parseInt(TimesPicked.getText());
                int passed = -1;
                if(Passed.getText().chars().allMatch(Character::isDigit) && !Passed.getText().isEmpty())
                    passed = Integer.parseInt(Passed.getText());
                int answered = -1;
                if(Answered.getText().chars().allMatch(Character::isDigit) && !Answered.getText().isEmpty())
                    answered = Integer.parseInt(Answered.getText());
                int phone = -1;
                if(Phone.getText().chars().allMatch(Character::isDigit) && !Phone.getText().isEmpty())
                    phone = Integer.parseInt(Phone.getText());
                int jail = -1;
                if(Jail.getText().chars().allMatch(Character::isDigit) && !Jail.getText().isEmpty())
                    jail = Integer.parseInt(Jail.getText());

                saveInformation(points,absents,timespicked,passed,answered,phone,jail);
                }
            });


        self.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        self.pack();
        self.setVisible(true);
    }

    private void saveInformation(int points, int absents, int timespicked, int passed, int answered, int phone, int jail) {
        ArrayList<Victim> victimsToEdit = new ArrayList<Victim>();
        for (Victim v : holder.getVictims()) {
            String id = v.getName().getFirstName() + v.getName().getNickName() + v.getName().getLastName();
            if (victimSelect.get(id) == 1) {
                //check to see what needs to change
                if (points < 0)
                    points = v.getPoints();
                if (absents < 0)
                    absents = v.getAbsences();
                if (timespicked < 0)
                    timespicked = v.getNumPicked();
                if (passed < 0)
                    passed = v.getPassed();
                if (answered < 0)
                    answered = v.getAnswered();
                if (phone < 0)
                    phone = v.getPhone();
                if (jail < 0)
                    jail = v.getJail();
                Victim newGuys = new Victim(v.getName(),
                        points,
                        absents,
                        timespicked,
                        passed,
                        answered,
                        phone,
                        jail);
                victimsToEdit.add(newGuys);
            }
        }
        for (Victim v : victimsToEdit) {
            holder.editVictim(v);
        }

        //kill the frame
        self.dispatchEvent(new WindowEvent(self, WindowEvent.WINDOW_CLOSING));

    }
}