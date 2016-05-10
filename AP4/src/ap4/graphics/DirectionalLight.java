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
public class DirectionalLight extends Light {

    Vector3 direction;
    public DirectionalLight(int color, Vector3 direction)
    {
        this.direction = Vector3.multiply(direction,-1);
        this.color = color;
    }
    
    @Override
    int calculateLighting(Vector3 position, Vector3 normal) {
        float f = Math.max(Vector3.dot(direction, normal), 0);
        return multiply(color, f);
    }
    
}
