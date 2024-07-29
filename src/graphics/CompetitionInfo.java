package graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Objects;

import animals.*;

public class CompetitionInfo {
    private static String CompetitionCategory = null;
    private static final ArrayList<CompetitionInfo> allAnimals = new ArrayList<>();
    private static int MAX_RUNNERS;
    private static int numRuns = 0;

    private static Timer timer;

    private final Animal animal;
    private final String category, type;
    private boolean display;

    private CompetitionInfo(Animal animal, String type){
        this.animal = animal;
        this.category = getCategory();
        this.type = type;
        this.display = true;
    }

    public static void addToArr(Animal animal, String type) {
        allAnimals.add(new CompetitionInfo(animal, type));
        numRuns++;
    }


    public static void printArr() {
        String[] columnNames = {"Animal", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy Consumption"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        timer = new Timer(1000, e -> {
            model.setRowCount(0);
            for (CompetitionInfo info : allAnimals) {
                Object[] row = {
                        info.animal.getAnimalName(),
                        info.category,
                        info.type,
                        info.animal.getSpeed(),
                        info.animal.getenergyAmount(),
                        info.animal.getTotalDistance(),
                        info.animal.getEnergyConsumption()
                };
                model.addRow(row);
            }

        });
        timer.start();



        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JFrame frame = new JFrame("Competition Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.setSize(800, 300);
        frame.setVisible(true);
    }

    public static void checks(){
        if (getCategory() == null) {
            JOptionPane.showMessageDialog(null, "Competition type not selected yet.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalStateException();
        }
        if (numRuns >= MAX_RUNNERS) {
            JOptionPane.showMessageDialog(null, "You have reached maximum animals for this competition.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalStateException();
        }
    }
    public static String getCategory() {
        return CompetitionCategory;
    }

    private static void checkADel(String categ){
        if (allAnimals.isEmpty()) return;
        for (CompetitionInfo info : allAnimals) {
            if (!Objects.equals(info.category, categ)) {
                info.display = false;
                numRuns--;
            }
        }
    }
    public static void setCategory(String categ) {
        if (!Objects.equals(getCategory(), categ)) checkADel(categ);
        MAX_RUNNERS = switch (categ) {
            case "Air" -> 5;
            case "Water" -> 4;
            case "Terrestrial" -> 3;
            default -> 0;
        };
        CompetitionCategory = categ;
    }

    public static String[] listAnimals(){
        return switch (getCategory()) {
            case "Air" -> new String[]{"Eagle", "Pigeon"};
            case "Water" -> new String[]{"Alligator", "Dolphin", "Whale"};
            case "Terrestrial" -> new String[]{"Alligator", "Cat", "Dog", "Snake"};
            default -> new String[0];
        };
    }

    public static ArrayList<Animal> getDisplayedAnimals(){
        ArrayList<Animal> animals = new ArrayList<>();
        for (CompetitionInfo info : allAnimals) {
            if (info.display) animals.add(info.animal);
        }
        return animals;
    }

    public static void clearSelectedAnimal() {
        int len = getDisplayedAnimals().size(),j=0;
        if (len==0){
            JOptionPane.showMessageDialog(null, "No animals to clear.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String[] animalNames = new String[len];
        for (int i = 0; i < allAnimals.size(); i++) {
            if (allAnimals.get(i).display) {
                String name = allAnimals.get(i).animal.getAnimalName();
                animalNames[j++] = i + " - " + allAnimals.get(i).type + ": " + name;
            }
        }


        String animalToClear = (String) JOptionPane.showInputDialog(
                null,
                "Select an animal to clear:",
                "clear Animal",
                JOptionPane.QUESTION_MESSAGE,
                null,
                animalNames,
                animalNames[0]
        );

        if (animalToClear != null) {
            String[] index = animalToClear.split(" - ");
            int i = Integer.parseInt(index[0]);
            allAnimals.get(i).display = false;
            numRuns--;
        }
    }

    public static void feedAnimal() {
        int len = getDisplayedAnimals().size(),j=0;
        if (len==0){
            JOptionPane.showMessageDialog(null, "No animals to feed.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String[] animalNames = new String[len];
        for (int i = 0; i < allAnimals.size(); i++) {
            if (allAnimals.get(i).display) {
                String name = allAnimals.get(i).animal.getAnimalName();
                animalNames[j++] = i + " - " + allAnimals.get(i).type + ": " + name;
            }
        }

        String animalToFeed = (String) JOptionPane.showInputDialog(
                null,
                "Select an animal to feed:",
                "Feed Animal",
                JOptionPane.QUESTION_MESSAGE,
                null,
                animalNames,
                animalNames[0]
        );

        if (animalToFeed != null) {
            String[] index = animalToFeed.split(" - ");
            int i = Integer.parseInt(index[0]);
            CompetitionInfo info = allAnimals.get(i);
            String input = JOptionPane.showInputDialog("Enter the amount of food:");
            try {
                int foodAmount = Integer.parseInt(input);
                info.animal.eat(foodAmount);
                JOptionPane.showMessageDialog(null, "Animal fed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a whole number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}




