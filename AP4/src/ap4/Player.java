package ap4;

import java.awt.Graphics;
import java.util.ArrayList;

public class Player extends Character {
    
    ArrayList<InvItem> items;
    
    Player(float x, float y, float moveSpeed){
        super(x, y, moveSpeed);
        
        items = new ArrayList<InvItem>();
    }
    
    
    void update(Game game, float time){
        // Movement
        
    }
    
    void draw(Graphics g){
        //stuffs
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
