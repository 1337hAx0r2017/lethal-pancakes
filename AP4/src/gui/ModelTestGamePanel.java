package gui;

import ap4.Controller;
import ap4.Game;
import ap4.graphics.ColorVertex;
import ap4.graphics.DirectionalLight;
import ap4.graphics.GouraudColorModelGraphic;
import ap4.graphics.Light;
import ap4.graphics.Matrix;
import ap4.graphics.PointLight;
import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import ap4.graphics.Vector3;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ModelTestGamePanel extends GeneralGamePanel {
    
    Game game;
    Light light;
    double theta;
    
    //GouraudColorModelGraphic[][] testGraphics;
    TextureModelGraphic model;
    public ModelTestGamePanel()
    {
        game = new Game();
        //game.camera.setTilt(-10);
        //game.camera.setPosition(8, 10, 6);
        game.camera.setPosition(0, .5f, 2);
        
        /*testGraphics = new GouraudColorModelGraphic[16][12];
        for(int x = 0; x < 16; x++)
            for(int y = 0; y < 12; y++)
            {
                testGraphics[x][y] = new GouraudColorModelGraphic(
                        new ColorVertex[]
                        {
                            new ColorVertex(0, 0, 0, 0xff000000 | ((x * 255 / 15) << 8) | (y * 255 / 15)),
                            new ColorVertex(1, 0, 0, 0xff000000 | ((x * 255 / 15) << 8) | (y * 255 / 15)),
                            new ColorVertex(0, 0, 1, 0xff000000 | ((x * 255 / 15) << 8) | (y * 255 / 15)),
                            new ColorVertex(1, 0, 1, 0xff000000 | ((x * 255 / 15) << 8) | (y * 255 / 15)),
                        },
                        new short[] { 0, 1, 2, 2, 1, 3 });
            }*/
        try{
            //GouraudColorModelGraphic[][] testGraphics;
            model = new TextureModelGraphic(
                            new TextureVertex[]
                            {
                                new TextureVertex(0, 0, 0, 0, 0),
                                new TextureVertex(1, 0, 0, 1, 0),
                                new TextureVertex(0, 0, 1, 0, 1),
                                new TextureVertex(1, 0, 1, 1, 1),
                            },
                            new short[]
                            { 
                                0, 1, 2, 
                                2, 1, 3
                            },
                            ImageIO.read(new File("47610129.jpg")));
        }
        catch(IOException ex)
        {
        }
        
        //light = new PointLight(0xffffff, -1,1, 1, 1);
        //light = new DirectionalLight(0xffffff, -1,-1, 1);
    }
    
    @Override
    public void update(float time)
    {
        game.update(time);
        theta += time;
        //((PointLight)light).source = new Vector3((float)Math.cos(theta), 1, (float)Math.sin(theta));
        //((DirectionalLight)light).setDirection((float)Math.cos(theta), -1, (float)Math.sin(theta));
    }
    
    @Override
    public void draw(Graphics g)
    {

        
        game.camera.beginDraw(g);
        game.camera.setWorld(Matrix.IDENTITY);
        
        model.draw(game.camera, 0, 0, .25f, 1, light);
        model.draw(game.camera, -1, 0, 0, 1, light);
        
        /*for(int x = 0; x < 16; x++)
            for(int y = 0; y < 12; y++)
            {
                testGraphics[x][y].draw(game.camera, x, y);
            }*/
        
        
        //Draw ALL THE THINGS
        //map.draw(camera);
        
        game.camera.endDraw(g);
        
        // Debug stuff
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2d.setColor(new Color(220, 220, 220, 240));
        g2d.fillRect(0, 0, 203, 65);
        
        g2d.setColor(Color.BLACK);
        g2d.drawString("Update Time: " + ((int)(getUpdateTime() * 1000)) + "ms (" +  ((int)(1.0/getUpdateTime())) +"Hz)", 2, 12);
        g2d.drawString("Draw Time: " + ((int)(getDrawTime() * 1000)) + "ms (" +  ((int)(1.0/getDrawTime())) +"Hz)", 2, 28);
        g2d.drawString("Cycle Time: " + ((int)(getCycleTime() * 1000)) + "ms (" +  ((int)(1.0/getCycleTime())) +"Hz)", 2, 44);

        g2d.setColor(Color.WHITE);
        /*g2d.drawString("Update Time: " + ((int)(getUpdateTime() * 1000)) + "ms (" +  ((int)(1.0/getUpdateTime())) +"Hz)", 1, 12);
        g2d.drawString("Draw Time: " + ((int)(getDrawTime() * 1000)) + "ms (" +  ((int)(1.0/getDrawTime())) +"Hz)", 1, 28);
        g2d.drawString("Cycle Time: " + ((int)(getCycleTime() * 1000)) + "ms (" +  ((int)(1.0/getCycleTime())) +"Hz)", 1, 44);
        g2d.drawString("Keys; U:" + control.up.getDown() + " D:" + control.down.getDown() + " L:" + control.left.getDown() + " R:" + control.right.getDown(), 1, 60);*/
    }
}
