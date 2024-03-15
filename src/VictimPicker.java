import java.io.*;
import java.util.*;

public class VictimPicker {
    private ArrayList<Victim> victims = new ArrayList<Victim>();

    public int victimNameIndex(String name)
    {
        int index = -1;

        for(Victim v : victims)
        {
            if(v.getName().equals(name))
            {
                index = victims.indexOf(v);
            }
        }

        return index;
    }

    public String getVictimName(int index)
    {
        return victims.get(index).getName();
    }

    public VictimPicker() throws FileNotFoundException {
        File ListofVictims = new File("src/ListofVictims");
        //get all victims from file
        Scanner sc = new Scanner(ListofVictims);
        while (sc.hasNextLine())
            victims.add(new Victim(sc.nextLine()));
    }
    public void markAbsent(int index){
        //save them with the date to the absent list
        File AbsentList = new File("src/AbsentList");
        //write new line
        Writer output = null;

        try {
            output = new BufferedWriter(new FileWriter(AbsentList, true));
            output.append("\n" + victims.get(index).getName() + ": " + new Date());
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //remove the absent victim from the array
        victims.remove(index);
    }

    public int getNumberOfVictims()
    {
        return victims.size();
    }
    public void AddVictim(String name){
        //add the name to the file
        File ListofVictims = new File("src/ListofVictims");
        //write new line

        Writer output = null;

        try {
            output = new BufferedWriter(new FileWriter(ListofVictims, true));
            output.append("\n" + name);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //update victim list

        Victim newVictim = new Victim(name);
        victims.add(newVictim);
        newVictim.setTimesPicked(victims.get(0).getTimesPicked());

    }
    public ArrayList<Victim> pickVictims(int numOfVictims){
        Random ran = new Random();
        ArrayList<Victim> victimHolder = new ArrayList<Victim>();
        //A list for the victims who are worthy
        ArrayList<Victim> worthyVictims = new ArrayList<Victim>();

        //find the lowest possible picked
        int lowest = victims.get(0).getTimesPicked();
        //make sure there are 2 of them
        for(Victim v : victims){
            if(v.getTimesPicked() == lowest) {
                worthyVictims.add(v);
            };
            if(v.getTimesPicked() < lowest) {
                lowest = v.getTimesPicked();
                //reset our worthy victims
                worthyVictims = new ArrayList<Victim>();
                worthyVictims.add(v);
            }
        }
        //there are too few worthy victims
        while(worthyVictims.size() < numOfVictims) {
            lowest++;
            //reset our worthy victims
            worthyVictims = new ArrayList<Victim>();
            //fill up on worthy victims
            for(Victim v : victims){
                if(v.getTimesPicked() <= lowest){
                    worthyVictims.add(v);
                }
            }
        }
        //picks some people twice
        do{
            int pick = ran.nextInt(0, worthyVictims.size());
            //add our sacrifice and add one to times picked
            worthyVictims.get(pick).picked();

            victimHolder.add(worthyVictims.get(pick));
            worthyVictims.remove(worthyVictims.get(pick));

        }while(victimHolder.size() < numOfVictims);

        return victimHolder;
    }

    public ArrayList<Victim> VolunteerVictims(String Volunteer){

        ArrayList<Victim> volunteerHolder = new ArrayList<Victim>();


        volunteerHolder.add(getVictim(Volunteer));
        volunteerHolder.get(0).picked();

        return volunteerHolder;
    }
    //displays the top scores up to the number
    public ArrayList<Victim> leaderBoard(int maxPlaces){
        ArrayList<Victim> topScores = new ArrayList<Victim>();
        ArrayList<Victim> topScorers = new ArrayList<Victim>();
        //make sure the maxPlaces isn't larger than victims otherwise, return nothing
        if(maxPlaces <= victims.size()) {
            //sort the victims
            for (Victim v : victims) {
                if (!topScorers.isEmpty()) {
                    boolean wasAdded = false;
                    for (int i = 0; i < topScorers.size(); i++) {
                        if (topScorers.get(i).getScore() < v.getScore()) {
                            //add the victim before it
                            topScorers.add(i, v);
                            wasAdded = true;
                            break;
                        }
                    }
                    //if it wasn't added then add it to the end
                    if(!wasAdded){
                        topScorers.addLast(v);
                    }
                } else {
                    topScorers.addFirst(v);
                }
            }
            //now that it is sorted, shave off the losers
            for (int i = 0; i < maxPlaces; i++) {
                topScores.add(topScorers.get(i));
            }
        }
        return topScores;
    }
    public Victim getVictim(String name){
        Victim returnVictim = null;
        for(Victim v: victims){
            if(Objects.equals(v.getName(), name)){
                returnVictim = v;
                break;
            }
        }
        return returnVictim;
    }
}
