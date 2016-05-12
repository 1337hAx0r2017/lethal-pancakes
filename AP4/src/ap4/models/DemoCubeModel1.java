/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.models;

import ap4.graphics.ColorVertex;
import ap4.graphics.GouraudColorModelGraphic;
import ap4.graphics.ModelGraphic;
import ap4.graphics.PixelShader;
import ap4.graphics.SolidColorModelGraphic;
import ap4.graphics.Vector3;
import ap4.graphics.Vertex;

/**
 *
 * @author ed.mason
 */
public class DemoCubeModel1 extends GouraudColorModelGraphic { // Gouraud shading smoothly interpolates between colored corners
    static ColorVertex[] vbuf;
    static short[] ibuf;
    static PixelShader shader;
    static
    {
        ModelHelper h = new ModelHelper();
        //A cube has six faces, so I'm going to create six "quads".
        //In a model definition, it should be centered about "the origin".
        //Positive x is to the right; positive y is up, positive z is toward us.
        //These points will be wound in a clockwise order when looking at the face.
        h.addQuad(-1, 1, -1, 1, 1, -1, 1, 1, 1, -1, 1, 1); //top face, four xyz coordinates, in clockwise order.
        h.addQuad(-1, 1, 1, 1, 1, 1, 1, -1, 1, -1, -1, 1); //front face, four xyz coordinates, in clockwise order.
        h.addQuad(-1, 1, -1, -1, 1, 1, -1, -1, 1, -1, -1, -1); //left face, four xyz coordinates, in clockwise order.
        h.addQuad(1, 1, 1, 1, 1, -1, 1, -1, -1, 1, -1, 1); //right face, four xyz coordinates, in clockwise order.
        h.addQuad(1, 1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1); //back face, four xyz coordinates, in clockwise order.
        h.addQuad(-1, -1, 1, 1, -1, 1, 1, -1, -1, -1, -1, -1); //bottom face, four xyz coordinates, in clockwise order.
        //Now that my helper has all six quads, I can extract the vertices and indices
        ibuf = h.getIndicies();
        Vector3[] corners = h.getVerices();
        vbuf = new ColorVertex[corners.length];
        for(int i = 0; i < corners.length; i++)
            vbuf[i] = new ColorVertex(corners[i], 0xffffffff); // a position and a color
    }
    
    public DemoCubeModel1() {
        super(vbuf, ibuf);
    }
}
