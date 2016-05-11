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
    
    void move(Game game, Controller controls) // Moving with butter
    {
        if (controls.left.getDown())
            xvel -= 1.5;
        if (controls.right.getDown())
            xvel += 1.5;
        if (controls.up.getDown())
            yvel -= 1.5;
        if (controls.down.getDown())
            yvel += 1.5;
        
        this.x += xvel;
        this.y += yvel;
        
        xvel *= 0.92;
        yvel *= 0.92;
    }
}