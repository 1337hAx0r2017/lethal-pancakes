package ap4;

import ap4.graphics.TextureModelGraphic;

public abstract class Enemy extends Character{
    
    public TextureModelGraphic visual;
     
    public Enemy(float x, float y, float movespeed)
    {
        super(x, y, movespeed);
    }
    
    @Override
    public abstract void draw(Game g);
    @Override
    public abstract void update(Game game, float time);
}