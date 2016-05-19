package gui;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

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
    public abstract void draw(Graphics g);
    public abstract void render();
    boolean drawing;
    
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
                    update((float)cycleTime);
                    long endUpdateTime = System.nanoTime();
                    //drawing = true;
                    render();
                    repaint();
                    //drawing = false;
                    long endDrawTime = System.nanoTime();
                    drawTime = (endDrawTime - endUpdateTime) / 1000000000.0;
                    int sleepTime = (int)(nanoseconds - (endDrawTime - startUpdateTime));
                    if(sleepTime > 0)
                        Thread.sleep(sleepTime / 1000000, sleepTime % 1000000);
                    long endSleepTime = System.nanoTime();
                    updateTime = (endUpdateTime - startUpdateTime) / 1000000000.0;
                    cycleTime = (endSleepTime - startUpdateTime) / 1000000000.0;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void paint(Graphics g)
    {
        if(!drawing)
        {
            super.paint(g);
            draw(g);
        }
    }   
}