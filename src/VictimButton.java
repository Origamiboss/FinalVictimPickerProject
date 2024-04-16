import javax.swing.*;

import static java.lang.Integer.parseInt;

public class VictimButton extends JButton {
    public VictimButton(String buttonName) {
        super(buttonName);
        setVisible(true);
    }

    public int numOfVictim(String numberOfVics)
    {
        return parseInt(numberOfVics);
    }

}
