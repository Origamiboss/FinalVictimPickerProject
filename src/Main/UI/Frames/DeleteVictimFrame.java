package Main.UI.Frames;

import Main.Holder;
import Main.UI.Format.VicFormatter;
import Students.Victim;
import UIElements.Buttons.RoundButton;
import UIElements.Panels.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class DeleteVictimFrame extends JFrame{
    Holder holder;
    JFrame self = this;
    public DeleteVictimFrame(Holder h) {
        holder = h;
        //create the options panel
        // UI adjustments that match formatting requirements for the options panel
        RoundedPanel optionMenu = new RoundedPanel(h.getTheme()); // Replace instances of J-panel with RoundedPanel
        optionMenu.setLayout(new BoxLayout(optionMenu, BoxLayout.Y_AXIS));
        optionMenu.setPreferredSize(new Dimension(300, holder.getVictims().size() * 200));

        for (Victim v : holder.getVictims()) {
            String name = v.getName().getFirstName() + " " + v.getName().getLastName();

            // UI adjustments that match formatting requirements
            RoundButton newButton = new RoundButton(name, h.getTheme()); // Replace instances of  J-button with RoundButton.
            newButton.setSize(new Dimension(300, 50));

            //VicFormatter keeps the UI elements from clumping together, which is necessary for UI adjustments.
            VicFormatter buttonForm = new VicFormatter(newButton, 5);
            optionMenu.add(buttonForm.getPanel());

            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //call delete for the button
                    holder.deleteVictim(v);

                    //Kill the frame
                    self.dispatchEvent(new WindowEvent(self, WindowEvent.WINDOW_CLOSING));
                }
            });

        }
        JScrollPane scrollPane = new JScrollPane(optionMenu);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 30, 400, 700);
        JPanel contentPane = new JPanel(null);

        // UI adjustment for panel background, this colors the entire panel.
        contentPane.setBackground(h.getTheme().getCurrentBackgroundColor().main());

        contentPane.setPreferredSize(new Dimension(500, 600));
        contentPane.add(scrollPane);
        self.setContentPane(contentPane);
        self.pack();
        self.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        self.setVisible(true);
    }
}