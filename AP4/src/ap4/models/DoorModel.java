/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.models;

import ap4.graphics.ColorVertex;
import ap4.graphics.GouraudColorModelGraphic;
import ap4.graphics.Light;
import ap4.graphics.PixelShader;
import ap4.graphics.TextureModelGraphic;
import ap4.graphics.TextureVertex;
import ap4.graphics.Vector3;
import java.awt.image.BufferedImage;

/**
 *
 * @author ed.mason
 */
public class DoorModel extends GouraudColorModelGraphic {
    static ColorVertex[] vbuf;
    static short[] ibuf;
    static PixelShader shader;
    static
    {
        ModelHelper h = new ModelHelper();
        h.addQuad(-1, 1, 1, -.75f, 1, 1, -.75f, 0, 1, -1, 0, 1);
        h.addQuad(-.75f, 1, 1, .75f, 1, 1, .75f, .75f, 1, -.75f, .75f, 1);
        h.addQuad(.75f, 1, 1, 1, 1, 1, 1, 0, 1, .75f, 0, 1);
        h.addQuad(-.75f, .75f, 1, -.75f, .75f, 0, -.75f, 0, 0, -.75f, 0, 1);
        h.addQuad(.75f, .75f, 0, .75f, .75f, 1, .75f, 0, 1, .75f, 0, 0);
        h.addQuad(-.75f, 0, 0, .75f, 0, 0, .75f, 0, 1, -.75f, 0, 1);
        vbuf = new ColorVertex[24];
        Vector3[] corners = h.getVerices();
        for(int i = 0; i < 12; i++)
            vbuf[i] = new ColorVertex(corners[i], Light.add(0xff807040, Light.multiply(0xff405080, corners[i].y)));
        for(int i = 12; i < 24; i++)
            vbuf[i] = new ColorVertex(corners[i], Light.multiply(0xff807040, corners[i].z));
        ibuf = h.getIndicies();
    }
    public DoorModel()
    {
        super(vbuf, ibuf);
    }
}
