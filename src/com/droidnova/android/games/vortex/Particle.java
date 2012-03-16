package com.droidnova.android.games.vortex;

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
	    Speed = new Vect3d(5);
	    OriginalSize= 100+30;
	    LifeLenght=sys.LIFELENGHT;
	    
	    //LifeLenght=30;
	    
	    cgl = new ColorGL();
	    cgl.Randomize(1);
	      
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
	  Pos.Add(Speed);
	  
	  
	  if(sys.GRAVITY!=0)
      {
        Speed.y +=sys.GRAVITY;
        Pos.Add(Speed);
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
        	
        	gl.glColor4f(cgl.r,cgl.g,cgl.b,1);
        	float size = (float)(LifeLenght-Age);
            ((GL11Ext) gl).glDrawTexfOES((float)Pos.x, (float)Pos.y, 0, size, size);
            
          
          /*
           * Vect3d v3d = (Vect3d)Pos.Clone();
          if(sys.REFLECTION && Pos.y > 1)
          {
            v3d.y = -v3d.y + KEY_HEIGHT; 
            ColorGL cglReflection = (ColorGL)cgl.Clone();
            gl.renderImage(v3d, CurrentSize(), cgl, 0.1);    
          }
          */
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