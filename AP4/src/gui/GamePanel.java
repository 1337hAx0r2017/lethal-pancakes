package gui;

import ap4.Controller;
import ap4.Game;
import ap4.graphics.ColorVertex;
import ap4.graphics.GouraudColorModelGraphic;
import ap4.graphics.Matrix;
import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import ap4.graphics.Vector3;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends GeneralGamePanel {
    
    Game game;
    Controller c;
    
    //GouraudColorModelGraphic[][] testGraphics;
    TextureModelGraphic model;
    public GamePanel()
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
        
        game.camera.beginDraw(g);
        game.camera.setWorld(Matrix.IDENTITY);
        
        model.draw(game.camera, 0, .25f);
        model.draw(game.camera, -1, 0);
        
        /*for(int x = 0; x < 16; x++)
            for(int y = 0; y < 12; y++)
            {
                testGraphics[x][y].draw(game.camera, x, y);
            }*/
        
        
        //Draw ALL THE THINGS
        //map.draw(camera);
        
        game.camera.endDraw(g);
        
        g.setColor(Color.BLACK);
        g.drawString("Update Time: " + ((int)(getUpdateTime() * 1000)) + "ms (" +  ((int)(1.0/getUpdateTime())) +"Hz)",1, 21);
        g.drawString("Draw Time: " + ((int)(getDrawTime() * 1000)) + "ms (" +  ((int)(1.0/getDrawTime())) +"Hz)",1, 41);
        g.drawString("Cycle Time: " + ((int)(getCycleTime() * 1000)) + "ms (" +  ((int)(1.0/getCycleTime())) +"Hz)",1, 61);
        g.setColor(Color.WHITE);
        g.drawString("Update Time: " + ((int)(getUpdateTime() * 1000)) + "ms (" +  ((int)(1.0/getUpdateTime())) +"Hz)",0, 20);
        g.drawString("Draw Time: " + ((int)(getDrawTime() * 1000)) + "ms (" +  ((int)(1.0/getDrawTime())) +"Hz)",0, 40);
        g.drawString("Cycle Time: " + ((int)(getCycleTime() * 1000)) + "ms (" +  ((int)(1.0/getCycleTime())) +"Hz)",0, 60);
    }
}
