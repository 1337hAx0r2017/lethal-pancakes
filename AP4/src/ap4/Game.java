package ap4;

import ap4.graphics.Camera;
import ap4.map.Map;
import ap4.map.Room;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    
    Controller control;
    public Camera camera;
    public Runnable logicRunnable;
    public Thread logicThread;
    
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
                playGame();
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
            new Map(5);
        }
        else if (input.nextInt() == 2)
        {
            new Map(8);
        }
        else if (input.nextInt() == 3)
        {
             new Map(11);
        }
        else if (input.nextInt() == 4)
        {
            new Map(15);
        }
    }
    
    public void update(float time)
    {
        
    }
    
    public void attachController(Controller control)
    { this.control = control; }
}
