
package ap4.graphics;


public class Matrix {

    float m11;
    float m12;
    float m13;
    float m14;
    float m21;
    float m22;
    float m23;
    float m24;
    float m31;
    float m32;
    float m33;
    float m34;
    float m41;
    float m42;
    float m43;
    float m44;
    public Matrix()
    {
        m11 = 1;
        m22 = 1;
        m33 = 1;
        m44 = 1;
    }
    public Matrix(Matrix m)
    {
        m11 = m.m11;
        m12 = m.m12;
        m13 = m.m13;
        m14 = m.m14;
        m21 = m.m21;
        m22 = m.m22;
        m23 = m.m23;
        m24 = m.m24;
        m31 = m.m31;
        m32 = m.m32;
        m33 = m.m33;
        m34 = m.m34;
        m41 = m.m41;
        m42 = m.m42;
        m43 = m.m43;
        m44 = m.m44;
    }
    public static final Matrix IDENTITY;
    static
    {
        IDENTITY = new Matrix();
    }
    public static Matrix createRotationX(double angle)
    {
        Matrix result = new Matrix();
        result.m22 = (float)Math.cos(angle);
        result.m32 = (float)Math.sin(angle);
        result.m33 = (float)Math.cos(angle);
        result.m23 = -(float)Math.sin(angle);
        return result;
    }
    public static Matrix createRotationY(double angle)
    {
        Matrix result = new Matrix();
        result.m11 = (float)Math.cos(angle);
        result.m31 = -(float)Math.sin(angle);
        result.m33 = (float)Math.cos(angle);
        result.m13 = (float)Math.sin(angle);
        return result;
    }
    public static Matrix createRotationZ(double angle)
    {
        Matrix result = new Matrix();
        result.m11 = (float)Math.cos(angle);
        result.m21 = (float)Math.sin(angle);
        result.m22 = (float)Math.cos(angle);
        result.m12 = -(float)Math.sin(angle);
        return result;
    }
    public static Matrix createTranslation(Vector3 position)
    {
        return createTranslation(position.x, position.y, position.z);
    }
    public static Matrix createTranslation(float x, float y, float z)
    {
        Matrix result = new Matrix();
        result.m14 = x;
        result.m24 = y;
        result.m34 = z;
        return result;
    }
    public static Matrix createScale(float s)
    {
        Matrix result = new Matrix();
        result.m11 = s;
        result.m22 = s;
        result.m33 = s;
        return result;
    }
    public static Matrix createProjection(double fov, float aspect, float near, float far)
    {
        Matrix result = new Matrix();
        float tan = (float)Math.tan(fov / 2);
        float lensWidth = tan * near;
        float lensHeight = tan * near / aspect;
        result.m11 = 1 / lensWidth;
        result.m14 = 0;
        result.m22 = 1 / lensHeight;
        result.m24 = 0;
        result.m33 = 1 / (far - near);
        result.m34 = near / (far - near) + 1;
        result.m43 = (1 - far/near)/(far-near);//-1 / near;
        result.m44 = 0;
        return result;
    }
    private static float rescale(float src1, float src2, float dest1, float dest2, float num)
    {
        return (num - src1) / (src2 - src1) * (dest2 - dest1) + dest1;
    }
    public static Matrix multiply(Matrix a, Matrix b)
    {
        Matrix result = new Matrix();
        result.m11 = a.m11 * b.m11 + a.m21 * b.m12 + a.m31 * b.m13 + a.m41 * b.m14;
        result.m12 = a.m12 * b.m11 + a.m22 * b.m12 + a.m32 * b.m13 + a.m42 * b.m14;
        result.m13 = a.m13 * b.m11 + a.m23 * b.m12 + a.m33 * b.m13 + a.m43 * b.m14;
        result.m14 = a.m14 * b.m11 + a.m24 * b.m12 + a.m34 * b.m13 + a.m44 * b.m14;
        result.m21 = a.m11 * b.m21 + a.m21 * b.m22 + a.m31 * b.m23 + a.m41 * b.m24;
        result.m22 = a.m12 * b.m21 + a.m22 * b.m22 + a.m32 * b.m23 + a.m42 * b.m24;
        result.m23 = a.m13 * b.m21 + a.m23 * b.m22 + a.m33 * b.m23 + a.m43 * b.m24;
        result.m24 = a.m14 * b.m21 + a.m24 * b.m22 + a.m34 * b.m23 + a.m44 * b.m24;
        result.m31 = a.m11 * b.m31 + a.m21 * b.m32 + a.m31 * b.m33 + a.m41 * b.m34;
        result.m32 = a.m12 * b.m31 + a.m22 * b.m32 + a.m32 * b.m33 + a.m42 * b.m34;
        result.m33 = a.m13 * b.m31 + a.m23 * b.m32 + a.m33 * b.m33 + a.m43 * b.m34;
        result.m34 = a.m14 * b.m31 + a.m24 * b.m32 + a.m34 * b.m33 + a.m44 * b.m34;
        result.m41 = a.m11 * b.m41 + a.m21 * b.m42 + a.m31 * b.m43 + a.m41 * b.m44;
        result.m42 = a.m12 * b.m41 + a.m22 * b.m42 + a.m32 * b.m43 + a.m42 * b.m44;
        result.m43 = a.m13 * b.m41 + a.m23 * b.m42 + a.m33 * b.m43 + a.m43 * b.m44;
        result.m44 = a.m14 * b.m41 + a.m24 * b.m42 + a.m34 * b.m43 + a.m44 * b.m44;
        return result;
    }
    public Vector4 transform(Vector4 v)
    {
        return new Vector4(m11 * v.x + m12 * v.y + m13 * v.z + m14 * v.w, m21 * v.x + m22 * v.y + m23 * v.z + m24 * v.w, m31 * v.x + m32 * v.y + m33 * v.z + m34 * v.w, m41 * v.x + m42 * v.y + m43 * v.z + m44 * v.w);
    }
    public Vector3 transform(Vector3 v)
    {
        return new Vector3(m11 * v.x + m12 * v.y + m13 * v.z + m14, m21 * v.x + m22 * v.y + m23 * v.z + m24, m31 * v.x + m32 * v.y + m33 * v.z + m34);
    }
    public Vector3 transformNormal(Vector3 v)
    {
        return new Vector3(m11 * v.x + m12 * v.y + m13 * v.z, m21 * v.x + m22 * v.y + m23 * v.z, m31 * v.x + m32 * v.y + m33 * v.z);
    }
    public Matrix inverse()
    {
        Matrix inverse = new Matrix(this);
        inverse.invert();
        return inverse;
    }
    public void invert()
    {
        float a1 = 1 / m11;
        m11 = 1;
        m12 = m12 * a1;
        m13 = m13 * a1;
        m14 = m14 * a1;
        m21 -= m21 * m11;
        m22 -= m21 * m12;
        m23 -= m21 * m13;
        m24 -= m21 * m14;
    }
}
