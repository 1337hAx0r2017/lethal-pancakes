package ap4.items;

import ap4.Etc;
import ap4.InvItem;
import ap4.Player;
import java.awt.Graphics;
import java.net.URL;
import javax.imageio.ImageIO;

public class MasterSword extends InvItem {
    
    public MasterSword()
    { 
        try
        {
            visual = ImageIO.read(new URL(Etc.host + "mastersword.png"));
        }
        catch (Exception ex)
        { System.out.println(ex); }
    }
    
    public void use(Player o)
    {
        
    }
    
    public void draw(Graphics g)
    {
        
    }
}