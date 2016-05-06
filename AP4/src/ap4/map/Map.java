/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.map;

import ap4.Game;
import java.util.ArrayList;

/**
 *
 * @author kardzhalao.2017
 */
public class Map {
    
    ArrayList<Room> rooms;
    
    Map(Game game)
    {
        this.rooms = game.rooms;
    }
    
    void createMap()
    {
        
// this method will link all of the rooms together
    }
    
    
}
