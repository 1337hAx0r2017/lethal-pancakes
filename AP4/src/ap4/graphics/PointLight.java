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
public class PointLight extends Light {

    public Vector3 source;
    public float power;
    public PointLight(int color, float x, float y, float z, float power)
    {
        this(color, new Vector3(x, y, z), power);
    }
    public PointLight(int color, Vector3 source, float power)
    {
        this.source = source;
        this.color = color;
        this.power = power;
    }
    
    @Override
    int calculateLighting(Vector3 position, Vector3 normal) {
        float r2 = Vector3.distanceSquared(position, source);
        if(r2 > power * 100)
            return 0xff000000;
        Vector3 direction = Vector3.normalize(Vector3.subtract(source, position));
        float f = power * Math.max(Vector3.dot(direction, normal), 0) / r2;
        return 0xff000000 | multiply(color, f);
    }
    
}
