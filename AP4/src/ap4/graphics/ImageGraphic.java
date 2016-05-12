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
public class ImageGraphic extends Graphic {

    BufferedImage image;
    public ImageGraphic(BufferedImage image)
    {
        this.image = image;
    }
    @Override
    public void draw(Camera camera, float x, float y, float z, float scale) {
        camera.drawImage(new Vector3(x, y, z), image, scale);
    }
}
