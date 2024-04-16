package UIElements.Panels;

import Main.UI.Format.VicFormatter;
import Students.Victim;
import UIElements.Buttons.RoundButton;
import UIElements.Buttons.RoundedButton;
import UIElements.Colors.CurrentUITheme;
import UIElements.Colors.ImageResizer;
import UIElements.Colors.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerPanel extends JPanel {
    private Images image;
    private String name;
    private final String type = "CustomImage";
    private CurrentUITheme theme;
    private DisplayPlayer player;
    private Images imageGetter;
    private RoundButton randomize;
    private RoundButton remove;
    private RoundButton testSize;
    private VicFormatter playerFormat;
    private VicFormatter randomFormat;
    private VicFormatter removeFormat;
    private int buffDistance = 5;
    private int elementNo;
    private Victim victim;

    public PlayerPanel(CurrentUITheme theme){
        //this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.theme = theme;
        this.setOpaque(false);

        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new BorderLayout());
        //tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));

        imageGetter = new Images("null", theme, type);
        player = new DisplayPlayer(imageGetter, theme);
        playerFormat = new VicFormatter(player, buffDistance);
        tempPanel.add(playerFormat.getPanel(), BorderLayout.CENTER);
        //tempPanel.add(playerFormat.getPanel());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        imageGetter = new Images("dice", theme, "UIimage");
        randomize = new RoundButton(imageGetter.getImage(), theme);
        randomFormat = new VicFormatter(randomize, buffDistance);
        buttonPanel.add(randomFormat.getPanel(), BorderLayout.WEST);
        //buttonPanel.add(randomFormat.getPanel());

        imageGetter = new Images("X", theme, "UIimage");
        remove = new RoundButton(imageGetter.getImage(), theme);
        removeFormat = new VicFormatter(remove, buffDistance);
        buttonPanel.add(removeFormat.getPanel(), BorderLayout.EAST);
        //buttonPanel.add(removeFormat.getPanel());
        buttonPanel.setOpaque(false);

        testSize = new RoundButton("test", theme);
        //buttonPanel.add(testSize, BorderLayout.CENTER);

        tempPanel.add(buttonPanel, BorderLayout.SOUTH);
        //tempPanel.add(buttonPanel);
        tempPanel.setOpaque(false);
        this.add(tempPanel);

    }

    public RoundButton getRandomButton(){
        return randomize;
    }

    public RoundButton getTestButton(){
        return testSize;
    }

    public RoundButton getRemoveButton(){
        return remove;
    }

    public DisplayPlayer getPlayerDisplay(){
        return this.player;
    }


    public void setImage(String name){
        this.name = name.toLowerCase();

        imageGetter = new Images(name, this.theme, type);
        player.updatePlayerImage(imageGetter);
    }

    public void changeDisplayName(String newName){
        this.player.updatePlayerName(newName);
    }

    public void setElementNo(int elementNo){
        this.elementNo = elementNo;
    }

    public int getElementNo(){
        return elementNo;
    }

    public void setPlayer(Victim inVic){
        setVictim(inVic);

        imageGetter = new Images(victim.getName().getFirstName(), theme, "CustomImage");

        player.updatePlayerImage(imageGetter);
        player.updatePlayerName(victim.getName().getFirstName());

    }

    public void setVictim(Victim inVic){
        victim = inVic;
    }

    public Victim getVictim(){
        return victim;
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    public void resizeComponents(int newPlayerWidth, int newPlayerHeight, int newButtonWidth, int newButtonHeight) {
        // Resize player image
        BufferedImage resizedPlayerImage = ImageResizer.resize(player.getImage(), newPlayerWidth, newPlayerHeight);
        player.updatePlayerImage(new Images(resizedPlayerImage));  // Assuming Images can take a BufferedImage directly

        // Resize randomize button image
        BufferedImage resizedRandomizeImage = ImageResizer.resize((BufferedImage) randomize.getImage(), newButtonWidth, newButtonHeight);
        randomize.updateImage(new Images(resizedRandomizeImage));  // Update the image of the button

        // Resize remove button image
        BufferedImage resizedRemoveImage = ImageResizer.resize((BufferedImage) remove.getImage(), newButtonWidth, newButtonHeight);
        remove.updateImage(new Images(resizedRemoveImage));  // Update the image of the button

        // Revalidate and repaint to apply changes
        revalidate();
        repaint();
    }

}
