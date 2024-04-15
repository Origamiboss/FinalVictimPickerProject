package src.Main.UI.Panels;

import src.Main.UI.Format.VicFormatter;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Buttons.RoundedButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;
import src.UIElements.Panels.ImagePanel;
import src.UIElements.Panels.RoundedPanel;
import src.UIElements.TextCanvas;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.HashMap;

public class SearchPanel extends JPanel{
    JPanel searchPanel;
    CurrentUITheme theme;
    Images logoGetter;
    Image logo;
    VicFormatter searchButton;
    VicFormatter textPanel;
    private VicFormatter topPanel;
    private TextCanvas textPNL;
    private RoundedPanel round;
    HashMap<String, JComponent> map;

    public SearchPanel(CurrentUITheme theme){
        map = new HashMap<>();
        this.theme = theme;
        int buffDistance = 5;
        searchPanel = new JPanel();
        searchPanel.setBackground(this.theme.getCurrentBackgroundColor().main());
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

        logoGetter = new Images("logo", this.theme, "Logo");
        logo = logoGetter.getImage();
        ImagePanel imgPanel = new ImagePanel(logo, theme);
        imgPanel.setPreferredSize(new Dimension(logo.getWidth(null), logo.getHeight(null)));
        logoGetter = new Images("magnifyGlass", theme, "UIimage");
        RoundedButton magButton = new RoundButton(logoGetter.getImage(), theme);
        searchButton = new VicFormatter(magButton, buffDistance);
        searchButton.getPanel().setBackground(theme.getCurrentBackgroundColor().main());
        map.put("topSearchButton", magButton);

        //textPanel = new VicTextPanel(theme, 30, true, 5, 200, 120);
        textPNL = new TextCanvas(theme, 22, true);
        //VicFormatter textFormat = new VicFormatter(textPNL, 5);
        //textPNL.setPreferredSize(new Dimension(1, 30));
        round = new RoundedPanel(theme);
        round.add(textPNL);
        round.setBackground(theme.getCurrentBackgroundColor().main());
        textPNL.setBorder(new BevelBorder(3,theme.getCurrentForegroundColor().main(), theme.getCurrentForegroundColor().main()));
        //round.setMaximumSize(new Dimension(7000, 1000));
        SwingUtilities.invokeLater(() -> textPNL.setCaretPosition(0));
        map.put("topSearchPanel", textPNL);

        textPNL.setColumnWidths(10);

        searchPanel.add(imgPanel); // Adding the image panel to the search panel
        searchPanel.add(Box.createRigidArea(new Dimension(100, 1)));
        searchPanel.add(searchButton.getPanel());
        searchPanel.add(round);
        //searchPanel.add(Box.createRigidArea(new Dimension(100, 1)));

        round.setSize(new Dimension(500, 50));
        round.setMaximumSize(new Dimension(500, 50));

        topPanel = new VicFormatter(searchPanel, buffDistance);
        topPanel.getPanel().setBackground(theme.getCurrentBackgroundColor().main());

    }

    public JPanel getFormat(){
        return topPanel.getPanel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Call superclass method first to paint the background
        textPNL.setColumnWidths(round.getWidth());
        System.out.println(round.getWidth());
        this.repaint();

        topPanel.getPanel().repaint();
    }

    public HashMap<String, JComponent> getMap(){
        return map;
    }

}
