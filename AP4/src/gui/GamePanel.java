package gui;

import ap4.Controller;
import ap4.Game;
import ap4.graphics.DirectionalLight;
import ap4.graphics.Light;
import ap4.graphics.PointLight;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class GamePanel extends GeneralGamePanel {
    
    Game game;
    Controller control;
    //public Light light = new PointLight(0xffffff, 5,1, 5, 500);
    //public DirectionalLight light = new DirectionalLight(0xffffff, -1f, -1f, 1f);
    public DirectionalLight light = null;
    
    public GamePanel()
    {
        game = new Game();
        //game.camera.setTilt(-10);
        //game.camera.setPosition(8, 10, 6);
        game.camera.setPosition(8, 10, 6);
        game.camera.setTilt(-90);
        control = new Controller();
        game.attachController(control);
        add(control);
    }
    
    @Override
    public void update(float time)
    {
        game.update(time);
    }
    
    @Override
    public void render()
    {
        game.camera.beginDraw();
        
        //Draw ALL THE THINGS
        game.drawStuff(light);
        
        game.camera.endDraw();
    }
    
    @Override
    public void draw(Graphics g)
    {
        game.camera.show(g);
        
        game.subdraw(g);
        
        // Debug stuff
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2d.setColor(new Color(220, 220, 220, 100));
        g2d.fillRect(0, 0, 203, 65);
        
        g2d.setColor(Color.BLACK);
        g2d.drawString("Update Time: " + ((int)(getUpdateTime() * 1000)) + "ms (" +  ((int)(1.0/getUpdateTime())) +"Hz)", 2, 12);
        g2d.drawString("Draw Time: " + ((int)(getDrawTime() * 1000)) + "ms (" +  ((int)(1.0/getDrawTime())) +"Hz)", 2, 28);
        g2d.drawString("Cycle Time: " + ((int)(getCycleTime() * 1000)) + "ms (" +  ((int)(1.0/getCycleTime())) +"Hz)", 2, 44);
        g2d.drawString("Keys; U:" + control.up.getDown() + " D:" + control.down.getDown() + " L:" + control.left.getDown() + " R:" + control.right.getDown(), 2, 60);

        g2d.setColor(Color.WHITE);
        /*g2d.drawString("Update Time: " + ((int)(getUpdateTime() * 1000)) + "ms (" +  ((int)(1.0/getUpdateTime())) +"Hz)", 1, 12);
        g2d.drawString("Draw Time: " + ((int)(getDrawTime() * 1000)) + "ms (" +  ((int)(1.0/getDrawTime())) +"Hz)", 1, 28);
        g2d.drawString("Cycle Time: " + ((int)(getCycleTime() * 1000)) + "ms (" +  ((int)(1.0/getCycleTime())) +"Hz)", 1, 44);
        g2d.drawString("Keys; U:" + control.up.getDown() + " D:" + control.down.getDown() + " L:" + control.left.getDown() + " R:" + control.right.getDown(), 1, 60);*/
    }
}
