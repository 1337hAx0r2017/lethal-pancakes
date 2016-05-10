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

    Vector3 source;
    public PointLight(int color, Vector3 source)
    {
        this.source = source;
        this.color = color;
    }
    
    @Override
    int calculateLighting(Vector3 position, Vector3 normal) {
        Vector3 direction = Vector3.normalize(Vector3.subtract(source, position));
        float f = Math.max(Vector3.dot(direction, normal), 0) / Vector3.distanceSquared(position, source);
        return multiply(color, f);
    }
    
}
