package ap4.items;

import ap4.Etc;
import ap4.InvItem;
import ap4.Player;
import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import gui.Inventory;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class WoodenSword extends InvItem {
    
    private float w = 1.6195f;
    private float h = 2.5f;
    
    public WoodenSword()
    { 
        try
        {
            visual = ImageIO.read(new URL(Etc.host + "woodensword.png"));
        }
        catch (IOException ex)
        { Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex); }
        
        try
        {
            animFrames.add(new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 0, 0, 0, 0),
                        new TextureVertex(w, 0, 0, 1, 0),
                        new TextureVertex(0, 0, h, 0, 1),
                        new TextureVertex(w, 0, h, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "wsframe1.png"))));
            animFrames.add(new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 0, 0, 0, 0),
                        new TextureVertex(w, 0, 0, 1, 0),
                        new TextureVertex(0, 0, h, 0, 1),
                        new TextureVertex(w, 0, h, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "wsframe2.png"))));
            animFrames.add(new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 0, 0, 0, 0),
                        new TextureVertex(w, 0, 0, 1, 0),
                        new TextureVertex(0, 0, h, 0, 1),
                        new TextureVertex(w, 0, h, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "wsframe3.png"))));
            animFrames.add(new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 0, 0, 0, 0),
                        new TextureVertex(w, 0, 0, 1, 0),
                        new TextureVertex(0, 0, h, 0, 1),
                        new TextureVertex(w, 0, h, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "wsframe4.png"))));
            animFrames.add(new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 0, 0, 0, 0),
                        new TextureVertex(w, 0, 0, 1, 0),
                        new TextureVertex(0, 0, h, 0, 1),
                        new TextureVertex(w, 0, h, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "wsframe5.png"))));
            animFrames.add(new TextureModelGraphic(
                    new TextureVertex[]
                    {
                        new TextureVertex(0, 0, 0, 0, 0),
                        new TextureVertex(w, 0, 0, 1, 0),
                        new TextureVertex(0, 0, h, 0, 1),
                        new TextureVertex(w, 0, h, 1, 1),
                    },
                    new short[]
                    {
                        0, 1, 2,
                        2, 1, 3,
                    },
                    ImageIO.read(new URL(Etc.host + "wsframe6.png"))));
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    public void use(Player o)
    {
        
    }
    
    public void draw(Graphics g)
    {
        
    }
}