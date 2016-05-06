package ap4;

import java.awt.Graphics;

public abstract class RoomObject {  
   public float x;
   public float y;     
   RoomObject(float x, float y)
   { 
       this.x = x;
       this.y = y;
      //consider 3dmodel vs images on gui    
   } 
   abstract void draw(Graphics g);
   //paint the images we put in   
   abstract void update(Game game);//will be different in object3d and image object       
}