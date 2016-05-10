package ap4;

import ap4.graphics.Camera;
import ap4.map.Map;
import ap4.map.Room;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    
    public Camera camera;
    public Game()
    {
        camera = new Camera(800, 600);
   
        // will initialize every room here, with their own sets of objects and enemies
        // will need rockobjects, enemies etc     
       playGame();
    }

    void playGame()
    {
         Scanner input = new Scanner(System.in);
         System.out.println("Welcome to Lethal Pancakes. Would You like to play an easy(1), medium(2), hard(3), or EXTREME(4) map.");
         if(input.nextInt() == 1)
         {
             new Map(5);
         }
         if(input.nextInt() == 2)
         {
             new Map(8);
         }
         if(input.nextInt() == 3)
         {
             new Map(11);
         }
         if(input.nextInt() == 4)
         {
             new Map(15);
         }
    }
}
>>>>>>> origin/dev
