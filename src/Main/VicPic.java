package src.Main;

import src.UIElements.Buttons.RoundButton;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class VicPic {
    public static void main(String[] args) throws FileNotFoundException {
        src.Main.Frontend frontend = new Frontend();
        HashMap<String, JComponent> map = frontend.getHolder().getMap();
        RoundButton target = (RoundButton) map.get("Test1");
    }
}