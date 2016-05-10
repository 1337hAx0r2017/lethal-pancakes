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

    static class TexturePixelShader extends PixelShader
    {
        BufferedImage texture;
        int[] data;
        public TexturePixelShader(BufferedImage texture)
        {
            this.texture = texture;
            data = new int[texture.getWidth() * texture.getHeight() * 4];
            texture.getData().getPixels(0, 0, texture.getWidth(), texture.getHeight(), data);
        }
        public int textureSample(float u, float v)
        {
            u %= 1;
            v %= 1;
            int x = (int)(texture.getWidth() * u);
            int y = (int)(texture.getHeight() * v);
            int base = (y * texture.getWidth() + x) * texture.getRaster().getNumDataElements();
            return (data[base + 0]<<16)|(data[base+1]<<8)|(data[base+2]<<0)|(texture.getRaster().getNumDataElements() == 3 ? 0xff000000 : data[base+3]<<24);
        }
        
        
        @Override
        public int colorAt(Vertex v1, Vertex v2, Vertex v3, float l1, float l2, float l3) {
            float vu1 = ((TextureVertex)v1).u;
            float vu2 = ((TextureVertex)v2).u;
            float vu3 = ((TextureVertex)v3).u;
            float vv1 = ((TextureVertex)v1).v;
            float vv2 = ((TextureVertex)v2).v;
            float vv3 = ((TextureVertex)v3).v;
            return textureSample(interpolate(vu1, vu2, vu3, l1, l2, l3), interpolate(vv1, vv2, vv3, l1, l2, l3));
        }
        
    }
    
    public TextureModelGraphic(TextureVertex[] vbuf, short[] ibuf, BufferedImage texture)
    {
        super(vbuf, ibuf, new TexturePixelShader(texture));
    }

}
