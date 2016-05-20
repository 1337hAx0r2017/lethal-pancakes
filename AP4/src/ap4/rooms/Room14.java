package ap4.rooms;

import ap4.map.Room;

public class Room14 extends Room {

    public Room14() 
    {
        super();
        exits[1] = true;
        exits[2] = true;
        exits[3] = true;
        
        // First make all tiles air tiles (they have floor though) via blankTiles(), called in super class
        // Now add other tiles (rocks, walls, etc)
       
        // Finalize tiles
        finalizeTiles();
    }
}