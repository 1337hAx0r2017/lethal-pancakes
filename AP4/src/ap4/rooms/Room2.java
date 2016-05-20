package ap4.rooms;

import ap4.map.Room;

public class Room2 extends Room implements StartingRoom {

    public Room2()
    {
        super();
        exits[0] = true;
        
        // First make all tiles air tiles (they have floor though) via blankTiles(), called in super class
        // Now add other tiles (rocks, walls, etc)
       
        // Finalize tiles
        finalizeTiles();
    }
}