package ap4.enemies;

import ap4.Enemy;
import ap4.Etc;
import ap4.Game;
import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import gui.Inventory;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Octorok extends Enemy{

    public Octorok(float x, float y)
    {
        super(x, y, 0.05f);
        try
        {
            visual = new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 0, 0, 0, 0),
                        new TextureVertex(0.77777f, 0, 0, 1, 0),
                        new TextureVertex(0, 0, 1, 0, 1),
                        new TextureVertex(0.77777f, 0, 1, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "tael.png")));
        }
        catch (IOException ex)
        { Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    @Override
    public void draw(Game game) {
        visual.draw(game.camera, x, 0.31f, y, 1, game.theLight);
    }
    @Override
    public void update(Game game, float time) {
       // specific to this class' move method
    }

    @Override
    public void move(Game game) {
       //specific movement
    }
}