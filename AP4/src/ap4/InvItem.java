package ap4;
import ap4.graphics.Graphic;
import ap4.graphics.TextureModelGraphic;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public abstract class InvItem {
 
    public BufferedImage visual;
    public abstract void use(Player o);
    public abstract void draw(Graphics g);
    public ArrayList<TextureModelGraphic> animFrames = new ArrayList<>();
}