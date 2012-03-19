package com.droidnova.android.games.vortex;

import java.util.ListIterator;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11Ext;

class Particle
{
  Vect3d Pos, Speed;
  ColorGL cgl;
  int OriginalSize;
  int Age;
  double LifeLenght;
  int Generation;
  
  
  public System sys;
  
  Particle(System s)
  {
	  	sys = s;
	    Generation=0;
	    Pos = new Vect3d(100);
	    Speed = new Vect3d(10);
	    OriginalSize= 100+30;
	    
	    LifeLenght=sys.LIFELENGHT + (sys.Rand.nextFloat() * sys.LIFE_RAND);
	    
	    //LifeLenght=30;
	    
	    cgl = new ColorGL();
	    cgl.Randomize(1);
  }
  Particle(System s, Vect3d speed)
  {
	  this(s);
	  Speed = speed;
  }
  
  
  Particle(Vect3d v3d, ColorGL inColor, boolean isDown, int Velocity, System s)
  {
	
    Generation=0;
    Pos = new Vect3d(v3d);
    Speed = new Vect3d(15);
    OriginalSize= (int)(Velocity*3 )+30;
    LifeLenght=sys.LIFELENGHT;
    Speed = new Vect3d();
    //LifeLenght=30;
    cgl = new ColorGL(inColor);
    
    sys = s;
  }
  
  Particle(Particle p)
  {
    Generation=p.Generation+1;
    Pos = new Vect3d(p.Pos);
    Speed = new Vect3d(p.Speed);
    OriginalSize=p.OriginalSize;
    Age=p.Age;
    LifeLenght=p.LifeLenght;
    
    cgl=new ColorGL(p.cgl);
    sys = p.sys;
  }
  
  void Move()
  {
	  //Accelerometer
	  if(sys.ACCELEROMETER_FLAG)
		  Speed.Add(sys.vActicity.v3dAccel);
      
      //Attractions
      	ListIterator it = sys.lstActionSpots.listIterator();
      	Vect3d vSpeedDeviation =new Vect3d();
		while ( it.hasNext() ) 
		{
			ActionSpot as = (ActionSpot)it.next();
	    	Vect3d v = Pos.Clone();
	        v.Sub(as.Pos);
	        v.Multiply(Math.pow(v.Distance(), 0.5) * -0.00001);
	        vSpeedDeviation.Add(v);
		}
		Speed.Add(vSpeedDeviation);
      
	  Pos.Add(Speed);
	  
	  
	  if (sys.BOUNCE_FLAG){
		  if (Pos.x <= 0 || Pos.x > sys.vView.getWidth())
		  {  
			  Speed.x=-Speed.x * sys.BOUNCE_FACTOR;
			  Pos.x += Speed.x;
		  }
		  if (Pos.y <= 0 || Pos.y > sys.vView.getHeight())
		  {
			  Speed.y=-Speed.y * sys.BOUNCE_FACTOR;
			  Pos.y += Speed.y;
		  }
	  }
/*
      if(sys.GRAVITY!=0)
      {
        Speed.y +=sys.GRAVITY;
        Pos.Add(Speed);
        if(Pos.y >= sys.FLOOR)
        {
        
          //If the ball has hit the floor
          Speed.y=-Speed.y;
          Pos.Add(Speed);
          Speed.y*=sys.BOUNCE;
        
          double range=(Speed.y/2)* sys.BOUNCE_RANDOMNESS;
          
          if(Generation<=MAXGENERATION)
          {
            //Split in three!
            Particle p1 = new Particle(this);
            Particle p2 = new Particle(this);
            ParticlesToAdd.add(p1);
            ParticlesToAdd.add(p2);
            
            Generation++;
            
            p1.Speed.Randomize(range);
            p2.Speed.Randomize(range);
            Speed.Randomize(range);
            
            p1.Age++;
            p2.Age++;
          }
          
        }
      }  
  */    
      
      Age++;
    } 
  
  
  void Draw(GL10 gl)
  {
        float tmpSize=CurrentSize();       
     
        if (tmpSize>0)
        {
          //sys.vRenderer.drawBall();
          //gl.renderImage(Pos, CurrentSize(), cgl, 1);   
        	
        	
        	//float size = (float)(LifeLenght-Age);
        	//gl.glColor4f(cgl.r,cgl.g,cgl.b,1);
            //((GL11Ext) gl).glDrawTexfOES((float)Pos.x, (float)Pos.y, 0, size, size);
            
        	float f = (float)(LifeLenght-Age)/(float)LifeLenght;
        	gl.glColor4f(cgl.r,cgl.g,cgl.b,f);
        	float size = (float) (80 + (Pos.z/6));
        	((GL11Ext) gl).glDrawTexfOES((float)Pos.x, (float)Pos.y, 0, size ,size );
          
          
        }
  }
  
  float CurrentSize()
  {
    if(Age < LifeLenght)
    {
      double lifePerc = (float)Math.abs(Age-LifeLenght)/LifeLenght;
      if(Generation==0)
        return (float)(OriginalSize*lifePerc);
      else
        return (float)((OriginalSize*lifePerc) / (Generation * 1.5));
    }
    else
      return 0;
    
  }
}