package ap4.map;

import ap4.graphics.Light;
import java.awt.Graphics;

public class Tile {
      
    public boolean exit = false;
    public boolean solid = false; //false = can walk through
    public int type = 0;
    /** types
     * 0: air
    */
    
    public Tile()
    {
        solid = false;
        exit = false;
        type = 0;
    }
    
    public void draw(Graphics g, Light light)
    {
        
    }
}
