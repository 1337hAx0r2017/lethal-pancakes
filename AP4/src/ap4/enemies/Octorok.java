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

public class Octorok extends Enemy{

    public Octorok(float x, float y, float movespeed)
    {
        super(x, y, movespeed);
         try
        {
            visual = ImageIO.read(new URL(Etc.host + "zeldashield.png"));
        }
        catch (IOException ex)
        { Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    @Override
    public void draw(Game game) {
        
    }
    @Override
    public void update(Game game, float time) {
       // specific to this class' move method
    }

    @Override
    public void move(Game game) {
       //specific movement
    }
}