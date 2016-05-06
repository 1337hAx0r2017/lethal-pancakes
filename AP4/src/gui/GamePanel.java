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
public class GamePanel extends JPanel {
    Game game;
    boolean running;
    double updateTime;
    double drawTime;
    double cycleTime;
    Thread loop;
    final float hertz = 30;
    final float seconds = 1 / hertz;
    final long nanoseconds = (int)(1000000000 * seconds);
    
    public GamePanel(Game game)
    {
        this.game = game;
        loop = new Thread(new GameLoop());
    }
    
    public void start()
    {
        running = true;
        loop.start();
    }
    
    public void stop()
    {
        running = false;
    }
    
    class GameLoop implements Runnable
    {

        @Override
        public void run() {
            try {
                while(running)
                {
                    long startUpdateTime = System.nanoTime();
                    update(seconds);
                    long endUpdateTime = System.nanoTime();
                    repaint();
                    long endDrawTime = System.nanoTime();
                    int sleepTime = (int)(nanoseconds - (endDrawTime - startUpdateTime));
                    Thread.sleep(sleepTime / 1000000, sleepTime % 1000000);
                    long endSleepTime = System.nanoTime();
                    updateTime = (endUpdateTime - startUpdateTime) / 1000000000.0;
                    drawTime = (endDrawTime - endUpdateTime) / 1000000000.0;
                    cycleTime = (endSleepTime - startUpdateTime) / 1000000000.0;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
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
        
        g.drawString("Update Time: " + ((int)(updateTime * 1000000)) + "ms (" +  ((int)(1.0/updateTime)) +"Hz)",0, 20);
        g.drawString("Draw Time: " + ((int)(drawTime * 1000000)) + "ms (" +  ((int)(1.0/drawTime)) +"Hz)",0, 40);
        g.drawString("Cycle Time: " + ((int)(cycleTime * 1000000)) + "ms (" +  ((int)(1.0/cycleTime)) +"Hz)",0, 60);
    }
}
