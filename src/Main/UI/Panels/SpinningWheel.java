package src.Main.UI.Panels;

import src.UIElements.Colors.CurrentUITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpinningWheel extends JPanel implements ActionListener {
    Timer timer;
    CurrentUITheme theme;
    private int angle = 0;

    public SpinningWheel(CurrentUITheme theme) {
        //dimensions of the wheel
        this.theme = theme;
        setPreferredSize(new Dimension(400, 400));
        //change the delay to change the speed
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int radius = 150;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        // Number of segments
        int numSegments = 24; // this can be changed to increase or decreas the number of segments on the wheel, depending on how many victims we have
        int degreesPerSegment = 360 / numSegments;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // rotates the entire graphics context to animate the wheel
        g2d.rotate(Math.toRadians(angle), centerX, centerY);

        // draws each segment
        for (int i = 0; i < numSegments; i++) {
            // alternates colors for better visibility, we can change if we do not want red or black
            if (i % 2 == 0) {
                g2d.setColor(this.theme.getCurrentForegroundColor().main());
            } else {
                g2d.setColor(this.theme.getCurrentBackgroundColor().main());
            }

            // calculates the start angle for this segment
            int startAngle = i * degreesPerSegment;

            // draws the segments as an arc
            g2d.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, startAngle, degreesPerSegment);
        }

        // updates the angle for the next frame
        angle = (angle + 1) % 360;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    //added main in here to test with
}
