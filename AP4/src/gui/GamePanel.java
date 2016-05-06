/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ap4.Game;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author ed.mason
 */
public class GamePanel extends GeneralGamePanel {
    Game game;
    
    public GamePanel(Game game)
    {
        this.game = game;
    }
    
    
    public void update(float time)
    {
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        //game.camera.beginDraw(g);
        
        //Draw ALL THE THINGS
        //map.draw(camera);
        
        //game.camera.endDraw(g);
        
        g.drawString("Update Time: " + ((int)(getUpdateTime() * 1000000)) + "ms (" +  ((int)(1.0/getUpdateTime())) +"Hz)",0, 20);
        g.drawString("Draw Time: " + ((int)(getDrawTime() * 1000000)) + "ms (" +  ((int)(1.0/getDrawTime())) +"Hz)",0, 40);
        g.drawString("Cycle Time: " + ((int)(getCycleTime() * 1000000)) + "ms (" +  ((int)(1.0/getCycleTime())) +"Hz)",0, 60);
    }
}
