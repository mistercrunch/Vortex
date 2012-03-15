package com.droidnova.android.games.vortex;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.microedition.khronos.opengles.GL10;

public class System {
	
	private LinkedList<Particle> lstParticles; //List of particles
	
	public VortexRenderer vRenderer;
	public VortexActivity vActicity;
	public VortexView vView;
		
	double    GRAVITY               = -0.1; //Use 3 if not 0
	boolean   PAUSE                 = false;
	boolean   VORTEX                = false;
	boolean   REFLECTION            = true;
	double    BOUNCE_RANDOMNESS     = 0.9;
	double    CAM_DISTANCE         = 700;
	double    FLOOR                = 0;
	double    BOUNCE                = 0.63;
	double     LIFELENGHT           = 100;
	double     MAXGENERATION        = 3;
	int       WIDTH                 = 900;
	int       HEIGHT                = 600;
	int       KEYS_WIDTH            = 30;
	int       X_OFFSET              = -55;
	
	public System(VortexActivity a, VortexRenderer r, VortexView v)
	{
		
		lstParticles = new LinkedList();
		
		vView = v;
		vActicity = a;
		vRenderer = r;
		
		
		GenerateParticles(WIDTH/2, HEIGHT/2, 10);
	}
	public void GenerateParticles(int x, int y, int Count)
	{
		for (int i=0; i<Count; i++)
		{
			Particle p = new Particle(this);
			p.Pos.x = x;
			p.Pos.y = y;
			lstParticles.add(p);
		}
	}
	
	public void Move()
	{
		
		ListIterator<Particle> it = lstParticles.listIterator();
		while ( it.hasNext() ) 
		{
			Particle p = (Particle)it.next();
	    	p.Move();
	    	if( (p.Age>p.LifeLenght ))
	        {  
	          //Particle has ended it's lifecycle, remove
	           it.remove();
	        }
		}
	}
	public void Draw(GL10 gl)
	{
		Iterator<Particle> it = lstParticles.listIterator();
		while ( it.hasNext() ) 
		{
			Particle p = (Particle)it.next();
	    	p.Draw(gl);
	    	
		}
	}
}
