package UIElements.Panels;

import javax.swing.*;
import java.awt.*;

public class BufferedPanel<T extends Component> extends JPanel {
    private T innerComponent;
    private int bufferDistance;

    public BufferedPanel(T innerComponent, int bufferDistance) {
        this.setOpaque(false);
        this.innerComponent = innerComponent;
        this.bufferDistance = bufferDistance;

        setLayout(new BorderLayout());

        this.setMaximumSize(new Dimension(innerComponent.getWidth() + 10, innerComponent.getHeight() + 10));

        // Inner component should not be larger than its preferred size.
        innerComponent.setMaximumSize(innerComponent.getPreferredSize());

        add(innerComponent, BorderLayout.CENTER);

        // Create rigid areas with fixed sizes
        Component northRigidArea = Box.createRigidArea(new Dimension(1, bufferDistance));
        Component westRigidArea = Box.createRigidArea(new Dimension(bufferDistance, 1));
        Component southRigidArea = Box.createRigidArea(new Dimension(1, bufferDistance));
        Component eastRigidArea = Box.createRigidArea(new Dimension(bufferDistance, 1));

        // Set their maximum sizes to be the same as their preferred sizes.
        northRigidArea.setMaximumSize(northRigidArea.getPreferredSize());
        westRigidArea.setMaximumSize(westRigidArea.getPreferredSize());
        southRigidArea.setMaximumSize(southRigidArea.getPreferredSize());
        eastRigidArea.setMaximumSize(eastRigidArea.getPreferredSize());

        add(northRigidArea, BorderLayout.NORTH);
        add(westRigidArea, BorderLayout.WEST);
        add(southRigidArea, BorderLayout.SOUTH);
        add(eastRigidArea, BorderLayout.EAST);

        //setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Call superclass method first to paint the background
        this.repaint();
        innerComponent.repaint();
    }

    public BufferedPanel getPanel(){
        return this;
    }
}
