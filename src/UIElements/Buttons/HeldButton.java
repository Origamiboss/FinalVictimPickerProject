package src.UIElements.Buttons;

import src.UIElements.Colors.CurrentUITheme;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeldButton extends RoundButton {
    private boolean isHeld = false;
    private CurrentUITheme theme;


    public HeldButton(Image image, CurrentUITheme currentUITheme) {
        super(image, currentUITheme);
        this.theme = currentUITheme;
        initializeButton();
    }

    public HeldButton(String text, CurrentUITheme currentUITheme) {
        super(text, currentUITheme);
        this.theme = currentUITheme;
        initializeButton();
    }

    private void initializeButton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isHeld = !isHeld; // Toggle the held state
                if (isHeld) {
                    setBackground(pressedBackgroundColor);
                    repaint();
                } else {
                    setBackground(theme.getCurrentBackgroundColor().main());
                    repaint();
                }
                // You might want to set a different foreground or border here as well
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // You may not need to do anything on mouse release if the button's state is toggled on press
            }
        });
    }

    private void toggleHeldState() {
        setHeld(!isHeld);
    }

    public boolean isHeld() {
        return isHeld;
    }

    public void setHeld(boolean held) {
        isHeld = held;
        if (isHeld) {
            setBackground(pressedBackgroundColor); // Make sure pressedBackgroundColor is defined or use an existing color
        } else {
            setBackground(theme.getCurrentBackgroundColor().main());
        }
        repaint();
    }

    // Optional: Override paintComponent if you want the HeldButton to have a different appearance
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cornerRadius = getCornerRadius();
        int borderThickness = getBorderThickness();  // Assuming you have a similar getter for borderThickness in RoundedButton

        // Draw the button border
        g2.setColor(theme.getCurrentForegroundColor().main()); // or any other color you want for the border
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius + borderThickness, cornerRadius + borderThickness);

        // Draw the button interior
        if (isHeld) {
            g2.setColor(pressedBackgroundColor);
        } else {
            if (getModel().isRollover()) {
                g2.setColor(hoverBackgroundColor);
            } else {
                g2.setColor(getBackground());
            }
        }

        int inset = borderThickness;
        g2.fillRoundRect(inset, inset, getWidth() - inset * 2, getHeight() - inset * 2, cornerRadius, cornerRadius);

        // Draw the image or text as needed
        if (image != null) {
            int x = (getWidth() - image.getWidth(null)) / 2;
            int y = (getHeight() - image.getHeight(null)) / 2;
            g2.drawImage(image, x, y, this);
        }

        g2.dispose();
    }

}
