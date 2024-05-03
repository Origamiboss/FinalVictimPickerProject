package Main.UI.Panels;

import Interfaces.Instructions;
import Main.Holder;
import Main.UI.Format.VicFormatter;
import Main.UI.Frames.ErrorMessageFrame;
import Main.UI.HighScore;
import Main.VictimPanelManager;
import Students.Victim;
import UIElements.Buttons.HeldButton;
import UIElements.Buttons.RoundButton;
import UIElements.Colors.CurrentUITheme;
import UIElements.Colors.Images;
import UIElements.Panels.RoundedPanel;
import UIElements.TextCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerOptions {
    private Victim victim;
    private RoundedPanel playerOptions;
    private VicFormatter plusButton;
    private VicFormatter minusButton;
    private VicFormatter fiveMultiplyButton;
    private VicFormatter jailButton;
    private VicFormatter fiftyFifButton;
    private VicFormatter phoneFriendButton;
    private VicFormatter absentButton;
    private VicFormatter passButton;
    private VicFormatter topPanel;
    private VicFormatter sendButton;

    private RoundButton addPoints;
    private RoundButton removePoints;
    private HeldButton fiveMultButton;
    private HeldButton JailButton;
    private HeldButton scalesButton;
    private HeldButton phoneButton;
    private HeldButton AbsentButton;
    private HeldButton PassButton;
    private RoundButton SendButton;
    private sendButtonInstruct sendButtonInstruct;

    private TextCanvas textPNL;

    private VictimPanelManager manager;

    private ArrayList<Instructions> instructionsArray;

    private HashMap<String, JComponent> map;

    private QandAPanel QAPanel;

    private static Holder mainHolder;

    int pointText = 0;

    private static CurrentUITheme theme;

    public static final int NUM_ATTRIBUTES = 6;
    private static HighScore highScore;
    private static HighScorePanel highScorePanel;
    public static ArrayList<Victim> victimsTopScores = new ArrayList<>();

    public PlayerOptions(CurrentUITheme theme, VictimPanelManager inManager, Holder holder){
        PlayerOptions.theme = theme;
        map = new HashMap<>();
        manager = inManager;
        instructionsArray = new ArrayList<>();

        playerOptions = new RoundedPanel(theme);
        map.put("poPlayerOptionPanel", playerOptions);

        playerOptions.setLayout(new BoxLayout(playerOptions, BoxLayout.X_AXIS));
        int buffDistance = 5;
        Images imageGetter;

        Image image = null;

        imageGetter = new Images("plus", theme, "UIimage");
        addPoints = new RoundButton(imageGetter.getImage(), theme);
        map.put("poButton1", addPoints);
        plusButton = new VicFormatter(addPoints, buffDistance);

        imageGetter = new Images("dash", theme, "UIimage");
        removePoints = new RoundButton(imageGetter.getImage(), theme);
        map.put("poButton2", removePoints);
        minusButton = new VicFormatter(removePoints, buffDistance);

        imageGetter = new Images("5", theme, "UIimage");
        fiveMultButton = new HeldButton(imageGetter.getImage(), theme);
        map.put("poButton3", fiveMultButton);
        fiveMultiplyButton = new VicFormatter(fiveMultButton, buffDistance);

        imageGetter = new Images("robber", theme, "UIimage");
        JailButton = new HeldButton(imageGetter.getImage(), theme);
        map.put("poButton4", JailButton);
        jailButton = new VicFormatter(JailButton, buffDistance);

        imageGetter = new Images("scales", theme, "UIimage");
        scalesButton = new HeldButton(imageGetter.getImage(), theme);
        map.put("poButton5", scalesButton);
        fiftyFifButton = new VicFormatter(scalesButton, buffDistance);

        imageGetter = new Images("phone", theme, "UIimage");
        phoneButton = new HeldButton(imageGetter.getImage(), theme);
        map.put("poButton6", phoneButton);
        phoneFriendButton = new VicFormatter(phoneButton, buffDistance);

        imageGetter = new Images("noSign", theme, "UIimage");
        AbsentButton = new HeldButton(imageGetter.getImage(), theme);
        map.put("poButton7", AbsentButton);
        absentButton = new VicFormatter(AbsentButton, buffDistance);

        imageGetter = new Images("pass", theme, "UIimage");
        PassButton = new HeldButton(imageGetter.getImage(), theme);
        map.put("poButton8", PassButton);
        passButton = new VicFormatter(PassButton, buffDistance);

        textPNL = new TextCanvas(theme, 19, true);
        VicFormatter textFormat = new VicFormatter(textPNL, buffDistance);
        //textFormat.getPanel().setOpaque(false);
        textPNL.setBackground(theme.getCurrentBackgroundColor().main());
        textPNL.setOpaque(false);
        textPNL.setColumnWidths(3);
        map.put("poTextCanvas", textPNL);
        textPNL.setPreferredSize(new Dimension(1, 30));
        RoundedPanel round = new RoundedPanel(theme);
        round.setPreferredSize(new Dimension(65, 48));
        round.add(textFormat.getPanel());
        int middle = textPNL.getWidth() / 2;
        textPNL.setCaretPosition(middle);
        VicFormatter roundFormat = new VicFormatter(round, buffDistance);

        imageGetter = new Images("uparrow", theme, "UIimage");
        SendButton = new RoundButton(imageGetter.getImage(), theme);
        map.put("poButton9", SendButton);
        sendButton = new VicFormatter(SendButton, buffDistance);

        playerOptions.add(sendButton.getPanel());
        playerOptions.add(round);
        playerOptions.add(plusButton.getPanel());
        playerOptions.add(minusButton.getPanel());
        playerOptions.add(Box.createRigidArea(new Dimension(70, 10)));
        playerOptions.add(fiveMultiplyButton.getPanel());
        playerOptions.add(jailButton.getPanel());
        playerOptions.add(fiftyFifButton.getPanel());
        playerOptions.add(phoneFriendButton.getPanel());
        playerOptions.add(absentButton.getPanel());
        playerOptions.add(passButton.getPanel());

        topPanel = new VicFormatter(playerOptions, buffDistance);
        topPanel.getPanel().setMinimumSize(new Dimension(300,300));
        map.put("poTopPanel", topPanel.getPanel());

        sendButtonInstruct = new sendButtonInstruct();

        sendButton.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (textPNL.getText().equals("")) //Empty text so don't send anything
                    return;

                if (textPNL.getText().equals("5")) //testing error message
                    new ErrorMessageFrame("Text is 5", holder);

                sendButtonInstruct.update(manager);

            }
        });

        plusButton.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (fiveMultButton.isHeld())
                    pointText += 5;
                else
                    pointText++;
                textPNL.setText(String.valueOf(pointText));

            }
        });

        minusButton.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (fiveMultButton.isHeld())
                    pointText -= 5;
                else
                    pointText--;
                textPNL.setText(String.valueOf(pointText));
            }
        });

        fiveMultiplyButton.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Your action handling code here
                //fiveMultButton.update(); // Assuming update() is a method that handles the toggle state
            }
        });

        jailButton.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Your action handling code here
                //JailButton.update(); // Assuming update() is a method that handles the toggle state
            }
        });

        fiftyFifButton.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Your action handling code here

            }
        });

        phoneFriendButton.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Your action handling code here
                //phoneButton.update(); // Assuming update() is a method that handles the toggle state
            }
        });

        absentButton.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Your action handling code here
                //AbsentButton.update(); // Assuming update() is a method that handles the toggle state
            }
        });

        passButton.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Your action handling code here
                //PassButton.update(); // Assuming update() is a method that handles the toggle state
            }
        });

    }

    public JPanel getFormat() { return topPanel.getPanel(); }

    public void setQAPanel(QandAPanel inQA){
        this.QAPanel = inQA;
    }

    public HashMap<String, JComponent> getMap() { return map; }

    public void updateColors(CurrentUITheme currentTheme) {
        // Update the main control panel
        playerOptions.setBackground(currentTheme.getCurrentBackgroundColor().main());
        playerOptions.setForeground(currentTheme.getCurrentForegroundColor().main());

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

        playerOptions.repaint(); // Repaint the control panel to reflect the theme changes
    }

    public static void currentHighScorePanel (HighScorePanel highScorePanel, HighScore highscore) //-------------------------------------------------------
    {
        PlayerOptions.highScorePanel = highScorePanel;
        PlayerOptions.highScore = highscore;
    }

    public static void updateLeaderBoard(Victim victim) //-----------------------------------------------------------------------
    {
        highScore.storeUpdatedVictim(victim);
        victimsTopScores = highScore.leaderBoard(5);
        highScorePanel.updatePanel(victimsTopScores);
    }

    public static class AddPointsInstructions implements Instructions<Victim>{
        private int points;

        public AddPointsInstructions(int points){
            this.points = points;
        }

        @Override
        public void update(Victim victim) {
            System.out.println("Victim: " + victim.getFullName());
            System.out.println("\tCurrent Points: " + victim.getPoints());
            victim.setPoints(points);
            updateLeaderBoard(victim);

        }
    }
    public static class AddJailInstructions implements Instructions<Victim>{
        private int points;

        public AddJailInstructions(int points){
            this.points = points;
        }

        @Override
        public void update(Victim victim) {
            System.out.println("Current Points: " + victim.getPoints());
            victim.setJail(points);
            updateLeaderBoard(victim);
        }
    }

    public static class AddPhoneInstructions implements Instructions<Victim>{
        private int points;

        public AddPhoneInstructions(int points){
            this.points = points;
        }

        @Override
        public void update(Victim victim) {
            victim.setPhone(points);
            updateLeaderBoard(victim);
        }
    }

    public static class AddAbsentInstructions implements Instructions<Victim>{
        private int points;

        public AddAbsentInstructions(int points){
            this.points = points;
        }

        @Override
        public void update(Victim victim) {
            victim.setAbsences(points);
            updateLeaderBoard(victim);
        }
    }

    public static class AddPassInstructions implements Instructions<Victim>{
        private int points;

        public AddPassInstructions(int points){
            this.points = points;
        }

        @Override
        public void update(Victim victim) {
            victim.setPoints(points);
            updateLeaderBoard(victim);
        }
    }

    public static class FiftyFifty implements Instructions<Victim>{
        int points;
        boolean isCorrect = true;

        public FiftyFifty(boolean check){
            isCorrect = check;
        }

        @Override
        public void update(Victim victim) {
            if (!isCorrect){
                points = (victim.getPoints() / 2) * -1;
            }
            else{
                points = victim.getPoints() / 2;
            }

            victim.setPoints(points);
            updateLeaderBoard(victim);
        }
    }

    public class sendButtonInstruct implements Instructions<VictimPanelManager>{

        private VictimPanelManager inManager = manager;

        private boolean isCorrect;

        public sendButtonInstruct()
        {

        }

        @Override
        public void update(VictimPanelManager component) {
            if (!fiveMultButton.isHeld() && !JailButton.isHeld() && !phoneButton.isHeld() && !AbsentButton.isHeld() && !PassButton.isHeld()) {
                System.out.println("Nothing Held");
                try {
                    int points = Integer.parseInt(textPNL.getText());  // Convert text to integer
                    instructionsArray.add(new AddPointsInstructions(points));
                } catch (NumberFormatException f) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    textPNL.setText("");
                }
            }
            else {
                for (int i = 3; i < NUM_ATTRIBUTES + 3; i++) {
                    if (i != 9) {
                        switch(i) {
                            case 3:
                                if (fiveMultButton.isHeld()) {
                                    System.out.println("FiveMultButton is held"); //These are temp while we wait to put in functionality
                                }
                                break;
                            case 4:
                                if (JailButton.isHeld()) {
                                    System.out.println("Jail Button is held");
                                    try {
                                        int points = Integer.parseInt(textPNL.getText());  // Convert text to integer
                                        instructionsArray.add(new AddJailInstructions(points));
                                    } catch (NumberFormatException f) {
                                        System.out.println("Invalid input. Please enter a valid number.");textPNL.setText("");
                                    }
                                }
                                break;
                            case 5:
                                if (scalesButton.isHeld()){

                                }
                            case 6:
                                if (phoneButton.isHeld()) {
                                    System.out.println("Phone Button is held");
                                    try {
                                        int points = Integer.parseInt(textPNL.getText());  // Convert text to integer
                                        instructionsArray.add(new AddPhoneInstructions(points));
                                    } catch (NumberFormatException f) {
                                        System.out.println("Invalid input. Please enter a valid number.");textPNL.setText("");
                                    }
                                }
                                break;
                            case 7:
                                if (AbsentButton.isHeld()) {
                                    System.out.println("Absent Button is held");
                                    try {
                                        int points = Integer.parseInt(textPNL.getText());  // Convert text to integer
                                        instructionsArray.add(new AddAbsentInstructions(points));
                                    } catch (NumberFormatException f) {
                                        System.out.println("Invalid input. Please enter a valid number.");textPNL.setText("");
                                    }
                                }
                                break;
                            case 8:
                                if (PassButton.isHeld()) {
                                    System.out.println("Pass Button is held");
                                    try {
                                        int points = Integer.parseInt(textPNL.getText());  // Convert text to integer
                                        instructionsArray.add(new AddPassInstructions(points));
                                    } catch (NumberFormatException f) {
                                        System.out.println("Invalid input. Please enter a valid number.");textPNL.setText("");
                                    }
                                }
                                break;
                        }
                    }
                }
            }

            for (Instructions instructions: instructionsArray){
                inManager.sendToVics(instructions);
            }

            instructionsArray.clear();

            pointText = 0;
            textPNL.setText("");
        }
    }

}
