package ap4.rooms;

import ap4.enemies.Octorok;
import ap4.map.Room;

public class Room13 extends Room implements StartingRoom {

    public Room13() 
    {
        super();
        exits[0] = true;
        exits[2] = true;
        exits[3] = true;
        
        // First make all tiles air tiles (they have floor though) via blankTiles(), called in super class
        // Now add other tiles (rocks, walls, etc)
        objects.add(new Octorok(3, 3));
       
        // Finalize tiles
        finalizeTiles();
    }
}