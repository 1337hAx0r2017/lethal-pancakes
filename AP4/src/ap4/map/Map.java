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

        
        do
        {
            rooms = new Room[sizeX][sizeY];//start over
            
                    rooms[sizeX/2][sizeY/2] =  getRoom();
                    cX = sizeX/2;
                    cY = sizeY/2;
               
            mapGen();//attach rooms
            
            //might want to make a method for this:
            //go through all the rooms
                //if there are any that exit in some direction but the map is null in that direction,
                    //place a random room to connect in that direction
            //repeat if any additions were made (kind of like our bubble sort!)
            
            
        }while(verify(nRooms) == false); //continue making maps until you find a good one
        
        finalizeRooms();        
        
        crop();
        
        System.out.println("done making "); 
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
        
        Room[][] newRooms = new Room[maxX-minX+1][maxY-minY+1];
        int cX = 0;
        int cY = 0;
        
        for(int x = minX; x <= maxX;x++){
            cY = 0;
            for(int y = minY; y <= maxY; y++){
                newRooms[cX][cY] = rooms[x][y];
                cY++;
            }
            cX++;
        }
        rooms = newRooms;
        System.out.println("done cropping");
        
        
    }
    void checkSurround(int rX, int rY){
            
            
        
            
            Room room;
                        boolean g = true;
                        
                        do{
                            g = true;
                            room = getRoom();
                            
                            if(room == null){
                                g = false;
                                
                            }
                            else if(rY > 0 &&  rooms[rX][rY-1] != null && rooms[rX][rY-1].exits[2] != room.exits[0]){
                                //check ABOVE room. if out of bounds or if there is a room, and the exit is wrong
                                g = false;
                            }
                            else if(rY < rooms[0].length-1 && rooms[rX][rY+1] != null && rooms[rX][rY+1].exits[0] != room.exits[2]){
                                //check BELOW room. if out of bounds or if there is a room, and the exit is wrong
                                g = false;
                            }
                            else if(rX < rooms.length-1 && rooms[rX+1][rY] != null && rooms[rX+1][rY].exits[3] != room.exits[1]){
                                //check RIGHT room. if out of bounds or if there is a room, and the exit is wrong
                                g = false;
                            }
                            else if(rX > 0 && rooms[rX-1][rY] != null && rooms[rX-1][rY].exits[1] != room.exits[3]){
                                //check LEFT room. if out of bounds or if there is a room, and the exit is wrong
                                g = false;
                            }
                            
                            //else, good fitting room, so continue and add room
                            //System.out.println("checking to fit room");
                            
                        }while(g == false);//continue searchhing for correct room if neccesary
                        
                        rooms[rX][rY] = room;
                        //System.out.println("found right room");
                    
    }
    void mapGen(){
        boolean good = true;
        
        do{
            
            good = true;
            
            for(int x = 1; x < rooms.length-1; x++){
                for(int y = 1; y < rooms[0].length-1;y++){
                    
                    if(rooms[x][y] != null && rooms[x+1][y] == null && rooms[x][y].exits[1] == true){//adding room to the right
                        checkSurround(x+1,y);//checks and adds correct room
                        good = false;
                    }
                    if(rooms[x][y] != null && rooms[x-1][y] == null && rooms[x][y].exits[3] == true){
                        checkSurround(x-1,y);
                        good = false;
                    }
                    if(rooms[x][y] != null && rooms[x][y+1] == null && rooms[x][y].exits[2] == true){
                         checkSurround(x,y+1);
                         good = false;
                     }
                    if(rooms[x][y] != null && rooms[x][y-1] == null && rooms[x][y].exits[0] == true){
                         checkSurround(x, y-1);
                         good = false;
                     }
                    

                }
            }
            
        }while(good == false);
        
        
    }
    
    boolean verify(int nRooms){
        //checking all exits
        int n = 0;
        
        for(int i = 0; i < rooms.length;i++){
            for(int x = 0; x < rooms[i].length;x++){
                if(rooms[i][x] != null){
                    n++;
                }
            }
        }
        System.out.println(n);
        if(n != nRooms){
            System.out.println("not right size");
            return false;
            
        }
        
        for(int x = 1; x < rooms.length-1; x++){
            for(int y = 1; y < rooms[0].length-1;y++){
                
                if((rooms[x][y] != null && rooms[x+1][y] != null) && (rooms[x][y].exits[1] != rooms[x+1][y].exits[3])){
                    //rooms =  new Room[sizeX][sizeY];
                    System.out.println("bad exit right");
                    return false;
                    
                    //bad map
                }
                if((rooms[x][y] != null && rooms[x-1][y] != null) && (rooms[x][y].exits[3] != rooms[x-1][y].exits[1])){
                    //rooms =  new Room[sizeX][sizeY];
                    System.out.println("bad exit left");
                    return false;
                }
                if((rooms[x][y] != null && rooms[x][y+1] != null) && (rooms[x][y].exits[2] != rooms[x][y+1].exits[0])){
                    //rooms =  new Room[sizeX][sizeY];
                    System.out.println("bad exit below");
                    return false;
                }
                if((rooms[x][y] != null && rooms[x][y-1] != null) && (rooms[x][y].exits[0] != rooms[x][y-1].exits[2])){
                    //rooms =  new Room[sizeX][sizeY];
                    System.out.println("bad exit up");
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

