package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCompetitionDialog extends JDialog {
    private JButton[] button;

    public AddCompetitionDialog(JFrame owner) {
        super(owner, "Add Competition", true);
        setLayout(new GridLayout(1, 3));

        button = new JButton[3];
        button[0] = new JButton("Air Competition");
        button[1] = new JButton("Water Competition");
        button[2] = new JButton("Terrestrial Competition");

        for (int i = 0; i < button.length; i++) {
            add(button[i]);
        }

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
        dispose();
    }

    private void handleWaterCompetition() {
        CompetitionInfo.setCategory("Water");
        dispose();
    }

    private void handleTerrestrialCompetition() {
        CompetitionInfo.setCategory("Terrestrial");
        dispose();
    }
}
