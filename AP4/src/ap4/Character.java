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
public class Character extends RoomObject{

    
    
    
    //LIVING OBJECTS IN ROOMS
    Character(float x, float y){
        super(x, y);
        
        
    }
    
    void move(Game game, float dx, float dy){//actually moving(giving parameters for moving in x and y directions
        
        
        this.x += dx;
        this.y += dy;
        //animation stuff
    }
    
    void attack(){//attacking animation
        
    }
    
    void defend(){//animation for when they are taking damage(ex. make red or throw up arms)
        
    }
    
    void die(){//die animation
        
    }
    
    
    //inherits paint
    
    
}
