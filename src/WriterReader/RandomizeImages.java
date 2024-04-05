package src.WriterReader;

import src.Students.Victim;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomizeImages {

    private ArrayList<Victim> victims;
    private File photoDirectory;
    private List<File> availablePhotos;

    public RandomizeImages(ArrayList<Victim> victims, String photoDirectoryPath) {
        this.victims = victims;
        this.photoDirectory = new File(photoDirectoryPath);
        this.availablePhotos = new ArrayList<>();
        loadPhotos();
    }

    private void loadPhotos() {
        File[] files = photoDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                // Skip any file starting with "null" regardless of its extension
                if (!file.getName().startsWith("null")) {
                    availablePhotos.add(file);
                }
            }
        }
    }

    public void assignPhotosToVictims() {
        Collections.shuffle(availablePhotos); // Randomize the list of photos

        for (Victim vic : victims) {
            File photo = getUniquePhotoForVictim(vic);
            if (photo != null) {
                renamePhoto(photo, vic.getName().getFirstName());
            }
        }
    }

    private File getUniquePhotoForVictim(Victim vic) {
        for (File photo : availablePhotos) {
            String photoName = photo.getName();
            // Check if the photo name is not already a victim's name
            if (!photoName.startsWith(vic.getName().getFirstName())) {
                availablePhotos.remove(photo);
                return photo;
            }
        }
        return null; // No unique photo found
    }

    private void renamePhoto(File photo, String newName) {
        String extension = photo.getName().substring(photo.getName().lastIndexOf('.'));
        String newNameLowercase = newName.toLowerCase();
        Path source = photo.toPath();
        try {
            Files.move(source, source.resolveSibling(newNameLowercase + extension), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetAll() {
        File[] files = photoDirectory.listFiles();
        if (files != null) {
            int i = 0;
            for (File file : files) {
                // Skip the 'null' file
                if (!file.getName().startsWith("null")) {
                    String extension = file.getName().substring(file.getName().lastIndexOf('.'));
                    String newName = "image_" + i++;
                    Path source = file.toPath();
                    try {
                        Files.move(source, source.resolveSibling(newName + extension), StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
