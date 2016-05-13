package ap4.map;

import ap4.Etc;
import ap4.Game;
import ap4.graphics.Light;
import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;

abstract public class Tile {
      
    public boolean exit = false;
    public boolean solid = false; //false = can walk through

    BufferedImage texture;
    
    
    public Tile()
    {
        solid = false;
        exit = false;
    }
    
    abstract public void draw(Graphics g, Game game, Light light);
    
    
    
    
}