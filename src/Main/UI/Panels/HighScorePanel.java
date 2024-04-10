package src.Main.UI.Panels;

import src.Main.UI.Format.VicFormatter;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.RoundedPanel;
import src.UIElements.TextCanvas;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HighScorePanel {
    private HashMap<String, JComponent> map;
    private CurrentUITheme theme;

    private VicFormatter scoreFormat;

    private RoundedPanel mainPanel;

    private JPanel ScoreContainer;

    private VicFormatter No1Form;
    private VicFormatter No2Form;
    private VicFormatter No3Form;
    private VicFormatter No4Form;
    private VicFormatter No5Form;

    private TextCanvas No1Lbl;
    private TextCanvas No2Lbl;
    private TextCanvas No3Lbl;
    private TextCanvas No4Lbl;
    private TextCanvas No5Lbl;

    private TextCanvas No1;
    private TextCanvas No2;
    private TextCanvas No3;
    private TextCanvas No4;
    private TextCanvas No5;

    private TextCanvas Highscores;

    public HighScorePanel(CurrentUITheme theme){
        map = new HashMap<>();

        int buffDistance = 5;
        int fontSize = 15;
        this.theme = theme;

        mainPanel = new RoundedPanel(this.theme);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(theme.getCurrentBackgroundColor().main());
        map.put("hsMainPanel", mainPanel);

        Highscores = new TextCanvas(theme, fontSize, false);
        Highscores.setText("      ~ HIGHSCORES ~");
        map.put("hsLabelMain", Highscores);

        ScoreContainer = new JPanel();
        ScoreContainer.setBackground(theme.getCurrentBackgroundColor().main());
        ScoreContainer.setLayout(new BoxLayout(ScoreContainer, BoxLayout.Y_AXIS));


        No1Lbl = new TextCanvas(theme, fontSize, false);
        No1Lbl.setText("#1:");
        No1Lbl.setHighlighter(null);
        No1Lbl.setEditable(false);
        No1Lbl.setFocusable(false);
        No2Lbl = new TextCanvas(theme, fontSize, false);
        No2Lbl.setText("#2:");
        No3Lbl = new TextCanvas(theme, fontSize, false);
        No3Lbl.setText("#3:");
        No4Lbl = new TextCanvas(theme, fontSize, false);
        No4Lbl.setText("#4:");
        No5Lbl = new TextCanvas(theme, fontSize, false);
        No5Lbl.setText("#5:");

        No1 = new TextCanvas(theme, fontSize, false);
        No2 = new TextCanvas(theme, fontSize, false);
        No3 = new TextCanvas(theme, fontSize, false);
        No4 = new TextCanvas(theme, fontSize, false);
        No5 = new TextCanvas(theme, fontSize, false);

        RoundedPanel panel1 = new RoundedPanel(theme);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        No1Lbl.setSize(new Dimension(30, 30));
        VicFormatter panel1Form = new VicFormatter(No1Lbl, 5);
        panel1Form.getPanel().setBackground(theme.getCurrentBackgroundColor().main());
        panel1.add(panel1Form.getPanel());
        panel1.add(No1);
        No1.setText("Test1");
        panel1.setSize(new Dimension(700, 10));
        No1Form = new VicFormatter(panel1, 2);
        map.put("hsTextPane1", No1);

        RoundedPanel panel2 = new RoundedPanel(theme);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        No2Lbl.setSize(new Dimension(30, 30));
        VicFormatter panel2Form = new VicFormatter(No2Lbl, 5);
        panel2.add(panel2Form.getPanel());
        panel2.add(No2);
        No2.setText("Test2");
        panel2.setSize(new Dimension(700, 10));
        No2Form = new VicFormatter(panel2, 2);
        map.put("hsTextPane2", No2);

        RoundedPanel panel3 = new RoundedPanel(theme);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        No3Lbl.setSize(new Dimension(30, 30));
        VicFormatter panel3Form = new VicFormatter(No3Lbl, 5);
        panel3.add(panel3Form.getPanel());
        panel3.add(No3);
        No3.setText("Test3");
        panel3.setSize(new Dimension(700, 10));
        No3Form = new VicFormatter(panel3, 2);
        map.put("hsTextPane3", No3);

        RoundedPanel panel4 = new RoundedPanel(theme);
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
        No4Lbl.setSize(new Dimension(30, 30));
        VicFormatter panel4Form = new VicFormatter(No4Lbl, 5);
        panel4.add(panel4Form.getPanel());
        panel4.add(No4);
        No4.setText("Test4");
        panel4.setSize(new Dimension(700, 10));
        No4Form = new VicFormatter(panel4, 2);
        map.put("hsTextPane4", No4);

        RoundedPanel panel5 = new RoundedPanel(theme);
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
        No5Lbl.setSize(new Dimension(30, 30));
        VicFormatter panel5Form = new VicFormatter(No5Lbl, 5);
        panel5.add(panel5Form.getPanel());
        panel5.add(No5);
        No5.setText("Test5");
        panel5.setSize(new Dimension(700, 10));
        No5Form = new VicFormatter(panel5, 2);
        map.put("hsTextPane5", No5);

        ScoreContainer.add(No1Form.getPanel());
        No1Form.getPanel().setPreferredSize(new Dimension(700, 170));
        No1Form.getPanel().setBackground(theme.getCurrentBackgroundColor().main());
        ScoreContainer.add(No2Form.getPanel());
        No2Form.getPanel().setPreferredSize(new Dimension(700, 170));
        No2Form.getPanel().setBackground(theme.getCurrentBackgroundColor().main());
        ScoreContainer.add(No3Form.getPanel());
        No3Form.getPanel().setPreferredSize(new Dimension(700, 170));
        No3Form.getPanel().setBackground(theme.getCurrentBackgroundColor().main());
        ScoreContainer.add(No4Form.getPanel());
        No4Form.getPanel().setPreferredSize(new Dimension(700, 170));
        No4Form.getPanel().setBackground(theme.getCurrentBackgroundColor().main());
        ScoreContainer.add(No5Form.getPanel());
        No5Form.getPanel().setPreferredSize(new Dimension(700, 170));
        No5Form.getPanel().setBackground(theme.getCurrentBackgroundColor().main());

        ScoreContainer.setBackground(theme.getCurrentBackgroundColor().main());

        VicFormatter contScoreFormat = new VicFormatter(ScoreContainer, buffDistance);
        contScoreFormat.getPanel().setBackground(theme.getCurrentBackgroundColor().main());
        contScoreFormat.getPanel().setSize(new Dimension(800, 600));
        contScoreFormat.getPanel().setBackground(theme.getCurrentBackgroundColor().main());
        VicFormatter highScoreFormat = new VicFormatter(Highscores, buffDistance);


        mainPanel.setPreferredSize(new Dimension(800, 100));
        mainPanel.add(contScoreFormat.getPanel(), BorderLayout.CENTER);
        mainPanel.add(highScoreFormat.getPanel(), BorderLayout.NORTH);
        mainPanel.setBackground(theme.getCurrentBackgroundColor().main());

        scoreFormat = new VicFormatter(mainPanel, buffDistance);
        scoreFormat.getPanel().setPreferredSize(new Dimension(800, 100));
        //scoreFormat.getPanel().setBackground(theme.getCurrentBackgroundColor().main());
        scoreFormat.getPanel().setOpaque(false);

    }

    public JPanel getFormat() { return scoreFormat.getPanel(); }

    public Map<String, JComponent> getMap(){
        return map;
    }

}
