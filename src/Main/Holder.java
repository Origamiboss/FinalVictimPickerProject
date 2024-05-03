package Main;

import Main.UI.HighScore;
import Main.UI.Panels.HighScorePanel;
import Questions.Questions;
import Students.StudentFunctions.Names;
import Students.Victim;
import UIElements.Colors.CurrentUITheme;
import UIElements.Panels.PlayerPanel;
import WriterReader.Input;
import WriterReader.Output;
import WriterReader.RandomizeImages;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

import static Main.Assets.filePaths.*;

public class Holder {

    private HashMap<String, JComponent> map;
    private CurrentUITheme theme;
    private RandomizeImages randomizeImg;
    private ArrayList<Victim> victims;
    private ArrayList<PlayerPanel> playerPanels;
    private ArrayList<Questions> questions;
    private VictimPanelManager manager;

    private static HighScore highScore;
    private static HighScorePanel highScorePanel;
    public static ArrayList<Victim> victimsTopScores = new ArrayList<>();

    public Holder() throws FileNotFoundException{
        //Option to change filepath. Especially on first launch
        theme = Input.readUIThemeFile(saveFilePath + uiTheme);
        victims = Input.readStudentFile(saveFilePath + vicList);
        questions = Input.readQuestionsFile(saveFilePath + questList);
        map = new HashMap<>();
    }

    public CurrentUITheme getTheme(){
        return theme;
    }

    public void setTheme(CurrentUITheme c){
        try {
            Output.writeUIFile(c);
        }catch(IOException e){
            //error
            JPanel errorHolder = new JPanel();
            JOptionPane.showConfirmDialog(errorHolder,"ERROR: File Not Found");
        }
    }

    public ArrayList<Victim> getVictims(){
        return victims;
    }

    // Be very careful with this. Should only be called if sure you want
    // Randomize images, giving a name from the victim list to each photo
    public void randomizeImages(boolean reset){
        randomizeImg = new RandomizeImages(victims, photoPath);
    }

    public void resetImages(boolean reset){
        // Be extra careful with this; If true, then will go through
        // The photoPath and give all the images in the class the name
        // "image_i" i being the count in the loop
        if(reset == true){
            randomizeImg.resetAll();
        }
    }

    public void saveStudents(){
    }

    public void setManagaer(VictimPanelManager inManager){
        manager = inManager;
    }

    public ArrayList<Questions> getQuestions(){
        return questions;
    }

    public HashMap<String, JComponent> getMap(){
        return map;
    }

    public void setMap(HashMap<String, JComponent> inMap){
        map.putAll(inMap);
    }

    public VictimPanelManager getManager(){
        return manager;
    }

    public static void currentHighScorePanel(HighScorePanel highScorePanel, HighScore highscore) //-------------------------------------------------------
    {
        Holder.highScorePanel = highScorePanel;
        Holder.highScore = highscore;

    }

    public static void updateLeaderBoard(Victim victim) //-----------------------------------------------------------------------
    {
        highScore.storeUpdatedVictim(victim);
        victimsTopScores = highScore.leaderBoard(5);
        highScorePanel.updatePanel(victimsTopScores);
    }

    public void addVictim(String name){
        String[] names = name.split(" ");
        Victim newGuy;

        if(names.length < 2){
            //only save one name
            Names n = new Names();
            n.setFirstName(names[0]);
            n.setLastName("");
            n.setNickName("");
            newGuy = new Victim(n, 0,0,0,0,0,2,2);
        }else{
            //save the first name and the very last name
            Names n = new Names();
            n.setFirstName(names[0]);
            n.setLastName(names[names.length - 1]);
            n.setNickName("");
            newGuy = new Victim(n, 0,0,0,0,0,2,2);
        }

        //add the new victim to the file
        try {
            victims.add(newGuy);
            Output.writeStudentFile(victims);
        }catch(IOException e){
            //error
            JPanel errorHolder = new JPanel();
            JOptionPane.showConfirmDialog(errorHolder,"ERROR: File Not Found");
        }
    }

    public void deleteVictim(Victim v){
        //Delete The Victim
        try{
            victims.remove(v);
            Output.writeStudentFile(victims);
        }catch(IOException e){
            //error
            JPanel errorHolder = new JPanel();
            JOptionPane.showConfirmDialog(errorHolder,"ERROR: File Not Found");
        }
    }
    public void editVictim(Victim v){
        //Update
        //Find the victim with the same name
        boolean foundTheTarget = false;
        for(Victim vic : victims){
            if(vic.getName().getFirstName() == v.getName().getFirstName() &&
                    vic.getName().getLastName() == v.getName().getLastName()){
                //replace the vic with v
                victims.add(victims.indexOf(vic), v);
                victims.remove(vic);
                foundTheTarget = true;
                break;
            }
        }
        try{
            if(foundTheTarget)
            {
                Output.writeStudentFile(victims);
                updateLeaderBoard(v);
            }
            else
                throw new Exception();
        }catch(ConcurrentModificationException e) {
            //error because two things are editing at the same time
            JPanel errorHolder = new JPanel();
            JOptionPane.showMessageDialog(errorHolder,"ERROR: File in use.");
        }catch (IOException e){
            //error
            JPanel errorHolder = new JPanel();
            JOptionPane.showMessageDialog(errorHolder,"ERROR: File Not Found");
        }catch(Exception e){
            //error
            JPanel errorHolder = new JPanel();
            JOptionPane.showMessageDialog(errorHolder,"ERROR: Updated Victim Not Found");
        }
    }

    public void exit() {
        System.exit(0);
    }
}