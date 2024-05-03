package Main.Assets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class filePaths {
    public static String UiImgPath;
    public static String photoPath;
    public static String vicList;
    public static String uiTheme;
    public static String questList;
    public static String saveFilePath;

    static {
        try {
            loadPaths();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            setDefaults();
        }
    }

    private static void loadPaths() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Github\\FinalVictimPickerProject\\src\\Main\\SaveFiles\\FilePaths.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.contains(":")) {
                String key = line.substring(0, line.indexOf(":")).trim();
                String value = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\"")).trim();

                switch (key) {
                    case "UiImgPath":
                        UiImgPath = value;
                        break;
                    case "PhotoPath":
                        photoPath = value;
                        break;
                    case "VicList":
                        vicList = value;
                        break;
                    case "UiTheme":
                        uiTheme = value;
                        break;
                    case "QuestList":
                        questList = value;
                        break;
                    case "SaveFilePath":
                        saveFilePath = value;
                        break;
                }
            }
        }
        scanner.close();
    }

    private static void setDefaults() {
        UiImgPath = "./src/Main/Assets/UIAssets/";
        photoPath = "./src/Main/Assets/VicPhotos/";
        vicList = "ListOfVictims.txt";
        uiTheme = "UITheme.txt";
        questList = "Questions.txt";
        saveFilePath = "./src/Main/SaveFiles/";
    }

    public static void resetToDefault() {
        setDefaults();
    }

    public static void savePaths() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("C:\\Github\\FinalVictimPickerProject\\src\\Main\\SaveFiles\\FilePaths.txt");
        writer.println("UiImgPath:\"" + UiImgPath + "\"");
        writer.println("PhotoPath:\"" + photoPath + "\"");
        writer.println("VicList:\"" + vicList + "\"");
        writer.println("UiTheme:\"" + uiTheme + "\"");
        writer.println("QuestList:\"" + questList + "\"");
        writer.println("SaveFilePath:\"" + saveFilePath + "\"");
        writer.close();
    }
}
