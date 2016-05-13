package ap4.map;

import ap4.Etc;
import ap4.Game;
import ap4.graphics.Light;
import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import java.awt.Graphics;
import java.net.URL;
import javax.imageio.ImageIO;

public class Tile {
      
    public boolean exit = false;
    public boolean solid = false; //false = can walk through
    public int type = 0;
    /** types
     * 0: air
    */
    
    private TextureModelGraphic bottom;
    
    public Tile()
    {
        solid = false;
        exit = false;
        type = 0;
        
        setupModels();
    }
    
    public void draw(Graphics g, Game game, Light light)
    {
        bottom.draw(game.camera, 0, 0, .25f, 1, light);
    }
    
    private void setupModels()
    {
        try
        {
            bottom = new TextureModelGraphic(
                        new TextureVertex[]
                        {
                            new TextureVertex(0, 0, 0, 0, 0),
                            new TextureVertex(1, 0, 0, 1, 0),
                            new TextureVertex(0, 0, 1, 0, 1),
                            new TextureVertex(1, 0, 1, 1, 1),
                        },
                        new short[]
                        {
                            0, 1, 2, 
                            2, 1, 3
                        },
                        ImageIO.read(new URL(Etc.host + "darkstonerough128.png")));
        }
        catch (Exception ex)
        { System.out.println("Exception: " + ex.toString()); }
    }
}