/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.rooms;

import ap4.map.Room;

/**
 *
 * @author kardzhalao.2017
 */
public class StartRoom extends Room{
    //this will be the starting room of the map, guarantee a south exit(to leave map if they win)
            //also add the north west east exits, make starting room most decision based
    StartRoom()
    {
       super();
       exits[0] = true;//north
       exits[1] = true;//east
       exits[2] = true;//south
       exits[3] = true;//west
    }
}