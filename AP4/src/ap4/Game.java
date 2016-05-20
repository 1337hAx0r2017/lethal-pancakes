package ap4;

import ap4.graphics.Camera;
import ap4.graphics.Light;
import ap4.map.Map;
import gui.Inventory;
import java.awt.Graphics;
import java.util.Scanner;

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
        for (int r = 0; r < map.rooms.length; r++)
        {
            String line = "";
            for (int c = 0; c < map.rooms[0].length; c++)
            {
                if (map.rooms[r][c] != null)
                    line += "ROOM ";
                else
                    line += "NULL ";
            }
            System.out.println(line);
        }
    
        inventory = new Inventory();
        //map = new Map(true);
        System.out.println("Map gen done");
        
        // Position camera accordingly
    }

    void playGame()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Lethal Pancakes, pick a map size: easy(1), medium(2), hard(3), or EXTREME(4) map.");
        if (input.nextInt() == 1)
        {
            map = new Map(5, 3, 3);
        }
        else if (input.nextInt() == 2)
        {
            map = new Map(8, 4, 4);
        }
        else if (input.nextInt() == 3)
        {
            map = new Map(11, 4, 4);
        }
        else if (input.nextInt() == 4)
        {
            map = new Map(15, 5, 5);
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
        if (control._s.getDown())
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
