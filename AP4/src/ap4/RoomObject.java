package ap4;

import java.awt.Graphics;

public abstract class RoomObject {
  
   public float x;
   public float y;
    
    //consider 3dmodel vs images on gui
    
    
   RoomObject(float x, float y)
   {
       this.x = x;
       this.y = y;
      
   }
     
   abstract void draw(Graphics g);
   //paint the images we put in   
   
   abstract void update(Game game);//will be different in 
        
}