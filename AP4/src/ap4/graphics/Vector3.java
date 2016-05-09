
package ap4.graphics;

public class Vector3 {
    public float x;
    public float y;
    public float z;
    public Vector3()
    {
    }
    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public static float dot(Vector3 a, Vector3 b)
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
    }
    public static Vector3 normalize(Vector3 v)
    {
        float length = (float)Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
        return new Vector3(v.x/length,v.y/length,v.z/length);
    }
    public String toString()
    {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
