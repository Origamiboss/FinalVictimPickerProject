package src;

import src.ButtonCommands.AddPlayerMenu;
import src.Interfaces.Instructions;
import src.Interfaces.SimpleInstructions;
import src.Main.Frontend;
import src.Main.VictimPanelManager;
import src.UIElements.Buttons.RoundButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {

        Frontend frontend = new Frontend();
        HashMap<String, JComponent> map = frontend.getHolder().getMap();

        // Testing features
        RoundButton checkPlayers = (RoundButton) map.get("csButton3");

        // 1. Grab the button
        RoundButton addPlayers = (RoundButton) map.get("csButton2");

        // 2. Create the instruction, this is where you write what you want to do
        // In this example, I made a class called AddPlayerMenu that will create
        // The add player panel based on the victim array passed to it
        SimpleInstructions addingPlayer = new SimpleInstructions() {
            @Override
            public void execute() {
                AddPlayerMenu addPlayerMenu = new AddPlayerMenu(frontend.getHolder().getVictims());
            }
        };

        // 3. Connect to the button, and that's it
        addPlayers.addActionListener(e -> addingPlayer.execute());

        // Ignore; Testing stuff
        checkPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VictimPanelManager manager = frontend.getHolder().getManager();

                boolean held = manager.anyHeld();
                if(held){
                    System.out.println("Are held");
                }
                else{
                    System.out.println("None Held");
                }
            }
        });

        //Deprecated Panels
        //VictimPicker v = new VictimPicker();
        //VictimGUI GUI = new VictimGUI(v);

    }
}