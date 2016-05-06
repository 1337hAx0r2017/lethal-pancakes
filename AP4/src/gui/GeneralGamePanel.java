/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author ed.mason
 */
public abstract class GeneralGamePanel extends JPanel {
    private boolean running;
    private double updateTime;
    private double drawTime;
    private double cycleTime;
    private Thread loop;
    final float hertz = 30;
    final float seconds = 1 / hertz;
    final long nanoseconds = (int)(1000000000 * seconds);
    public abstract void update(float time);
    
    public GeneralGamePanel()
    {
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
    
    public double getUpdateTime() { return updateTime; }
    public double getDrawTime() { return drawTime; }
    public double getCycleTime() { return cycleTime; }
    
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
    
}
