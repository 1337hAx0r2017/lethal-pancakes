package ap4.map;

import ap4.Etc;
import ap4.Game;
import ap4.graphics.Light;
import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Tile {
    
    public static TextureModelGraphic northwall;
    public static TextureModelGraphic southwall;
    public static TextureModelGraphic eastwall;
    public static TextureModelGraphic westwall;
      
    public boolean exit = false;
    public boolean solid = false; //false = can walk through
    
    public Tile()
    {
        solid = false;
        exit = false;
    }
    
    static {
        try {
            // Setup models for use in tiles
            northwall = new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 1, 0, 0, 0),
                        new TextureVertex(1, 1, 0, 1, 0),
                        new TextureVertex(0, 0, 0, 0, 1),
                        new TextureVertex(1, 0, 0, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "darkwoodwalls90r.jpg")));
            southwall = new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 1, 1, 0, 0),
                        new TextureVertex(1, 1, 1, 1, 0),
                        new TextureVertex(0, 0, 1, 0, 1),
                        new TextureVertex(1, 0, 1, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "darkwoodwalls90r.jpg")));
            eastwall = new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(1, 1, 0, 0, 0),
                        new TextureVertex(1, 1, 1, 1, 0),
                        new TextureVertex(1, 0, 0, 0, 1),
                        new TextureVertex(1, 0, 1, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "darkwoodwalls.jpg")));
            westwall = new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 1, 0, 0, 0),
                        new TextureVertex(0, 1, 1, 1, 0),
                        new TextureVertex(0, 0, 0, 0, 1),
                        new TextureVertex(0, 0, 1, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "darkwoodwalls.jpg")));
        }
        catch (IOException ex)
        { Logger.getLogger(Wall.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void draw(Graphics g, Game game, Light light)
    {
        
    }
}