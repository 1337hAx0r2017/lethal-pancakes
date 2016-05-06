/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.map;

/**
 *
 * @author kileys.2017
 */
public class Tile {
      
    boolean exit;
    boolean solid;//false = can walk into
    
    
    Tile(){
        solid = false;
        exit = false;
    }
     
}
