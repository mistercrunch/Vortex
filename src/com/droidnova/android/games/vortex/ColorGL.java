package com.droidnova.android.games.vortex;
import java.util.Random;
import android.graphics.Color;
import android.util.Log;

public class ColorGL
{
  float r,g,b;
  Random rand;
  private static final String LOG_TAG = ColorGL.class.getSimpleName();
  
  ColorGL(){
	Init();
    r=g=b=0;
  }
  
  ColorGL(ColorGL inCgl){
	Init();
    r=inCgl.r;
    g=inCgl.g;
    b=inCgl.b;
  }
  ColorGL(float inR, float inG, float inB){
	  Init() ; 
    SetColor(inR, inG, inB);
  }
  ColorGL Clone(){
	  Init();
    ColorGL tmp = new ColorGL(r, g, b);
    return tmp;
  }
  
  void SetColor(float inR, float inG, float inB){
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
  void SetHSV(float d, float e, float f){
	  float [] hsv = new float[3];
	  hsv[0] = d*360;
	  hsv[1] = e;
	  hsv[2] = f;
	  int c = Color.HSVToColor(hsv);
	  Log.d(LOG_TAG, "Color:"+c);
	  
	  r = (float) ((float)Color.red(c)/255.0);
	  g = (float) ((float)Color.green(c)/255.0);
	  b = (float) ((float)Color.blue(c)/255.0);
	  Log.d(LOG_TAG, "red:"+Color.red(c));
  }
  
  void Randomize(float range){ 
    r += (Randomdouble(range)) - range/2;
    g += (Randomdouble(range)) - range/2;
    b += (Randomdouble(range)) - range/2;
    
    FixRange(r);
    FixRange(g);
    FixRange(b);
  }
  void RandomSaturated(){
	  SetHSV((float)Randomdouble(1),(float)1.0,(float)1.0);
  }
  
  float FixRange(float a){
    if (a>1)a=1;
    else if (a<0)a=1;
    return a;
  }
}
