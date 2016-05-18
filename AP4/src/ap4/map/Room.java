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
    
    public float x = 0;
    public float y = 0;
    public float z = 0;
    
    public static int width = 16;
    public static int height = 12;
    
    public ArrayList<RoomObject> objects;
    
    public Tile[][] tiles;
    public boolean tilesFinalized = false;
    public boolean[] exits = new boolean[4];
    
    public Room()//, int x, int y //??ArrayList<RoomObject> objects,
    {
        tiles = new Tile[height][width];
        
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
        
        if (tilesFinalized)
            for (int r = 0; r < tiles.length; r++)
                for (int c = 0; c < tiles[0].length; c++)
                    tiles[r][c].draw(g, game, this);
    }
    
    private void setupModels()
    {
        //go through each tile in the room
            //if the tile is a floor,
                //put down the floor
            //if the tile to the NORTH is a wall (whether a wall tile or if the space above is out of the room,
                //put down the north-side (south-facing) wall
            //same with south, east, west
            //if this tile IS a wall, then put the top on it
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
    
    //////////////////////////////////// Tile stuff //////
    public void blankTiles()
    {
        for (int r = 0; r < tiles.length; r++)
            for (int c = 0; c < tiles[0].length; c++)
                tiles[r][c] = new Tile();
    }
    
    public void finalizeTiles()
    {
        for (int r = 0; r < tiles.length; r++)
            for (int c = 0; c < tiles[0].length; c++)
            {
                tiles[r][c].x = c;
                tiles[r][c].y = 0;
                tiles[r][c].z = r;
                tiles[r][c].setupWalls(tiles);
            }
        
        tilesFinalized = true;
    }
}
