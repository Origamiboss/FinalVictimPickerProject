package Main.UI.Frames;

import javax.swing.*;

public class AddVictimFrame extends JFrame {
    public AddVictimFrame(src.Main.Holder holder){
        String VictimName = JOptionPane.showInputDialog(this,
                "Add the Victims Name", null);
        if(VictimName != null) {
            holder.addVictim(VictimName);
        }
    }
}

