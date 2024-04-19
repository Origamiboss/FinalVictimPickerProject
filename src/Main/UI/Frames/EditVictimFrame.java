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
    public EditVictimFrame(Holder h){
        holder = h;
        //create the Victim Select Panel
        RoundedPanel optionMenu = new RoundedPanel(holder.getTheme());
        optionMenu.setPreferredSize(new Dimension(200,holder.getVictims().size() * 31));
        for (Victim v : holder.getVictims()) {
            String name = v.getName().getFirstName() + " " + v.getName().getLastName();
            RoundButton newButton = new RoundButton(name, holder.getTheme());
            newButton.setSize(new Dimension(300, 50));
            VicFormatter buttonForm = new VicFormatter(newButton, 5);
            optionMenu.add(buttonForm.getPanel());
            optionMenu.add(newButton);
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //create the Panel to Edit that specific victim
                    MakeEditScreen(v);
                }
            });

        }
        JScrollPane scrollPane = new JScrollPane(optionMenu);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 30, 200, 300);
        RoundedPanel contentPane = new RoundedPanel(holder.getTheme());
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        self.setContentPane(contentPane);
        self.pack();
        self.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        self.setVisible(true);


    }
    private void MakeEditScreen(Victim v){
        final JFrame victimEditor = new JFrame();
        RoundedPanel statHolder = new RoundedPanel(holder.getTheme());
        statHolder.setPreferredSize(new Dimension(300,300));
        victimEditor.add(statHolder);
        //Create the name Holder
        String name = v.getName().getFirstName() + " " + v.getName().getLastName();
        statHolder.add(new JLabel(name));

        //create the stat holders and editors
        //Put them into their own panel so they stay together by using a pointer
        RoundedPanel tempHolder = new RoundedPanel(holder.getTheme());
        tempHolder.setPreferredSize(new Dimension(300,25));
        tempHolder.add(new JLabel("Nickname:"));
        JTextField Nickname = new JTextField();
        Nickname.setPreferredSize(new Dimension(100, 25));
        Nickname.setText(v.getName().getNickName());
        tempHolder.add(Nickname);
        statHolder.add(tempHolder);

        tempHolder = new RoundedPanel(holder.getTheme());
        tempHolder.setPreferredSize(new Dimension(300,25));
        tempHolder.add(new JLabel("Times Picked:"));
        JTextField TimesPicked = new JTextField();
        TimesPicked.setPreferredSize(new Dimension(100,25));
        TimesPicked.setText(Integer.toString(v.getNumPicked()));
        tempHolder.add(TimesPicked);
        statHolder.add(tempHolder);

        tempHolder = new RoundedPanel(holder.getTheme());
        tempHolder.setPreferredSize(new Dimension(300,25));
        tempHolder.add(new JLabel("Points:"));
        JTextField Points = new JTextField();
        Points.setPreferredSize(new Dimension(100,25));
        Points.setText(Integer.toString(v.getPoints()));
        tempHolder.add(Points);
        statHolder.add(tempHolder);

        tempHolder = new RoundedPanel(holder.getTheme());
        tempHolder.setPreferredSize(new Dimension(300,25));
        tempHolder.add(new JLabel("Absents:"));
        JTextField Absents = new JTextField();
        Absents.setPreferredSize(new Dimension(100,25));
        Absents.setText(Integer.toString(v.getAbsences()));
        tempHolder.add(Absents);
        statHolder.add(tempHolder);

        tempHolder = new RoundedPanel(holder.getTheme());
        tempHolder.setPreferredSize(new Dimension(300,25));
        tempHolder.add(new JLabel("Answered:"));
        JTextField Answered = new JTextField();
        Answered.setPreferredSize(new Dimension(100,25));
        Answered.setText(Integer.toString(v.getAnswered()));
        tempHolder.add(Answered);
        statHolder.add(tempHolder);

        tempHolder = new RoundedPanel(holder.getTheme());
        tempHolder.setPreferredSize(new Dimension(300,25));
        tempHolder.add(new JLabel("Passed:"));
        JTextField Passed = new JTextField();
        Passed.setPreferredSize(new Dimension(100,25));
        Passed.setText(Integer.toString(v.getPassed()));
        tempHolder.add(Passed);
        statHolder.add(tempHolder);

        tempHolder = new RoundedPanel(holder.getTheme());
        tempHolder.setPreferredSize(new Dimension(300,25));
        tempHolder.add(new JLabel("Phone:"));
        JTextField Phone = new JTextField();
        Phone.setPreferredSize(new Dimension(100,25));
        Phone.setText(Integer.toString(v.getPhone()));
        tempHolder.add(Phone);
        statHolder.add(tempHolder);

        tempHolder = new RoundedPanel(holder.getTheme());
        tempHolder.setPreferredSize(new Dimension(300,25));
        tempHolder.add(new JLabel("Jail:"));
        JTextField Jail = new JTextField();
        Jail.setPreferredSize(new Dimension(100,25));
        Jail.setText(Integer.toString(v.getJail()));
        tempHolder.add(Jail);
        statHolder.add(tempHolder);

        String saveStats = "Save Stats";
        RoundButton saveButton = new RoundButton(saveStats, holder.getTheme());
        statHolder.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save stats
                //make sure all stats are real
                int points = v.getPoints();
                if(Points.getText().chars().allMatch(Character::isDigit) && !Points.getText().isEmpty() &&
                        Integer.parseInt(Points.getText()) >=0)
                    points = Integer.parseInt(Points.getText());
                int absents = v.getAbsences();
                if(Absents.getText().chars().allMatch(Character::isDigit) && !Absents.getText().isEmpty() &&
                        Integer.parseInt(Absents.getText()) >=0)
                    absents = Integer.parseInt(Absents.getText());
                int timespicked = v.getNumPicked();
                if(TimesPicked.getText().chars().allMatch(Character::isDigit) && !TimesPicked.getText().isEmpty() &&
                        Integer.parseInt(TimesPicked.getText()) >=0)
                    timespicked = Integer.parseInt(TimesPicked.getText());
                int passed = v.getPassed();
                if(Passed.getText().chars().allMatch(Character::isDigit) && !Passed.getText().isEmpty() &&
                        Integer.parseInt(Passed.getText()) >=0)
                    passed = Integer.parseInt(Passed.getText());
                int answered = v.getAnswered();
                if(Answered.getText().chars().allMatch(Character::isDigit) && !Answered.getText().isEmpty() &&
                        Integer.parseInt(Answered.getText()) >=0)
                    answered = Integer.parseInt(Answered.getText());
                int phone = v.getPhone();
                if(Phone.getText().chars().allMatch(Character::isDigit) && !Phone.getText().isEmpty() &&
                        Integer.parseInt(Phone.getText()) >=0)
                    phone = Integer.parseInt(Phone.getText());
                int jail = v.getPhone();
                if(Jail.getText().chars().allMatch(Character::isDigit) && !Jail.getText().isEmpty() &&
                        Integer.parseInt(Jail.getText()) >=0)
                    jail = Integer.parseInt(Jail.getText());


                //actually save stats
                v.getName().setNickName(Nickname.getText());
                Victim newGuy = new Victim(v.getName(),
                        points,
                        absents,
                        timespicked,
                        passed,
                        answered,
                        phone,
                        jail);
                holder.editVictim(newGuy);

                //kill the frame
                victimEditor.dispatchEvent(new WindowEvent(victimEditor, WindowEvent.WINDOW_CLOSING));
                self.dispatchEvent(new WindowEvent(self, WindowEvent.WINDOW_CLOSING));
            }
        });


        victimEditor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        victimEditor.pack();
        victimEditor.setVisible(true);
    }
}