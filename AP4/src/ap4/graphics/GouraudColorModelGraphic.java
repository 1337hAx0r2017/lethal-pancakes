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
public class GouraudColorModelGraphic extends ModelGraphic {

    static class GouraudPixelShader extends LightPixelShader
    {
        @Override
        public int colorAt(Vertex v1, Vertex v2, Vertex v3, float w1, float w2, float w3, float l1, float l2, float l3) {
            int c1 = ((ColorVertex)v1).color;
            int c2 = ((ColorVertex)v2).color;
            int c3 = ((ColorVertex)v3).color;
            if(light != null)
            {
                c1 = Light.multiply(c1, vl1);
                c2 = Light.multiply(c2, vl2);
                c3 = Light.multiply(c3, vl3);
            }
            return 0xff000000 | interpolate(c1, c2, c3, l1, l2, l3);
        }
    }
    ColorVertex[] VertexBuffer;
    public GouraudColorModelGraphic(ColorVertex[] vbuf, short[] ibuf)
    {
        super(vbuf, ibuf, new GouraudPixelShader());
    }
    
}
