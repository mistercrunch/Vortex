package com.droidnova.android.games.vortex;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import android.app.ListActivity;
import android.util.Log;

public class System {
	//1586.50 22 juillet
	private static final String LOG_TAG = VortexRenderer.class.getSimpleName();
	
	
	public LinkedList lstActionSpots;
	public LinkedList lstActionSpotsToAdd;
	
	
	public VortexRenderer vRenderer;
	public VortexActivity vActicity;
	public VortexView vView;

	float FLASH = 1;
	double    ACCELERATION          = -0.05;
	boolean   PAUSE                 = false;
	boolean   VORTEX                = false;
	boolean   REFLECTION            = true;
	double    BOUNCE_RANDOMNESS     = 0.9;
	double    CAM_DISTANCE         = 700;
	double    FLOOR                = 0;
	double    BOUNCE                = 0.8;
	double    LIFELENGHT           = 100;
	double    LIFE_RAND           = 20;
	
	double    MAXGENERATION        = 3;
	int       WIDTH                 = 900;
	int       HEIGHT                = 600;
	int       KEYS_WIDTH            = 30;
	int       X_OFFSET              = -55;
	Random	Rand;
	
	public System(VortexActivity a, VortexRenderer r, VortexView v)
	{
		
		Rand = new Random();
		
		vView = v;
		vActicity = a;
		vRenderer = r;
		lstActionSpots = new LinkedList();
		lstActionSpotsToAdd = new LinkedList();
		
		
		//GenerateParticles(WIDTH/2, HEIGHT/2, 200);
	}
	
	public void CreateActionSpot(int pid, Vect3d v3d){
		Log.d(LOG_TAG, "CreatorA:"+pid);
		if(GetActionSpot(pid)==null)
		{
			Log.d(LOG_TAG, "CreatorB:"+pid);
			ActionSpot as = new ActionSpot(this, pid, v3d);
			this.lstActionSpotsToAdd.add(as);
		}
	}
	
	public void SetActionSpotPos(int pid, double x, double y){
		ActionSpot as = GetActionSpot(pid);
		if (as != null){
			as.LastPos = as.Pos.Clone();
			as.Pos = new Vect3d(x,y,0);
		}
	}
	void RemoveActionSpot(int pid)
	{
		//Log.d(LOG_TAG, "RemoveA:"+pid);
		ActionSpot as = GetActionSpot(pid);
		if (as != null){	
			
			as.FADE_OUT = true;
			as.PointerId = -1;
		} 
		//Log.d(LOG_TAG, "RemoveB:"+pid);
	}
	ActionSpot GetActionSpot(int pid)
	{
		Iterator it = this.lstActionSpots.listIterator();
		ActionSpot as = null;
		while ( it.hasNext() ){
			ActionSpot tmpAs = (ActionSpot)it.next();
	    	if (tmpAs.PointerId==pid && !tmpAs.FADE_OUT){
	    		as = tmpAs;
	    		break;
	    	}
		}
		return as;
	}

	public void Move()
	{
		//Log.d(LOG_TAG, "x:"+vActicity.ax+" y:"+vActicity.ay + " z:" + vActicity.az);
		
		lstActionSpots.addAll(lstActionSpotsToAdd);
		lstActionSpotsToAdd.clear();
		ListIterator it =lstActionSpots.listIterator();
		while ( it.hasNext() ) 
		{
			ActionSpot as = (ActionSpot)it.next();
	    	as.Move();
	    	if(as.FADE_OUT && as.lstParticles.size() ==0)
	    		it.remove();
		}
	}
	public void Draw(GL10 gl)
	{
		ListIterator it =lstActionSpots.listIterator();
		while ( it.hasNext() ) 
		{
			ActionSpot as = (ActionSpot)it.next();
	    	as.Draw(gl);
		}
	}
}
