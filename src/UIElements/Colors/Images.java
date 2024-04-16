package UIElements.Colors;

import src.Main.Assets.filePaths;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import src.UIElements.Colors.CurrentUITheme;
import java.io.IOException;

public class Images {
    private BufferedImage image;
    private String imageSelect;
    private String imagePath;
    private String assetPath;
    private String photoPath;
    private final String extension = ".png";

    public Images(String imageSelect, CurrentUITheme theme, String imageType) {
        this.imageSelect = imageSelect.toLowerCase();
        this.assetPath = filePaths.UiImgPath;
        this.photoPath = filePaths.photoPath;

        // Determine the correct path based on the image type
        if (imageType.equals("CustomImage")) {
            this.assetPath = this.photoPath;
        }

        // Construct the full image path
        this.imagePath = this.assetPath + this.imageSelect + this.extension;

        ImageConfig imageCon = new ImageConfig(imageType);

        try {
            File file = new File(this.imagePath);
            if (file.exists()) {
                this.image = ImageIO.read(file);
            } else {
                // Load default image if the specified one is not found
                File defaultImageFile = new File(filePaths.UiImgPath + "null" + this.extension);
                if (defaultImageFile.exists()) {
                    this.image = ImageIO.read(defaultImageFile);
                } else {
                    // Create an empty placeholder if even the default image is missing
                    this.image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
                }
            }

            // Apply image transformations if needed
            if (this.image != null) {
                if (imageCon.alter) {
                    processImage(theme.getCurrentForegroundColor().main());
                }

                if (imageCon.reSize && (this.image.getWidth() > 100 || this.image.getHeight() > 100)) {
                    this.image = resizeImage(this.image, 100, 100);
                }

                if (imageCon.crop) {
                    this.image = cropImageToSize(this.image, 200, 200, imageCon);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        }
    }

    private void processImage(Color color){
        double blendFactor = 0.75;

        for (int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                int rgba = image.getRGB(x, y);
                int alpha = (rgba >> 24) & 0xFF;
                int red = (rgba >> 16) & 0xFF;
                int green = (rgba >> 8) & 0xFF;
                int blue = rgba & 0xFF;

                red = (int)((red * (1 - blendFactor)) + (color.getRed() * blendFactor));
                green = (int)((green * (1 - blendFactor)) + (color.getGreen() * blendFactor));
                blue = (int)((blue * (1 - blendFactor)) + (color.getBlue() * blendFactor));

                int newRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, newRGB);

            }
        }
    }


    public BufferedImage getImage() {
        return image;
    }


    private BufferedImage cropImageToSize(BufferedImage sourceImage, int maxWidth, int maxHeight, ImageConfig imageCon) {
        // Ensure that the maxWidth and maxHeight don't exceed the image's dimensions
        maxWidth = Math.min(maxWidth, sourceImage.getWidth());
        maxHeight = Math.min(maxHeight, sourceImage.getHeight());

        // Calculate the top-left corner of the crop area to center the crop
        int x = Math.max(0, (sourceImage.getWidth() - maxWidth) / 2);
        int y = Math.max(0, (sourceImage.getHeight() - maxHeight) / 2);

        // Check if the calculated x, y, maxWidth, and maxHeight fit within the source image
        if (x + maxWidth > sourceImage.getWidth()) {
            x = sourceImage.getWidth() - maxWidth;
        }
        if (y + maxHeight > sourceImage.getHeight()) {
            y = sourceImage.getHeight() - maxHeight;
        }

        BufferedImage croppedImage = sourceImage.getSubimage(x, y, maxWidth, maxHeight);

        // Now create the image with the border
        if (imageCon.addBuffer) {
            return addTransparentBorder(croppedImage, 25, 25);
        }else{
            return sourceImage.getSubimage(x, y, maxWidth, maxHeight);
        }
    }

    public String getName(){
        return imageSelect;
    }

    private BufferedImage addTransparentBorder(BufferedImage croppedImage, int borderSizeX, int borderSizeY) {
        // Adjusted to add half of borderSizeX to width and 1.3 times borderSizeY to height
        int newWidth = (int) (croppedImage.getWidth() + borderSizeX * 0.5);
        int newHeight = (int) (croppedImage.getHeight() + borderSizeY * 1.3);

        // Create a new image with transparency
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = newImage.createGraphics();

        // Enable antialiasing for shapes
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clear the new image with a transparent alpha
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, newWidth, newHeight);

        // Reset composite to draw the croppedImage over the transparent background
        g2d.setComposite(AlphaComposite.SrcOver);

        // Calculate the position to center the croppedImage in the new image
        int x = (newWidth - croppedImage.getWidth()) / 2;
        int y = (newHeight - croppedImage.getHeight()) / 2;

        // Draw the croppedImage onto the new image
        g2d.drawImage(croppedImage, x, y, null);
        g2d.dispose();

        return newImage;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();

        return outputImage;
    }

    public static class ImageConfig{
        public boolean alter;
        public boolean crop;
        public boolean addBuffer;
        public boolean reSize;

        public ImageConfig(String type){
            if (type == "UIimage"){
                alter = true;
                crop = true;
                addBuffer = false;
                reSize = false;
            }
            else if(type == "CustomImage"){
                alter = false;
                crop = true;
                addBuffer = true;
                reSize = true;
            }
            else if(type == "Logo"){
                alter = true;
                crop = false;
                addBuffer = false;
                reSize = false;
            }
            else{
                alter = true;
                crop = false;
                addBuffer = false;
                reSize = false;
            }

        }

    }

}