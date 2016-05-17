/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.graphics;

import ap4.graphics.TextureModelGraphic.TexturePixelShader;
import java.awt.image.BufferedImage;

/**
 *
 * @author ed.mason
 */
public class FlatImageGraphic extends ModelGraphic {

    static TextureVertex[] vbuf;
    static short[] ibuf;
    static
    {
        vbuf = new TextureVertex[]
        {
            new TextureVertex(-1, 0, -1, 0, 0),
            new TextureVertex(1, 0, -1, 1, 0),
            new TextureVertex(-1, 0, 1, 0, 1),
            new TextureVertex(1, 0, 1, 1, 1),
        };
        ibuf = new short[] { 0, 1, 2, 2, 1, 3, };
    }
    BufferedImage image;
    public FlatImageGraphic(BufferedImage image)
    {
        super(vbuf, ibuf, new TexturePixelShader(image));
    }
    @Override
    public void draw(Camera camera, float x, float y, float z, float scale) {
        draw(camera, Matrix.multiply(Matrix.createScale(scale), Matrix.createTranslation(x, y, z)), null);
    }
    @Override
    public void draw(Camera camera, Matrix world, Light light) {
        PixelShader shader = getPixelShader();
        if(shader != null && shader instanceof LightPixelShader)
            ((LightPixelShader)shader).setPreferredLight(light);
        camera.drawTriangle(vbuf[0], vbuf[1], vbuf[2], shader);
        camera.drawTriangle(vbuf[2], vbuf[3], vbuf[0], shader);
    }
}
