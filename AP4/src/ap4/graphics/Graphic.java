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
public abstract class Graphic {
    public void draw(Camera camera, float x, float z)
    {
        draw(camera, x, 0, z, 1, null);
    }
    public void draw(Camera camera, float x, float y, float z)
    {
        draw(camera, x, y, z, 1, null);
    }
    public void draw(Camera camera, float x, float y, float z, float scale)
    {
        draw(camera, x, y, z, 1, null);
    }
    public void draw(Camera camera, float x, float z, Light light)
    {
        draw(camera, x, 0, z, 1, light);
    }
    public void draw(Camera camera, float x, float y, float z, Light light)
    {
        draw(camera, x, y, z, 1, light);
    }
    public abstract void draw(Camera camera, float x, float y, float z, float scale, Light light);
}
