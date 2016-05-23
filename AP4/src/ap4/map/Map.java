package ap4.map;

import ap4.Game;
import ap4.rooms.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

public class Map {

    static Random random;
    static ArrayList<Room> roomTypes;
        int sizeX;
        int sizeY;
        int nRooms = 0;
        int n;
        
    static
    {
        random = new Random();
        roomTypes = new ArrayList<Room>();
        
        roomTypes.add(new StartRoom());
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
    
    
    public Map(int nRooms)
    {
        this(nRooms, nRooms * 2 - 1, nRooms * 2 - 1);
    }
    public Map(int nRooms, int sizeX, int sizeY) {
        
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        
        int cX = 0;//prevY
        int cY = 0;//prevX
        
        this.nRooms = nRooms;
        
        boolean success;
        
        do
        {
            n = 0;
            rooms = new Room[sizeX][sizeY];//start over
            
                    do
                    {
                        rooms[sizeX/2][sizeY/2] =  getRoom();
                    }while(!(rooms[sizeX/2][sizeY/2] instanceof StartingRoom));
                    cX = sizeX/2;
                    cY = sizeY/2;
                    n++;
               
            success = mapGen();//attach rooms
            
            //might want to make a method for this:
            //go through all the rooms
                //if there are any that exit in some direction but the map is null in that direction,
                    //place a random room to connect in that direction
            //repeat if any additions were made (kind of like our bubble sort!)
            
            
        }while(!success || n != nRooms);// || shortestDistanceBetween(cX, cY, cX-4, cY-4) == -1); //continue making maps until you find a good one
        
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
    /*int checkSize(){
        int n = 0;
        for(int i = 0; i < rooms.length;i++){
            for(int x = 0; x < rooms[i].length;x++){
                if(rooms[i][x] != null){
                    n++;
                }
            }
        }
        System.out.println(n);
        return n;
    }*/
    void crop()
    {
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
                        n++;
                        //System.out.println("found right room");
                    
    }
    boolean mapGen(){
        boolean good = true;
        
        do{
            good = true;
            
            for(int x = 1; x < rooms.length-1; x++){
                for(int y = 1; y < rooms[0].length-1;y++){
                    
                    if(n > nRooms){
                        System.out.println(n);
                        return false;
                    }
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
        return true;
        
    }
    
/*    boolean verify(){
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

        return true;
    }
  */  
    public void draw(Game game)
    {
        /*for (int x = 0; x < rooms.length; x++)
            for (int y = 0; y < rooms[0].length; y++)
            {
                if (rooms[x][y] != null)
                {
                    rooms[x][y].draw(game);
                }
            }*/
        int c = (int)(game.currentRoom.x / 18);
        int r = (int)(game.currentRoom.z / 14);
        if (r - 1 > -1)
        {
            if (c - 1 > -1)
                if (rooms[c - 1][r - 1] != null)
                    rooms[c - 1][r - 1].draw(game);
            if (rooms[c][r - 1] != null)
                    rooms[c][r - 1].draw(game);
            if (c + 1 < rooms.length)
                if (rooms[c + 1][r - 1] != null)
                    rooms[c + 1][r - 1].draw(game);
        }
        
        if (c - 1 > -1)
            if (rooms[c - 1][r] != null)
                rooms[c - 1][r].draw(game);
        rooms[c][r].draw(game);
        if (c + 1 < rooms.length)
            if (rooms[c + 1][r] != null)
                rooms[c + 1][r].draw(game);
        
        if (r + 1 < rooms[0].length)
        {
            if (c - 1 > -1)
                if (rooms[c - 1][r + 1] != null)
                    rooms[c - 1][r + 1].draw(game);
            if (rooms[c][r + 1] != null)
                rooms[c][r + 1].draw(game);
            if (c + 1 < rooms.length)
                if (rooms[c + 1][r + 1] != null)
                    rooms[c + 1][r + 1].draw(game);
        }
    }
    
    private class PathNode
    {
        int x;
        int y;
        int gen;
        PathNode parent;
        ArrayList<PathNode> children;
        PathNode(int x, int y)
        {
            this(x, y, 0, null);
        }
        private PathNode(int x, int y, int gen, PathNode parent)
        {
            this.x = x;
            this.y = y;
            this.gen = gen;
            this.parent = parent;
            children = new ArrayList<PathNode>(4);
        }
        void branch(Map map)
        {
            Room current = map.rooms[x][y];
            if(current.exits[0] && !ancestorHas(x, y-1))
                children.add(new PathNode(x, y-1, gen+1, this));
            if(current.exits[1] && !ancestorHas(x+1, y))
                children.add(new PathNode(x+1, y, gen+1, this));
            if(current.exits[2] && !ancestorHas(x, y+1))
                children.add(new PathNode(x, y+1, gen+1, this));
            if(current.exits[3] && !ancestorHas(x-1, y))
                children.add(new PathNode(x-1, y, gen+1, this));
            for(int i = 0; i < children.size(); i++)
                children.get(i).branch(map);
        }
        void branch(Map map, int destx, int desty)
        {
            if(!(this.x == destx && this.y == desty))
                branch(map);
        }
        private boolean ancestorHas(int x, int y)
        {
            PathNode current = this;
            do
            {
                if(current.x == x && current.y == y)
                    return true;
                current = current.parent;
            }
            while(current != null);
            return false;
        }
        void findEnds(HashSet<PathNode> results, int destx, int desty)
        {
            if(this.x == destx && this.y == desty)
                results.add(this);
            else
                for(int i = 0; i < children.size(); i++)
                    children.get(i).findEnds(results, destx, desty);
        }
    }
    public int shortestDistanceBetween(int x1, int y1, int x2, int y2)
    {
        if(rooms[x1][y1] == null || rooms[x2][y2] == null)
            return -1;
        PathNode tree = new PathNode(x1, y1);
        tree.branch(this, x2, y2);
        HashSet<PathNode> results = new HashSet<PathNode>();
        tree.findEnds(results, x2, y2);
        ArrayList<PathNode> list = new ArrayList<PathNode>(results);
        if(list.size() == 0)
            return -1;
        list.sort(new Comparator<PathNode>(){

            @Override
            public int compare(PathNode o1, PathNode o2) {
                return Integer.compare(o1.gen, o2.gen);
            }
        });
        if(list.size() == 1)
            System.out.println("There is 1 path to get from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + "), which is " + (list.get(0).gen == 1 ? "1 room" : list.get(0).gen + " rooms") + " long.");
        else
            System.out.println("There are " + list.size() + " paths to get from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + "), the shortest of which is " + (list.get(0).gen == 1 ? "1 room" : list.get(0).gen + " rooms") + " long.");
        return list.get(0).gen;
    }
    
    public int getXOf(Room room)
    {
        for(int y = 0; y < rooms[0].length; y++)
            for(int x = 0; x < rooms.length; x++)
                if(rooms[x][y] == room)
                    return x;
        return -1;
    }
    public int getYOf(Room room)
    {
        for(int y = 0; y < rooms[0].length; y++)
            for(int x = 0; x < rooms.length; x++)
                if(rooms[x][y] == room)
                    return y;
        return -1;
    }
}

