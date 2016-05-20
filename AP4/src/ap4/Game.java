package ap4;

import ap4.graphics.Camera;
import ap4.graphics.Light;
import ap4.map.Map;
import ap4.map.Room;
import gui.Inventory;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    
    public Controller control;
    public Inventory inventory;
    public Camera camera;
    public Runnable logicRunnable;
    public Thread logicThread;
    
    public int state = 0;
    public Map map;
    public Light theLight;
        
    
    public Game()
    {
        camera = new Camera(790, 620);
        
        map = new Map(10);
        
        //
        for (int r = 0; r < map.rooms[0].length; r++)
        {
            String line = "";
            for (int c = 0; c < map.rooms.length; c++)
            {
                if (map.rooms[c][r] != null)
                    line += "ROOM ";
                else
                    line += "NULL ";
            }
            System.out.println(line);
        }
        
        ArrayList<Room> rs = new ArrayList<>();
        // Give rooms their coordinates
        for (int r = 0; r < map.rooms[0].length; r++)
        {
            for (int c = 0; c < map.rooms.length; c++)
            {
                rs.add(map.rooms[c][r]);
                if (map.rooms[c][r] != null)
                {
                    map.rooms[c][r].z = r * 14;
                    map.rooms[c][r].x = c * 18;
                }
            }
        }
        rs.get(new Random().nextInt(rs.size())).isStartRoom = true;
    
        inventory = new Inventory();
        //map = new Map(true);
        System.out.println("Map gen done");
        
        // Position camera accordingly
        for (int r = 0; r < map.rooms[0].length; r++)
        {
            for (int c = 0; c < map.rooms.length; c++)
            {
                if (map.rooms[c][r] != null)
                {
                    if (map.rooms[c][r].isStartRoom)
                    {
                        camera.setPosition(map.rooms[c][r].x, 5, map.rooms[c][r].z);
                        break;
                    }
                }
            }
        }
    }

    //////////// UPDATE ///////////////
    public void update(float time)
    {
        if (control._w.getDown())
            camera.setPosition(camera.getX(), camera.getY(), camera.getZ() - 0.1f);
        if (control._s.getDown())
            camera.setPosition(camera.getX(), camera.getY(), camera.getZ() + 0.1f);
        if (control._a.getDown())
            camera.setPosition(camera.getX() - 0.1f, camera.getY(), camera.getZ());
        if (control._d.getDown())
            camera.setPosition(camera.getX() + 0.1f, camera.getY(), camera.getZ());
        if (control._q.getDown())
            camera.setPosition(camera.getX(), camera.getY() + 0.1f, camera.getZ());
        if (control._e.getDown())
            camera.setPosition(camera.getX(), camera.getY() - 0.1f, camera.getZ());
    }
    
    //////////// DRAWING ///////////////
    public void drawStuff(Light l)
    {
        theLight = l;
        
        if (map != null)
            map.draw(this);
    }
    
    public void subdraw(Graphics g)
    {
        inventory.draw(g);
    }
    
    public void attachController(Controller control)
    { this.control = control; }
}
