package graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import animals.*;
import mobility.Point;
import competitions.*;

public final class CompetitionInfo {
    private static String CompetitionCategory = null;
    private static String CompetitionType = null;
    private static final ArrayList<CompetitionInfo> allAnimals = new ArrayList<>();
    private static int MAX_GROUPS;
    private static int numGroups = 0;

    private static Timer timer;

    private final Animal animal;
    private String category;
    private final String type;
    private boolean display;
    private int group;
    private int runner;

    private CompetitionInfo(Animal animal, String type,int group,int runner){
        this.animal = animal;
        this.category = getCategory();
        this.type = type;
        this.group = group;
        this.runner = runner;
        this.display = true;
    }

    public static void addToArr(Animal animal, String type,int group,int runner) {
        if (runner == 2) {
            dynamicPosition(animal);
            allAnimals.add(new CompetitionInfo(animal, type,group,runner));
        } else {
            allAnimals.add(0, new CompetitionInfo(animal, type,group,runner));
        }
    }

    public static int getMaxGroups(){ return MAX_GROUPS; }
    public static int getNumGroups(){ return numGroups; }
    public static void setNumGroups(int groups){
        numGroups = groups;
    }
    public static String getCompetitionType(){ return CompetitionType; }
    public static void setCompetitionType(String cType){
        CompetitionType = cType;
    }
    public static Tournament getCompetition(){
        return switch (getCompetitionType()){
          case "Regular" -> new RegularTournament();
          case "Courier" -> new CourierTournament();
          default -> null;
        };
    }

    public static void dynamicPosition(Animal animal){
        Point p;
        if ("Terrestrial".equals(getCategory())) {
            p = new Point(735,455);
        } else {
            int x = animal.getLocation().getX();
            int y = animal.getLocation().getY();
            p = new Point(x+getDistanceNeeded(),y);
        }
        animal.editPosition(p,Orientation.WEST);
    }

    public static Point getPosition(int group) {
        return switch (getCategory()) {
            case "Air" -> new Point(0,560* (group-1) / MAX_GROUPS);
            case "Water" -> new Point(20,460* (group-1) / MAX_GROUPS +60);
            case "Terrestrial" -> new Point(0,0);
            default -> new Point();
        };
    }
    public static int getDistanceNeeded(){
        return switch (getCategory()) {
            case "Air" -> 735;
            case "Water" -> 710;
            case "Terrestrial" -> 1190;
            default -> 0;
        };
    }
    public static void printArr() {
        String[] columnNames = {"Animal", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy Consumption"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        timer = new Timer(200, e -> {
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

    public static void printScores(){
        String[] columnNames = {"Group","Time"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        timer = new Timer(200, e -> {
            model.setRowCount(0);
            for (Map.Entry<String, Date> entry :
                    TournamentThread.getRealtimeScores().entrySet()) {
                Object[] row = {entry.getKey(),entry.getValue()};
                model.addRow(row);
            }
        });
        timer.start();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JFrame frame = new JFrame("Competition Scores");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.setSize(350, 300);
        frame.setVisible(true);
    }
    public static String getCategory() {
        return CompetitionCategory;
    }

    public static ArrayList<ArrayList<Animal>> getGroups(){
        ArrayList<ArrayList<Animal>> groups = new ArrayList<>();
        for (int i=1; i<=numGroups; i++){
            groups.add(getAnimals(i));
        }
        return groups;
    }
    private static ArrayList<Animal> getAnimals(int group){
        ArrayList<Animal> animals = new ArrayList<>();
        for (CompetitionInfo info : allAnimals){
            if (info.display && info.group == group) {
                animals.add(info.animal);
            }
        }
        return animals;
    }


    public static void setCategory(String categ) {
        MAX_GROUPS = switch (categ) {
            case "Air" -> 5;
            case "Water" -> 4;
            case "Terrestrial" -> 3;
            default -> 0;
        };
        CompetitionCategory = categ;
        clearAll();
    }
    public static void clearAll(){
        for (CompetitionInfo info : allAnimals){
            info.display = false;
        }
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
                if (info.animal.eat(foodAmount)) {
                    JOptionPane.showMessageDialog(null, "Animal fed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Food amount must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a whole number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}




