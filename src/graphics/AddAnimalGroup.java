package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAnimalGroup extends JDialog {
    private JButton[][] addButtons;
    private int groups;
    private String competitionType;
    private int totalButtons;
    private int clickedButtons;
    JFrame owner;

    public AddAnimalGroup(JFrame owner) {
        super(owner, "Add Animals to Groups", true);
        this.owner = owner;
        this.groups = CompetitionInfo.getNumGroups();
        this.competitionType = CompetitionInfo.getCompetitionType();
        this.totalButtons = "Regular".equals(competitionType) ? groups : groups * 2;
        this.clickedButtons = 0;
        int col = "Regular".equals(competitionType) ? 2:3;


        setLayout(new GridLayout(col, groups + 1));

        addButtons = new JButton[2][groups];

        // Create headers for each group
        for (int i = 1; i <= groups; i++) {
            add(new JLabel("Group " + i, JLabel.CENTER));
        }

        // Create Add buttons according to the competition type
        for (int i = 0; i < groups; i++) {
            addButtons[0][i] = new JButton("Add Animal");
            addButtons[0][i].addActionListener(new AddAnimalAction(i, 0));
            add(addButtons[0][i]);

            if (!"Regular".equals(competitionType)) {
                addButtons[1][i] = new JButton("Add Animal 2");
                addButtons[1][i].addActionListener(new AddAnimalAction(i, 1));
                add(addButtons[1][i]);
            }
        }

        setSize(600, 200);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class AddAnimalAction implements ActionListener {
        private final int groupIndex;
        private final int buttonIndex;

        public AddAnimalAction(int groupIndex, int buttonIndex) {
            this.groupIndex = groupIndex;
            this.buttonIndex = buttonIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new AddAnimalDialog(owner,groupIndex+1,buttonIndex+1);
            addButtons[buttonIndex][groupIndex].setEnabled(false);
            clickedButtons++;

            // Check if all buttons have been clicked
            if (clickedButtons == totalButtons) {
                JOptionPane.showMessageDialog(null, "All animals added. Closing window.", "Done", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the dialog
            }
        }
    }
}
