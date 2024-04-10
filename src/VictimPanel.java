package src;

import src.Victim.Victim;
import src.Victim.VictimPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VictimPanel extends JPanel {

    private final VictimPicker victimPicker;
    private Timer timer;
    private JLabel timerLabel;
    private int timeLeftInSeconds = 300;
    private JButton startTimerButton = new JButton("Start Timer");
    private JButton absentButton;
    private JButton pointButton;
    private JComboBox<String> victimCountComboBox = null;
    private JComboBox<String> VolunteerComboBox = null;
    private final ArrayList<Victim> presentVictims = new ArrayList<Victim>();

    private final ArrayList<String> absentVictims = new ArrayList<String>();

    private final ArrayList<String> points = new ArrayList<String>();
    private final JPanel victimDisplayPanel = new JPanel();
    private final JPanel absentDisplayPanel = new JPanel();

    private final JPanel pointDisplayPanel = new JPanel();


    public VictimPanel(VictimPicker victimPicker) {
        super(new BorderLayout()); // Better layout manager

        this.victimPicker = victimPicker;

        MenuPanel menuPanel = new MenuPanel(victimPicker);

        menuPanel.setLayout(new GridLayout(0, 1)); // Dynamically add rows

        add(createControlPanel(), BorderLayout.NORTH);
        add(menuPanel, BorderLayout.EAST);
        add(victimDisplayPanel, BorderLayout.CENTER);
        add(absentDisplayPanel, BorderLayout.SOUTH);
        add(timerPanel(), BorderLayout.WEST);

        absentDisplayPanel.setLayout(new GridLayout(0, 4)); // Dynamically add rows

    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        JPanel Volunteer = new JPanel();

        JLabel selectLabel2 = new JLabel("Select Name of volunteers:");
        Volunteer.add(selectLabel2);

        VolunteerComboBox = new JComboBox<>(generateVolunteerOptions());
        VolunteerComboBox.addActionListener(this::handleVolunteerChange);
        Volunteer.add(VolunteerComboBox);

        controlPanel.add(Volunteer, BorderLayout.SOUTH);

        JLabel selectLabel = new JLabel("Select number of victims:");
        controlPanel.add(selectLabel);

        victimCountComboBox = new JComboBox<>(generateVictimCountOptions());
        victimCountComboBox.addActionListener(this::handleVictimCountChange);
        controlPanel.add(victimCountComboBox);

        JButton victimButton = new JButton("Pick Victims");
        victimButton.addActionListener(this::handlePickVictims);
        controlPanel.add(victimButton);


        return controlPanel;
    }

    public JPanel timerPanel()
    {
        JPanel timerPanel = new JPanel(new GridLayout(4, 1));

        timerLabel = new JLabel("Time Left: " + timeLeftInSeconds + " seconds");
        timerLabel.setFont(new Font("Times New Roman", Font.BOLD, 31));
        timerPanel.add(timerLabel);

        startTimerButton.addActionListener(this::handleTimerCountChange);

        JButton stopTimerButton = new JButton("Stop Timer");
        stopTimerButton.addActionListener(this::handleTimerStopCountChange);

        JButton resetTimerButton = new JButton("Reset Timer");
        resetTimerButton.addActionListener(this::handleTimerResetCountChange);


        timerPanel.add(startTimerButton, BorderLayout.CENTER);
        timerPanel.add(stopTimerButton, BorderLayout.CENTER);
        timerPanel.add(resetTimerButton, BorderLayout.CENTER);


        return timerPanel;
    }

    private void handleTimerResetCountChange(ActionEvent actionEvent)
    {
        if (timer != null)
        {
            timer.stop();
        }
        timeLeftInSeconds = 300;
        timerLabel.setText("Time Left: " + timeLeftInSeconds + " seconds");
    }
    private void handleTimerStopCountChange(ActionEvent actionEvent)
    {
        StopTimer();
    }

    private void handleTimerCountChange(ActionEvent actionEvent)
    {
        startTimer();
    }

    //For starting the timer
    private void startTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeftInSeconds--;
                if (timeLeftInSeconds >= 0) {
                    timerLabel.setText("Time Left: " + timeLeftInSeconds + " seconds");
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private void StopTimer()
    {
        if (timer != null && timer.isRunning())
        {
            timer.stop();
        }
    }

    private void handleVolunteerChange(ActionEvent actionEvent)
    {
        String volunteerName = ((String) VolunteerComboBox.getSelectedItem());

        List<Victim> newVictims = victimPicker.VolunteerVictims(volunteerName);

        updateVolunteerDisplay(newVictims);
    }

    private void updateVolunteerDisplay(List<Victim> volunteerName)
    {
        int arrayPos = 1;

        String name = volunteerName.get(0).getName();

        victimDisplayPanel.removeAll(); // Clear existing victims
        absentDisplayPanel.removeAll(); // Clear existing victims
        pointDisplayPanel.removeAll(); // Clear existing victims

        presentVictims.clear();
        presentVictims.addAll(volunteerName);

        for (Victim v : volunteerName) {
            JLabel label = new JLabel("Volunteer: " + volunteerName.get(0).getName());
            PickedVictimDisplay pickedVictimDisplay = new PickedVictimDisplay(v);
            victimDisplayPanel.add(label);
            victimDisplayPanel.add(pickedVictimDisplay);
        }

        victimDisplayPanel.revalidate();
        victimDisplayPanel.repaint();

    }

    private String[] generateVolunteerOptions()
    {
        String[] volunteers = new String[victimPicker.getNumberOfVictims()];

        for (int victim = 0; victim < victimPicker.getNumberOfVictims(); victim++)
        {
            volunteers[victim] = victimPicker.getVictimName(victim);
        }

        return volunteers;
    }

    private String[] generateVictimCountOptions() {
        int numberOfVictims = victimPicker.getNumberOfVictims();

        String[] options = new String[numberOfVictims];
        for (int i = 0; i < numberOfVictims; i++) {
            options[i] = String.valueOf(i + 1);
        }
        return options;
    }

    private void handleVictimCountChange(ActionEvent e)
    {
        int selectedCount = Integer.parseInt((String) victimCountComboBox.getSelectedItem());
        List<Victim> newVictims = victimPicker.pickVictims(selectedCount);
        updateVictimDisplay(newVictims);
    }

    private void handlePickVictims(ActionEvent e)
    {
        handleVictimCountChange(e);  // Reuse the main logic
    }

    private void updateVictimDisplay(List<Victim> newVictims)
    {
        int arrayPos = 0;
        victimDisplayPanel.removeAll(); // Clear existing victims
        absentDisplayPanel.removeAll(); // Clear existing victims
        pointDisplayPanel.removeAll(); // Clear existing victims

        presentVictims.clear();
        presentVictims.addAll(newVictims);

        for (Victim victim : presentVictims)
        {
            arrayPos++;
            JLabel label = new JLabel("<html> Victim: <br/>" + victim.getName() + "<br/></html>");
            absentButton = new JButton("Absent: " + victim.getName());
            PickedVictimDisplay pickedVictimDisplay = new PickedVictimDisplay(victim);
            int finalArrayPos = arrayPos;
            absentButton.addActionListener(event -> handlAbsentVictims(event, finalArrayPos));

            //pointDisplayPanel.add(pointButton);
            absentDisplayPanel.add(absentButton);
            victimDisplayPanel.add(label);
            victimDisplayPanel.add(pickedVictimDisplay);
            victimDisplayPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 25));
        }

        victimDisplayPanel.revalidate();
        victimDisplayPanel.repaint();

        absentDisplayPanel.revalidate();
        absentDisplayPanel.repaint();

        //pointDisplayPanel.revalidate();
        //pointDisplayPanel.repaint();
    }

    private void handlePointButton(ActionEvent actionEvent, int i)
    {
        String name = String.valueOf(presentVictims.get(i - 1).getName());
        System.out.println("Points added to: " + name);

        boolean nameFound = false;

        if(points.isEmpty())
        {
            points.add(name + ", Points: " + 1);
        }
        else
        {
            for(String point : points)
            {
                if(point.contains(name))
                {
                    String[] splitArray = point.split(", Points: ");
                    int pointsAdded = Integer.parseInt(splitArray[1]);
                    pointsAdded++;
                    points.remove(point);
                    points.add(name + ", Points: " + pointsAdded);
                    System.out.println(points);
                    nameFound = true;
                }
            }
            if(nameFound == false)
            {
                points.add(name + ", Points: " + 1);
            }
        }
    }

    private void handlAbsentVictims(ActionEvent actionEvent, int i)
    {
        String name = String.valueOf(presentVictims.get(i - 1).getName());
       // System.out.println("Absent: " + name);
        absentVictims.add(name);
        int index = victimPicker.victimNameIndex(name);
        victimPicker.markAbsent(index);
    }

    public ArrayList<Victim> returnVictim()
    {
        return presentVictims;
    }
}
