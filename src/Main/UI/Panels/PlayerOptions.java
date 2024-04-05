package src.Main.UI.Panels;

import src.Main.UI.Format.VicFormatter;
import src.UIElements.Buttons.HeldButton;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;
import src.UIElements.Panels.RoundedPanel;
import src.UIElements.TextCanvas;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerOptions {
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

    private HashMap<String, JComponent> map;

    public PlayerOptions(CurrentUITheme theme){
        map = new HashMap<>();

        playerOptions = new RoundedPanel(theme);
        map.put("poPlayerOptionPanel", playerOptions);

        playerOptions.setLayout(new BoxLayout(playerOptions, BoxLayout.X_AXIS));
        int buffDistance = 5;
        Images imageGetter;

        Image image = null;

        imageGetter = new Images("plus", theme, "UIimage");
        RoundButton addPoints = new RoundButton(imageGetter.getImage(), theme);
        map.put("poButton1", addPoints);
        plusButton = new VicFormatter(addPoints, buffDistance);

        imageGetter = new Images("dash", theme, "UIimage");
        RoundButton removePoints = new RoundButton(imageGetter.getImage(), theme);
        map.put("poButton2", removePoints);
        minusButton = new VicFormatter(removePoints, buffDistance);

        imageGetter = new Images("5", theme, "UIimage");
        HeldButton fiveMultButton = new HeldButton(imageGetter.getImage(), theme);
        map.put("poButton3", fiveMultButton);
        fiveMultiplyButton = new VicFormatter(fiveMultButton, buffDistance);

        imageGetter = new Images("robber", theme, "UIimage");
        HeldButton JailButton = new HeldButton(imageGetter.getImage(), theme);
        map.put("poButton4", removePoints);
        jailButton = new VicFormatter(JailButton, buffDistance);

        imageGetter = new Images("scales", theme, "UIimage");
        RoundButton scalesButton = new RoundButton(imageGetter.getImage(), theme);
        map.put("poButton5", removePoints);
        fiftyFifButton = new VicFormatter(scalesButton, buffDistance);

        imageGetter = new Images("phone", theme, "UIimage");
        HeldButton phoneButton = new HeldButton(imageGetter.getImage(), theme);
        map.put("poButton6", phoneButton);
        phoneFriendButton = new VicFormatter(phoneButton, buffDistance);

        imageGetter = new Images("noSign", theme, "UIimage");
        HeldButton AbsentButton = new HeldButton(imageGetter.getImage(), theme);
        map.put("poButton7", AbsentButton);
        absentButton = new VicFormatter(AbsentButton, buffDistance);

        imageGetter = new Images("pass", theme, "UIimage");
        HeldButton PassButton = new HeldButton(imageGetter.getImage(), theme);
        map.put("poButton8", PassButton);
        passButton = new VicFormatter(PassButton, buffDistance);

        TextCanvas textPNL = new TextCanvas(theme, 19, true);
        VicFormatter textFormat = new VicFormatter(textPNL, buffDistance);
        textPNL.setBackground(null);
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
        RoundButton SendButton = new RoundButton(imageGetter.getImage(), theme);
        map.put("poButton9", PassButton);
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

    }

    public JPanel getFormat() { return topPanel.getPanel(); }

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

}
