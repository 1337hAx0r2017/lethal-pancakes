/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.models;

import ap4.graphics.ColorVertex;
import ap4.graphics.PixelShader;
import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import ap4.graphics.Vector3;
import java.awt.image.BufferedImage;

/**
 *
 * @author ed.mason
 */
public class DoorModel extends TextureModelGraphic {
    static TextureVertex[] vbuf;
    static short[] ibuf;
    static PixelShader shader;
    static
    {
        ModelHelper h = new ModelHelper();
        h.addQuad(-1, 1, 1, -.75f, 1, 1, -.75f, 0, 1, -1, 0, 1);
        h.addQuad(-.75f, 1, 1, .75f, 1, 1, .75f, .75f, 1, -.75f, .75f, 1);
        h.addQuad(.75f, 1, 1, 1, 1, 1, 1, 0, 1, -.75f, 0, 1);
        h.addQuad(-.75f, .75f, 1, -.75f, .75f, 0, -.75f, 0, 0, -.75f, 0, 1);
        h.addQuad(.75f, .75f, 0, .75f, .75f, 1, .75f, 0, 1, .75f, 0, 0);
        h.addQuad(-.75f, 0, 0, .75f, 0, 0, .75f, 0, 1, -.75f, 0, 1);
        vbuf = new TextureVertex[24];
        Vector3[] corners = h.getVerices();
        for(int i = 0; i < 12; i++)
            vbuf[i] = new TextureVertex(corners[i], corners[i].x + 1, 1 - corners[i].y);
        for(int i = 12; i < 24; i++)
            vbuf[i] = new TextureVertex(corners[i], i % 2, (i % 4) / 2);
        ibuf = h.getIndicies();
    }
    public DoorModel(BufferedImage texture)
    {
        super(vbuf, ibuf, texture);
    }
}
