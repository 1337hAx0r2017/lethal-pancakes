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
public abstract class Light {
    int color;
    abstract int calculateLighting(Vector3 position, Vector3 normal);
    public static int multiply(int c1, int c2)
    {
        int r1 = (c1 >> 16) & 0xff;
        int g1 = (c1 >> 8) & 0xff;
        int b1 = (c1 >> 0) & 0xff;
        int r2 = (c2 >> 16) & 0xff;
        int g2 = (c2 >> 8) & 0xff;
        int b2 = (c2 >> 0) & 0xff;
        return 0xff000000 | (Math.min(255, r1 * r2 / 255)<<16)|(Math.min(255, g1 * g2 / 255)<<8)|(Math.min(255, b1 * b2 / 255)<<0);
    }
    public static int multiply(int c1, float f)
    {
        int r1 = (c1 >> 16) & 0xff;
        int g1 = (c1 >> 8) & 0xff;
        int b1 = (c1 >> 0) & 0xff;
        return 0xff000000 | ((int)Math.min(255,r1 * f)<<16)|((int)Math.min(255,g1 * f)<<8)|((int)Math.min(255,b1 * f)<<0);
    }
    public static int add(int c1, int c2)
    {
        int r1 = (c1 >> 16) & 0xff;
        int g1 = (c1 >> 8) & 0xff;
        int b1 = (c1 >> 0) & 0xff;
        int r2 = (c2 >> 16) & 0xff;
        int g2 = (c2 >> 8) & 0xff;
        int b2 = (c2 >> 0) & 0xff;
        return 0xff000000 | ((int)Math.min(255,r1+r2)<<16)|((int)Math.min(255,g1+g2)<<8)|((int)Math.min(255,b1+b2)<<0);
    }
    public int calculateColor(int color, Vector3 position, Vector3 normal)
    {
        return multiply(color, calculateLighting(position, normal));
    }
}
