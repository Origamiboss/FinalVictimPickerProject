package Main;

import Main.UI.Format.VicFormatter;
import Students.Victim;
import UIElements.Buttons.RoundButton;
import UIElements.Buttons.RoundedButton;
import UIElements.Colors.CurrentUITheme;
import UIElements.TextCanvas;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ReformattingUIElements {

    JButton buttonOne;
    JTextField panelOne;

    VicFormatter buttonForm;
    VicFormatter panelForm;

    ReformattingUIElements(CurrentUITheme theme){
        JFrame testingButtons = new JFrame("Buttons and Panels");
        testingButtons.setLocation(100, 100);

        JPanel testPanel = new JPanel();
        testPanel.setLayout(new BoxLayout(testPanel, BoxLayout.Y_AXIS));

        testPanel.setBackground(theme.getCurrentBackgroundColor().main());

        buttonOne = new RoundedButton("Test Button", theme);
        buttonForm = new VicFormatter(buttonOne, 5);
        panelOne = new TextCanvas(theme, 32, true);
        panelOne.setBorder(new LineBorder(theme.getCurrentForegroundColor().main(), 2));
        panelOne.setSize(new Dimension(200, 100));
        panelForm = new VicFormatter(panelOne, 5);


        testPanel.add(buttonForm.getPanel());
        testPanel.add(panelForm.getPanel());

        testingButtons.add(testPanel);

        testingButtons.pack();
        testingButtons.setVisible(true);

    }

}
