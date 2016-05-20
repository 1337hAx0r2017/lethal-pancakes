/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author kardzhalao.2017
 */
public class ArmorItem extends InvItem{
  
    ArmorItem()
    { 
        try
        {
            visual = ImageIO.read(new URL(Etc.host + "zeldashield.png"));
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
