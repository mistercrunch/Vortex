package com.droidnova.android.games.vortex;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;


public class Texture
{
	private static String TAG = "Texture: ";
    /*Begin public declarations*/
    public float x = 0;
    public float y = 0;
    public float z = 0;
    public float width = 0;
    public float height = 0;
    /*Begin Private Declarations*/
    private GL10 gl;
    public int[] texture;    //holds the texture in integer form
    private int texture_name;
    private int[] mCropWorkspace;
    private final BitmapFactory.Options sBitmapOptions;

	
	/*Begin Methods*/
	public Texture( GL10 gl_obj )
	{
	    gl = gl_obj;
	    texture = new int[1];
	    mCropWorkspace = new int[4];
	    sBitmapOptions = new BitmapFactory.Options();
	    sBitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
	    //Log.d(TAG, "Initializing Texture Object");
	}    
	public int get_texture_name( )
	{
	    return texture_name;
	}
	
	/*Loads the resource to memory*/
	public boolean Load( Bitmap bitmap ) //rename this to glLoad and don't have it as an initializer parameter
	{
	    /*many thanks to sprite method test if this works*/
	    if ( gl == null )
	    {
	        Log.e(TAG, "Failed to load resource.  Context/GL is NULL");
	        return false;
	    }
	    int error;
	
	    int textureName = -1;
	    gl.glGenTextures(1, texture, 0);
	    textureName = texture[0];
	
	    //Log.d(TAG, "Generated texture: " + textureName);
	    gl.glBindTexture(GL10.GL_TEXTURE_2D, textureName);
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
	    //gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_REPLACE);
	    gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_COLOR, GL10.GL_REPLACE );
	
	    
	    GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
	
	    mCropWorkspace[0] = 0;
	    mCropWorkspace[1] = bitmap.getHeight();
	    mCropWorkspace[2] = bitmap.getWidth();
	    mCropWorkspace[3] = -bitmap.getHeight();
	
	    ((GL11) gl).glTexParameteriv(GL10.GL_TEXTURE_2D, 
	            GL11Ext.GL_TEXTURE_CROP_RECT_OES, mCropWorkspace, 0);
	
	    error = gl.glGetError();
	    if (error != GL10.GL_NO_ERROR)
	    { 
	        Log.e(TAG, "GL Texture Load Error: " + error);
	
	    }
	    Log.d(TAG, "Loaded texture: " + textureName);
	    return true;
	}
}