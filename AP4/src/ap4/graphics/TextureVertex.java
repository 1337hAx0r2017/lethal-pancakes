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
public class TextureVertex extends Vertex {
    public float u;
    public float v;
    public TextureVertex(Vector3 position, float u, float v)
    {
        this.position = position;
        this.u = u;
        this.v = v;
    }
    public TextureVertex(float x, float y, float z, float u, float v)
    {
        this(new Vector3(x, y, z), u, v);
    }
    public Vertex lerpToward(Vertex v2, float f)
    {
        return new TextureVertex(Vector3.lerp(position, v2.position, f), u * (1 - f) + ((TextureVertex)v2).u * f, v * (1 - f) + ((TextureVertex)v2).v * f);
    }
}
