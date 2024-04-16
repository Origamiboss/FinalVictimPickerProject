package UIElements.Panels;

import UIElements.Colors.CurrentUITheme;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private final int cornerRadius = 15;
    private final CurrentUITheme current;

    public RoundedPanel(CurrentUITheme currentUITheme) {
        this.current = currentUITheme;

        setBackground(currentUITheme.getCurrentBackgroundColor().main());
        setForeground(currentUITheme.getCurrentForegroundColor().main());

        setOpaque(false);

        Border marginBorder = BorderFactory.createEmptyBorder(100, 100, 100, 100);
        setBorder(marginBorder);

        Border roundedBorder = BorderFactory.createLineBorder(currentUITheme.getCurrentForegroundColor().main(), 3, true);
        setBorder(roundedBorder);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.dispose();
    }

    public void updateColors(CurrentUITheme currentTheme) {
        setBackground(currentTheme.getCurrentBackgroundColor().main());
        setForeground(currentTheme.getCurrentForegroundColor().main());
        // If there are other color attributes to update, do it here
        repaint(); // Refresh the component to apply new colors
    }
}