
package ap4.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;


public class Camera {
    float x;
    float y;
    float z;
    double fov;
    double pan;
    double tilt;
    float near;
    float far;
    private Matrix world;
    private Matrix view;
    private Matrix projection;
    private Matrix worldView;
    private Matrix viewProjection;
    int width;
    int height;
    DataBufferInt databuf;
    BufferedImage backbuf;
    WritableRaster raster;
    //DataBuffer dataBuffer;
    int[] pixels;
    float[] zbuf;
    private static final float[] inputvbuf;
    private static final float[] viewvbuf;
    private static final float[] projectionvbuf;
    private static final Vertex[] vertexWheel;
    private boolean useZbuf;
    private final Vector3 lightDir;
    private boolean additive;
    static
    {
        inputvbuf = new float[16];
        viewvbuf = new float[16];
        projectionvbuf = new float[16];
        vertexWheel = new Vertex[4];
    }
    public Camera(int screenWidth, int screenHeight)
    {
        view = new Matrix();
        //projection = new Matrix();
        width = screenWidth;
        height = screenHeight;
        pixels = new int[width*height];
        zbuf = new float[width*height];
        databuf = new DataBufferInt(pixels, pixels.length);
        int[] bandMasks = { 0xff0000, 0xff00, 0xff, 0xff000000 };
        raster = Raster.createPackedRaster(databuf, width, height, width, bandMasks, null);
        backbuf = new BufferedImage(ColorModel.getRGBdefault(), raster, ColorModel.getRGBdefault().isAlphaPremultiplied(), null);
        //raster = backbuf.getRaster().createCompatibleWritableRaster();
        //backbuf.setData(raster);
        //dataBuffer = raster.getDataBuffer();
        //raster.getPixels(0, 0, width, height, pixels);
        near = 1;
        far = 50;
        fov = Math.PI / 2;
        lightDir = Vector3.normalize(new Vector3(-1,3,2));
    }
    public void setAdditive(boolean value)
    {
        additive = value;
    }
    public void setPosition(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void setTilt(double degrees)
    {
        tilt = degrees * Math.PI / 180;
    }
    public void setPan(double degrees)
    {
        pan = degrees * Math.PI / 180;
    }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }
    public void setZoom(float zoom)
    {
        fov = Math.atan(zoom) * 2;
    }
    private void setProjection(double fov, float aspect, float near, float far)
    {
        projection = Matrix.createProjection(fov, aspect, near, far);
    }
    private void setView(Vector3 position, double pan, double tilt)
    {
        view = Matrix.createTranslation(-position.x,-position.y,-position.z);
        view = Matrix.multiply(view, Matrix.createRotationY(-pan));
        view = Matrix.multiply(view, Matrix.createRotationX(-tilt));
    }
    private void setView(float x, float y, float z, double pan, double tilt)
    {
        view = Matrix.createTranslation(-x,-y,-z);
        view = Matrix.multiply(view, Matrix.createRotationY(-pan));
        view = Matrix.multiply(view, Matrix.createRotationX(-tilt));
    }
    public void setWorld(Matrix world)
    {
        this.world = world;
        this.worldView = Matrix.multiply(world,view);
    }
    public void setZBuffer(boolean use)
    {
        useZbuf = use;
    }
    private void transformBuffers(float[] vbuf1, float[] vbuf2, Matrix matrix)
    {
        for(int i = 0; i < 4; i++)
        {
            vbuf2[i * 4 + 0] = vbuf1[i * 4 + 0] * matrix.m11 + vbuf1[i * 4 + 1] * matrix.m12 + vbuf1[i * 4 + 2] * matrix.m13 + vbuf1[i * 4 + 3] * matrix.m14;
            vbuf2[i * 4 + 1] = vbuf1[i * 4 + 0] * matrix.m21 + vbuf1[i * 4 + 1] * matrix.m22 + vbuf1[i * 4 + 2] * matrix.m23 + vbuf1[i * 4 + 3] * matrix.m24;
            vbuf2[i * 4 + 2] = vbuf1[i * 4 + 0] * matrix.m31 + vbuf1[i * 4 + 1] * matrix.m32 + vbuf1[i * 4 + 2] * matrix.m33 + vbuf1[i * 4 + 3] * matrix.m34;
            vbuf2[i * 4 + 3] = vbuf1[i * 4 + 0] * matrix.m41 + vbuf1[i * 4 + 1] * matrix.m42 + vbuf1[i * 4 + 2] * matrix.m43 + vbuf1[i * 4 + 3] * matrix.m44;
        }
    }
    private void setBuffer(float[] vbuf, int index, Vector3 v)
    {
        vbuf[index * 4 + 0] = v.x;
        vbuf[index * 4 + 1] = v.y;
        vbuf[index * 4 + 2] = v.z;
        vbuf[index * 4 + 3] = 1;
    }
    private void setBuffer(float[] vbuf, int index, Vector4 v)
    {
        vbuf[index * 4 + 0] = v.x;
        vbuf[index * 4 + 1] = v.y;
        vbuf[index * 4 + 2] = v.z;
        vbuf[index * 4 + 3] = v.w;
    }
    private static void rotateBuffer3(float[] vbuf, Vertex[] vertices)
    {
        float x = vbuf[0];
        float y = vbuf[1];
        float z = vbuf[2];
        float w = vbuf[3];
        for(int i = 0; i < 8; i++)
            vbuf[i] = vbuf[i + 4];
        vbuf[8] = x;
        vbuf[9] = y;
        vbuf[10] = z;
        vbuf[11] = w;
        
        Vertex v = vertices[0];
        vertices[0] = vertices[1];
        vertices[1] = vertices[2];
        vertices[2] = v;
    }
    private boolean checkEntirelyBehind(float[] vbuf)
    {
        for(int i = 0; i < 3; i++)
            if(vbuf[i*4 + 2] <= -near)
                return false;
        return true;
    }
    private static boolean checkCounterClockwise(float[] vbuf)
    {
        float x1 = vbuf[0*4+0]/vbuf[0*4+3];
        float x2 = vbuf[1*4+0]/vbuf[1*4+3];
        float x3 = vbuf[2*4+0]/vbuf[2*4+3];
        float y1 = vbuf[0*4+1]/vbuf[0*4+3];
        float y2 = vbuf[1*4+1]/vbuf[1*4+3];
        float y3 = vbuf[2*4+1]/vbuf[2*4+3];
        return (x2-x1) * (y3-y1) - (x3-x1) * (y2-y1) >= 0;
    }
    public int calculateLitColor(int color, Vector3 normal)
    {
        float dot = Math.max(0, Vector3.dot(normal, lightDir));
        int r = (color >> 16) & 0xff;
        int g = (color >> 8) & 0xff;
        int b = (color >> 0) & 0xff;
        return 0xff000000 | ((int)(r * dot)<<16) | ((int)(g * dot)<<8) | ((int)(b * dot)<<0);
    }
    
    private byte prepareVertices(Vertex v1, Vertex v2, Vertex v3)
    {
        setBuffer(inputvbuf, 0, v1.position);
        setBuffer(inputvbuf, 1, v2.position);
        setBuffer(inputvbuf, 2, v3.position);
        vertexWheel[0] = v1;
        vertexWheel[1] = v2;
        vertexWheel[2] = v3;
        vertexWheel[3] = null;
        transformBuffers(inputvbuf, viewvbuf, worldView);
        if(checkEntirelyBehind(viewvbuf))
            return 0;
        int repeatcount = 0;
        while(viewvbuf[0*4+2] > -near || viewvbuf[1*4+2] > -near && viewvbuf[2*4+2] <= -near) // get any behind to the end
        {
            rotateBuffer3(viewvbuf, vertexWheel);
            repeatcount++;
            if(repeatcount == 3)
            {
                System.err.println("Something went wrong; tell Mr. Mason:");
                for(int i = 0; i < 3; i++)
                {
                    System.err.print("(");
                    for (int j = 0; j < 4; j++)
                        System.err.print(viewvbuf[i * 4 + j] + (j < 3 ? ", " : ")\r\n"));
                }
                throw new Error("Something went wrong");
            }
        }
        if(viewvbuf[2*4+2] > -near)
        {
            if(viewvbuf[1*4+2] > -near) // if both points are behind
            {
                float t2 = (-near  - viewvbuf[0*4+2]) / (viewvbuf[1*4+2] - viewvbuf[0*4+2]);
                viewvbuf[1*4+0] = viewvbuf[0*4+0] + (viewvbuf[1*4+0] - viewvbuf[0*4+0]) * t2;
                viewvbuf[1*4+1] = viewvbuf[0*4+1] + (viewvbuf[1*4+1] - viewvbuf[0*4+1]) * t2;
                viewvbuf[1*4+2] = -near;
                viewvbuf[1*4+3] = 1;
                vertexWheel[1] = vertexWheel[0].lerpToward(vertexWheel[1], t2);
                float t3 = (-near  - viewvbuf[0*4+2]) / (viewvbuf[2*4+2] - viewvbuf[0*4+2]);
                viewvbuf[2*4+0] = viewvbuf[0*4+0] + (viewvbuf[2*4+0] - viewvbuf[0*4+0]) * t3;
                viewvbuf[2*4+1] = viewvbuf[0*4+1] + (viewvbuf[2*4+1] - viewvbuf[0*4+1]) * t3;
                viewvbuf[2*4+2] = -near;
                viewvbuf[2*4+3] = 1;
                vertexWheel[2] = vertexWheel[0].lerpToward(vertexWheel[2], t3);
                transformBuffers(viewvbuf, projectionvbuf, projection);
                if(checkCounterClockwise(projectionvbuf))
                    return 0;
                else
                    return (byte)(3 | (repeatcount<<4));
            }
            else //only v3 is behind
            {
                float t13 = (-near  - viewvbuf[0*4+2]) / (viewvbuf[2*4+2] - viewvbuf[0*4+2]);
                viewvbuf[3*4+0] = viewvbuf[0*4+0] + (viewvbuf[2*4+0] - viewvbuf[0*4+0]) * t13;
                viewvbuf[3*4+1] = viewvbuf[0*4+1] + (viewvbuf[2*4+1] - viewvbuf[0*4+1]) * t13;
                viewvbuf[3*4+2] = -near;
                viewvbuf[3*4+3] = 1;
                vertexWheel[3] = vertexWheel[0].lerpToward(vertexWheel[2], t13);
                float t23 = (-near  - viewvbuf[1*4+2]) / (viewvbuf[2*4+2] - viewvbuf[1*4+2]);
                viewvbuf[2*4+0] = viewvbuf[1*4+0] + (viewvbuf[2*4+0] - viewvbuf[1*4+0]) * t23;
                viewvbuf[2*4+1] = viewvbuf[1*4+1] + (viewvbuf[2*4+1] - viewvbuf[1*4+1]) * t23;
                viewvbuf[2*4+2] = -near;
                viewvbuf[2*4+3] = 1;
                vertexWheel[2] = vertexWheel[1].lerpToward(vertexWheel[2], t23);
                transformBuffers(viewvbuf, projectionvbuf, projection);
                if(checkCounterClockwise(projectionvbuf))
                    return 0;
                else
                    //draw quad
                    return (byte)(4 | (repeatcount<<4));
            }
        }
        else
        {
            transformBuffers(viewvbuf, projectionvbuf, projection);
            if(checkCounterClockwise(projectionvbuf))
                return 0;
            else
                return (byte)(3 | (repeatcount<<4));
        }
    }
    public void drawTriangle(Vertex v1, Vertex v2, Vertex v3, PixelShader shader)
    {
        byte result = prepareVertices(v1, v2, v3);
        
        if((result & 7) == 3) // three vertices used
        {
            int x1 = (int)(width / 2 * (projectionvbuf[0*4+0]/projectionvbuf[0*4+3] + 1)); 
            int y1 = (int)(height / 2 * (-projectionvbuf[0*4+1]/projectionvbuf[0*4+3] + 1)); 
            int x2 = (int)(width / 2 * (projectionvbuf[1*4+0]/projectionvbuf[1*4+3] + 1)); 
            int y2 = (int)(height / 2 * (-projectionvbuf[1*4+1]/projectionvbuf[1*4+3] + 1)); 
            int x3 = (int)(width / 2 * (projectionvbuf[2*4+0]/projectionvbuf[2*4+3] + 1)); 
            int y3 = (int)(height / 2 * (-projectionvbuf[2*4+1]/projectionvbuf[2*4+3] + 1)); 
            drawTriangle(x1,y1,projectionvbuf[0*4+2],projectionvbuf[0*4+3],vertexWheel[0],x2,y2,projectionvbuf[1*4+2],projectionvbuf[1*4+3],vertexWheel[1],x3,y3,projectionvbuf[2*4+2],projectionvbuf[2*4+3],vertexWheel[2],shader);
        }
        else if((result & 7) == 4) // four vertices used
        {
            int x1 = (int)(width / 2 * (projectionvbuf[0*4+0]/projectionvbuf[0*4+3] + 1)); 
            int y1 = (int)(height / 2 * (-projectionvbuf[0*4+1]/projectionvbuf[0*4+3] + 1)); 
            int x2 = (int)(width / 2 * (projectionvbuf[1*4+0]/projectionvbuf[1*4+3] + 1)); 
            int y2 = (int)(height / 2 * (-projectionvbuf[1*4+1]/projectionvbuf[1*4+3] + 1)); 
            int x3 = (int)(width / 2 * (projectionvbuf[2*4+0]/projectionvbuf[2*4+3] + 1)); 
            int y3 = (int)(height / 2 * (-projectionvbuf[2*4+1]/projectionvbuf[2*4+3] + 1)); 
            int x4 = (int)(width / 2 * (projectionvbuf[3*4+0]/projectionvbuf[3*4+3] + 1)); 
            int y4 = (int)(height / 2 * (-projectionvbuf[3*4+1]/projectionvbuf[3*4+3] + 1)); 
            //draw quad
            drawTriangle(x1,y1,projectionvbuf[0*4+2],projectionvbuf[0*4+3],vertexWheel[0],x2,y2,projectionvbuf[1*4+2],projectionvbuf[1*4+3],vertexWheel[1],x3,y3,projectionvbuf[2*4+2],projectionvbuf[2*4+3],vertexWheel[2],shader);
            drawTriangle(x3,y3,projectionvbuf[2*4+2],projectionvbuf[0*4+3],vertexWheel[2],x4,y4,projectionvbuf[3*4+2],projectionvbuf[3*4+3],vertexWheel[3],x1,y1,projectionvbuf[0*4+2],projectionvbuf[0*4+3],vertexWheel[0],shader);
        }
    }
    private void drawTriangle(int x1, int y1, float z1, float w1, Vertex v1, int x2, int y2, float z2, float w2, Vertex v2, int x3, int y3, float z3, float w3, Vertex v3, PixelShader shader)
    {
        final float tolerance = .000001f;
        float a = x2-x1;
        float b = x3-x1;
        float c = y2-y1;
        float d = y3-y1;
        if(Math.abs(a*d-b*c) <= tolerance)
            return;
        if(shader instanceof LightPixelShader && ((LightPixelShader)shader).light != null)
        {
            ((LightPixelShader)shader).setWorld(world);
            ((LightPixelShader)shader).setNormal(v1, v2, v3);
        }
        float det = 1 / (a*d-b*c);
        float m11 = d * det;
        float m12 = -b * det;
        float m21 = -c * det;
        float m22 = a * det;
        int minx = Math.max(0, Math.min(Math.min(x1, x2),x3));
        int miny = Math.max(0, Math.min(Math.min(y1, y2),y3));
        int maxx = Math.min(width - 1, Math.max(Math.max(x1, x2),x3));
        int maxy = Math.min(height - 1, Math.max(Math.max(y1, y2),y3));
        for(int x = minx; x <= maxx; x++)
            for(int y = miny; y <= maxy; y++)
            {
                float l2 = (x-x1) * m11 + (y-y1) * m12;
                if(l2 < 0 - tolerance || l2 > 1 + tolerance)
                    continue;
                float l3 = (x-x1) * m21 + (y-y1) * m22;
                if(l3 < 0 - tolerance || l3 > 1 + tolerance)
                    continue;
                float l1 = 1 - l2 - l3;
                if(l1 < 0 - tolerance || l1 > 1 + tolerance)
                    continue;
                if(useZbuf)
                {
                    float z = z1 * l1 + z2 * l2 + z3 * l3;
                    if(z < zbuf[y * width + x])
                        continue;
                }
                int sample = shader.colorAt(v1, v2, v3, w1, w2, w3, l1, l2, l3);
                if(((sample>>24)&0xff)>=192)
                {
                    zbuf[y * width + x] = z;
                    if(additive)
                        sample = Light.add(sample, pixels[y * width + x]);
                    pixels[y * width + x] = sample;
                }
            }
    }
    public void beginDraw(Graphics g)
    {
        beginDraw(g, 0xff000000);
    }
    public void beginDraw(Graphics g, int color)
    {
        for(int i = 0; i < pixels.length; i++)
        {
            pixels[i] = color;
            zbuf[i] = 0;
        }
        setView(x, y, z, pan, tilt);
        setProjection(fov, (float)g.getClipBounds().getWidth() / (float)g.getClipBounds().getHeight(), near, far);
        this.viewProjection = Matrix.multiply(view, projection);
    }
    public void endDraw(Graphics g)
    {
        //raster.set
        //raster.setPixels(0, 0, width, height, pixels);
        //raster.setDataElements(0, 0, width, height, pixels);
        //backbuf.setData(raster);
        //backbuf.setRGB(0, 0, width, height, pixels, 0, width);
        g.drawImage(backbuf, 0, 0, null);
    }
    /*public Image getBackBuffer()
    {
        return backbuf;
    }*/
    static int[] imagedata = new int[800*600*4];
    public void drawImage(Vector3 position, BufferedImage image, float scale)
    {
        Vector4 pos = viewProjection.transform(world.transform(new Vector4(position.x, position.y, position.z, 1)));
        scale /= pos.w;
        int cx = (int)(width / 2 * (pos.x / pos.w + 1));
        int cy = (int)(-height / 2 * (pos.y / pos.w - 1));
        int left = (int)(cx - scale * image.getWidth() / 2);
        int right = (int)(cx + scale * image.getWidth() / 2);
        int top = (int)(cy - scale * image.getHeight());
        int bottom  = (int)(cy);
        int minx = Math.max(left, 0);
        int maxx = Math.min(right, width - 1);
        int miny = Math.max(top, 0);
        int maxy = Math.min(bottom, height - 1);
        image.getData().getPixels(0, 0, image.getWidth(), image.getHeight(), imagedata);
        for(int y = miny; y <= maxy; y++)
            for(int x = minx; x <= maxx; x++)
            {
                if(useZbuf && pos.z < zbuf[y * width + x])
                    continue;
                int sx = Math.max(0, Math.min(image.getWidth() - 1, image.getWidth() * (x - left) / (right - left)));
                int sy = Math.max(0, Math.min(image.getHeight() - 1, image.getHeight() * (y - top) / (bottom - top)));
                int base = (sy * image.getWidth() + sx) * 4;
                int sample = (imagedata[base + 0] << 16) | (imagedata[base + 1] << 8) | (imagedata[base + 2]) | (imagedata[base + 3] << 24);
                if(((sample >> 24) & 0xff) >= 192)
                {
                    pixels[y * width + x] = 0xff000000 | sample;
                    if(useZbuf)
                        zbuf[y * width + x] = pos.z;
                }
            }
    }
}
