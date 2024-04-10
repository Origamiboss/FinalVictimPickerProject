package src;

import src.Victim.Victim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PickedVictimDisplay extends JPanel
{
    private Button addPoint;
    private Button subPoint;
    private Victim theVictim;
    public PickedVictimDisplay(Victim v){
        //set up background
        addPoint = new Button();
        subPoint = new Button();
        theVictim = v;

        addPoint.setLabel("+");
        subPoint.setLabel("-");

        add(addPoint);
        add(subPoint);

        //if add point is clicked, add a point
        addPoint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == addPoint){
                    theVictim.addScore(1);
                }
            }
        });

        subPoint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == subPoint){
                    theVictim.addScore(-1);
                }
            }
        });


    }

}