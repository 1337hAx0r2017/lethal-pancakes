package ap4;

import java.awt.Graphics;

public abstract class RoomObject {  
  
    public float x;
    public float y;     
   
    RoomObject(float tX, float tY) // Consider later how graphics will be added in
    { 
        x = tX;
        y = tY;
        
        // Consider 3dmodel vs images on gui    
    } 
    
    public float distanceTo(float x, float y)
    {
        return (float)Math.sqrt((x * x) + (y * y));
    }
    
    public float distanceTo(RoomObject object)
    {
        return distanceTo(object.x, object.y);   
    }
    
    // Paint the images we put in
    abstract void draw(Graphics g);
    
    // Will be different in object3d and image object      
    abstract void update(Game game, float time); 
}