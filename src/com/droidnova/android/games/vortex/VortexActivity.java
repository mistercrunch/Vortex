package com.droidnova.android.games.vortex;
 
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
 
public class VortexActivity extends Activity {
    private static final String TAG = VortexActivity.class.getSimpleName();
    private VortexView _vortexView;
    System sys;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
        _vortexView = new VortexView(this);
        setContentView(_vortexView);
        
        sys = new System(this, _vortexView._renderer,_vortexView);
        _vortexView.SetSystem(sys);
        
    }
    Context getAppContext()
    {
    	return this.getApplicationContext();
    }
}