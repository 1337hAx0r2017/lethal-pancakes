package ap4.map;

import ap4.Etc;
import ap4.Game;
import ap4.graphics.Light;
import ap4.graphics.Matrix;
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
    public static TextureModelGraphic cap;
    public static TextureModelGraphic floor;
      
    public boolean exit = false;
    public boolean solid = false; //false = can walk through
    public boolean isPit = false;
    public int type = 0;
    
    public boolean drawnorth = false;
    public boolean drawsouth = false;
    public boolean draweast = false;
    public boolean drawwest = false;
    
    public float x;
    public float y;
    public float z;
    
    public Tile()
    {
        // Default to air
        type = 0;
        solid = false;
        exit = false;
    }
    
    public Tile(int type)
    {
        exit = false;
        this.type = type;
        if (type == 1)
            solid = true;
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
                    ImageIO.read(new URL(Etc.host + "darkwoodwalls.jpg")));
            southwall = new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(1, 1, 1, 0, 0),
                        new TextureVertex(0, 1, 1, 1, 0),
                        new TextureVertex(1, 0, 1, 0, 1),
                        new TextureVertex(0, 0, 1, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "darkwoodwalls.jpg")));
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
                        new TextureVertex(0, 1, 1, 0, 0),
                        new TextureVertex(0, 1, 0, 1, 0),
                        new TextureVertex(0, 0, 1, 0, 1),
                        new TextureVertex(0, 0, 0, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "darkwoodwalls.jpg")));
            cap = new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 1, 0, 0, 0),
                        new TextureVertex(1, 1, 0, 1, 0),
                        new TextureVertex(0, 1, 1, 0, 1),
                        new TextureVertex(1, 1, 1, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "darkwoodwalls.jpg")));
            floor = new TextureModelGraphic(
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
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "darkstonerough128.png")));
        }
        catch (IOException ex)
        { Logger.getLogger(Wall.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void setupWalls(Tile[][] t)
    {
        for (int r = 0; r < t.length; r++)
            for (int c = 0; c < t[0].length; c++)
            {
                if (t[r][c] == this)
                {
                    if (r == 0)
                        drawnorth = true;
                    else if (r == t.length - 1)
                        drawsouth = true;
                    
                    if (c == 0)
                        drawwest = true;
                    else if (c == t[0].length - 1)
                        draweast = true;
                    
                    if (r > 0)
                        if (t[r - 1][c].type == 1)
                            drawnorth = true;
                    if (r < t.length - 1)
                        if (t[r + 1][c].type == 1)
                            drawsouth = true;
                    
                    if (c > 0)
                        if (t[r][c - 1].type == 1)
                            drawwest = true;
                    if (c < t.length - 1)
                        if (t[r][c + 1].type == 1)
                            draweast = true;
                }
            }
    }
    
    public void draw(Game game, Room r)
    {
        if (drawnorth)
            northwall.draw(game.camera, r.x + x, r.y + y, r.z + z, 1, game.theLight);
        if (drawsouth)
            southwall.draw(game.camera, r.x + x, r.y + y, r.z + z, 1, game.theLight);
        if (draweast)
            eastwall.draw(game.camera, r.x + x, r.y + y, r.z + z, 1, game.theLight);
        if (drawwest)
            westwall.draw(game.camera, r.x + x, r.y + y, r.z + z, 1, game.theLight);
        if (type == 1)
            cap.draw(game.camera, r.x + x, r.y + y, r.z + z, 1, game.theLight);
        if (!isPit)
            floor.draw(game.camera, r.x + x, r.y + y, r.z + z, 1, game.theLight);
    }
}