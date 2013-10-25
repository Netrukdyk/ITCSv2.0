package com.itcs;

import org.json.JSONObject;

import android.app.Application;

public class Global extends Application{
	   private JSONObject json;
	 
	   public JSONObject getData(){
	     return this.json;
	   }
	 
	   public void setData(JSONObject j){
	     this.json=j;
	   }
	}