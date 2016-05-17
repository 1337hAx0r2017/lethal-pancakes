/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.graphics;

import java.util.ArrayList;

/**
 *
 * @author ed.mason
 */
public class LightList extends Light {
    ArrayList<Light> lights;
    public LightList()
    {
        lights = new ArrayList<Light>();
    }
    public void add(Light light)
    {
        lights.add(light);
    }
    int calculateLighting(Vector3 position, Vector3 normal)
    {
        int light = 0xff000000;
        for(int i = 0; i < lights.size(); i++)
            light = add(light, lights.get(i).calculateLighting(position, normal));
        return light;
    }
}
