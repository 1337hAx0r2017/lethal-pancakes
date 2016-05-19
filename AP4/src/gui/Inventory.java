package gui;

import ap4.Etc;
import ap4.InvItem;
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
        
        g2d.drawImage(slots, null, 658, 496);
    }
}