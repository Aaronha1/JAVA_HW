package graphics;
import animals.*;
import mobility.Point;
import olympics.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class AddAnimalDialog extends JDialog {

    private String[] animalsType;
    int group,runner;

    public AddAnimalDialog(JFrame owner,int group,int runner) {
        super(owner, "Add Animal to Competition", true);
        this.group = group;
        this.runner = runner;

        animalsType = CompetitionInfo.listAnimals();

        setLayout(new GridLayout(2, 2));
        JButton[] button = new JButton[animalsType.length];

        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton(animalsType[i]);
            String aType = animalsType[i];
            add(button[i]);
            button[i].addActionListener(e -> addAnimal(aType));
        }


        setSize(400, 300);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    private AnimalAttributes gatherCommonAttributes() {
        String name = promptForString("Enter name:", "Name cannot be empty.");
        Gender gender = promptForGender();
        double weight = promptForDouble("Enter weight:", "Weight must be a positive number.");
        int speed = promptForInt("Enter speed:", "Speed must be a positive number.");
        int id = promptForInt("Enter ID:", "ID must be a positive number.");
        int maxEnergy = promptForInt("Enter max energy:", "Max energy must be a positive number.");
        int energyPerMeter = promptForInt("Enter energy per meter:", "Energy per meter must be a positive number.");
        ArrayList<Medal> medals = gatherMedals();

        return new AnimalAttributes(name, gender, weight, speed, medals, id, maxEnergy, energyPerMeter, Orientation.EAST);
    }
    private String promptForString(String message, String errorMessage) {
        String input = null;
        while (input == null || input.trim().isEmpty()) {
            input = JOptionPane.showInputDialog(this, message);
            if (input == null || input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return input;
    }
    private double promptForDiveDepth(String message, String errorMessage) {
        double value = Double.NaN;
        boolean validInput = false;

        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(this, message);
                if (input == null) {
                    return Double.NaN; // Handle cancel scenario
                }
                value = Double.parseDouble(input);
                if (value > 0 || value < WaterAnimal.MAX_DIVE) {
                    JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return value;
    }
    private double promptForAltitudeOfFlight(String message, String errorMessage) {
        double value = Double.NaN;
        boolean validInput = false;

        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(this, message);
                if (input == null) {
                    return Double.NaN; // Handle cancel scenario
                }
                value = Double.parseDouble(input);
                if (value < 0 || value > Eagle.getMaxALTITUDE()) {
                    JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return value;
    }
    private int promptForInt(String message, String errorMessage) {
        int value = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(this, message);
                if (input == null) {
                    return -1; // Handle cancel scenario
                }
                value = Integer.parseInt(input);
                if (value <= 0) {
                    JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return value;
    }
    private double promptForDouble(String message, String errorMessage) {
        double value = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(this, message);
                if (input == null) {
                    return -1; // Handle cancel scenario
                }
                value = Double.parseDouble(input);
                if (value <= 0) {
                    JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return value;
    }
    private Gender promptForGender() {
        Gender[] options = {Gender.MALE, Gender.FEMALE, Gender.HERMAPHRODITE};
        return (Gender) JOptionPane.showInputDialog(this, "Select gender:", "Gender",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }
    private WaterType promptForWaterType() {
        WaterType[] options = {WaterType.SEA, WaterType.SWEET};
        return (WaterType) JOptionPane.showInputDialog(this, "Select Water Type:", "Water Type",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }
    private Poisonous promptForPoisonous() {
        Poisonous[] options = {Poisonous.HIGH, Poisonous.MEDIUM,Poisonous.LOW};
        return (Poisonous) JOptionPane.showInputDialog(this, "Select Poisonous:", "Poisonous",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }
    private boolean promptForBoolean(String message) {
        int option = JOptionPane.showConfirmDialog(this, message, "Boolean Input", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }
    private olympics.Type promptForMedalType() {
        olympics.Type[] options = {olympics.Type.GOLD, olympics.Type.SILVER, olympics.Type.BRONZE};
        return (olympics.Type) JOptionPane.showInputDialog(this, "Select medal type:", "Medal Type",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }
    private ArrayList<Medal> gatherMedals() {
        boolean hasMedals = promptForBoolean("Does the animal have medals?");
        ArrayList<Medal> medalsList = new ArrayList<>();

        if (hasMedals) {
            int numberOfMedals = promptForInt("How many medals does the animal have?", "Number of medals must be a positive number.");

            for (int i = 0; i < numberOfMedals; i++) {
                olympics.Type type = promptForMedalType();
                String tournament = promptForString("Enter the name of the tournament for medal " + (i + 1) + ":", "Tournament name cannot be empty.");
                int year = promptForInt("Enter the year of the tournament for medal " + (i + 1) + ":", "Year must be a positive number.");
                medalsList.add(new Medal(type, tournament, year));
            }
        }

        return medalsList;
    }

    private void addAnimal(String type) {

        AnimalAttributes attributes = gatherCommonAttributes();
        Animal animal = null;

        switch (type) {
            case "Alligator":
                double diveDpet =promptForDiveDepth("Enter div dept for Alligator:", "dive dpet must be a negative number between 0 to "+
                        WaterAnimal.MAX_DIVE);
                int noLegs = promptForInt("Enter legs number:", "legs number must be a positive number..");
                String areaOfLiving = promptForString("Enter area of living:", "area of living cannot be empty.");
                animal = new Alligator(attributes.name, attributes.gender, attributes.weight,attributes.speed,attributes.loction,
                        attributes.medals, attributes.id,attributes.maxEnergy,attributes.energyPerMeter,
                        attributes.orien,diveDpet,noLegs,areaOfLiving);
                break;
            case "Cat":
                int noLegsCat = promptForInt("Enter legs number:", "legs number must be a positive number.");
                boolean castrated = promptForBoolean("Castrated?");
                animal = new Cat(attributes.name, attributes.gender,attributes.weight,attributes.speed,attributes.loction,attributes.medals,
                        attributes.id,attributes.maxEnergy,attributes.energyPerMeter,attributes.orien,
                        noLegsCat,castrated);
                break;
            case "Dog":
                int noLegsDog = promptForInt("Enter legs number:", "legs number must be a positive number.");
                String breed = promptForString("Enter breed type:", "breed type cannot be empty.");
                animal = new Dog(attributes.name,attributes.gender,attributes.weight,attributes.speed,attributes.loction,attributes.medals,
                        attributes.id,attributes.maxEnergy,attributes.energyPerMeter,attributes.orien,
                        noLegsDog,breed);
                break;
            case "Dolphin":
                double diveDpetDolphin = promptForDiveDepth("Enter div dept for Dolphin:", "dive dpet must be a negative number between 0 to "
                        + WaterAnimal.MAX_DIVE);
                WaterType waterType = promptForWaterType();
                animal = new Dolphin(attributes.name,attributes.gender,attributes.weight,attributes.speed,attributes.loction,attributes.medals,
                        attributes.id,attributes.maxEnergy,attributes.energyPerMeter,attributes.orien,
                        diveDpetDolphin, waterType);
                break;
            case "Eagle":
                double altitudeOfFlight = promptForAltitudeOfFlight("Enter altitudeOfFlight:",
                        "altitudeOfFlight must be a positive number. max of" + Eagle.getMaxALTITUDE());
                double wingSpan = promptForDouble("Enter wing span:",
                        "wing span must be a positive number.");
                animal = new Eagle(attributes.name,attributes.gender,attributes.weight,attributes.speed,attributes.loction,attributes.medals,
                        attributes.id,attributes.maxEnergy,attributes.energyPerMeter,attributes.orien,wingSpan,
                        altitudeOfFlight);
                break;
            case "Pigeon":
                String family = promptForString("Enter family type:", "family type cannot be empty.");
                double wingSpanPigeon = promptForDouble("Enter wing span:",
                        "wing span must be a positive number.");
                animal = new Pigeon(attributes.name,attributes.gender,attributes.weight,attributes.speed,attributes.loction,attributes.medals,
                        attributes.id,attributes.maxEnergy,attributes.energyPerMeter,attributes.orien,wingSpanPigeon,
                        family);
                break;
            case "Snake":
                double length = promptForDouble("Enter length:", "length must be a positive number.");
                Poisonous poisonous = promptForPoisonous();
                animal = new Snake(attributes.name,attributes.gender,attributes.weight,attributes.speed,attributes.loction,attributes.medals,
                        attributes.id,attributes.maxEnergy,attributes.energyPerMeter,attributes.orien,length,
                        poisonous);
                break;
            case "Whale":
                double diveDpetWhale = promptForDiveDepth("Enter div dept for Whale:", "dive dpet must be a negative number between 0 to " + WaterAnimal.MAX_DIVE);
                String foodType = promptForString("Enter food type:", "food type cannot be empty.");
                animal = new Whale(attributes.name,attributes.gender,attributes.weight,attributes.speed,attributes.loction,attributes.medals,
                        attributes.id,attributes.maxEnergy,attributes.energyPerMeter,attributes.orien,
                        diveDpetWhale,foodType);
                break;
        }

        if (animal != null) {
            CompetitionInfo.addToArr(animal,type,1,1);
            dispose();
        }

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
                         int maxEnergy, int energyPerMeter, Orientation orien) {
            this.name = name;
            this.gender = gender;
            this.weight = weight;
            this.speed = speed;
            this.medals = medals;
            this.id = id;
            this.maxEnergy = maxEnergy;
            this.energyPerMeter = energyPerMeter;
            this.orien = orien;
            this.loction = CompetitionInfo.getPosition(0);
        }
    }

}
