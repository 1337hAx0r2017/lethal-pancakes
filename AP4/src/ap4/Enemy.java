package ap4;

import ap4.graphics.TextureModelGraphic;
import java.util.Random;

public abstract class Enemy extends Character{
    
    public float dir = 0.0f;
    public float speed = 0.06f;
    public TextureModelGraphic visual;
     
    public Enemy(float x, float y, float movespeed)
    {
        super(x, y, movespeed);
    }
    
    @Override
    public abstract void draw(Game g);
    @Override
    public abstract void update(Game game, float time);
    
    public void wander(Game game)
    {
        dir += (new Random().nextFloat() * 30.0f) - 15.0f;
        x += Math.cos(Math.toRadians(dir)) * speed;
        y += Math.sin(Math.toRadians(dir)) * speed;
        
        if (x < game.currentRoom.x)
        {
            dir += 180;
            x = game.currentRoom.x;
        }
        if (x > game.currentRoom.x + 16 - 1)
        {
            dir += 180;
            x = game.currentRoom.x + 16 - 1;
        }
        if (y > game.currentRoom.z + 12 - 1)
        {
            dir += 180;
            y = game.currentRoom.z + 12 - 1;
        }
        if (y < game.currentRoom.z)
        {
            dir += 180;
            y = game.currentRoom.z;
        }
    }
}