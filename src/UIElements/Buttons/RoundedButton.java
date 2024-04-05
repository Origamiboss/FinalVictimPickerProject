package src.UIElements.Buttons;

import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.UIColors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {
    protected Color hoverBackgroundColor;
    protected Color pressedBackgroundColor;
    private final int cornerRadius = 50;
    private final int borderThickness = 2;
    private final int fontSize = 16;

    private CurrentUITheme current;
    private CurrentUITheme tempTheme;

    protected Image image;

    private boolean isTeamColorSet = false;

    public RoundedButton(String text, CurrentUITheme currentUITheme) {
        super(text);
        initButton(currentUITheme);
        tempTheme = currentUITheme;
    }

    public RoundedButton(Image image, CurrentUITheme currentUITheme) {
        super();
        this.image = image;
        initButton(currentUITheme);
        tempTheme = currentUITheme;
        //setMaximumSize(new Dimension(25,25));
    }

    public void setTeamColorSet(String teamColors){
        tempTheme.setCurrentBackgroundColor(teamColors);
        tempTheme.setCurrentForegroundColor(current.getForegroundString());
        isTeamColorSet = true;
        updateButtonColors();
    }

    protected void initButton(CurrentUITheme currentUITheme) {
        this.current = currentUITheme;
        this.setSize(new Dimension(350, 100));
        this.setPreferredSize(new Dimension(350, 100));

        // Use theme colors
        setBackground(currentUITheme.getCurrentBackgroundColor().main());
        hoverBackgroundColor = currentUITheme.getCurrentBackgroundColor().select();
        pressedBackgroundColor = currentUITheme.getCurrentBackgroundColor().action();
        setForeground(currentUITheme.getCurrentForegroundColor().main());

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        Border whiteline = BorderFactory.createLineBorder(currentUITheme.getCurrentForegroundColor().main(), 3);
        setBorder(whiteline);

        setFont(new Font(getFont().getName(), Font.BOLD, fontSize));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(currentUITheme.getCurrentBackgroundColor().select());
                //setBackground(hoverBackgroundColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateButtonColors();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int red = 0, green = 0, blue = 0;
                red = 255 - currentUITheme.getCurrentForegroundColor().main().getRed();
                green = 255 - currentUITheme.getCurrentForegroundColor().main().getGreen();
                blue = 255 - currentUITheme.getCurrentForegroundColor().main().getBlue();
                Color tempForeground = new Color(red, green, blue);
                setBackground(currentUITheme.getCurrentBackgroundColor().action());
                setBackground(pressedBackgroundColor);
                setForeground(tempForeground);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(currentUITheme.getCurrentBackgroundColor().main());
                setForeground(currentUITheme.getCurrentForegroundColor().main());
            }
        });
    }

    protected void updateButtonColors(){
        if(isTeamColorSet){
            setBackground(tempTheme.getCurrentBackgroundColor().main());
            hoverBackgroundColor = tempTheme.getCurrentBackgroundColor().select();
            pressedBackgroundColor = tempTheme.getCurrentBackgroundColor().action();
        }else{
            setBackground(current.getCurrentBackgroundColor().main());
            hoverBackgroundColor = current.getCurrentBackgroundColor().select();
            pressedBackgroundColor = current.getCurrentBackgroundColor().action();
        }
        setForeground(current.getCurrentForegroundColor().main());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw mock border
        g2.setColor(current.getCurrentForegroundColor().main());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius + 5, cornerRadius + 5);

        // Draw inner button
        int inset = borderThickness;
        if (getModel().isPressed()) {
            g2.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g2.setColor(hoverBackgroundColor);
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(inset, inset, getWidth() - inset * 2, getHeight() - inset * 2, cornerRadius, cornerRadius);

        // Draw image if available
        if (getImage() != null) {
            int x = (getWidth() - getImage().getWidth(this)) / 2;
            int y = (getHeight() - getImage().getHeight(this)) / 2;
            g2.drawImage(getImage(), x, y, this);
        if (image != null) {
            int x = (getWidth() - image.getWidth(this)) / 2;
            int y = (getHeight() - image.getHeight(this)) / 2;
            g2.drawImage(image, x, y, this);
        } else {
            // Draw text if no image
            super.paintComponent(g2);
        }

        g2.dispose();
    }

    public void setImage(Image image) {
        this.image = image;

        revalidate();
        repaint();
    }

    public Image getImage(){
        return image;
    }

        repaint();
    }

    protected int getCornerRadius() {
        return cornerRadius;
    }

    protected int getBorderThickness(){
        return borderThickness;
    }
}