/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4.graphics;

/**
 *
 * @author ed.mason
 */
public abstract class ModelGraphic extends Graphic {
    private Vertex[] VertexBuffer;
    private short[] IndexBuffer;
    private PixelShader shader;
    
    public PixelShader getPixelShader() { return shader; }
    
    public ModelGraphic(Vertex[] vbuf, short[] ibuf, PixelShader shader)
    {
        this.VertexBuffer = vbuf;
        this.IndexBuffer = ibuf;
        this.shader = shader;
    }
    
    @Override
    public void draw(Camera camera, float x, float y, float z, float scale) {
        draw(camera, x, y, z, scale, null);
    }
    public void draw(Camera camera, float x, float y, float z, float scale, Light light) {
        if(shader instanceof LightPixelShader)
            ((LightPixelShader)shader).setPreferredLight(light);
        camera.setWorld(Matrix.multiply(Matrix.createScale(scale), Matrix.createTranslation(x, y, z)));
        for(int i = 0; i < IndexBuffer.length; i+= 3)
            camera.drawTriangle(VertexBuffer[IndexBuffer[i+0]], VertexBuffer[IndexBuffer[i+1]], VertexBuffer[IndexBuffer[i+2]], shader);
    }
}
