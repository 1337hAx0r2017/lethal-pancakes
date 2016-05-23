/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ap4.Player;
import ap4.map.Map;
import ap4.map.Room;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;

/**
 *
 * @author ed.mason
 */
public class MiniMap {
    Map map;
    HashSet<Room> discovered;
    int pX;
    int pY;
    int roomSize;
    long nanoTime;
    public MiniMap(Map map, int roomSize)
    {
        this.map = map;
        this.roomSize = roomSize;
        discovered = new HashSet<Room>();
        nanoTime = System.nanoTime();
    }
    public void draw(Graphics g, int x, int y)
    {
        for(int ry = 0; ry < map.rooms[0].length; ry++)
            for(int rx = 0; rx < map.rooms.length; rx++)
                if(map.rooms[rx][ry] != null)
                {
                    g.setColor(discovered.contains(map.rooms[rx][ry]) ? Color.CYAN : Color.GRAY);
                    g.fillRect(x + rx * (roomSize + 2) + 1, y + (ry * (roomSize + 2) + 1), roomSize, roomSize);
                }
        for(int ry = 0; ry < map.rooms[0].length; ry++)
            for(int rx = 0; rx < map.rooms.length; rx++)
            {
                if(rx < map.rooms.length - 1 && map.rooms[rx][ry] != null && map.rooms[rx + 1][ry] != null && map.rooms[rx][ry].exits[1] && map.rooms[rx+1][ry].exits[3] && (discovered.contains(map.rooms[rx][ry]) || discovered.contains(map.rooms[rx + 1][ry])))
                {
                    g.setColor(Color.CYAN);
                    g.fillRect(x + rx * (roomSize + 2) + roomSize + 1, y + (ry * (roomSize + 2) + 1) + roomSize / 2 - 2, 2, 4);
                }
                if(ry < map.rooms[0].length - 1 && map.rooms[rx][ry] != null && map.rooms[rx][ry + 1] != null && map.rooms[rx][ry].exits[2] && map.rooms[rx][ry+1].exits[0] && (discovered.contains(map.rooms[rx][ry]) || discovered.contains(map.rooms[rx][ry + 1])))
                {
                    g.setColor(Color.CYAN);
                    g.fillRect(x + rx * (roomSize + 2) + roomSize / 2 - 2 + 1, y + (ry * (roomSize + 2) + 1) + roomSize, 4, 2);
                }
            }
        float t = (float)(System.nanoTime() - nanoTime) / 1000000000.0f % 1.0f;
        g.setColor(new Color((int)(255 * (1 - t)), (int)(255 * (1 - t)), 0));
        g.fillRect(1 + x + pX * (roomSize + 2) + roomSize / 2 - 2, 1 + y + pY * (roomSize + 2) + roomSize / 2 - 2, 4, 4);
    }
    public void playerEntered(int pX, int pY)
    {
        if(this.pX != pX || this.pY != pY)
        {
            this.pX = pX;
            this.pY = pY;
            if(pX >= 0 && pX < map.rooms.length && pY >= 0 && pY <= map.rooms[0].length && map.rooms[pX][pY] != null)
                discovered.add(map.rooms[pX][pY]);
        }
    }
    public int getWidth()
    {
        return map.rooms.length * (roomSize + 2);
    }
    public int getHeight()
    {
        return map.rooms[0].length * (roomSize + 2);
    }
}
