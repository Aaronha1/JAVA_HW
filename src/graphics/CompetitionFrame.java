package graphics;

import javax.swing.*;
import java.awt.*;


public class CompetitionFrame extends JFrame {
    private MenuBar menuBar;
    private CompetitionPanel competitionPanel;
    private BackGroundPanel backGroundPanel;
    private JButton[] button;
    public CompetitionFrame(){

        super("Competition");

        setLayout(new BorderLayout());

        button = new JButton[8];

        // menu panel setup
        menuBar = new MenuBar();
        add(menuBar, BorderLayout.NORTH);


        // Background setup

        backGroundPanel = new BackGroundPanel();
        add(backGroundPanel,BorderLayout.CENTER);


        // CompetitionPanel
        competitionPanel = new CompetitionPanel();
        add(competitionPanel,BorderLayout.AFTER_LAST_LINE);

        setPreferredSize(new Dimension(800, 600));

        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args){
        new CompetitionFrame();
    }
}
