package graphics;

public class Image {
    private static final String DIR = "graphics2/";
    private static final String EX = ".png";

    public static String file(String name) {
        return DIR + name + EX;
    }
}
