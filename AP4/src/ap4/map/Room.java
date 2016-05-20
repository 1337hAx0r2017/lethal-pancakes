package ap4.map;

import ap4.Game;
import ap4.RoomObject;
import ap4.graphics.Matrix;
import static ap4.map.Tile.cap;
import ap4.models.DoorModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Room {
    
    public float x = 0;
    public float y = 0;
    public float z = 0;
    
    public static int width = 16;
    public static int height = 12;
    public boolean isTheStartRoom = false;
    
    static DoorModel doormodel;
    static
    {
            doormodel = new DoorModel();//ImageIO.read(new URL(Etc.host + "darkwoodwalls90r.jpg")));
    }
    
    public ArrayList<RoomObject> objects;
    
    public Tile[][] tiles;
    public boolean tilesFinalized = false;
    public boolean[] exits = new boolean[4];
    
    public Room()//, int x, int y //??ArrayList<RoomObject> objects,
    {
        tiles = new Tile[height][width];
        
        blankTiles();
    }
    
    public Room(boolean[] ex)//, int x, int y //??ArrayList<RoomObject> objects,
    {
        tiles = new Tile[width][height];
        
        exits[0] = ex[0];
        exits[1] = ex[1];
        exits[2] = ex[2];
        exits[3] = ex[3];
        
        blankTiles();
    }
    
    public Room clone()
    {
        try {
            return this.getClass().newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void draw(Game game)
    {
        //Graphics2D g2d = (Graphics2D) g;
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (tilesFinalized)
            for (int r = 0; r < tiles.length; r++)
                for (int c = 0; c < tiles[0].length; c++)
                    tiles[r][c].draw(game, this);
        if(exits[0])
            doormodel.draw(game.camera, Matrix.createTranslation(width / 2 + x, 0 + y, -1 + z), game.theLight);
        if(exits[1])
            doormodel.draw(game.camera, Matrix.multiply(Matrix.createRotationY(-Math.PI/2), Matrix.createTranslation(width + x + 1, 0 + y, height / 2 + z)), game.theLight);
        if(exits[2])
            doormodel.draw(game.camera, Matrix.multiply(Matrix.createRotationY(Math.PI), Matrix.createTranslation(width / 2 + x, 0 + y, height + 1 + z)), game.theLight);
        if(exits[3])
            doormodel.draw(game.camera, Matrix.multiply(Matrix.createRotationY(Math.PI/2), Matrix.createTranslation(-1 + x, 0 + y, height / 2 + z)), game.theLight);

        for(int x = -1; x <= width; x++)
        {
            cap.draw(game.camera, this.x + x, this.y, this.z - 1, 1, game.theLight);
            cap.draw(game.camera, this.x + x, this.y, this.z + height , 1, game.theLight);
        }
        for(int z = 0; z < height; z++)
        {
            cap.draw(game.camera, this.x - 1, this.y, this.z + z, 1, game.theLight);
            cap.draw(game.camera, this.x + width, this.y, this.z + z, 1, game.theLight);
        }
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
        //the exits are actually OUTSIDE the room's array.
        if (exits[0] == true) {//if north exit
            //tiles[width/2][0].exit = true;
            //tiles[(width/2)-1][0].exit = true;
            if(tiles[0][width/2].type == Tile.TILE_FLOOR)
                ((Floor)tiles[0][width/2]).drawnorth = false;
            if(tiles[0][width/2-1].type == Tile.TILE_FLOOR)
                ((Floor)tiles[0][width/2-1]).drawnorth = false;
        }
        if (exits[1] == true) {//if east exit
            //tiles[width-1][height/2].exit = true;
            //tiles[width-1][(height/2)-1].exit = true;
            if(tiles[height/2][width-1].type == Tile.TILE_FLOOR)
                ((Floor)tiles[height/2][width-1]).draweast = false;
            if(tiles[height/2-1][width-1].type == Tile.TILE_FLOOR)
                ((Floor)tiles[height/2-1][width-1]).draweast = false;
        }
        if (exits[2] == true) {//if south exit
            //tiles[width/2][height-1].exit = true;
            //tiles[(width/2)-1][height-1].exit = true;
            if(tiles[height-1][width/2].type == Tile.TILE_FLOOR)
                ((Floor)tiles[height-1][width/2]).drawsouth = false;
            if(tiles[height-1][width/2-1].type == Tile.TILE_FLOOR)
                ((Floor)tiles[height-1][width/2-1]).drawsouth = false;
        }
        if (exits[3] == true) {//if west exit
            //tiles[0][height/2].exit = true;
            //tiles[0][(height/2)-1].exit = true;
            if(tiles[height/2][0].type == Tile.TILE_FLOOR)
                ((Floor)tiles[height/2][0]).drawwest = false;
            if(tiles[height/2-1][0].type == Tile.TILE_FLOOR)
                ((Floor)tiles[height/2-1][0]).drawwest = false;
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
                tiles[r][c] = new Floor();
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
        createExits();
        
        
        
        tilesFinalized = true;
    }
}
