package ap4.map;

import ap4.Game;
import ap4.rooms.*;
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
    static ArrayList<Room> roomTypes;
        int sizeX;
        int sizeY;
        
    static
    {
        random = new Random();
        roomTypes = new ArrayList<Room>();
        
        roomTypes.add(new Room2());
        roomTypes.add(new Room3());
        roomTypes.add(new Room4());
        roomTypes.add(new Room5());
        roomTypes.add(new Room6());
        roomTypes.add(new Room7());
        roomTypes.add(new Room8());
        roomTypes.add(new Room9());
        roomTypes.add(new Room10());
        roomTypes.add(new Room11());
        roomTypes.add(new Room12());
        roomTypes.add(new Room13());
        roomTypes.add(new Room14());
        roomTypes.add(new Room15());
        //        etc
    }
    
    static Room getRoom() 
    {
        if (roomTypes.size() > 0)
        {
/*            try {
                return (Room)roomTypes.get(random.nextInt(roomTypes.size())).newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            return roomTypes.get(random.nextInt(roomTypes.size()));
        }
        return null;
    }
    
    public Room[][] rooms;
    Random r = new Random();
    
    public Map(boolean thing)
    {
        if (thing)
        {
            rooms = new Room[1][1];
            rooms[0][0] = new StartRoom();
        }
        //sdrgsdgr
    }
    public Map(int nRooms)
    {
        this(nRooms, nRooms * 2 - 1, nRooms * 2 - 1);
    }
    public Map(int nRooms, int sizeX, int sizeY) {
        
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        
        int cX = 0;//prevY
        int cY = 0;//prevX
        int count = 0;
        
        do
        {
            rooms = new Room[sizeX][sizeY];//start over
            
            System.out.println(count);
        
            for (int i = 0; i < nRooms; i++)
            {
                if (i == 0)
                {
                    rooms[sizeX/2][sizeY/2] =  getRoom();
                    cX = sizeX/2;
                    cY = sizeY/2;
                }  
                else
                {
                    int randInt = r.nextInt(4);//random 0-3 for directions

                    if(randInt == 0 &&  cY > 0 && rooms[cX][cY].exits[0]){//Add room above

                        
                        Room room = getRoom();
                        while(room == null || room.exits[2] == false){
                            room = getRoom();

                        }
                        rooms[cX][cY-1] = room;
                        cY = cY-1;
                    }
                    else if(randInt == 1 &&  cX < rooms.length-1 && rooms[cX][cY].exits[1]){//add room to the right
                       
                        Room room = getRoom();
                        while(room == null || room.exits[3] == false){
                            room = getRoom();

                        }
                        rooms[cX+1][cY] = room;
                        cX = cX+1;
                    }
                    else if(randInt == 2 &&  cY < rooms[0].length-1 && rooms[cX][cY].exits[2]){//add room below
                       
                        Room room = getRoom();
                        while(room == null || room.exits[0] == false){
                            room = getRoom();

                        }
                        rooms[cX][cY+1] = room;
                        cY = cY+1;
                    }
                    else if(randInt == 3 &&  cX > 0 && rooms[cX][cY].exits[3]){//add room to the left
                        
                        Room room = getRoom();
                        while(room == null && room.exits[1] == false){
                            room = getRoom();
                        }
                        rooms[cX-1][cY] = room;
                        cX = cX-1;
                    }
                    else{
                        i--;
                    }
                }
            }
            count++; // this is how many tries we've made
            
            
            checkExits();
            
            //might want to make a method for this:
            //go through all the rooms
                //if there are any that exit in some direction but the map is null in that direction,
                    //place a random room to connect in that direction
            //repeat if any additions were made (kind of like our bubble sort!)
            
        }while(verify() == false); //continue making maps until you find a good one
        
        finalizeRooms();        
        
        crop();
        
        System.out.println("done"); 
    }
    
    void finalizeRooms()
    {
        for(int x = 0; x < rooms.length; x++ )
            for(int y = 0; y < rooms[x].length; y++)
                if(rooms[x][y] != null)
                    rooms[x][y] = rooms[x][y].clone();
    }
    
    void crop()
    {
        //find the leftmost x coordinate
        //find the rightmost x coordinate
        //find the topmost y coordinate
        //find the bottommost y coordinate
        //allocate a new array
        //copy stuff over
        int minX = 1000;
        int minY = 1000;
        int maxX = 0;
        int maxY = 0;
        
        
        
        for(int x = 0 ; x < rooms.length; x++){//min
            for(int y = 0;y < rooms[0].length; y++){
                if(rooms[x][y] != null &&  x > maxX){
                    maxX = x;
                    
                }
                if(rooms[x][y] != null &&  x < minX){
                    minX = x;
                }
                if(rooms[x][y] != null &&  y > maxY){
                    maxY = y;
                }
                if(rooms[x][y] != null &&  y < minY){
                    minY = y;
                }
                
            }
        }
        
        Room[][] newRooms = new Room[maxX-minX][maxY-minY];
        
        for(int x = 0; x < maxX-minX;x++){
            for(int y = 0; y < maxY-minY; y++){
                newRooms[x][y] = rooms[x+minX][y+minY];
            }
        }
        System.out.println("done cropping");
        
        
    }
    
    void checkExits(){
        boolean good = true;
        
        do{
            good = true;
            for(int x = 1; x < rooms.length-1; x++){
                for(int y = 1; y < rooms[0].length-1;y++){

                    if(rooms[x][y] != null && rooms[x+1][y] == null && rooms[x][y].exits[1] == true){
                        Room room = getRoom();
                        while(room == null && room.exits[3] == false){
                            room = getRoom();
                        }
                        rooms[x+ 1][y] = room;
                        
                          good = false;
                    }
                    if(rooms[x][y] != null && rooms[x-1][y] == null && rooms[x][y].exits[3] == true){
                        Room room = getRoom();
                        while(room == null && room.exits[1] == false){
                            room = getRoom();
                        }
                        rooms[x-1][y] = room;
                        
                          good = false;
                    }
                    if(rooms[x][y] != null && rooms[x][y+1] == null && rooms[x][y].exits[2] == true){
                        Room room = getRoom();
                        while(room == null && room.exits[0] == false){
                            room = getRoom();
                        }
                        rooms[x][y+1] = room;
                        
                          good = false;
                    }
                    if(rooms[x][y] != null && rooms[x][y-1] == null && rooms[x][y].exits[0] == true){
                        Room room = getRoom();
                        while(room == null && room.exits[2] == false){
                            room = getRoom();
                        }
                        rooms[x][y-1] = room;
                        
                          good = false;
                    }
                    
                }
            }
        }while(good == false);
        
        
    }
    
    boolean verify(){
        //checking all exits
        for(int x = 1; x < rooms.length-1; x++){
            for(int y = 1; y < rooms[0].length-1;y++){
                
                if((rooms[x][y] != null && rooms[x+1][y] != null) && (rooms[x][y].exits[1] != rooms[x+1][y].exits[3])){
                    //rooms =  new Room[sizeX][sizeY];
                    return false;
                    
                    //bad map
                }
                if((rooms[x][y] != null && rooms[x-1][y] != null) && (rooms[x][y].exits[3] != rooms[x-1][y].exits[1])){
                    //rooms =  new Room[sizeX][sizeY];
                    return false;
                }
                if((rooms[x][y] != null && rooms[x][y+1] != null) && (rooms[x][y].exits[2] != rooms[x][y+1].exits[0])){
                    //rooms =  new Room[sizeX][sizeY];
                    return false;
                }
                if((rooms[x][y] != null && rooms[x][y-1] != null) && (rooms[x][y].exits[0] != rooms[x][y-1].exits[2])){
                    //rooms =  new Room[sizeX][sizeY];
                    return false;
                }
            }
        }
        return true;
    }
    
    public void draw(Game game)
    {
        for (int x = 0; x < rooms.length; x++)
            for (int y = 0; y < rooms[0].length; y++)
            {
                if (rooms[x][y] != null)
                {
                    rooms[x][y].draw(game);
                }
            }
    }
}

