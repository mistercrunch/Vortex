package com.droidnova.android.games.vortex;

 
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11Ext;
 
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.content.Context;
 
public class VortexRenderer implements GLSurfaceView.Renderer {
    private static final String LOG_TAG = VortexRenderer.class.getSimpleName();
 
    private float _red = 0.9f;
    private float _green = 0.2f;
    private float _blue = 0.2f;
    private long startTime = 0;
    private Context curContext;
    private Texture texParticle;
    public System sys;
    
    public VortexRenderer(Context c)
    {
    	super();
    	curContext = c;
    	
    }
    public void SetSystem(System s)
    {
    	sys = s;
    }
    
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Do nothing special.

    	texParticle = new Texture(gl);
    	
        //Setup the texture co-ordinates
        Bitmap bmpParticle = BitmapFactory.decodeResource(curContext.getResources(), R.drawable.particle2);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        
        gl.glDisable(GL10.GL_LIGHTING);
        gl.glDisable(GL10.GL_CULL_FACE);
        gl.glDisable(GL10.GL_DEPTH_BUFFER_BIT);
        gl.glDisable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_DITHER);
        gl.glEnable(GL10.GL_MODULATE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
        
        
        texParticle.Load(bmpParticle);
        
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texParticle.texture[0]);
    	gl.glEnable(GL10.GL_TEXTURE_2D );
    	gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);  
    	
    }
 
    @Override
    public void onSurfaceChanged(GL10 gl, int w, int h) {
        gl.glViewport(0, 0, w, h);
    }
 
    @Override
    
    public void onDrawFrame(GL10 gl) {
    	
    	sys.Move();
        
    	// define the color we want to be displayed as the "clipping wall"
        gl.glClearColor(0,0,0, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
    	sys.Draw(gl);
    	
    }
    
    public void setColor(float r, float g, float b) {
        _red = r;
        _green = g;
        _blue = b;
    }
}