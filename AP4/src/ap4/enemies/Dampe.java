/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.enemies;

import ap4.Controller;
import ap4.Enemy;
import ap4.Etc;
import ap4.Game;
import gui.Inventory;
import gui.Window;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kardzhalao.2017
 */
public class Dampe extends Enemy{
    
    public Dampe(float x, float y, float movespeed)
    {
        super(x, y, movespeed);
        try
        {
            visual = ImageIO.read(new URL(Etc.host + "dampe.png"));
        }
        catch (IOException ex)
        { Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex); }
    }
    @Override
    public void draw(Graphics g) {
        //import image of an octorok
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(visual, null, Window.sx - 133, Window.sy - 95);
    }

    @Override
    public void update(Game game, float time) {
       // will be specific to this class' movement
    }

    @Override
    public void move(Game game, Controller controls) {
        //movement specific to dampe
    }
}