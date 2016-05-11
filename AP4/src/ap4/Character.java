package ap4;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Character extends RoomObject
{
    public float moveSpeed;
    public double xvel = 0;
    public double yvel = 0;
    
    //LIVING OBJECTS IN ROOMS
    Character(float x, float y, float moveSpeed)
    {   
        super(x, y);
        this.moveSpeed = moveSpeed;
    }
    
    @Override
    abstract void update(Game game, float time);
    
    @Override
    void draw(Graphics g)
    {
        
    }
    
    void move(Game game, boolean left, boolean right, boolean up, boolean down) // Moving with butter
    {
        if (left)
            xvel -= 1.5;
        if (right)
            xvel += 1.5;
        if (up)
            yvel -= 1.5;
        if (down)
            yvel += 1.5;
        
        this.x += xvel;
        this.y += yvel;
        
        xvel *= 0.92;
        yvel *= 0.92;
    }
}