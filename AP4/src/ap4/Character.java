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
    abstract void draw(Graphics g);
    abstract void move(Game game, Controller controls);
}