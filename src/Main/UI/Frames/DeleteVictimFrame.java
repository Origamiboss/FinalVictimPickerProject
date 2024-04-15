package src.Main.UI.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class DeleteVictimFrame extends JFrame{
    src.Main.Holder holder;
    JFrame self = this;
    public DeleteVictimFrame(src.Main.Holder h) {
        holder = h;
        //create the options panel
        JPanel optionMenu = new JPanel();
        optionMenu.setPreferredSize(new Dimension(200, holder.getVictims().size() * 31));
        for (src.Students.Victim v : holder.getVictims()) {
            String name = v.getName().getFirstName() + " " + v.getName().getLastName();
            JButton newButton = new JButton(name);
            optionMenu.add(newButton);
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
        scrollPane.setBounds(50, 30, 200, 300);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        self.setContentPane(contentPane);
        self.pack();
        self.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        self.setVisible(true);
    }
}