package com.droidnova.android.games.vortex;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.microedition.khronos.opengles.GL10;

public class ActionSpot {
	
	public System sys;
	public LinkedList<Particle> lstParticles;
	Vect3d Pos;
	Vect3d LastPos;
	int PointerId;
	int counter = 0;
	boolean FADE_OUT = false;
	ColorGL col;
	
	public ActionSpot(System s, int pId) {
		sys = s;
		PointerId = pId;
		Pos = new Vect3d();
		LastPos = new Vect3d();
		lstParticles = new LinkedList<Particle>();
		col = new ColorGL();
		col.RandomSaturated();
		col.r = 1;
	}
	public ActionSpot(System s, int pId, Vect3d v3d) {
		this(s, pId);
		Pos = v3d;
		LastPos = Pos.Clone();
	}
	void SetPos(int x,int y){
		Pos.x =x;
		Pos.y =y;
	}
	public void Move()
	{
		counter++;
		if(!this.FADE_OUT)
			GenerateParticles(1);
		
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
	public void GenerateParticles(int Count)
	{
		for (int i=0; i<Count; i++)
		{
			
			Particle p = new Particle(sys);
			Vect3d v = Pos.Clone();
			v.Sub(LastPos);
			p.Speed.Add(v);
			p.Pos= this.Pos.Clone();
			p.cgl = this.col.Clone(); 
			p.cgl.Randomize((float)0.3);
			//p.cgl.RandomSaturated();
		    lstParticles.add(p);
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
