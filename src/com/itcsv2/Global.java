package com.itcsv2;

import org.json.JSONObject;

import android.app.Application;

public class Global extends Application{
	
	   private JSONObject json;
	   private int x = 2;
	 
	   public JSONObject getData(){
	     return this.json;
	   }
	   public int getX(){
		     return this.x;
	   }
	 	   
	   public void setData(JSONObject j){
	     this.json=j;
	   }
	   public void setX(int x){
		     this.x=x;
	   }
}