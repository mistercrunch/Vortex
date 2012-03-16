package com.droidnova.android.games.vortex;

import java.util.Random;


class Vect3d
{
  double x,y,z;
  Random r;
  
  Vect3d()
  { 
    Init();
    x=y=z=0;
  }
  
  Vect3d(double range)
  {
	  Init();
    x= Randomdouble(range)-(range/2);
    y= Randomdouble(range)-(range/2);
    z= Randomdouble(range)-(range/2);
  }
  Vect3d(double daX, double daY, double daZ)
  { 
	  Init();
    x=daX;
    y=daY;
    z=daZ;
  }
  
  Vect3d(Vect3d p)
  { 
	  Init();
    x=p.x;
    y=p.y;
    z=p.z;
  }
  private void Init()
  {
	  r = new Random();
  }
  double Randomdouble(double range)
  {	
	  return r.nextDouble() * range;
  }
  
  void Randomize(double range)
  { 
    x += (Randomdouble(range)) - range/2;
    y += (Randomdouble(range)) - range/2;
    z += (Randomdouble(range)) - range/2;
  }
  double Distance()
  {
    double d = (Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
    return Math.pow(d, 0.5);
  } 
  double Distance(Vect3d p)
  {
    double d = Math.pow((float)x-p.x,2.0) + Math.pow(y-p.y, 2.0) + Math.pow(z-p.z,2.0);
    return Math.pow(d, 0.5);
  }
  
  void Sub(Vect3d p)
  {
    x -= p.x;
    y -= p.y;
    z -= p.z; 
  }

  void Add(Vect3d p)
  {
    x += p.x;
    y += p.y;
    z += p.z; 
  }
  void Div(double fDivider)
  {

    x /= fDivider;
    y /= fDivider;
    z /= fDivider;
  }
  void Multiply(double multiplier)
  {
    x *= multiplier;
    y *= multiplier;
    z *= multiplier;
  }
  Vect3d Clone()
  {
    Vect3d tmp = new Vect3d(x,y,z);
    return tmp;
  }
  
  void Clone(Vect3d v3d)
  {
    x=v3d.x;
    y=v3d.y;
    z=v3d.z;
  }
  
  void Become(Vect3d p3d)
  {
    x = p3d.x;
    y = p3d.y;
    z = p3d.z;
  }
};