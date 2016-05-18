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
    
    Wall()
    {
        solid = true;
        type = 1;
    }
    
    static {
    }
    
    public void draw(Graphics g, Game game, Room r)
    {
        super.draw(g, game, r);
    }
}
