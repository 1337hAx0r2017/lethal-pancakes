package ap4;

import ap4.graphics.Camera;
import ap4.graphics.FlatImageGraphic;
import ap4.graphics.ImageGraphic;
import ap4.graphics.Light;
import ap4.map.Map;
import ap4.map.Room;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Game {
    
    Controller control;
    public Camera camera;
    public Runnable logicRunnable;
    public Thread logicThread;
    
    public int state = 0;
    public Map map;
    public Light theLight;
        
    
    public Game()
    {
        camera = new Camera(800, 600);
        map = new Map(5, 3, 3);
        System.out.println("Map gen done");
        
        
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
    public void drawStuff(Graphics g, Light l)
    {
        theLight = l;
        
        
        
        
        
        if (map != null)
            map.draw(g, this);
        
        
        
        
        
    }
    
    public void attachController(Controller control)
    { this.control = control; }
}
