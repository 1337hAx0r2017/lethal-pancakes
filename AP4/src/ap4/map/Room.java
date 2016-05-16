package ap4.map;

import ap4.Etc;
import ap4.Game;
import ap4.RoomObject;
import ap4.graphics.Light;
import ap4.graphics.PointLight;
import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Room {
    
    int x;
    int y;
    
    static int width = 16;
    static int height = 12;
    
    ArrayList<RoomObject> objects;
    
    Tile[][] tiles;
    boolean[] exits = new boolean[4];
    private TextureModelGraphic lwall;
    private TextureModelGraphic rwall;
    private TextureModelGraphic twall;
    private TextureModelGraphic bwall;
    
    public Room()//, int x, int y //??ArrayList<RoomObject> objects,
    {
        tiles = new Tile[width][height];
        
        createExits();
        setupModels();
    }
    
    public Room(boolean[] ex)//, int x, int y //??ArrayList<RoomObject> objects,
    {
        tiles = new Tile[width][height];
        
        exits[0] = ex[0];
        exits[1] = ex[1];
        exits[2] = ex[2];
        exits[3] = ex[3];
        
        createExits();
        setupModels();
    }
    
    public void draw(Graphics g, Game game)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw unique parts of room
        lwall.draw(game.camera, 0, 0, .25f, 1, game.theLight);
        
        // Draw tiles
        for (int x = 0; x < tiles.length; x++)
            for (int y = 0; y < tiles[0].length; y++)
            {
                if (tiles[x][y] != null)
                {
                    tiles[x][y].draw(g, game, game.theLight);
                }
            }
    }
    
    private void setupModels()
    {
        try
        {
            lwall = new TextureModelGraphic(
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
                        ImageIO.read(new URL(Etc.host + "darkwoodwalls.jpg")));
        }
        catch (Exception ex)
        { System.out.println("Exception: " + ex.toString()); }
    }
    
    private void createExits(){
        if (exits[0] == true) {//if north exit
            tiles[width/2][0].exit = true;
            tiles[(width/2)-1][0].exit = true;
        }
        if (exits[1] == true) {//if east exit
            tiles[width-1][height/2].exit = true;
            tiles[width-1][(height/2)-1].exit = true;
        }
        if (exits[2] == true) {//if south exit
            tiles[width/2][height-1].exit = true;
            tiles[(width/2)-1][height-1].exit = true;
        }
        if (exits[3] == true) {//if west exit
            tiles[0][height/2].exit = true;
            tiles[0][(height/2)-1].exit = true;
        }
    }
    
    private boolean checkNorthExit() {
        if (exits[0] == true){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean checkEastExit() {
        if (exits[1] == true) {
            return true;
        }
        else {
            return false;
        }
    }
    private boolean checkSouthExit() {
        if (exits[2] == true) {
            return true;
        }
        else {
            return false;
        }
    }
    private boolean checkWestExit() {
        if (exits[3] == true) {
            return true;
        }
        else {
            return false;
        }
    }
}
