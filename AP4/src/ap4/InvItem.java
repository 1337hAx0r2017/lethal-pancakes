package ap4;
import ap4.graphics.Graphic;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
public abstract class InvItem {
 
    public BufferedImage visual;
    public abstract void use(Player o);
    public abstract void draw(Graphics g);
}