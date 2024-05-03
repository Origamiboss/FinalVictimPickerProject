package Main.UI.Frames;

import Main.Holder;
import Main.UI.Format.VicFormatter;
import UIElements.Buttons.RoundButton;
import UIElements.Buttons.RoundedButton;
import UIElements.Panels.RoundedPanel;
import UIElements.TextCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ErrorMessageFrame extends JFrame {

    JFrame self = this;

    public ErrorMessageFrame(String errorMessage, Holder holder) {

        RoundedPanel errorMenu = new RoundedPanel(holder.getTheme());

        setTitle("Error");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(errorMessage.length() * 11, 200));
        setMinimumSize(new Dimension(300, 200));
        setLocationRelativeTo(null);


        errorMenu.setLayout(new BoxLayout(errorMenu, BoxLayout.Y_AXIS));

        TextCanvas label = new TextCanvas(holder.getTheme(), 20, false);
        label.setText(errorMessage);
        label.setSize(new Dimension(errorMessage.length() * 11, 50));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        VicFormatter labelFormat = new VicFormatter(label, 5);
        label.setForeground(holder.getTheme().getCurrentForegroundColor().main());
        errorMenu.add(labelFormat.getPanel());

        RoundedButton okButton = new RoundedButton("Ok", holder.getTheme());

        VicFormatter canvasFormat = new VicFormatter(okButton, 5);
        canvasFormat.getPanel().setSize(30, 20);

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                self.dispose();
            }
        });

        errorMenu.add(canvasFormat.getPanel());

        errorMenu.setBackground(holder.getTheme().getCurrentBackgroundColor().main());
        setContentPane(errorMenu);
        pack();
        setVisible(true);


        //JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
