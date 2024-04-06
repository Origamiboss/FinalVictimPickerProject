package src;

import src.Victim.Victim;
import src.Victim.VictimPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MenuPanel extends JPanel {

    private final VictimPicker victimPicker;
    private final JPanel leaderboardPanel = new JPanel();

    public MenuPanel(VictimPicker victimPicker) {
        super(new BorderLayout());
        this.victimPicker = victimPicker;

        add(createMenuPanel(), BorderLayout.CENTER);
        add(leaderboardPanel, BorderLayout.CENTER);
        leaderboardPanel.setLayout(new GridLayout(0, 1)); // Dynamically add rows
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));

        JLabel menuLabel = new JLabel("Leaderboard Top 5");
        menuPanel.add(menuLabel);

        JButton leaderBoardButton = new JButton("Leaderboard");
        leaderBoardButton.addActionListener(this::handleMenuLeaderBoard);
        menuPanel.add(leaderBoardButton);

        return menuPanel;
    }

    private void handleMenuLeaderBoard(ActionEvent actionEvent) {
        displayLeaderboard();
    }

    private void displayLeaderboard() {
        leaderboardPanel.removeAll(); // Clear previous results

        List<Victim> topScorers = victimPicker.leaderBoard(5); // Assuming getLeaderboard exists

        for (Victim v : topScorers) {
            JLabel scoreLabel = new JLabel(v.getName() + " - Score: " + v.getScore());
            leaderboardPanel.add(scoreLabel);
        }

        leaderboardPanel.revalidate();
        leaderboardPanel.repaint();
    }
}
