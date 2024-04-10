package src.UIElements.Panels;

import src.UIElements.Colors.CurrentUITheme;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private Image image;
    private CurrentUITheme theme;

    public ImagePanel(Image image, CurrentUITheme theme) {
        this.image = image;
        this.theme = theme;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(theme.getCurrentBackgroundColor().main());
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}