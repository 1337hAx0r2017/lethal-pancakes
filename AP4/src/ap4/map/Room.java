
package ap4.map;

import ap4.RoomObject;
import java.util.ArrayList;

public class Room {
    
    static int width = 16;
    static int height = 12;
    
    ArrayList<RoomObject> objects;
    
    Tile[][] tiles;
    boolean[] exits = new boolean[4];
    
    Room(boolean north, boolean east, boolean south, boolean west, ArrayList<RoomObject> objects){
        tiles = new Tile[width][height];
        
        this.objects = objects;
        
        exits[0] = north;
        exits[1] = east;
        exits[2] = south;
        exits[3] = west;
        
        createExits();
    }
    void createExits(){
        if(exits[0] == true){//if north exit
            tiles[width/2][0].exit = true;
            tiles[(width/2)-1][0].exit = true;
        }
        if(exits[1] == true){//if east exit
            tiles[width-1][height/2].exit = true;
            tiles[width-1][(height/2)-1].exit = true;
        }
        if(exits[2] == true){//if south exit
            tiles[width/2][height-1].exit = true;
            tiles[(width/2)-1][height-1].exit = true;
        }
        if(exits[3] == true){//if west exit
            tiles[0][height/2].exit = true;
            tiles[0][(height/2)-1].exit = true;
        }
    }
    
    boolean checkNorthExit(){
        if(exits[0] == true){
            return true;
        }
        else{
            return false;
        }
    }
     boolean checkEastExit(){
        if(exits[1] == true){
            return true;
        }
        else{
            return false;
        }
    }
      boolean checkSouthExit(){
        if(exits[2] == true){
            return true;
        }
        else{
            return false;
        }
    }
       boolean checkWestExit(){
        if(exits[3] == true){
            return true;
        }
        else{
            return false;
        }
    }
    
    
}
