/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4;

import java.util.ArrayList;

/**
 *
 * @author kileys.2017
 */
public class Player extends Character{
    
    boolean up;
    boolean down;
    boolean right;
    boolean left;
    
    ArrayList<InvItem> items;
    
    Player(float x, float y, float moveSpeed){
        super(x, y, moveSpeed);
        
        items = new ArrayList<InvItem>();
    }
    
    void update(Game game, float time){
        if(up){
            move(game, 0, time*moveSpeed);
            up = false;
        }
        if(down){
            move(game, 0, -1*time*moveSpeed);
            down = false;
        }
        if(right){
            move(game,  time*moveSpeed, 0);
            right = false;
        }
        if(left){
            move(game, -1*time*moveSpeed, 0);
            left = false;
        }
    }
}
