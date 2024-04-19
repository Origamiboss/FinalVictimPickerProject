package Main.UI;

import Main.Holder;
import Main.UI.Panels.HighScorePanel;
import Students.Victim;
import UIElements.Colors.CurrentUITheme;

import java.util.ArrayList;

public class HighScore
{
    private ArrayList<Victim> victims = new ArrayList<Victim>();
    boolean isVictimInList = false;

    public void storeUpdatedVictim(Victim victim)
    {
        isVictimInList = false;

        for(Victim v : victims)
        {

            if(v.getFullName().equals(victim.getFullName()))
            {
                if(v.getPoints() == victim.getPoints())
                {
                    isVictimInList = true;
                }
                else
                {
                    v.resetPoints();
                    v.setPoints(victim.getPoints());
                    isVictimInList = true;
                }
            }
        }
        if(!isVictimInList)
        {
            victims.add(victim);
        }

    }
    public ArrayList<Victim> leaderBoard(int maxPlaces)
    {
        ArrayList<Victim> topScores = new ArrayList<Victim>();
        ArrayList<Victim> topScorers = new ArrayList<Victim>();
            //make sure the maxPlaces isn't larger than victims otherwise, return nothing
            if (victims.size() > 0)
            {
                //sort the victims
                for (Victim v : victims) {
                    if (!topScorers.isEmpty()) {
                        boolean wasAdded = false;
                        for (int i = 0; i < topScorers.size(); i++) {
                            if (topScorers.get(i).getPoints() < v.getPoints()) {
                                //add the victim before it
                                topScorers.add(i, v);
                                wasAdded = true;
                                break;
                            }
                        }
                        //if it wasn't added then add it to the end
                        if (!wasAdded) {
                            topScorers.addLast(v);
                        }
                    } else {
                        topScorers.addFirst(v);
                    }
                }
                //now that it is sorted, shave off the losers
                for (int i = 0; i < topScorers.size(); i++) {
                    topScores.add(topScorers.get(i));
                }

                return topScores;
            }
            else
            {
                topScores.add(victims.get(0));
                return topScores;
            }
        }
}
