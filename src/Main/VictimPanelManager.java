package src.Main;

import src.Interfaces.Instructions;
import src.Main.UI.Panels.PlayerDisplayPanel;
import src.Students.Victim;
import src.UIElements.Panels.PlayerPanel;

import java.util.ArrayList;

public class VictimPanelManager {
    private ArrayList<PlayerPanel> victims;
    private PlayerDisplayPanel displayPanel;

    public VictimPanelManager(PlayerDisplayPanel displayPanel){
        this.displayPanel = displayPanel;
        this.victims = displayPanel.getPlayers();
    }

    public void sendToVics(Instructions instructions){
        for (PlayerPanel panel: victims){
            if (panel.getPlayerDisplay().isHeld()){
                Victim victim = panel.getVictim();
                instructions.update(victim);
            }
        }
    }

    public boolean anyHeld(){
        boolean held = false;

        for(PlayerPanel panel : victims){
            if(panel.getPlayerDisplay().isHeld()){
                held = true;
                return held;
            }
        }

        return held;
    }

    public ArrayList<PlayerPanel> getHeld(){
        ArrayList<PlayerPanel> heldVics = new ArrayList<>();

        for(PlayerPanel panel : victims){
            if (panel.getPlayerDisplay().isHeld()) {
                heldVics.add(panel);
            }
        }

        return heldVics;
    }

    public void deleteHeldVictims(){
    }

}
