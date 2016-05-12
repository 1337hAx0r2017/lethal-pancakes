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

    Vector3 directionToLight;
    public DirectionalLight(int color, float x, float y, float z)
    {
        this(color, Vector3.normalize(new Vector3(x,y,z)));
    }
    public DirectionalLight(int color, Vector3 direction)
    {
        this.directionToLight = Vector3.multiply(direction,-1);
        this.color = color;
    }
    
    public void setDirection(float x, float y, float z)
    {
        directionToLight = Vector3.normalize(new Vector3(-x, -y, -z));
    }
    
    @Override
    int calculateLighting(Vector3 position, Vector3 normal) {
        float f = Math.max(Vector3.dot(directionToLight, normal), 0);
        return 0xff000000 | multiply(color, f);
    }
    
}
