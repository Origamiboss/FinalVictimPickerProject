package src.Main.UI.Panels;

import src.Main.UI.Format.VicFormatter;
import src.Students.Victim;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.BufferedPanel;
import src.UIElements.Panels.RoundedPanel;
import src.UIElements.TextCanvas;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;

public class CurrentUserStats extends JPanel{
    private HashMap<String, JComponent> map;
    private CurrentUITheme theme;
    private CurrentUITheme inverse;

    private Victim student;

    private VicFormatter displayFormat;
    private VicFormatter mainPanelFormat;

    private RoundedPanel mainPanel;

    private JPanel contentFrame;

    private String firstName = "";
    private String lastName = "";
    private String nickName = "";
    private String points = "";
    private String absents = "";
    private String answered = "";
    private String timesPicked = "";
    private String passed = "";
    private String influence = "";

    private TextCanvas fullNameLbl;
    private TextCanvas nickNameLbl;
    private TextCanvas pointsLbl;
    private TextCanvas absentLbl;
    private TextCanvas answeredLbl;
    private TextCanvas timesPickLbl;
    private TextCanvas passedLbl;
    private TextCanvas inflLbl;

    private TextCanvas fullName;
    private TextCanvas nickNameMain;
    private TextCanvas pointsMain;
    private TextCanvas absentMain;
    private TextCanvas answeredMain;
    private TextCanvas timesPickedMain;
    private TextCanvas passedMain;
    private TextCanvas influenceMain;

    public CurrentUserStats(CurrentUITheme inTheme){

        map = new HashMap<>();

        int buffDistance = 3;
        int fontSize = 18;
        boolean unEditable = false;

        theme = inTheme;
        inverse = new CurrentUITheme(theme.getForegroundString(), theme.getBackgroundString());

        mainPanel = new RoundedPanel(theme);
        mainPanel.setPreferredSize(new Dimension(720, 125));
        //mainPanel.setPreferredSize(new Dimension(900, 75));
        mainPanelFormat = new VicFormatter<>(mainPanel, buffDistance);

        contentFrame = new JPanel();
        contentFrame.setBackground(inverse.getCurrentBackgroundColor().main());
        contentFrame.setLayout(new BoxLayout(contentFrame, BoxLayout.Y_AXIS));
        VicFormatter contentFrameFormat = new VicFormatter(contentFrame, buffDistance);

        JPanel fullNickPanel = new JPanel();
        fullNickPanel.setLayout(new BoxLayout(fullNickPanel, BoxLayout.X_AXIS));

        JPanel fullNamePnl = new JPanel();
        fullNamePnl.setLayout(new BoxLayout(fullNamePnl, BoxLayout.X_AXIS));
        fullNamePnl.setBackground(null);
        fullNameLbl = new TextCanvas(inverse, fontSize, unEditable);
        fullNameLbl.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        fullNameLbl.setText("Full Name: ");
        fullNameLbl.setPreferredSize(new Dimension(2, 1));
        fullName = new TextCanvas(inverse, fontSize, unEditable); // Where first and last name displayed
        fullName.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        fullName.setText("Test");
        fullName.setSize(new Dimension(90, 1));
        map.put("stat1", fullName);
        //fullName.setText(firstName + " " + lastName);
        fullNamePnl.add(fullNameLbl);
        fullNamePnl.add(fullName);

        JPanel nickNamePnl = new JPanel();
        nickNamePnl.setLayout(new BoxLayout(nickNamePnl, BoxLayout.X_AXIS));
        nickNamePnl.setBackground(null);
        nickNameLbl = new TextCanvas(inverse, fontSize, unEditable);
        nickNameLbl.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        nickNameLbl.setText("Nickname: ");
        nickNameLbl.setPreferredSize(new Dimension(50, 1));
        nickNameMain = new TextCanvas(inverse, fontSize, unEditable);
        nickNameMain.setPreferredSize(new Dimension(150, 1));
        nickNameMain.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        nickNameMain.setText("Test");
        map.put("stat2", nickNameMain);
        nickNamePnl.add(nickNameLbl);
        nickNamePnl.add(nickNameMain);

        fullNickPanel.add(fullNamePnl);
        fullNickPanel.add(nickNamePnl);


        JPanel pointsAndAbsents = new JPanel();
        pointsAndAbsents.setLayout(new BoxLayout(pointsAndAbsents, BoxLayout.X_AXIS));

        JPanel pointsPnl = new JPanel();
        pointsPnl.setLayout(new BoxLayout(pointsPnl, BoxLayout.X_AXIS));
        pointsPnl.setBackground(null);
        pointsLbl = new TextCanvas(inverse, fontSize, unEditable);
        pointsLbl.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        pointsLbl.setOpaque(true);
        pointsLbl.setText("Points: ");
        pointsLbl.setPreferredSize(new Dimension(1, 1));
        pointsMain = new TextCanvas(inverse, fontSize, unEditable); // Where first and last name displayed
        pointsMain.setPreferredSize(new Dimension(30, 1));
        pointsMain.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        pointsMain.setText("Test");
        map.put("stat3", pointsMain);
        //pointsMain.setText(points);
        pointsPnl.add(pointsLbl);
        pointsPnl.add(pointsMain);

        JPanel absentPnl = new JPanel();
        absentPnl.setLayout(new BoxLayout(absentPnl, BoxLayout.X_AXIS));
        absentPnl.setBackground(null);
        absentLbl = new TextCanvas(inverse, fontSize, unEditable);
        absentLbl.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        absentPnl.setOpaque(true);
        absentLbl.setText("Absents: ");
        absentMain = new TextCanvas(inverse, fontSize, unEditable);
        absentMain.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        absentMain.setSize(new Dimension(200, 10));
        absentMain.setText(absents);
        map.put("stat4", absentMain);
        absentPnl.add(absentLbl);
        absentPnl.add(absentMain);

        JPanel ansAndTimePickedPanel = new JPanel();
        ansAndTimePickedPanel.setLayout(new BoxLayout(ansAndTimePickedPanel, BoxLayout.X_AXIS));

        JPanel answeredPanel = new JPanel();
        answeredPanel.setLayout(new BoxLayout(answeredPanel, BoxLayout.X_AXIS));
        answeredPanel.setBackground(null);
        answeredLbl = new TextCanvas(inverse, fontSize, unEditable);
        answeredLbl.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        answeredLbl.setText("Answered: ");
        answeredMain = new TextCanvas(inverse, fontSize, unEditable); // Where first and last name displayed
        answeredMain.setPreferredSize(new Dimension(170, 1));
        answeredMain.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        answeredMain.setText(answered);
        map.put("stat5", answeredMain);
        answeredPanel.add(answeredLbl);
        answeredPanel.add(answeredMain);

        JPanel timesPickedPanel = new JPanel();
        timesPickedPanel.setLayout(new BoxLayout(timesPickedPanel, BoxLayout.X_AXIS));
        timesPickedPanel.setBackground(null);
        timesPickLbl = new TextCanvas(inverse, fontSize, unEditable);
        timesPickLbl.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        timesPickLbl.setText("Times Picked: ");
        timesPickedMain = new TextCanvas(inverse, fontSize, unEditable);
        timesPickedMain.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        timesPickedMain.setPreferredSize(new Dimension(200, 1));
        timesPickedMain.setText(timesPicked);
        map.put("stat6", timesPickedMain);
        timesPickedPanel.add(timesPickLbl);
        timesPickedPanel.add(timesPickedMain);

        ansAndTimePickedPanel.add(answeredPanel);
        ansAndTimePickedPanel.add(timesPickedPanel);

        pointsAndAbsents.add(pointsPnl);
        pointsAndAbsents.add(absentPnl);

        JPanel passAndInfluencePanel = new JPanel();
        passAndInfluencePanel.setLayout(new BoxLayout(passAndInfluencePanel, BoxLayout.X_AXIS));

        JPanel passedPanel = new JPanel();
        passedPanel.setLayout(new BoxLayout(passedPanel, BoxLayout.X_AXIS));
        passedPanel.setBackground(null);
        passedLbl = new TextCanvas(inverse, fontSize, unEditable);
        passedLbl.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        passedLbl.setText("Passed: ");
        passedMain = new TextCanvas(inverse, fontSize, unEditable); // Where first and last name displayed
        passedMain.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        passedMain.setPreferredSize(new Dimension(170, 1));
        passedMain.setText(passed);
        map.put("stat7", passedMain);
        passedPanel.add(passedLbl);
        passedPanel.add(passedMain);

        JPanel influencePanel = new JPanel();
        influencePanel.setLayout(new BoxLayout(influencePanel, BoxLayout.X_AXIS));
        influencePanel.setBackground(null);
        inflLbl = new TextCanvas(inverse, fontSize, unEditable);
        inflLbl.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        inflLbl.setText("Influence: ");
        influenceMain = new TextCanvas(inverse, fontSize, unEditable);
        influenceMain.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        influenceMain.setPreferredSize(new Dimension(200, 1));
        influenceMain.setText(influence);
        map.put("stat8", influenceMain);
        influencePanel.add(inflLbl);
        influencePanel.add(influenceMain);

        passAndInfluencePanel.add(passedPanel);
        passAndInfluencePanel.add(influencePanel);

        contentFrame.add(fullNickPanel);
        contentFrame.add(pointsAndAbsents);
        contentFrame.add(ansAndTimePickedPanel);
        contentFrame.add(passAndInfluencePanel);

        mainPanel.add(contentFrame);
        mainPanel.setBackground(null);

        map.put("statMain", this);

    }

    public void setStudent(Victim inStudent){
        student = new Victim(inStudent);
    }

    public Victim getStudent(){
        return student;
    }

    public void updateStatText(Victim inStudent){
        setStudent(inStudent);

        firstName = student.getName().getFirstName();
        lastName = student.getName().getLastName();
        points = Integer.toString(student.getPoints());
        absents = Integer.toString(student.getAbsences());
        answered = Integer.toString(student.getAnswered());
        timesPicked = Integer.toString(student.getNumPicked());
        passed = Integer.toString(student.getPassed());
        influence = Double.toString(student.getInfluenceScore());

        writeStats();

    }

    public void writeStats(){
        fullName.setText(firstName + " " + lastName);
        pointsMain.setText(points);
        absentMain.setText(absents);
        answeredMain.setText(answered);
        timesPickedMain.setText(timesPicked);
        passedMain.setText(passed);
        influenceMain.setText(influence);
    }

    public BufferedPanel getMainPanel(){
        return mainPanelFormat.getPanel();
    }

    public HashMap<String, JComponent> getMap(){
        return map;
    }

}
