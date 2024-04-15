package src.UIElements.Panels;

import src.UIElements.Buttons.HeldButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class DisplayPlayer extends HeldButton {
    private String playerName;
    private Images playerImage;
    private int bufferDistance = 5;

    public DisplayPlayer(Images image, CurrentUITheme currentUITheme) {
        super(image.getImage(), currentUITheme);
        this.playerName = image.getName();
        this.playerImage = image; // Store the Images object to retrieve the image later
        setImage(image.getImage());
    }

    public void updatePlayerImage(Images newImage) {
        this.playerImage = newImage; // Update the image
        this.playerName = newImage.getName(); // Update the player name
        setImage(newImage.getImage()); // Update the button's image

        revalidate();
        repaint();
    }

    public void updatePlayerName(String newName){
        this.playerName = newName;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Draw the player's name below the image
        if (playerName != null && !playerName.isEmpty()) {
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(playerName)) / 2;
            int y = getHeight() - fm.getHeight() / 2;
            g2.drawString(playerName, x, y);
        }

        g2.dispose();
    }

    public BufferedImage getImage() {
        return playerImage != null ? playerImage.getImage() : null;
    }

    public HeldButton getDispPanel() {
        return this;
    }
}
