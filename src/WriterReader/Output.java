package src.WriterReader;

import src.Students.Victim;
import src.UIElements.Colors.CurrentUITheme;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Output {
    public static void writeStudentFile(ArrayList<Victim> students) throws IOException {
        String fileName = "./src/Main/SaveFiles/ListOfVictims.txt";
        FileWriter writer = new FileWriter(fileName);

        for (Victim student : students) {
            writer.write("First name:" + student.getName().getFirstName() + "\n");
            writer.write("Last name:" + student.getName().getLastName() + "\n");
            writer.write("Nickname:" + student.getName().getNickName() + "\n");
            writer.write("Points:" + student.getPoints() + "\n");
            writer.write("Absents:" + student.getAbsences() + "\n");
            writer.write("Answered:" + student.getAnswered() + "\n");
            writer.write("Times Picked:" + student.getNumPicked() + "\n");
            writer.write("Passed:" + student.getPassed() + "\n");
            writer.write("Phone:" + student.getPhone() + "\n");
            writer.write("Jail:" + student.getJail() + "\n");
            writer.write("\n");  // Adding an empty line to separate entries
        }

        writer.close();
    }

    public static void writeUIFile(CurrentUITheme theme) throws IOException{
        String fileName = "./src/Main/SaveFiles/UITheme.txt";
        FileWriter writer = new FileWriter(fileName);

        writer.write("Background:" + theme.getBackgroundString() + "\n");
        writer.write("Foreground:" + theme.getForegroundString() + "\n");

        writer.close();
    }
}
