package Main.UI.Frames;

import javax.swing.*;

public class ErrorMessageFrame extends JFrame {

    public ErrorMessageFrame(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
