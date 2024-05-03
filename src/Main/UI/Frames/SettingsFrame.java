package Main.UI.Frames;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import Main.Holder;
import Main.UI.Format.VicFormatter;
import UIElements.Buttons.HeldButton;
import UIElements.Buttons.RoundButton;
import UIElements.Colors.CurrentUITheme;
import UIElements.Colors.UIColors;
import UIElements.Panels.RoundedPanel;
import UIElements.TextCanvas;
import Main.Assets.filePaths;
import WriterReader.RandomizeImages;

public class SettingsFrame extends JFrame {
    final private JFrame self = this;
    private Holder holder;
    private TextCanvas victimFile;
    private TextCanvas questionFile;
    private TextCanvas pictureFile;
    private JComboBox<String> backColorComboBox;
    private JComboBox<String> foreColorComboBox;
    private HeldButton randAssignButton;
    private HeldButton resetButton;
    private RoundButton assignRandButton;
    private RoundButton resetImagesButtons;
    private boolean assignPhotos = false;
    private boolean resetPhotos = false;

    public SettingsFrame(Holder h) {
        holder = h;

        victimFile = new TextCanvas(holder.getTheme(), 10, true);
        questionFile = new TextCanvas(holder.getTheme(), 10, true);
        pictureFile = new TextCanvas(holder.getTheme(), 10, true);

        self.setTitle("Settings");
        RoundedPanel statHolder = new RoundedPanel(h.getTheme());
        self.add(statHolder);
        statHolder.setLayout(new BoxLayout(statHolder, BoxLayout.Y_AXIS));

        RoundedPanel backcolorHolder = setupColorChooser("Background:", UIColors.colors, holder.getTheme().getBackgroundString());
        statHolder.add(backcolorHolder);

        RoundedPanel frontColorHolder = setupColorChooser("Foreground:", UIColors.colors, holder.getTheme().getForegroundString());
        statHolder.add(frontColorHolder);

        addFilePathSetting(statHolder, "Victim List:", "Select Victim List File", false, victimFile);
        victimFile.setText(filePaths.UiImgPath + filePaths.vicList);
        addFilePathSetting(statHolder, "Question File:", "Select Question File", false, questionFile);
        questionFile.setText(filePaths.saveFilePath + filePaths.questList);
        addFilePathSetting(statHolder, "Picture Folder:", "Select Victim Picture Folder", true, pictureFile);
        pictureFile.setText(filePaths.photoPath);

        RoundedPanel testPanel = new RoundedPanel(holder.getTheme());
        JLabel messageLabel = new JLabel("Assign: Randomly assign victim names to photos, Reset: rename image files");
        testPanel.add(messageLabel);
        RoundButton assignRandButton = new RoundButton("ASSIGN", holder.getTheme());
        assignRandButton.setForeground(Color.BLACK);
        assignRandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignRandButton.setBackground(h.getTheme().getCurrentBackgroundColor().action());
                assignPhotos = true;
            }
        });
        RoundButton resetImagesButtons = new RoundButton("RESET", holder.getTheme());
        resetImagesButtons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetImagesButtons.setBackground(h.getTheme().getCurrentBackgroundColor().action());
                resetPhotos = true;
            }
        });
        assignRandButton.setSize(new Dimension(200, 100));
        testPanel.add(assignRandButton);
        testPanel.add(resetImagesButtons);
        statHolder.add(testPanel);

        setupDisplayCanvas(statHolder);
        setupSaveButton(statHolder);

        self.pack();
        self.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        self.setVisible(true);
    }

    private RoundedPanel setupColorChooser(String label, String[] options, String initialSelection) {
        RoundedPanel colorHolder = new RoundedPanel(holder.getTheme());
        colorHolder.add(new JLabel(label));
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setSelectedItem(initialSelection);
        colorHolder.add(comboBox);
        if (label.equals("Background:")) {
            backColorComboBox = comboBox;
        } else {
            foreColorComboBox = comboBox;
        }
        return colorHolder;
    }

    private void addFilePathSetting(RoundedPanel holderPanel, String label, String buttonText, boolean selectDirectory, TextCanvas inCanvas) {
        TextCanvas textCanvas = inCanvas;
        textCanvas.setPreferredSize(new Dimension(300, 25));
        textCanvas.setBorder(new LineBorder(holder.getTheme().getCurrentForegroundColor().main(), 2));
        VicFormatter textForm = new VicFormatter(textCanvas, 5);
        JButton fileChooserButton = new JButton(buttonText);
        fileChooserButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (selectDirectory) {
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            }
            int result = fileChooser.showOpenDialog(self);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                textCanvas.setText(selectedFile.getAbsolutePath());
            }
        });
        RoundedPanel filePathPanel = new RoundedPanel(holder.getTheme());
        filePathPanel.add(new JLabel(label));
        filePathPanel.add(textForm.getPanel());
        filePathPanel.add(fileChooserButton);
        holderPanel.add(filePathPanel);
    }

    private void setupDisplayCanvas(RoundedPanel holderPanel) {
        TextCanvas displayCanvas = new TextCanvas(holder.getTheme(), 15, false);
        displayCanvas.setText("To save your changes restart the program!");
        displayCanvas.setBorder(new LineBorder(holder.getTheme().getCurrentForegroundColor().main(), 2));
        VicFormatter canvasFormat = new VicFormatter(displayCanvas, 5);
        holderPanel.add(canvasFormat.getPanel());
    }

    private void setupSaveButton(RoundedPanel holderPanel) {
        RoundButton doneButton = new RoundButton("Save", holder.getTheme());
        doneButton.addActionListener(e -> saveSettings());
        VicFormatter buttonFormat = new VicFormatter(doneButton, 5);
        holderPanel.add(buttonFormat.getPanel());
    }

    private void saveSettings() {
        // Update file path settings in filePaths
        filePaths.UiImgPath = new File(victimFile.getText()).getParent() + "/";
        filePaths.vicList = new File(victimFile.getText()).getName();
        filePaths.saveFilePath = new File(questionFile.getText()).getParent() + "/";
        filePaths.questList = new File(questionFile.getText()).getName();
        filePaths.photoPath = pictureFile.getText();

        // Update UI theme in Holder
        holder.setTheme(new CurrentUITheme((String) foreColorComboBox.getSelectedItem(), (String) backColorComboBox.getSelectedItem()));

        // Save the new paths to the configuration file
        try {
            filePaths.savePaths();  // Call the method to write changes to FilePaths.txt
            JOptionPane.showMessageDialog(self, "Settings saved successfully. Please restart the application for changes to take effect.");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(self, "Error saving settings: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        if(assignPhotos) {
            holder.randomizeImages();
            RandomizeImages randImg = holder.getRandomizeImg();
            randImg.assignPhotosToVictims();
        }
        holder.setResetBool(resetPhotos);

    }
}
