package ap4.map;

import ap4.Game;
import ap4.graphics.Light;
import java.awt.Graphics;

abstract public class Tile {
      
    public boolean exit = false;
    public boolean solid = false; //false = can walk through
    
    public Tile()
    {
        solid = false;
        exit = false;
    }
    
    abstract public void draw(Graphics g, Game game, Light light);
}