package src.UIElements.Colors;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageResizer {
        public static BufferedImage resize(BufferedImage originalImage, int targetWidth, int targetHeight) {
            // Create a new image of the desired size
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());

            // Get the Graphics2D context of the new image
            Graphics2D g2d = resizedImage.createGraphics();

            // Draw the original image resized into the new image
            g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
            g2d.dispose();

            return resizedImage;
        }
}
