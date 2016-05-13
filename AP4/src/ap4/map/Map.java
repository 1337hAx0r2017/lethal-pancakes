package ap4.map;

import ap4.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Map {

    static Random random;
    static ArrayList<Class> roomTypes;
        
    static
    {
        random = new Random();
        roomTypes = new ArrayList<Class>();
        
        //roomTypes.add(Room1.class);
        //roomTypes.add(Room2.class);
        //roomTypes.add(Room3.class);
        //        etc
    }
    
    static Room getRoom()
    {
        if (roomTypes.size() > 0)
        {
            try {
                return (Room)roomTypes.get(random.nextInt(roomTypes.size())).newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    Room[][] rooms;
    Random r = new Random();
    
    public Map(int nRooms, int sizeX, int sizeY) {
        
        rooms = new Room[sizeX][sizeY];
        
        int cX = 0;//prevY
        int cY = 0;//prevX
        
        for(int i = 0; i < nRooms; i++)
        {
            if (i == 0) {
                rooms[sizeX/2][sizeY/2] =  new Room(new boolean[] {false, false, false, false});
                cX = sizeX/2;
                cY = sizeY/2;
            }  
            else
            {

                if (i == 0){
                    rooms[sizeX/2][sizeY/2] =  getRoom();
                    cX = sizeX/2;
                    cY = sizeY/2;
                }  
                else
                {
                    int randInt = r.nextInt(4);//random 0-3 for directions

                    if(randInt == 0 &&  cY > 0){//Add room above

                        rooms[cX][cY].exits[0] = true;//add exit to prev room
                        Room room = getRoom();
                        int count = 0;
                        while(room.exits[2] == false  && count < 5){
                            room = getRoom();
                            count++;
                        }
                        rooms[cX][cY-1] = room;
                        cY = cY-1;
                    }
                    else if(randInt == 1 &&  cX < rooms.length-1){//add room to the right
                        rooms[cX][cY].exits[1] = true;
                        Room room = getRoom();
                        int count = 0;
                        while(room.exits[3] == false  && count < 5){
                            room = getRoom();
                            count++;
                        }
                        rooms[cX+1][cY] = room;
                        cX = cX+1;
                    }
                    else if(randInt == 2 &&  cY < rooms[0].length-1){//add room below
                        rooms[cX][cY].exits[2] = true;
                        Room room = getRoom();
                        int count = 0;
                        while(room.exits[0] == false  && count < 5){
                            room = getRoom();
                            count++;
                        }
                        rooms[cX][cY+1] = room;
                        cY = cY+1;
                    }
                    else if(randInt == 3 &&  cX > 0){//add room to the left
                        rooms[cX][cY].exits[3] = true;
                        Room room = getRoom();
                        int count = 0;
                        while(room.exits[1] == false && count < 5){
                            room = getRoom();
                            count++;
                        }
                        rooms[cX-1][cY] = room;
                        cX = cX-1;
                    }
                    else{
                        i--;
                    }

                }
            }
        
    }while(verify() == false);//continue making maps until you find a good one
        
        
}
    
    
    boolean verify(){
        //checking all exits
        for(int x = 1; x < rooms.length-1; x++){
            for(int y = 1; y < rooms[0].length-1;y++){
                
                if((rooms[x][y] != null && rooms[x+1][y] != null) && (rooms[x][y].exits[1] != rooms[x+1][y].exits[3])){
                    return false;
                    //bad map
                }
                if((rooms[x][y] != null && rooms[x-1][y] != null) && (rooms[x][y].exits[3] != rooms[x-1][y].exits[1])){
                    return false;
                }
                if((rooms[x][y] != null && rooms[x][y+1] != null) && (rooms[x][y].exits[2] != rooms[x][y+1].exits[0])){
                    return false;
                }
                if((rooms[x][y] != null && rooms[x][y-1] != null) && (rooms[x][y].exits[0] != rooms[x][y-1].exits[2])){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void draw(Graphics g, Game game)
    {
        for (int x = 0; x < rooms.length; x++)
            for (int y = 0; y < rooms[0].length; y++)
            {
                if (rooms[x][y] != null)
                {
                    rooms[x][y].draw(g, game);
                }
            }
    }
}

