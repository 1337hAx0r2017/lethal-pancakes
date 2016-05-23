package ap4;

import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import java.awt.Graphics;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Player extends Character {
    
    public ArrayList<InvItem> items;
    
    public TextureModelGraphic visual;
    
    public Player(float x, float y, float moveSpeed){
        super(x, y, moveSpeed);
        
        items = new ArrayList<InvItem>();
        
        try
        {
            visual = new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 0, 0, 0, 0),
                        new TextureVertex(1.17f, 0, 0, 1, 0),
                        new TextureVertex(0, 0, 1.5f, 0, 1),
                        new TextureVertex(1.17f, 0, 1.5f, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "seghead75.png")));
        }
        catch (Exception ex)
        { System.out.println(ex); }
    }
    
    @Override
    public void update(Game game, float time){
        // Movement
        move(game);
    }
    
    @Override
    public void draw(Game game) {
        visual.draw(game.camera, x, 0.3f, y, 1, game.theLight);
    }
    
    @Override
    public void move(Game game) // Moving with butter
    {
        if (game.control.left.getDown())
            xvel -= moveSpeed;
        if (game.control.right.getDown())
            xvel += moveSpeed;
        if (game.control.up.getDown())
            yvel -= moveSpeed;
        if (game.control.down.getDown())
            yvel += moveSpeed;
        
        this.x += xvel;
        this.y += yvel;
        //this is where we'd use the base RoomObject's move method.
        //don't forget to multiply by time!
        //don't forget to take time as a parameter!
        
        xvel *= 0.92;
        yvel *= 0.92;
    }
}
