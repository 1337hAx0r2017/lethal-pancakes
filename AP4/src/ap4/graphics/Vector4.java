
package ap4.graphics;


public class Vector4 {
    public float x;
    public float y;
    public float z;
    public float w;
    public Vector4()
    {
    }
    public Vector4(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    /*public static float dot(Vector3 a, Vector3 b)
    {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }
    public static Vector3 cross(Vector3 a, Vector3 b)
    {
        return new Vector3(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
    }
    public static Vector3 add(Vector3 a, Vector3 b)
    {
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }
    public static Vector3 subtract(Vector3 a, Vector3 b)
    {
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }
    public static Vector3 multiply(Vector3 a, Vector3 b)
    {
        return new Vector3(a.x * b.x, a.y * b.y, a.z * b.z);
    }
    public static Vector3 multiply(Vector3 a, float b)
    {
        return new Vector3(a.x * b, a.y * b, a.z * b);
    }*/
    public Vector3 toScreenSpace()
    {
        return new Vector3(x/w, y/w, z/w);
    }
    public String toString()
    {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }
}
