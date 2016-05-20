package ap4.rooms;

import ap4.map.Room;

public class Room4 extends Room {

    public Room4()
    {
        super();
        exits[2] = true;
        
        // First make all tiles air tiles (they have floor though) via blankTiles(), called in super class
        // Now add other tiles (rocks, walls, etc)
       
        // Finalize tiles
        finalizeTiles();
    }
}