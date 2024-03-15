import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        VictimPicker v = new VictimPicker();
        VictimGUI GUI = new VictimGUI(v);

    }
}