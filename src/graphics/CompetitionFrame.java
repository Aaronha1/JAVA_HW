package graphics;

import javax.swing.*;
import java.awt.*;

public class CompetitionFrame extends JFrame {
    private MenuBar menuBar;
    private CompetitionPanel competitionPanel;
    private ZooPanel zooPanel;
    private JButton[] button;

    public CompetitionFrame() {
        super("Competition");

        setLayout(new BorderLayout());

        button = new JButton[8];

        // Menu bar setup
        menuBar = new MenuBar();
        add(menuBar, BorderLayout.NORTH);

        // Background setup with ZooPanel
        zooPanel = new ZooPanel();
        add(zooPanel, BorderLayout.CENTER);

        // CompetitionPanel
        competitionPanel = new CompetitionPanel();
        add(competitionPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(800, 600)); // Ensure this matches the background size

        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CompetitionFrame::new);
    }
}
