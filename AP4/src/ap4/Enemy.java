 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author kardzhalao.2017
 */
public abstract class Enemy extends Character{
    
    public BufferedImage visual;
     
    public Enemy(float x, float y, float movespeed)
    {
        super(x, y, movespeed);
    }
    
    @Override
    public abstract void draw(Game g);
    @Override
    public abstract void update(Game game, float time);
}