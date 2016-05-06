/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author ed.mason
 */
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
    BufferedImage backbuf;
    //WritableRaster raster;
    //DataBuffer dataBuffer;
    int[] pixels;
    float[] zbuf;
    private static final float[] inputvbuf;
    private static final float[] viewvbuf;
    private static final float[] projectionvbuf;
    private boolean useZbuf;
    private final Vector3 lightDir;
    static
    {
        inputvbuf = new float[16];
        viewvbuf = new float[16];
        projectionvbuf = new float[16];
    }
    public Camera(int screenWidth, int screenHeight)
    {
        view = new Matrix();
        //projection = new Matrix();
        width = screenWidth;
        height = screenHeight;
        backbuf = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        //raster = backbuf.getRaster().createCompatibleWritableRaster();
        //backbuf.setData(raster);
        //dataBuffer = raster.getDataBuffer();
        pixels = new int[width*height];
        zbuf = new float[width*height];
        //raster.getPixels(0, 0, width, height, pixels);
        near = 1;
        far = 50;
        fov = Math.PI / 2;
        lightDir = Vector3.normalize(new Vector3(-1,3,2));
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
    private void rotateBuffer3(float[] vbuf)
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
    public void drawTriangle(Vector3 v1, Vector3 v2, Vector3 v3, int color)
    {
        setBuffer(inputvbuf, 0, v1);
        setBuffer(inputvbuf, 1, v2);
        setBuffer(inputvbuf, 2, v3);
        transformBuffers(inputvbuf, viewvbuf, worldView);
        
        if(checkEntirelyBehind(viewvbuf))
            return;
        int repeatcount = 0;
        while(viewvbuf[0*4+2] > -near || viewvbuf[1*4+2] > -near && viewvbuf[2*4+2] <= -near) // get any behind to the end
        {
            rotateBuffer3(viewvbuf);
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
                break;
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
                viewvbuf[1*4+4] = 1;
                float t3 = (-near  - viewvbuf[0*4+2]) / (viewvbuf[2*4+2] - viewvbuf[0*4+2]);
                viewvbuf[2*4+0] = viewvbuf[0*4+0] + (viewvbuf[2*4+0] - viewvbuf[0*4+0]) * t3;
                viewvbuf[2*4+1] = viewvbuf[0*4+1] + (viewvbuf[2*4+1] - viewvbuf[0*4+1]) * t3;
                viewvbuf[2*4+2] = -near;
                viewvbuf[2*4+4] = 1;
                transformBuffers(viewvbuf, projectionvbuf, projection);
                if(checkCounterClockwise(projectionvbuf))
                    return;
                int x1 = (int)(width / 2 * (projectionvbuf[0*4+0]/projectionvbuf[0*4+3] + 1)); 
                int y1 = (int)(height / 2 * (-projectionvbuf[0*4+1]/projectionvbuf[0*4+3] + 1)); 
                int x2 = (int)(width / 2 * (projectionvbuf[1*4+0]/projectionvbuf[1*4+3] + 1)); 
                int y2 = (int)(height / 2 * (-projectionvbuf[1*4+1]/projectionvbuf[1*4+3] + 1)); 
                int x3 = (int)(width / 2 * (projectionvbuf[2*4+0]/projectionvbuf[2*4+3] + 1)); 
                int y3 = (int)(height / 2 * (-projectionvbuf[2*4+1]/projectionvbuf[2*4+3] + 1)); 
                drawTriangle(x1,y1,projectionvbuf[0*4+3],x2,y2,projectionvbuf[1*4+3],x3,y3,projectionvbuf[2*4+3],color);
            }
            else //only v3 is behind
            {
                float t13 = (-near  - viewvbuf[0*4+2]) / (viewvbuf[2*4+2] - viewvbuf[0*4+2]);
                viewvbuf[3*4+0] = viewvbuf[0*4+0] + (viewvbuf[2*4+0] - viewvbuf[0*4+0]) * t13;
                viewvbuf[3*4+1] = viewvbuf[0*4+1] + (viewvbuf[2*4+1] - viewvbuf[0*4+1]) * t13;
                viewvbuf[3*4+2] = -near;
                viewvbuf[3*4+3] = 1;
                float t23 = (-near  - viewvbuf[1*4+2]) / (viewvbuf[2*4+2] - viewvbuf[1*4+2]);
                viewvbuf[2*4+0] = viewvbuf[1*4+0] + (viewvbuf[2*4+0] - viewvbuf[1*4+0]) * t13;
                viewvbuf[2*4+1] = viewvbuf[1*4+1] + (viewvbuf[2*4+1] - viewvbuf[1*4+1]) * t13;
                viewvbuf[2*4+2] = -near;
                viewvbuf[2*4+3] = 1;
                transformBuffers(viewvbuf, projectionvbuf, projection);
                if(checkCounterClockwise(projectionvbuf))
                    return;
                int x1 = (int)(width / 2 * (projectionvbuf[0*4+0]/projectionvbuf[0*4+3] + 1)); 
                int y1 = (int)(height / 2 * (-projectionvbuf[0*4+1]/projectionvbuf[0*4+3] + 1)); 
                int x2 = (int)(width / 2 * (projectionvbuf[1*4+0]/projectionvbuf[1*4+3] + 1)); 
                int y2 = (int)(height / 2 * (-projectionvbuf[1*4+1]/projectionvbuf[1*4+3] + 1)); 
                int x3 = (int)(width / 2 * (projectionvbuf[2*4+0]/projectionvbuf[2*4+3] + 1)); 
                int y3 = (int)(height / 2 * (-projectionvbuf[2*4+1]/projectionvbuf[2*4+3] + 1)); 
                int x4 = (int)(width / 2 * (projectionvbuf[3*4+0]/projectionvbuf[3*4+3] + 1)); 
                int y4 = (int)(height / 2 * (-projectionvbuf[3*4+1]/projectionvbuf[3*4+3] + 1)); 
                //draw quad
                drawTriangle(x1,y1,projectionvbuf[0*4+2],x2,y2,projectionvbuf[1*4+2],x3,y3,projectionvbuf[2*4+2],color);
                drawTriangle(x2,y2,projectionvbuf[2*4+2],x3,y3,projectionvbuf[2*4+2],x4,y4,projectionvbuf[3*4+2],color);
            }
        }
        else
        {
            transformBuffers(viewvbuf, projectionvbuf, projection);
            if(checkCounterClockwise(projectionvbuf))
                return;
            int x1 = (int)(width / 2 * (projectionvbuf[0*4+0]/projectionvbuf[0*4+3] + 1)); 
            int y1 = (int)(height / 2 * (-projectionvbuf[0*4+1]/projectionvbuf[0*4+3] + 1)); 
            int x2 = (int)(width / 2 * (projectionvbuf[1*4+0]/projectionvbuf[1*4+3] + 1)); 
            int y2 = (int)(height / 2 * (-projectionvbuf[1*4+1]/projectionvbuf[1*4+3] + 1)); 
            int x3 = (int)(width / 2 * (projectionvbuf[2*4+0]/projectionvbuf[2*4+3] + 1)); 
            int y3 = (int)(height / 2 * (-projectionvbuf[2*4+1]/projectionvbuf[2*4+3] + 1)); 
            drawTriangle(x1,y1,projectionvbuf[0*4+2],x2,y2,projectionvbuf[1*4+2],x3,y3,projectionvbuf[2*4+2],color);
        }
    }
    private void drawTriangle(int x1, int y1, float z1, int x2, int y2, float z2, int x3, int y3, float z3, int color)
    {
        final float tolerance = .000001f;
        float a = x2-x1;
        float b = x3-x1;
        float c = y2-y1;
        float d = y3-y1;
        if(Math.abs(a*d-b*c) <= tolerance)
            return;
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
                    zbuf[y * width + x] = z;
                }
                pixels[y * width + x] = color;
            }
    }
    public void drawLine(Vector3 v1, Vector3 v2, int color)
    {
        setBuffer(inputvbuf, 0, v1);
        setBuffer(inputvbuf, 1, v2);
        transformBuffers(inputvbuf, viewvbuf, worldView);

        if(viewvbuf[0*4+2] > -near && viewvbuf[1*4+2] > -near)
            return;
        if(viewvbuf[0*4+2] > -near)
        {
            float t = (-near - viewvbuf[1*4+2]) / (viewvbuf[0*4+2] - viewvbuf[1*4+2]);
            viewvbuf[0*4+0] = viewvbuf[1*4+0] + (viewvbuf[0*4+0] - viewvbuf[1*4+0]) * t;
            viewvbuf[0*4+1] = viewvbuf[1*4+1] + (viewvbuf[0*4+1] - viewvbuf[1*4+1]) * t;
            viewvbuf[0*4+2] = -near;
            viewvbuf[0*4+3] = 1;
        }
        if(viewvbuf[1*4+2] > -near)
        {
            float t = (-near - viewvbuf[0*4+2]) / (viewvbuf[1*4+2] - viewvbuf[0*4+2]);
            viewvbuf[1*4+0] = viewvbuf[0*4+0] + (viewvbuf[1*4+0] - viewvbuf[0*4+0]) * t;
            viewvbuf[1*4+1] = viewvbuf[0*4+1] + (viewvbuf[1*4+1] - viewvbuf[0*4+1]) * t;
            viewvbuf[1*4+2] = -near;
            viewvbuf[1*4+3] = 1;
        }
        transformBuffers(viewvbuf, projectionvbuf, projection);
        drawLine((int)(projectionvbuf[0*4+0]/projectionvbuf[0*4+3]*width/2) + width/2, (int)(projectionvbuf[0*4+1]/projectionvbuf[0*4+3]*-height/2) + height/2, (int)(projectionvbuf[1*4+0]/projectionvbuf[1*4+3] * width / 2) + width /2, (int)(projectionvbuf[1*4+1]/projectionvbuf[1*4+3] * -height / 2) + height / 2, color);
    }
    private void drawLine(int x1, int y1, int x2, int y2, int color)
    {
        if(x2 == x1)
        {
            if(x1 < 0 || x1 >= width)
                return;
            y1 = Math.max(0, Math.min(y1, height-1));
            y2 = Math.max(0, Math.min(y2, height-1));
            int miny = Math.min(y1, y2);
            int maxy = Math.max(y1, y2);
            for(int y = miny; y <= maxy; y++)
                pixels[y * width + x1] = color;
        }
        else if(y2 == y1)
        {
            if(y1 < 0 || y1 >= height)
                return;
            x1 = Math.max(0, Math.min(x1, width-1));
            x2 = Math.max(0, Math.min(x2, width-1));
            int minx = Math.min(x1, x2);
            int maxx = Math.max(x1, x2);
            
            for(int x = minx; x <= maxx; x++)
                pixels[y1 * width + x] = color;
        }
        else
        {
            float slope = (float)(y2 - y1) / (x2 - x1);
            if(x1 < 0)
            {
                y1 = (int)(slope * (0 - x2)) + y2;
                x1 = 0;
            }
            if(x1 > width - 1)
            {
                y1 = (int)(slope * (width - 1 - x2)) + y2;
                x1 = width - 1;
            }
            if(x2 < 0)
            {
                y2 = (int)(slope * (0 - x1)) + y1;
                x2 = 0;
            }
            if(x2 > width - 1)
            {
                y2 = (int)(slope * (width - 1 - x1)) + y1;
                x2 = width - 1;
            }
            if(y1 < 0)
            {
                x1 = (int)((0 - y2) / slope) + x2;
                y1 = 0;
            }
            if(y1 > height - 1)
            {
                x1 = (int)((height - 1 - y2) / slope) + x2;
                y1 = height - 1;
            }
            if(y2 < 0)
            {
                x2 = (int)((0 - y1) / slope) + x1;
                y2 = 0;
            }
            if(y2 > height - 1)
            {
                x2 = (int)((height - 1 - y1) / slope) + x1;
                y2 = height - 1;
            }
            /*int minx = Math.min(x1, x2);
            int maxx = Math.max(x1, x2);
            int miny = Math.min(y1, y2);
            int maxy = Math.max(y1, y2);*/
                   
            if(Math.abs(y2 - y1) > Math.abs(x2 - x1))
            {
                int yinc = y2 > y1 ? 1 : -1;
                for(int y = y1; y != y2; y+=yinc)
                {
                    int x = (int)((y - y1) / slope + x1);
                    pixels[y * width + x] = color;
                }
            }
            else
            {
                int xinc = x2 > x1 ? 1 : -1;
                for(int x = x1; x != x2; x+=xinc)
                {
                    int y = (int)((x - x1) * slope + y1);
                    pixels[y * width + x] = color;
                }
            }
        }
    }
    public void beginDraw(Graphics g)
    {
        for(int i = 0; i < pixels.length; i++)
        {
            pixels[i] = 0xff404040;
            zbuf[i] = 0;
        }
        setView(x, y, z, pan, tilt);
        setProjection(fov, (float)g.getClipBounds().getWidth() / (float)g.getClipBounds().getHeight(), near, far);
        this.viewProjection = Matrix.multiply(view, projection);
    }
    public void endDraw(Graphics g)
    {
        //raster.setPixels(0, 0, width, height, pixels);
        //backbuf.set.setData(raster);
        backbuf.setRGB(0, 0, width, height, pixels, 0, width);
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
    
    //because Parsons requested it
    public void drawImage(Vector3 position, BufferedImage image, float scale, int alpha)
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
                    int r1 = (sample & 0xff0000) >> 16;
                    int g1 = (sample & 0xff00) >> 8;
                    int b1 = (sample & 0xff);
                    int a1 = (sample & 0xff000000) >> 24;
                    int sample2 = pixels[y * width + x];
                    int r2 = (sample2 & 0xff0000) >> 16;
                    int g2 = (sample2 & 0xff00) >> 8;
                    int b2 = (sample2 & 0xff);
                    int a2 = (sample2 & 0xff000000) >> 24;
                    sample = (((r1 * alpha + r2 * (65025 - a1 * alpha)/255) / 255) << 16)
                            |(((g1 * alpha + g2 * (65025 - a1 * alpha)/255) / 255) << 8)
                            |(((b1 * alpha + b2 * (65025 - a1 * alpha)/255) / 255) << 0)
                            |(((a1 * alpha + a2 * (65025 - a1 * alpha)/255) / 255) << 24);
                    pixels[y * width + x] = 0xff000000 | sample;
                    if(useZbuf)
                        zbuf[y * width + x] = pos.z;
                }
            }
    }
    
}
