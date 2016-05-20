package ap4.items;

import ap4.Etc;
import ap4.InvItem;
import ap4.Player;
import gui.Inventory;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class WoodenSword extends InvItem {
    
    public WoodenSword()
    { 
        try
        {
            visual = ImageIO.read(new URL(Etc.host + "woodensword.png"));
        }
        catch (IOException ex)
        { Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void use(Player o)
    {
        
    }
    
    public void draw(Graphics g)
    {
        
    }
}