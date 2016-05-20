package ap4.rooms;

import ap4.map.Room;

public class Room3 extends Room {

    public Room3()
    {
        super();
        exits[1] = true;
        
        // First make all tiles air tiles (they have floor though) via blankTiles(), called in super class
        // Now add other tiles (rocks, walls, etc)
       
        // Finalize tiles
        finalizeTiles();
    }
}