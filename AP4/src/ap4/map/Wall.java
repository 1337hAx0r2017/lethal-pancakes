package ap4.map;

import ap4.Etc;
import ap4.Game;
import ap4.graphics.Light;
import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import static ap4.map.Tile.eastwall;
import static ap4.map.Tile.northwall;
import static ap4.map.Tile.southwall;
import static ap4.map.Tile.westwall;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Wall extends Tile {
    
    public static TextureModelGraphic cap;
    
    Wall()
    {
        solid = true;
    }
    
    static {
        try {
            // Setup models for use in tiles
            cap = new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 1, 0, 0, 0),
                        new TextureVertex(1, 1, 0, 1, 0),
                        new TextureVertex(0, 1, 1, 0, 1),
                        new TextureVertex(1, 1, 1, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "darkwoodwalls.jpg")));
        }
        catch (IOException ex)
        { Logger.getLogger(Wall.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    @Override
    public void draw(Graphics g, Game game, Light light)
    {
        
    }
}
