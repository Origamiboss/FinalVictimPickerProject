package src.UIElements.Panels;

import src.Main.UI.Format.VicFormatter;
import src.Students.Victim;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Buttons.RoundedButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;

import javax.swing.*;

public class PlayerPanel extends JPanel {
    private Images image;
    private String name;
    private final String type = "CustomImage";
    private CurrentUITheme theme;
    private DisplayPlayer player;
    private Images imageGetter;
    private RoundButton randomize;
    private RoundButton remove;
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
        tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));

        imageGetter = new Images("null", theme, type);
        player = new DisplayPlayer(imageGetter, theme);
        playerFormat = new VicFormatter(player, buffDistance);
        tempPanel.add(playerFormat.getPanel());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        imageGetter = new Images("dice", theme, "UIimage");
        randomize = new RoundButton(imageGetter.getImage(), theme);
        randomFormat = new VicFormatter(randomize, buffDistance);
        buttonPanel.add(randomFormat.getPanel());

        imageGetter = new Images("X", theme, "UIimage");
        remove = new RoundButton(imageGetter.getImage(), theme);
        removeFormat = new VicFormatter(remove, buffDistance);
        buttonPanel.add(removeFormat.getPanel());
        buttonPanel.setOpaque(false);

        tempPanel.add(buttonPanel);
        tempPanel.setOpaque(false);
        this.add(tempPanel);

    }

    public RoundButton getRandomButton(){
        return randomize;
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
}
