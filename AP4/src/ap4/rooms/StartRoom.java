package ap4.rooms;

import ap4.map.Room;

public class StartRoom extends Room {
    //this will be the starting room of the map, guarantee a south exit(to leave map if they win)
            //also add the north west east exits, make starting room most decision based
    public StartRoom()
    {
       super();
       exits[0] = true; //north
       exits[1] = true; //east
       exits[2] = true; //south
       exits[3] = true; //west
       
       // First make all tiles air tiles (they have floor though) via blankTiles(), called in super class
       // Now add other tiles (rocks, walls, etc)
       
       // Finalize tiles
       finalizeTiles();
    }
}