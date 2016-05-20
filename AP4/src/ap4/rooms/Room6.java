package ap4.rooms;

import ap4.map.Room;

public class Room6 extends Room {

    public Room6()
    {
        super();
        exits[0] = true;
        exits[2] = true;
        
        // First make all tiles air tiles (they have floor though) via blankTiles(), called in super class
        // Now add other tiles (rocks, walls, etc)
       
        // Finalize tiles
        finalizeTiles();
    }
}