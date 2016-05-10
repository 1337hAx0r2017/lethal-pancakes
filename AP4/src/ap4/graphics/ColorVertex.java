/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.graphics;

/**
 *
 * @author ed.mason
 */
public class ColorVertex extends Vertex {
    public int color;
    public ColorVertex(Vector3 position, int color)
    {
        this.position = position;
        this.color = 0xff000000 | color;
    }
    public ColorVertex(float x, float y, float z, int color)
    {
        this(new Vector3(x, y, z), color);
    }
    private static int colorLerp(int c1, int c2, float f)
    {
        int r = (((c1>>16)&0xff) * (int)(255 - f * 255) + ((c2>>16)&0xff) * (int)(f * 255)) / 255;
        int g = (((c1>>8)&0xff) * (int)(255 - f * 255) + ((c2>>8)&0xff) * (int)(f * 255)) / 255;
        int b = (((c1>>0)&0xff) * (int)(255 - f * 255) + ((c2>>0)&0xff) * (int)(f * 255)) / 255;
        return 0xff000000 | (r<<16) | (g<<8) | (b<<0);
    }
    public Vertex lerpToward(Vertex v2, float f)
    {
        return new ColorVertex(Vector3.lerp(position, v2.position, f), colorLerp(color, ((ColorVertex)v2).color, f));
        
    }
    
    
}
