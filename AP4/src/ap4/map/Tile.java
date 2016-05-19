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

public abstract class Tile {
    
    public static final int TILE_FLOOR = 0;
    public static final int TILE_WALL = 2;
    public static final int TILE_PIT = 2;
    
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
        if (type == TILE_WALL)
            solid = true;
        if (type == TILE_PIT)
            isPit = true;
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
                    ImageIO.read(new URL(Etc.host + "darkwoodwalls90r.jpg")));
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
                    ImageIO.read(new URL(Etc.host + "darkwoodwalls90r.jpg")));
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
    
    public abstract void setupWalls(Tile[][] t);
    public abstract void draw(Game game, Room r);
}