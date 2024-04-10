package src.UIElements;

// Currently under construction

import src.UIElements.Colors.CurrentUITheme;

import javax.swing.*;
import java.awt.*;

public class TextCanvas extends JTextField {
    private final CurrentUITheme current;
    private final Font font;

    public TextCanvas(CurrentUITheme current, int fontSize, boolean editable){
        this.current = current;
        font = new Font("SansSerif", Font.BOLD, fontSize);
        this.setBackground(this.current.getCurrentBackgroundColor().main());
        this.setForeground(this.current.getCurrentForegroundColor().main());
        this.setFont(font);
        this.setEditable(editable);
        this.setBorder(null);
    }

    public void updateColors(CurrentUITheme currentTheme) {
        setBackground(currentTheme.getCurrentBackgroundColor().main());
        setForeground(currentTheme.getCurrentForegroundColor().main());
        // If there are other color attributes to update, do it here
        repaint(); // Refresh the component to apply new colors
    }

    public void updateColors(Color thisColor){
        setBackground(thisColor);
    }

    public void setColumnWidths(int columnWidth){
        this.setColumns(columnWidth);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Custom painting code here
    }

}
