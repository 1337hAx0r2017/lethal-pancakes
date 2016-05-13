package ap4;
import ap4.graphics.Graphic;
import java.awt.Graphics;
public abstract class InvItem {
 
    public Graphic graphic;
    public abstract void Use(Player o);
    public abstract void draw(Graphics g);
}