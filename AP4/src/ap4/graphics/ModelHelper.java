/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.graphics;

import java.util.ArrayList;

/**
 *
 * @author ed.mason
 */
public class ModelHelper {
    ArrayList<Vector3> vbuf;
    ArrayList<Short> ibuf;
    public ModelHelper()
    {
        vbuf = new ArrayList<Vector3>();
        ibuf = new ArrayList<Short>();
    }
    public void addTriangle(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3)
    {
        addTriangle(new Vector3(x1,y1,z1),new Vector3(x2,y2,z2),new Vector3(x3,y3,z3));
    }
    public void addTriangle(Vector3 v1, Vector3 v2, Vector3 v3)
    {
        vbuf.add(v1);
        vbuf.add(v2);
        vbuf.add(v3);
        ibuf.add((short)(vbuf.size() - 2));
        ibuf.add((short)(vbuf.size() - 1));
        ibuf.add((short)(vbuf.size() - 0));
    }
    public void addQuad(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4)
    {
        addQuad(new Vector3(x1,y1,z1),new Vector3(x2,y2,z2),new Vector3(x3,y3,z3), new Vector3(x4, y4, z4));
    }
    public void addQuad(Vector3 v1, Vector3 v2, Vector3 v3, Vector3 v4)
    {
        vbuf.add(v1);
        vbuf.add(v2);
        vbuf.add(v3);
        vbuf.add(v4);
        ibuf.add((short)(vbuf.size() - 3));
        ibuf.add((short)(vbuf.size() - 2));
        ibuf.add((short)(vbuf.size() - 1));
        ibuf.add((short)(vbuf.size() - 1));
        ibuf.add((short)(vbuf.size() - 2));
        ibuf.add((short)(vbuf.size() - 0));
    }
    public Vector3[] getVerices() { return (Vector3[])vbuf.toArray(); }
    public Short[] getIndicies() { return (Short[])ibuf.toArray(); }
}
