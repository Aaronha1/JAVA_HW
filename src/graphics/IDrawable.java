package graphics;

import java.awt.*;

public interface IDrawable {
    public final static String PICTURE_PATH = "/src/graphics/graphics2";
    public void loadImages(String nm);
    public void drewObject(Graphics g);
}
