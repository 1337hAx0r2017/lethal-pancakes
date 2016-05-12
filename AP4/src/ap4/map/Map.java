package ap4.map;

import ap4.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
            if (i == 0) {
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
                    cY = cY-1;
                }
                else if(randInt == 1){//add room to the right
                    rooms[cX][cY].exits[1] = true;
                    rooms[cX+1][cY] = new Room(new boolean[] {false,false,false,true});
                    cX = cX+1;
                }
                else if(randInt == 2){//add room below
                    rooms[cX][cY].exits[2] = true;
                    rooms[cX][cY+1] = new Room(new boolean[] {true,false,false,false});
                    cY = cY+1;
                }
                else if(randInt == 3){//add room to the left
                    rooms[cX][cY].exits[3] = true;
                    rooms[cX-1][cY] = new Room(new boolean[] {false,true,false,false});
                    cX = cX-1;
                }
            }
        }
        
        //checking all exits
        for(int x = 0; x < rooms.length; x++){
            for(int y = 0; y < rooms[0].length;y++){
                
                if(rooms[x][y] != null && rooms[x+1][y] != null){
                    
                }
            }
        }
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
