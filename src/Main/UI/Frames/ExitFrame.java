package Main.UI.Frames;

import javax.swing.*;

public class ExitFrame extends JFrame {
    public ExitFrame(src.Main.Holder holder){
        int response = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION)
            holder.exit();
        else {
            this.remove(this);
        }
    }
}
