package graphics;

import java.awt.*;

public interface IDrawable {
    public final static String PICTURE_PATH = "C:/Users/win10/IdeaProjects/JAVA_HW/src/graphics/graphics2/";
    public void loadImages(String nm);
    public void drewObject(Graphics g);
    public static String getPng(String name) {
        return PICTURE_PATH + name + ".png";
    }
}
