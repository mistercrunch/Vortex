package com.droidnova.android.games.vortex;
import java.util.Random;

public class ColorGL
{
  float r,g,b;
  Random rand;
  
  ColorGL()
  {
	Init();
    r=g=b=0;
  }
  
  ColorGL(ColorGL inCgl)
  {
	  Init();
    r=inCgl.r;
    g=inCgl.g;
    b=inCgl.b;
  }
  ColorGL(float inR, float inG, float inB)
  {
	  Init() ; 
    SetColor(inR, inG, inB);
  }
  ColorGL Clone()
  {
	  Init();
    ColorGL tmp = new ColorGL(r, g, b);
    return tmp;
  }
  
  void SetColor(float inR, float inG, float inB)
  {
	  
    r=inR;
    g=inG;
    b=inB;
  }
  
  void Init(){
	  rand = new Random();
	  
  }
  
  double Randomdouble(double range)
  {	
	  return rand.nextDouble() * range;
  }
  
  void Randomize(float range)
  { 
    		
    
    r += (Randomdouble(range)) - range/2;
    g += (Randomdouble(range)) - range/2;
    b += (Randomdouble(range)) - range/2;
    
    FixRange(r);
    FixRange(g);
    FixRange(b);
  }
  
  float FixRange(float a)
  {
    if (a>1)a=1;
    else if (a<0)a=1;
    return a;
  }
}
