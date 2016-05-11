package ap4.map;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    
    Room[][] rooms;
    Random r = new Random();
    
    public Map(int nRooms) {
        ArrayList<Room> rooms = new ArrayList<Room>();//temporary storage for rooms
        
        int lX; //largest x direction room
        int lY; //largest y direction room
        
        for(int i = 0; i < nRooms; i++){
            if(i == 0){
                rooms.add(new Room(false, false, false, false, 5,5));
            }
        }
    }
}