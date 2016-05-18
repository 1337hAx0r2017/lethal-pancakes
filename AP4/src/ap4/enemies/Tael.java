/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.enemies;

import ap4.Controller;
import ap4.Enemy;
import ap4.Game;
import java.awt.Graphics;

/**
 *
 * @author kardzhalao.2017
 */
public class Tael extends Enemy{
    public Tael(float x, float y, float movespeed)
    {
        super(x, y, movespeed);
    }
    @Override
    public void draw(Graphics g) {
        //import image of an octorok
    }

    @Override
    public void update(Game game, float time) {
       // will be specific to this class' movement
    }

    @Override
    public void move(Game game, Controller controls) {
        //movement specific to dampe
    }
}
