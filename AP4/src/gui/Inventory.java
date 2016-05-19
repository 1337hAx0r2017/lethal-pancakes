package gui;

import ap4.Etc;
import ap4.InvItem;
import ap4.items.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Inventory {

    public InvItem weapon = null;
    public InvItem thing = null;
    
    private BufferedImage slots;
    
    public Inventory()
    {
        weapon = new MasterSword();
        thing = new Potion();
        
        try
        {
            slots = ImageIO.read(new URL(Etc.host + "slots.png"));
        }
        catch (IOException ex)
        { Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.white);
        
        g2d.drawImage(slots, null, Window.sx - 142, Window.sy - 104);
        if (weapon != null)
            g2d.drawImage(weapon.visual, null, Window.sx - 133, Window.sy - 95);
        if (thing != null)
            g2d.drawImage(thing.visual, null, Window.sx - 71, Window.sy - 95);
    }
}