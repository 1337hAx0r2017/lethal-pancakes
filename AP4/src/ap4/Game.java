package ap4;

import ap4.graphics.Camera;
import ap4.map.Map;
import ap4.map.Room;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    
    Controller control;
    public Camera camera;
    public Runnable logicRunnable;
    public Thread logicThread;
    
    public int state = 0;
    public Map map;
    
    public Game()
    {
        camera = new Camera(800, 600);
   
       // will initialize every room here, with their own sets of objects and enemies
       // will need rockobjects, enemies etc     
       //playGame();
        
        // Logic setup
        logicRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                //playGame();
                map = new Map(5, 3, 3);
            }
        };

        logicThread = new Thread(logicRunnable);
        logicThread.start();
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
        
    }
    
    //////////// DRAWING ///////////////
    public void drawStuff(Graphics g)
    {
        if (map != null)
            map.draw(g, this);
    }
    
    public void attachController(Controller control)
    { this.control = control; }
}
