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
    public boolean canMove = true;
    
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
        if (canMove)
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
        
        xvel *= 0.88;
        yvel *= 0.88;
        
        // Check collisions with walls
        //System.out.println("Player x: " + x + ", y: " + y + ", current room x: " + game.currentRoom.x + " z: " + game.currentRoom.z);
        if (x < game.currentRoom.x)
        {
            xvel = 0.0f;
            x = game.currentRoom.x;
        }
        if (x > game.currentRoom.x + 16 - 1)
        {
            xvel = 0.0f;
            x = game.currentRoom.x + 16 - 1;
        }
        if (y > game.currentRoom.z + 12 - 1)
        {
            yvel = 0.0f;
            y = game.currentRoom.z + 12 - 1;
        }
        if (y < game.currentRoom.z)
        {
            yvel = 0.0f;
            y = game.currentRoom.z;
        }
        
        // Check for touching doors
        if (game.currentRoom.exits[0])
        {
            if (x > game.currentRoom.x + 6.8f && x < game.currentRoom.x + 8.2f && y < game.currentRoom.z + 0.05f && game.control.up.getDown())
            {
                // Touching north door
                xvel = 0; yvel = 0;
                canMove = false;
                y -= 0.05f;
                game.moveCamera("up");
            }
        }
        if (game.currentRoom.exits[2])
        {
            if (x > game.currentRoom.x + 6.8f && x < game.currentRoom.x + 8.2f && y > game.currentRoom.z + 12 - 1 - 0.05f && game.control.down.getDown())
            {
                // Touching south door
                xvel = 0; yvel = 0;
                canMove = false;
                y += 0.05f;
                game.moveCamera("down");
            }
        }
        if (game.currentRoom.exits[1])
        {
            if (y > game.currentRoom.z + 4.8f && y < game.currentRoom.z + 6.1f && x > game.currentRoom.x + 16 - 1 - 0.05f && game.control.right.getDown())
            {
                // Touching east door
                xvel = 0; yvel = 0;
                canMove = false;
                x += 0.05f;
                game.moveCamera("right");
            }
        }
        if (game.currentRoom.exits[3])
        {
            if (y > game.currentRoom.z + 4.8f && y < game.currentRoom.z + 6.1f && x < game.currentRoom.x + 0.05f && game.control.left.getDown())
            {
                // Touching west door
                xvel = 0; yvel = 0;
                canMove = false;
                x -= 0.05f;
                game.moveCamera("left");
            }
        }
    }
}
