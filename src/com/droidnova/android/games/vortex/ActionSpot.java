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
		lstParticles = new LinkedList<Particle>();
		col = new ColorGL();
		col.Randomize(1);
		col.r = 1;
	}
	public ActionSpot(System s, int pId, Vect3d v3d) {
		this(s, pId);
		Pos = v3d;
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
			p.Pos.x = Pos.x;
			p.Pos.y = Pos.y;
			p.cgl = this.col.Clone(); 
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
