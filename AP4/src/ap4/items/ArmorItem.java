/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.items;

import ap4.Etc;
import ap4.InvItem;
import ap4.Player;
import java.awt.Graphics;
import java.net.URL;
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
        catch (Exception ex)
        { System.out.println(ex); }
    }
    
    public void Use(Player o)
    {
    }
    
    public void draw(Graphics g)
    {
    }
}
