package ap4;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Character extends RoomObject
{
    public float moveSpeed;
    public double xvel = 0;
    public double yvel = 0;
    
    //LIVING OBJECTS IN ROOMS
    public Character(float x, float y, float moveSpeed)
    {   
        super(x, y);
        this.moveSpeed = moveSpeed;
    }
    
    @Override
    public abstract void update(Game game, float time);
    public abstract void draw(Graphics g);
    public abstract void move(Game game, Controller controls);
}