package ap4.enemies;

import ap4.Enemies;
import ap4.Game;
import java.awt.Graphics;

/**
 *
 * @author kardzhalao.2017
 */
public class Octorok extends Enemies{

    Octorok(float x, float y, float movespeed)
    {
        super(x, y, movespeed);
    }
    @Override
    public void draw(Graphics g) {
        //import image of an octorok
    }

    @Override
    public void update(Game game, float time) {
       // movement will be specific to octorok
    }
}