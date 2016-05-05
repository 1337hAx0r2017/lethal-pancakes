/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ap4.Game;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ed.mason
 */
public class GamePanel extends JPanel {
    Game game;
    public GamePanel(Game game)
    {
        this.game = game;
    }
    public void paint(Graphics g)
    {
        game.camera.beginDraw(g);
        
        //Draw ALL THE THINGS
        //map.draw(camera);
        
        game.camera.endDraw(g);
    }
}
