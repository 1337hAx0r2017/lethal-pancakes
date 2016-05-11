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
public abstract class LightPixelShader extends PixelShader {
    Light light;
    Vector3 normal;
    Matrix world;
    public void setPreferredLight(Light light) { this.light = light; }
    public void setNormal(Vector3 normal) { this.normal = normal; }
    public void setWorld(Matrix world) { this.world = world; }
    public void setNormal(Vertex v1, Vertex v2, Vertex v3) { this.normal = Vector3.normalize(world.transformNormal(Vector3.cross(Vector3.subtract(v3.position, v1.position), Vector3.subtract(v2.position, v1.position)))); }
}
