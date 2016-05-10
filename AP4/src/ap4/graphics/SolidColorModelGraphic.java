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

    static class SolidColorPixelShader extends PixelShader
    {
        int color;
        public SolidColorPixelShader(int color)
        {
            this.color = color;
        }
        @Override
        public int colorAt(Vertex v1, Vertex v2, Vertex v3, float l1, float l2, float l3) {
            return color;
        }
    }

    public SolidColorModelGraphic(Vertex[] vbuf, short[] ibuf, int color)
    {
        super(vbuf, ibuf, new SolidColorPixelShader(color));
    }

}
