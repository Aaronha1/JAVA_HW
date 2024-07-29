package graphics;

import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {
    private final java.awt.Image image = new ImageIcon(getClass().getResource("graphics2/competitionBackground.png")).getImage();


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gr.drawImage(image,0,0,getWidth(),getHeight(),this);
    }

    public Dimension getPreferredSize(){
        return new  Dimension(1236,881);
    }
}
