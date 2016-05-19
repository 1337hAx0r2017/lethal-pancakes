/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.map;

import ap4.Game;
import ap4.graphics.Light;
import java.awt.Graphics;

/**
 *
 * @author kileys.2017
 */
public class Pit extends Floor {
    
    public boolean drawnorth = false;
    public boolean drawsouth = false;
    public boolean draweast = false;
    public boolean drawwest = false;
    
    Pit(){
        solid = false;
        isPit = true;
    }
    public void setupWalls(Tile[][] t)
    {
        for (int r = 0; r < t.length; r++)
            for (int c = 0; c < t[0].length; c++)
            {
                if (t[r][c] == this)
                {
                    if (r == 0)
                        drawnorth = true;
                    else if (r == t.length - 1)
                        drawsouth = true;
                    
                    if (c == 0)
                        drawwest = true;
                    else if (c == t[0].length - 1)
                        draweast = true;
                    
                    if (r > 0)
                        if (t[r - 1][c].type != 2)
                            drawnorth = true;
                    if (r < t.length - 1)
                        if (t[r + 1][c].type != 2)
                            drawsouth = true;
                    
                    if (c > 0)
                        if (t[r][c - 1].type != 2)
                            drawwest = true;
                    if (c < t.length - 1)
                        if (t[r][c + 1].type != 2)
                            draweast = true;
                }
            }
        super.setupWalls(t);
    }
    
    public void draw(Game game, Room r)
    {
        if (super.drawnorth)
            northwall.draw(game.camera, r.x + x, r.y + y, r.z + z, 1, game.theLight);
        if (super.drawsouth)
            southwall.draw(game.camera, r.x + x, r.y + y, r.z + z, 1, game.theLight);
        if (super.draweast)
            eastwall.draw(game.camera, r.x + x, r.y + y, r.z + z, 1, game.theLight);
        if (super.drawwest)
            westwall.draw(game.camera, r.x + x, r.y + y, r.z + z, 1, game.theLight);
        if (drawnorth)
            northwall.draw(game.camera, r.x + x, r.y + y - 1, r.z + z, 1, game.theLight);
        if (drawsouth)
            southwall.draw(game.camera, r.x + x, r.y + y - 1, r.z + z, 1, game.theLight);
        if (draweast)
            eastwall.draw(game.camera, r.x + x, r.y + y - 1, r.z + z, 1, game.theLight);
        if (drawwest)
            westwall.draw(game.camera, r.x + x, r.y + y - 1, r.z + z, 1, game.theLight);
    }
}
