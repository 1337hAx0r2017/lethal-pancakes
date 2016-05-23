/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.graphics;

import java.awt.image.BufferedImage;

/**
 *
 * @author ed.mason
 */
public class SolidColorModelGraphic extends ModelGraphic {

    static class SolidColorPixelShader extends LightPixelShader
    {
        int color;
        public SolidColorPixelShader(int color)
        {
            this.color = color;
        }
        @Override
        public int colorAt(Vertex v1, Vertex v2, Vertex v3, float w1, float w2, float w3, float l1, float l2, float l3) {
            if(light != null)
            {
                int c1 = Light.multiply(color, vl1);
                int c2 = Light.multiply(color, vl2);
                int c3 = Light.multiply(color, vl3);
                return 0xff000000 | interpolate(c1, c2, c3, l1, l2, l3);
            }
            else
                return color;
        }
    }

    public SolidColorModelGraphic(Vertex[] vbuf, short[] ibuf, int color)
    {
        super(vbuf, ibuf, new SolidColorPixelShader(color));
    }

}
