package Main.UI.Panels;

import Main.Holder;
import Main.UI.Format.VicFormatter;
import Students.Victim;
import UIElements.Buttons.RoundButton;
import UIElements.Buttons.RoundedButton;
import UIElements.Colors.CurrentUITheme;
import UIElements.Colors.Images;
import UIElements.Panels.ImagePanel;
import UIElements.Panels.RoundedPanel;
import UIElements.TextCanvas;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchPanel extends JPanel{
    JPanel searchPanel;
    CurrentUITheme theme;
    Images logoGetter;
    Image logo;
    Holder holder;
    VicFormatter searchButton;
    VicFormatter textPanel;
    private VicFormatter topPanel;
    private TextCanvas textPNL;
    private RoundedPanel round;
    HashMap<String, JComponent> map;
    private ArrayList<Victim> victims;
    private String studentSearch;
    private PlayerDisplayPanel displayPanel;

    public SearchPanel(Holder tempHolder){
        map = new HashMap<>();
        this.theme = tempHolder.getTheme();
        holder = tempHolder;
        victims = holder.getVictims();
        //displayPanel = holder.get
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

        magButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean found = false;
                Victim sendVic = null;
                String inputText = textPNL.getText().trim().toLowerCase();

                if (inputText == ""){

                }
                else{
                    for(int i = 0; i < victims.size() && !found; i++){
                        Victim vic = victims.get(i);
                        String firstName = vic.getName().getFirstName().toLowerCase();
                        String lastName = vic.getName().getLastName().toLowerCase();
                        String fullName = (vic.getName().getFirstName() + " " + vic.getName().getLastName()).toLowerCase();
                        String nickName = vic.getName().getNickName().toLowerCase();

                        if(inputText.equals(firstName)){
                            sendVic = victims.get(i);
                            found = true;
                        }
                        else if(inputText.equals(lastName)){
                            sendVic = victims.get(i);
                            found = true;
                        }
                        else if(inputText.equals(fullName)){
                            sendVic = victims.get(i);
                            found = true;
                        }
                        else if(inputText.equals(nickName)){
                            sendVic = victims.get(i);
                            found = true;
                        }
                    }
                    if (found && sendVic != null){
                        holder.getManager().searchOption(sendVic);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Victim not found", "Victim was not found", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

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
