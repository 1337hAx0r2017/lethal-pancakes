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
public class Vertex {
    public Vector3 position;
    public Vertex()
    {
        position = new Vector3();
    }
    public Vertex(Vector3 position)
    {
        this.position = position;
    }
    public Vertex(float x, float y, float z)
    {
        this.position = new Vector3(x, y, z);
    }
    public Vertex lerpToward(Vertex v2, float f)
    {
        return new Vertex(Vector3.lerp(position, v2.position, f));
    }
    
}
