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
public class AmbientLight extends Light {

    public AmbientLight(int color)
    {
        this.color = 0xff000000 | color;
    }
    
    @Override
    int calculateLighting(Vector3 position, Vector3 normal) {
        return color;
    }
    
}
