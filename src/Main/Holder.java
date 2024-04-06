package src.Main;

import src.Questions.Questions;
import src.Students.Victim;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.PlayerPanel;
import src.WriterReader.Input;
import src.WriterReader.RandomizeImages;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static src.Main.Assets.filePaths.*;

public class Holder {

    private HashMap<String, JComponent> map;
    private CurrentUITheme theme;
    private RandomizeImages randomizeImg;
    private ArrayList<Victim> victims;
    private ArrayList<PlayerPanel> playerPanels;
    private ArrayList<Questions> questions;
    private VictimPanelManager manager;

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

}
