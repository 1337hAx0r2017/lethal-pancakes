package ap4.enemies;

import ap4.Controller;
import ap4.Enemy;
import ap4.Game;
import java.awt.Graphics;

/**
 *
 * @author kardzhalao.2017
 */
public class Octorok extends Enemy{

    public Octorok(float x, float y, float movespeed)
    {
        super(x, y, movespeed);
    }
    @Override
    public void draw(Graphics g) {
        //import image of an octorok
    }

    @Override
    public void update(Game game, float time) {
       // specific to this class' move method
    }

    @Override
    public void move(Game game, Controller controls) {
       //specific movement
    }
}