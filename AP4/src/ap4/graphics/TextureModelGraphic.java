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
public class TextureModelGraphic extends ModelGraphic {

    static class TexturePixelShader extends LightPixelShader
    {
        //BufferedImage texture;
        int stride;
        int width;
        int height;
        int[] data;
        public TexturePixelShader(BufferedImage texture)
        {
            //this.texture = texture;
            data = new int[texture.getWidth() * texture.getHeight() * 4];
            stride = texture.getRaster().getNumDataElements();
            width = texture.getWidth();
            height = texture.getHeight();
            texture.getData().getPixels(0, 0, texture.getWidth(), texture.getHeight(), data);
        }
        public int textureSample(float u, float v)
        {
            int x = (int)(width * u) % width;
            int y = (int)(height * v) % height;
            int base = (y * width+ x) * stride;
            return (data[base + 0]<<16)|(data[base+1]<<8)|(data[base+2]<<0)|(stride == 3 ? 0xff000000 : data[base+3]<<24);
        }
        
        
        @Override
        public int colorAt(Vertex v1, Vertex v2, Vertex v3, float w1, float w2, float w3, float l1, float l2, float l3) {
            float iw1 = 1/w1;
            float iw2 = 1/w2;
            float iw3 = 1/w3;
            float vu1 = ((TextureVertex)v1).u*iw1;
            float vu2 = ((TextureVertex)v2).u*iw2;
            float vu3 = ((TextureVertex)v3).u*iw3;
            float vv1 = ((TextureVertex)v1).v*iw1;
            float vv2 = ((TextureVertex)v2).v*iw2;
            float vv3 = ((TextureVertex)v3).v*iw3;
            
            float w = interpolate(iw1, iw2, iw3, l1, l2, l3);
            int c = textureSample(interpolate(vu1, vu2, vu3, l1, l2, l3) / w, interpolate(vv1, vv2, vv3, l1, l2, l3) / w);
            if(light != null)
            {
                int l = interpolate(vl1, vl2, vl3, l1, l2, l3);
                return Light.multiply(c, l);
            }
            else
                return c;
        }
        
    }
    
    public TextureModelGraphic(TextureVertex[] vbuf, short[] ibuf, BufferedImage texture)
    {
        super(vbuf, ibuf, new TexturePixelShader(texture));
    }

}
