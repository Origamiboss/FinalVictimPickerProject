package src.Main.UI.Format;

import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.BufferedPanel;

import javax.swing.*;

public class VicFormatter<T extends JComponent> {
    private T component;
    private BufferedPanel magPanel;

    public VicFormatter(T component, int buffDistance){
        this.component = component;
        magPanel = new BufferedPanel<>(component, buffDistance);
    }

    public BufferedPanel getPanel(){
        return magPanel;
    }

    public T getComponent() {
        return component;
    }
}
