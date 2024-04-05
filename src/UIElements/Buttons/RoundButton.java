package src.UIElements.Buttons;

import java.awt.*;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;


public class RoundButton extends RoundedButton {
    private int diameter;

    public RoundButton(Image image, CurrentUITheme currentUITheme) {
        super(image, currentUITheme); // Use the constructor from RoundedButton
        this.diameter = Math.max(image.getWidth(null), image.getHeight(null)) + 20;
        //setPreferredSize(new Dimension(diameter, diameter));
        setMaximumSize(new Dimension(diameter, diameter));
        setSize(diameter, diameter);
    }

    public RoundButton(String text, CurrentUITheme currentUITheme) {
        super(text, currentUITheme);
        this.diameter = 100; // Example diameter, adjust as necessary
        setPreferredSize(new Dimension(diameter, diameter));
        setSize(diameter, diameter);
    }

    // No need to override paintComponent if it doesn't differ from RoundedButton
    // If you need specific behavior, you can override and add it here

    // Overrides to ensure the button is round
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(diameter, diameter);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public void updateImage(Images newImage){
        setImage(newImage.getImage());

        this.diameter = Math.max(newImage.getImage().getWidth(null), newImage.getImage().getHeight(null)) + 20;
        //setPreferredSize(new Dimension(diameter, diameter));
        setMaximumSize(new Dimension(diameter, diameter));
        setSize(diameter, diameter);

        revalidate();
        repaint();

    }
}