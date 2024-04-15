package src.Main;

import src.Interfaces.Instructions;

import javax.swing.*;

public class OpenMenu implements Instructions {
    @Override
    public void update(Object component) {
        JPopupMenu test = new JPopupMenu("test");
    }
}
