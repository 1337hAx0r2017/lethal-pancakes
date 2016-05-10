
package ap4;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Character extends RoomObject{

    
    final float moveSpeed;
    
    //LIVING OBJECTS IN ROOMS
    Character(float x, float y, float moveSpeed){
        
        super(x, y);
        this.moveSpeed = moveSpeed;
        
    }
    
    abstract void update(Game game, float time);
        
    
    void draw(Graphics g){
        
    }
    
    void move(Game game, float dx, float dy){//actually moving(giving parameters for moving in x and y directions
        
        
        this.x += dx;
        this.y += dy;
        //animation stuff
    }

}
