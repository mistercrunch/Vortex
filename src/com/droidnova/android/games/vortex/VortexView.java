package com.droidnova.android.games.vortex;
 
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
 
public class VortexView extends GLSurfaceView {
    private static final String LOG_TAG = VortexView.class.getSimpleName();
    public VortexRenderer _renderer;
    System sys;
    int LastPointerCount;
 
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
        queueEvent(new Runnable() {
            public void run() {
            	int pCount = event.getPointerCount();
            	
            	if(pCount > LastPointerCount)
            	{}
            		//newAction spot
            	else if (pCount < LastPointerCount)
            	{}	//Kill right actionspot

            	for (int a = 0; a < pCount; a++) {
            		int pid = event.getPointerId(a);
            		sys.GenerateParticles((int)event.getX(pid), (int)getHeight()-(int)event.getY(pid), 1);
            	}
            	LastPointerCount = pCount;
            }
        });
        return true;
    }
}