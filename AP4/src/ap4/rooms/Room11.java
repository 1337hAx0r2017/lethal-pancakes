package ap4.rooms;

import ap4.enemies.Octorok;
import ap4.map.Room;

public class Room11 extends Room {

    public Room11() 
    {
        super();
        exits[1] = true;
        exits[3] = true;
        
        // First make all tiles air tiles (they have floor though) via blankTiles(), called in super class
        // Now add other tiles (rocks, walls, etc)
        objects.add(new Octorok(this.x + 3, this.z + 3));
       
        // Finalize tiles
        finalizeTiles();
    }
}