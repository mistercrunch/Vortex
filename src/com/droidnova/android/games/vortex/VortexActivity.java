package com.droidnova.android.games.vortex;
 
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class VortexActivity extends Activity implements SensorEventListener{
    private static final String LOG_TAG = VortexActivity.class.getSimpleName();
    private VortexView _vortexView;
    
    private SensorManager sensorManager;
    double ax,ay,az;   // these are the acceleration in x,y and z axis
    Vect3d v3dAccel= new Vect3d();
    System sys;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
        _vortexView = new VortexView(this);
        setContentView(_vortexView);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sys = new System(this, _vortexView._renderer,_vortexView);
        _vortexView.SetSystem(sys);
        
    }
    
    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
    	
         if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
        	 
       	  	
	        	 v3dAccel.x=event.values[0] * sys.ACCELERATION;
	        	 v3dAccel.y=event.values[1] * sys.ACCELERATION;
	        	 v3dAccel.z=0;
             }
         //Log.d(LOG_TAG, "x:"+ax+" y:"+ay + " z:" + az);
    }
 
    Context getAppContext() {
    	return this.getApplicationContext();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
            	setContentView(R.layout.about);
                return true;
                
            case R.id.preferences:
            	Intent settingsActivity = new Intent(getBaseContext(),Preferences.class);
            	startActivity(settingsActivity);
            	
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onStart(){
    	super.onStart();
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    	sys.BOUNCE_FLAG = prefs.getBoolean("bounce", true);
    	sys.ACCELEROMETER_FLAG = prefs.getBoolean("accelerometer", true);
    }
}