package Main.UI.Frames;

import Main.Holder;

import javax.swing.*;

public class AddVictimFrame extends JFrame {
    public AddVictimFrame(Holder holder){
        String VictimName = JOptionPane.showInputDialog(this,
                "Add the Victims Name", null);
        if(VictimName != null) {
            holder.addVictim(VictimName);
        }
    }
}