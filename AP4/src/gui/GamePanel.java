package gui;

import ap4.Controller;
import ap4.Game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class GamePanel extends GeneralGamePanel {
    
    Game game;
    Controller c;
    
    public GamePanel(Game game)
    {
        this.game = game;
        
        // Keys
        c = new Controller();
        add(c);
    }
    
    @Override
    public void update(float time)
    {
        c.update();
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        //game.camera.beginDraw(g);
        
        //Draw ALL THE THINGS
        //map.draw(camera);
        
        //game.camera.endDraw(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2d.drawString("Update Time: " + ((int)(getUpdateTime() * 1000000)) + "ms (" +  ((int)(1.0/getUpdateTime())) +"Hz)", 2, 12);
        g2d.drawString("Draw Time: " + ((int)(getDrawTime() * 1000000)) + "ms (" +  ((int)(1.0/getDrawTime())) +"Hz)", 2, 28);
        g2d.drawString("Cycle Time: " + ((int)(getCycleTime() * 1000000)) + "ms (" +  ((int)(1.0/getCycleTime())) +"Hz)", 2, 44);
    }
}
