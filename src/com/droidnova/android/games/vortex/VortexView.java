package com.droidnova.android.games.vortex;
 
import java.util.Iterator;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
 
public class VortexView extends GLSurfaceView {
    private static final String LOG_TAG = VortexView.class.getSimpleName();
    public VortexRenderer _renderer;
    System sys;
 
    public VortexView(Context context) {
        super(context);
        
        _renderer = new VortexRenderer(this.getContext());
        setRenderer(_renderer);
        
    }
    public void SetSystem( System s)
    {
    	sys  = s;
    	_renderer.SetSystem(sys);
    	
    	sys.WIDTH = getWidth();
    	sys.HEIGHT = getHeight();
    }
    
    public boolean onTouchEvent(final MotionEvent event) {
        //queueEvent(new Runnable() {
            	int action = event.getActionMasked();
            	int pid = event.getPointerId(event.getActionIndex());
            	
            	if (action == event.ACTION_DOWN || action == event.ACTION_POINTER_DOWN){
            		//Log.d(LOG_TAG, "DOWN:"+pid);
            		Vect3d v3d = new Vect3d(event.getX(event.getActionIndex()), getHeight()-event.getY(event.getActionIndex()), 0);
            		sys.CreateActionSpot(pid, v3d);
            	}
            	else if (action == event.ACTION_UP || action == event.ACTION_POINTER_UP){
            		//Log.d(LOG_TAG, "UP:"+pid);
	            	sys.RemoveActionSpot(pid);
            	}
            	
            	for (int a = 0; a < event.getPointerCount(); a++) {
            		pid = event.getPointerId(a);
            		sys.SetActionSpotPos(pid, (int)event.getX(a), (int)getHeight()-(int)event.getY(a));
            	}	
            	
        return true;
    }
}