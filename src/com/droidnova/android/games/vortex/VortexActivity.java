package com.droidnova.android.games.vortex;
 
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
 
public class VortexActivity extends Activity implements SensorEventListener{
    private static final String LOG_TAG = VortexActivity.class.getSimpleName();
    private VortexView _vortexView;
    private SensorManager sensorManager;
    double ax,ay,az;   // these are the acceleration in x,y and z axis
    Vect3d v3dAccel= new Vect3d();
    System sys;
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
    Context getAppContext()
    {
    	return this.getApplicationContext();
    }
}