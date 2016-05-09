package ap4;

import java.awt.Graphics;

public abstract class RoomObject {  
  
    public float x;
    public float y;     
   
   RoomObject(float x, float y)// consider later how graphics will be added in
   { 
       this.x = x;
       this.y = y;
      //consider 3dmodel vs images on gui    
   } 
   
   public float distanceTo(float x, float y)
   {
   
   }
   public float distanceTo(RoomObject object)
   {
     return distanceTo(object.x, object.y);   
   }

   abstract void draw(Graphics g);
   //paint the images we put in   
   abstract void update(Game game, float time);//will be different in object3d and image object       
}