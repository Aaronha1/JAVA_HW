package graphics;

import animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompetitionPanel extends JPanel {
    private JButton[] button;
    public CompetitionPanel(){

        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(CompetitionPanel.this);
        button = new JButton[7];

        setLayout(new GridLayout(1,7));
        button[0] = new JButton("Add Competition");
        button[1] = new JButton("Start");
        button[2] = new JButton("Scores");
        button[3] = new JButton("Clear");
        button[4] = new JButton("Eat");
        button[5] = new JButton("Info");
        button[6] = new JButton("Exit");


        for (int i = 0 ; i < button.length ; i ++){
            add(button[i]);
            button[i].setBackground(Color.lightGray);
        }

        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCompetitionDialog(owner);
                new GroupSelectionDialog(owner);
                new AddAnimalGroup(owner);
            }
        });
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompetitionInfo.printScores();
            }
        });
        button[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompetitionInfo.clearSelectedAnimal();
            }
        });
        button[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompetitionInfo.feedAnimal();
            }
        });
        button[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompetitionInfo.printArr();
            }
        });

        button[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
