package graphics;

import animals.Animal;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ZooPanel extends BackGroundPanel {
    private final ArrayList<Animal> animals;
    private Timer timer;

    public ZooPanel() {
        this.animals = getAnimals();
        initPanel();
    }

    private ArrayList<Animal> getAnimals(){
        return CompetitionInfo.getDisplayedAnimals();
    }

    private void initPanel() {
        setPreferredSize(new Dimension(800,600)); // Match the size with the background image
        startTimer();
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            updateAnimals();
            repaint();
        });
        timer.start();
    }

    private void updateAnimals() {
        for (Animal animal : getAnimals()) {
            animal.move();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Paint the background
        drawAnimals(g);
    }

    private void drawAnimals(Graphics g) {
        for (Animal animal : getAnimals()) {
             animal.drewObject(g);
        }
    }

}
