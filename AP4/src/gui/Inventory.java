package gui;

import ap4.InvItem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Inventory {

    public InvItem weapon = null;
    public InvItem thing = null;
    
    public Inventory()
    {
        
    }
    
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.white);
        g2d.drawString("bloop", 300, 300);
    }
}