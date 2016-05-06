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
    
    ArrayList<InvItem> items;
    
    Player(float x, float y){
        super(x, y);
        
        items = new ArrayList<InvItem>();
    }
    
}
