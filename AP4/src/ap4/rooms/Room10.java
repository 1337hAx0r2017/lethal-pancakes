package ap4.rooms;

import ap4.map.Room;

public class Room10 extends Room implements StartingRoom {

    public Room10() 
    {
        super();
        exits[2] = true;
        exits[3] = true;
        
        // First make all tiles air tiles (they have floor though) via blankTiles(), called in super class
        // Now add other tiles (rocks, walls, etc)
       
        // Finalize tiles
        finalizeTiles();
    }
}