package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JPanel {
    private JButton fileButton;
    private JButton helpButton;

    public MenuBar(){
        fileButton = new JButton("File");
        helpButton = new JButton("Help");

        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        add(fileButton);
        add(helpButton);

        fileButton.setBorderPainted(false);
        helpButton.setBorderPainted(false);
        fileButton.setBackground(Color.WHITE);
        helpButton.setBackground(Color.WHITE);
        setBackground(Color.WHITE);

        JPopupMenu popupMenuFile = new JPopupMenu();
        JPopupMenu popupMenuHelp = new JPopupMenu();
        JMenuItem menuFile = new JMenuItem("Exit");
        JMenuItem menuHelp = new JMenuItem("Help");
        popupMenuFile.add(menuFile);
        popupMenuHelp.add(menuHelp);

        // menu panel Action Listener
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenuFile.show(fileButton, 0, fileButton.getHeight());
            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenuHelp.show(helpButton, 0, helpButton.getHeight());
            }
        });

        // Add ActionListener for Exit in menu panel

        menuFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add ActionListener for Help in menu panel

        menuHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuBar.this, "Home Work 2\n GUI",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
