package graphics;

import animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompetitionPanel extends JPanel {
    private JButton[] button;
    private AddCompetitionDialog addCompetitionDialog;
    private AddAnimalDialog addAnimalDialog;
    private final ArrayList<Animal> animals;
    public CompetitionPanel(){

        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(CompetitionPanel.this);
        this.animals = new ArrayList<>();
        button = new JButton[6];

        setLayout(new GridLayout(1,6));
        button[0] = new JButton("Add Competition");
        button[1] = new JButton("Add Animal");
        button[2] = new JButton("Clear");
        button[3] = new JButton("Eat");
        button[4] = new JButton("Info");
        button[5] = new JButton("Exit");


        for (int i = 0 ; i < button.length ; i ++){
            add(button[i]);
            button[i].setBackground(Color.lightGray);
        }

        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addCompetitionDialog = new AddCompetitionDialog(owner);
            }
        });
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAnimalDialog = new AddAnimalDialog(owner,animals);
            }
        });

        button[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
