package src.UIElements.Panels;

import src.Main.UI.Format.VicFormatter;
import src.UIElements.Buttons.HeldButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.TextCanvas;

import javax.swing.*;
import java.awt.*;

public class QuestionAndButton extends JPanel {
    private int fontSize = 22;
    private boolean notEdit = false;

    // This will be displayed in the optionCanvas
    // This will be one of the question options
    private String optionText;

    // A held button for the user to select the option displayed
    private HeldButton optionButton;

    // Canvas to display option
    private TextCanvas optionCanvas;

    VicFormatter canvasFormat;
    VicFormatter buttonFormat;

    public QuestionAndButton(String inOption, CurrentUITheme theme){
        CurrentUITheme invert = new CurrentUITheme(theme.getForegroundString(), theme.getBackgroundString());

        optionText = new String(inOption);
        optionCanvas = new TextCanvas(theme, fontSize, notEdit);
        optionCanvas.setForeground(theme.getCurrentForegroundColor().main());
        optionButton = new HeldButton("", theme);
        optionButton.setPreferredSize(new Dimension(50, 50));

        VicFormatter textFormat = new VicFormatter(optionCanvas, 5);
        textFormat.getPanel().setBackground(theme.getCurrentBackgroundColor().main());
        VicFormatter buttonFormat = new VicFormatter(optionButton, 5);
        buttonFormat.getPanel().setBackground(theme.getCurrentBackgroundColor().main());

        //optionCanvas.setText("Test");
        //optionCanvas.setText(inOption);

        this.setLayout(new BorderLayout());
        this.setBackground(theme.getCurrentBackgroundColor().main());

        this.add(textFormat.getPanel(), BorderLayout.NORTH);
        this.add(buttonFormat.getPanel(), BorderLayout.CENTER);

        /*
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(theme.getCurrentBackgroundColor().main());

        this.add(textFormat.getPanel());
        this.add(buttonFormat.getPanel());
         */

        VicFormatter thisHolder = new VicFormatter(this, 5);
        thisHolder.getPanel().setBackground(theme.getCurrentBackgroundColor().main());

        this.setPreferredSize(new Dimension(150, 150));

    }

    public boolean getButtonHeld(){
        return optionButton.isHeld();
    }

    public String getOptionText(){
        return optionText;
    }

    // This will be used after the check button is pressed
    // If this option is not the correct one, change to red, if it is change to green
    // Returns a boolean to determine if correct
    public boolean isCorrect(String inAnswer){
        boolean correct;
        if (optionText == inAnswer){
            optionCanvas.setBackground(Color.GREEN);
            optionCanvas.updateColors(Color.GREEN);
            correct = true;
        }
        else{
            optionCanvas.setBackground(Color.RED);
            optionCanvas.updateColors(Color.RED);
            correct = false;
        }
        optionCanvas.repaint();

        return correct;

    }

    public void setText(String option){
        optionText = option;
        optionCanvas.setHorizontalAlignment(JTextField.CENTER);
        optionCanvas.setText(optionText);
    }

    public String getText(){
        return optionText;
    }

    public HeldButton getButton(){
        return optionButton;
    }

    public TextCanvas getCanvas(){
        return optionCanvas;
    }

}
