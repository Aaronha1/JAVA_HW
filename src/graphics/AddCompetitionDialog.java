package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCompetitionDialog extends JDialog {
    private JButton[] button;
    private JCheckBox regularTournamentCheckBox;
    private JCheckBox courierTournamentCheckBox;

    public AddCompetitionDialog(JFrame owner) {
        super(owner, "Add Competition", true);
        setLayout(new BorderLayout());

        JPanel competitionTypePanel = new JPanel(new GridLayout(1, 2));
        button = new JButton[3];
        button[0] = new JButton("Air Competition");
        button[1] = new JButton("Water Competition");
        button[2] = new JButton("Terrestrial Competition");

        for (int i = 0; i < button.length; i++) {
            competitionTypePanel.add(button[i]);
        }

        JPanel tournamentTypePanel = new JPanel(new GridLayout(1, 2));
        regularTournamentCheckBox = new JCheckBox("Regular Tournament", true);
        courierTournamentCheckBox = new JCheckBox("Courier Tournament");

        ButtonGroup tournamentGroup = new ButtonGroup();
        tournamentGroup.add(regularTournamentCheckBox);
        tournamentGroup.add(courierTournamentCheckBox);

        tournamentTypePanel.add(regularTournamentCheckBox);
        tournamentTypePanel.add(courierTournamentCheckBox);

        add(competitionTypePanel, BorderLayout.CENTER);
        add(tournamentTypePanel, BorderLayout.SOUTH);

        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAirCompetition();
            }
        });

        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWaterCompetition();
            }
        });

        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTerrestrialCompetition();
            }
        });

        setSize(600, 300);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void handleAirCompetition() {
        CompetitionInfo.setCategory("Air");
        createTournament();
        dispose();
    }

    private void handleWaterCompetition() {
        CompetitionInfo.setCategory("Water");
        createTournament();
        dispose();
    }

    private void handleTerrestrialCompetition() {
        CompetitionInfo.setCategory("Terrestrial");
        createTournament();
        dispose();
    }

    private void createTournament() {
        if (regularTournamentCheckBox.isSelected()) {
            CompetitionInfo.setCompetitionType("Regular");
        } else if (courierTournamentCheckBox.isSelected()) {
            CompetitionInfo.setCompetitionType("Courier");
        }
    }
}
