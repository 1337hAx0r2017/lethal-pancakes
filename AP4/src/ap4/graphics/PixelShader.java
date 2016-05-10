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
public abstract class PixelShader {
    public abstract int colorAt(Vertex v1, Vertex v2, Vertex v3, float l1, float l2, float l3);
    protected static Vector3 interpolate(Vector3 v1, Vector3 v2, Vector3 v3, float l1, float l2, float l3)
    {
        return new Vector3(v1.x * l1 + v2.x * l2 + v3.x * l3, v1.y * l1 + v2.y * l2 + v3.y * l3, v1.z * l1 + v2.z * l2 + v3.z * l3);
    }
    protected static float interpolate(float v1, float v2, float v3, float l1, float l2, float l3)
    {
        return v1 * l1 + v2 * l2 + v3 * l3;
    }
    protected static int interpolate(int c1, int c2, int c3, float l1, float l2, float l3)
    {
        int r = (((c1>>16)&0xff) * (int)(l1 * 255) + ((c2>>16)&0xff) * (int)(l2 * 255) + ((c3>>16)&0xff) * (int)(l3 * 255)) / 255;
        int g = (((c1>>8)&0xff) * (int)(l1 * 255) + ((c2>>8)&0xff) * (int)(l2 * 255) + ((c3>>8)&0xff) * (int)(l3 * 255)) / 255;
        int b = (((c1>>0)&0xff) * (int)(l1 * 255) + ((c2>>0)&0xff) * (int)(l2 * 255) + ((c3>>0)&0xff) * (int)(l3 * 255)) / 255;
        return 0xff000000 | (r<<16) | (g<<8) | (b<<0);
    }
}
