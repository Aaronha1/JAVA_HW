package graphics;

import animals.*;
import mobility.Point;
import olympics.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddAnimalDialog extends JDialog {

    private String[] animalsType;
    int group, runner;

    public AddAnimalDialog(JFrame owner, int group, int runner) {
        super(owner, "Add Animal to Competition", true);
        this.group = group;
        this.runner = runner;

        animalsType = CompetitionInfo.listAnimals();

        setLayout(new BorderLayout());

        JPanel animalSelectionPanel = new JPanel(new GridLayout(1, animalsType.length));
        JButton[] buttons = new JButton[animalsType.length];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(animalsType[i]);
            String aType = animalsType[i];
            animalSelectionPanel.add(buttons[i]);
            buttons[i].addActionListener(e -> addAnimal(aType));
        }

        add(animalSelectionPanel, BorderLayout.NORTH);

        setSize(600, 400);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void addAnimal(String type) {
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 10, 10));

        JTextField nameField = new JTextField();
        JComboBox<Gender> genderComboBox = new JComboBox<>(Gender.values());
        JTextField weightField = new JTextField();
        JTextField speedField = new JTextField();
        JTextField idField = new JTextField();
        JTextField maxEnergyField = new JTextField();
        JTextField energyPerMeterField = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Gender:"));
        inputPanel.add(genderComboBox);
        inputPanel.add(new JLabel("Weight:"));
        inputPanel.add(weightField);
        inputPanel.add(new JLabel("Speed:"));
        inputPanel.add(speedField);
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Max Energy:"));
        inputPanel.add(maxEnergyField);
        inputPanel.add(new JLabel("Energy per Meter:"));
        inputPanel.add(energyPerMeterField);

        // Specific fields for different animal types
        JTextField specificField1 = new JTextField();
        JTextField specificField2 = new JTextField();
        JTextField specificField3 = new JTextField();
        JComboBox<?> specificComboBox1 = null;

        // Customize the specific fields based on the animal type
        switch (type) {
            case "Alligator":
                inputPanel.add(new JLabel("Dive Depth:"));
                inputPanel.add(specificField1);
                inputPanel.add(new JLabel("Number of Legs:"));
                inputPanel.add(specificField2);
                inputPanel.add(new JLabel("Area of Living:"));
                inputPanel.add(specificField3);
                break;
            case "Cat":
                inputPanel.add(new JLabel("Number of Legs:"));
                inputPanel.add(specificField1);
                inputPanel.add(new JLabel("Castrated (Yes/No):"));
                inputPanel.add(specificField2);
                break;
            case "Dog":
                inputPanel.add(new JLabel("Number of Legs:"));
                inputPanel.add(specificField1);
                inputPanel.add(new JLabel("Breed Type:"));
                inputPanel.add(specificField2);
                break;
            case "Dolphin":
                inputPanel.add(new JLabel("Dive Depth:"));
                inputPanel.add(specificField1);
                inputPanel.add(new JLabel("Water Type:"));
                specificComboBox1 = new JComboBox<>(WaterType.values());
                inputPanel.add(specificComboBox1);
                break;
            case "Eagle":
                inputPanel.add(new JLabel("Altitude of Flight:"));
                inputPanel.add(specificField1);
                inputPanel.add(new JLabel("Wing Span:"));
                inputPanel.add(specificField2);
                break;
            case "Pigeon":
                inputPanel.add(new JLabel("Family Type:"));
                inputPanel.add(specificField1);
                inputPanel.add(new JLabel("Wing Span:"));
                inputPanel.add(specificField2);
                break;
            case "Snake":
                inputPanel.add(new JLabel("Length:"));
                inputPanel.add(specificField1);
                inputPanel.add(new JLabel("Poisonous:"));
                specificComboBox1 = new JComboBox<>(Poisonous.values());
                inputPanel.add(specificComboBox1);
                break;
            case "Whale":
                inputPanel.add(new JLabel("Dive Depth:"));
                inputPanel.add(specificField1);
                inputPanel.add(new JLabel("Food Type:"));
                inputPanel.add(specificField2);
                break;
        }

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Enter "+type+" Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            AnimalAttributes attributes = new AnimalAttributes(
                    nameField.getText(),
                    (Gender) genderComboBox.getSelectedItem(),
                    Double.parseDouble(weightField.getText()),
                    Integer.parseInt(speedField.getText()),
                    gatherMedals(),
                    Integer.parseInt(idField.getText()),
                    Integer.parseInt(maxEnergyField.getText()),
                    Integer.parseInt(energyPerMeterField.getText()),
                    Orientation.EAST,
                    group
            );

            Animal animal = null;

            switch (type) {
                case "Alligator":
                    animal = new Alligator(
                            attributes.name, attributes.gender, attributes.weight, attributes.speed, attributes.loction,
                            attributes.medals, attributes.id, attributes.maxEnergy, attributes.energyPerMeter,
                            attributes.orien, Double.parseDouble(specificField1.getText()), Integer.parseInt(specificField2.getText()),
                            specificField3.getText());
                    break;
                case "Cat":
                    animal = new Cat(
                            attributes.name, attributes.gender, attributes.weight, attributes.speed, attributes.loction,
                            attributes.medals, attributes.id, attributes.maxEnergy, attributes.energyPerMeter,
                            attributes.orien, Integer.parseInt(specificField1.getText()),
                            Boolean.parseBoolean(specificField3.getText()));
                    break;
                case "Dog":
                    animal = new Dog(
                            attributes.name, attributes.gender, attributes.weight, attributes.speed, attributes.loction,
                            attributes.medals, attributes.id, attributes.maxEnergy, attributes.energyPerMeter,
                            attributes.orien, Integer.parseInt(specificField1.getText()), specificField2.getText());
                    break;
                case "Dolphin":
                    animal = new Dolphin(
                            attributes.name, attributes.gender, attributes.weight, attributes.speed, attributes.loction,
                            attributes.medals, attributes.id, attributes.maxEnergy, attributes.energyPerMeter,
                            attributes.orien, Double.parseDouble(specificField1.getText()),
                            (WaterType) specificComboBox1.getSelectedItem());
                    break;
                case "Eagle":
                    animal = new Eagle(
                            attributes.name, attributes.gender, attributes.weight, attributes.speed, attributes.loction,
                            attributes.medals, attributes.id, attributes.maxEnergy, attributes.energyPerMeter,
                            attributes.orien, Double.parseDouble(specificField2.getText()),
                            Double.parseDouble(specificField1.getText()));
                    break;
                case "Pigeon":
                    animal = new Pigeon(
                            attributes.name, attributes.gender, attributes.weight, attributes.speed, attributes.loction,
                            attributes.medals, attributes.id, attributes.maxEnergy, attributes.energyPerMeter,
                            attributes.orien, Double.parseDouble(specificField2.getText()), specificField1.getText());
                    break;
                case "Snake":
                    animal = new Snake(
                            attributes.name, attributes.gender, attributes.weight, attributes.speed, attributes.loction,
                            attributes.medals, attributes.id, attributes.maxEnergy, attributes.energyPerMeter,
                            attributes.orien, Double.parseDouble(specificField1.getText()),
                            (Poisonous) specificComboBox1.getSelectedItem());
                    break;
                case "Whale":
                    animal = new Whale(
                            attributes.name, attributes.gender, attributes.weight, attributes.speed, attributes.loction,
                            attributes.medals, attributes.id, attributes.maxEnergy, attributes.energyPerMeter,
                            attributes.orien, Double.parseDouble(specificField1.getText()), specificField2.getText());
                    break;
            }

            if (animal != null) {
                CompetitionInfo.addToArr(animal, type, group, runner);
            }

            dispose();
        }
    }

    // Helper method to gather medals
    private ArrayList<Medal> gatherMedals() {
        ArrayList<Medal> medalsList = new ArrayList<>();
        if (promptForBoolean("Does the animal have medals?")) {
            int numberOfMedals = promptForInt("How many medals does the animal have?", "Number of medals must be a positive number.");
            for (int i = 0; i < numberOfMedals; i++) {
                olympics.Type type = promptForMedalType();
                String tournament = promptForString("Enter the name of the tournament for medal " + (i + 1) + ":");
                int year = promptForInt("Enter the year of the tournament for medal " + (i + 1) + ":", "Year must be a valid number.");
                medalsList.add(new Medal(type, tournament, year));
            }
        }
        return medalsList;
    }

    // Helper methods to prompt the user for various inputs
    private boolean promptForBoolean(String message) {
        int response = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

    private int promptForInt(String message, String errorMessage) {
        while (true) {
            String input = JOptionPane.showInputDialog(this, message);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private olympics.Type promptForMedalType() {
        return (olympics.Type) JOptionPane.showInputDialog(this, "Select the medal type:", "Medal Type",
                JOptionPane.QUESTION_MESSAGE, null, olympics.Type.values(), olympics.Type.values()[0]);
    }

    private String promptForString(String message) {
        return JOptionPane.showInputDialog(this, message);
    }


// attributes.loction
    private static class AnimalAttributes {
        String name;
        Gender gender;
        double weight;
        int speed,id, maxEnergy,energyPerMeter;
        ArrayList<Medal> medals;
        Orientation orien;
        Point loction;

        AnimalAttributes(String name, Gender gender, double weight, int speed, ArrayList<Medal> medals, int id,
                         int maxEnergy, int energyPerMeter, Orientation orien, int group) {
            this.name = name;
            this.gender = gender;
            this.weight = weight;
            this.speed = speed;
            this.medals = medals;
            this.id = id;
            this.maxEnergy = maxEnergy;
            this.energyPerMeter = energyPerMeter;
            this.orien = orien;
            this.loction = CompetitionInfo.getPosition(group);
        }
    }

}
