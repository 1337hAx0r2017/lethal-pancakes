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
import ap4.graphics.Vertex;

/**
 *
 * @author ed.mason
 */
public class DemoCubeModel extends GouraudColorModelGraphic { // Gouraud shading smoothly interpolates between colored corners
    static ColorVertex[] vbuf;
    static short[] ibuf;
    static PixelShader shader;
    static
    {
        vbuf = new ColorVertex[8]; // a cube has eight corners
        //Each of the folowing vertices' parameters are x, y, z, and a color.
        //In a model definition, it should be centered about "the origin".
        //Positive x is to the right; positive y is up, positive z is toward us.
        vbuf[0] = new ColorVertex(-1, 1, -1, 0xffff0000);
        vbuf[1] = new ColorVertex(1, 1, -1, 0xff00ff00);
        vbuf[2] = new ColorVertex(-1, 1, 1, 0xff0000ff);
        vbuf[3] = new ColorVertex(1, 1, 1, 0xffffff00);
        vbuf[4] = new ColorVertex(-1, -1, -1, 0xffffff00);
        vbuf[5] = new ColorVertex(1, -1, -1, 0xff0000ff);
        vbuf[6] = new ColorVertex(-1, -1, 1, 0xff00ff00);
        vbuf[7] = new ColorVertex(1, -1, 1, 0xffff0000);
        //Now I have to specify which triplets of vertices form triangles.
        //The following array will list sets of three indices into the vbuf above,
        //such that the vertices are specified in a CLOCKWISE order when looking at it.
        //Since a cube consists of six squares, it will consist of 12 triangles,
        //each of which takes three vertices, so I'll have a total of 36 indices.
        ibuf = new short[] { //this is a shortcut for creating an array of initial values
            0, 1, 2, 2, 1, 3, //top face's two triangles
            2, 3, 6, 6, 3, 7, //front face's two triangles
            1, 0, 5, 5, 0, 4, //back face (remember clockwise from perspective!)
            6, 7, 4, 4, 7, 5, //bottom
            0, 2, 4, 4, 2, 6, //left
            3, 1, 7, 7, 1, 5, //right
        };
    }
    
    public DemoCubeModel() {
        super(vbuf, ibuf);
    }
}
