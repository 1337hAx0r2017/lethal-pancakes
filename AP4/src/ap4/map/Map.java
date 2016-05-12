package ap4.map;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    
    Room[][] rooms;
    Random r = new Random();
    
    public Map(int nRooms, int sizeX, int sizeY) {
        
        rooms = new Room[sizeX][sizeY];
        
        int cX = 0;//prevY
        int cY = 0;//prevX
        
        for(int i = 0; i < nRooms; i++)
        {
            
            if (i == 0){
                rooms[sizeX/2][sizeY/2] =  new Room(new boolean[] {false, false, false, false});
                cX = sizeX/2;
                cY = sizeY/2;
            }  
            else
            {
                int randInt = r.nextInt(4);//random 0-3 for directions
                
                if(randInt == 0){//Add room above
                    rooms[cX][cY].exits[0] = true;//add exit to prev room
                    rooms[cX][cY-1] = new Room(new boolean[] {false,false,true,false});//add sout exit to new room
                }
                else if(randInt == 1){//add room to the right
                    rooms[cX][cY].exits[1] = true;
                    rooms[cX+1][cY] = new Room(new boolean[] {false,false,false,true});
                }
                else if(randInt == 2){//add room below
                    rooms[cX][cY].exits[2] = true;
                    rooms[cX][cY-1] = new Room(new boolean[] {true,false,false,false});
                }
                else if(randInt == 3){//add room to the left
                    
                }
                
            }
        }
    }
}