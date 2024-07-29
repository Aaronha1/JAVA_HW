package animals;

public interface IWaterAnimal {
    public static final int MAX_DIVE = -800;
    public boolean Dive(double dive);
    public static double getMaxDive(){
        return MAX_DIVE;
    }
}
