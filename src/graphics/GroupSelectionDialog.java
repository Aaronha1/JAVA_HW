package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupSelectionDialog extends JDialog {
    private JComboBox<Integer> groupSelector;
    private JButton confirmButton;

    public GroupSelectionDialog(JFrame owner) {
        super(owner, "Select Number of Groups", true);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Select the number of groups:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        int maxGroups = CompetitionInfo.getMaxGroups();
        Integer[] groupOptions = new Integer[maxGroups];
        for (int i = 0; i < maxGroups; i++) {
            groupOptions[i] = i + 1;
        }

        groupSelector = new JComboBox<>(groupOptions);
        groupSelector.setSelectedIndex(0);
        add(groupSelector, BorderLayout.CENTER);

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompetitionInfo.setNumGroups((int)groupSelector.getSelectedItem());
                dispose();
            }
        });
        add(confirmButton, BorderLayout.SOUTH);

        setSize(300, 150);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
