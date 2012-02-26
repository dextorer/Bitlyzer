package com.megadevs.bitlyzer.utils;

import java.util.HashMap;

/** HTTP parameters for HTTP Post request*/
public class HTTPPostParameters {
         
	/**
	 * Map of the parameters
	 */
	private HashMap<String, Object> params;
	  
	/**
	 * Create a new HTTPPost parameters container
	 */
	public HTTPPostParameters(){
		params = new HashMap<String, Object>();
	}
	
	/**
	 * Add new parameters
	 * @param name 
	 * @param value
	 */
	public void addParam(String name,Object value){
		params.put(name, value);
	}
	
	/**
	 * Get a specified parameters
	 * @param name
	 * @return
	 */
	public Object getPram(String name){
		return params.get(name);
	}
	
	/**
	 * Get number of parameters
	 * @return
	 */
	public int getParamCount(){
		return params.size();
	}
	
	/**
	 * Generate the string needed for the HTTP post request
	 * @return a post string
	 */
	public String generateHTTPPostParameters(){
		
		String urlParameters="";
		for(String key : params.keySet())
			urlParameters=urlParameters + key + "=" +params.get(key) + "&"; 
		
		//System.out.println(urlParameters);
		if(urlParameters.endsWith("&") && urlParameters.length()>=1 /* avoid fail :P */) 
			urlParameters = urlParameters.substring(0, urlParameters.length()-1);
		
		//System.out.println(urlParameters);
		return urlParameters;
	}

	public HashMap<String, Object> getParams() {
		return params;
	}

	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}
	
}
