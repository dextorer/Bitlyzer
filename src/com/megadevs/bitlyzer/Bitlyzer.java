package com.megadevs.bitlyzer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.megadevs.bitlyzer.utils.Utils;

public class Bitlyzer {

	private String formatString 	= "&format=";
	private String apiKeyString		= "&apiKey=";
	private String longUrlString	= "&longUrl=";
	private String endpoint			= "http://api.bit.ly/v3/shorten?login=";
	
	private String url;
	
	private String login;
	private String apiKey;

	private BitlyzerCallback mCallback;
	
	public Bitlyzer(String login, String apiKey) {
		this.login = login;
		this.apiKey = apiKey;
	}
	
	public void shorten(String url, BitlyzerCallback c) {
		this.url = url;
		mCallback = c;
		
		new BitlyThread().start();
	}

    private class BitlyThread extends Thread {

    	private JSONResponse mResponse;
    	
    	@Override
    	public void run() {

    		if (!url.contains("http://"))
    			url = "http://" + url; 
    		
    		endpoint = endpoint + login + apiKeyString + apiKey + formatString + "json" + longUrlString + url;
    		System.out.println(endpoint);
    		
			try {
				URLConnection conn = new URL(endpoint).openConnection();
				InputStream is = conn.getInputStream();
				String result = Utils.readInputStreamAsString(is);
				
				Gson g = new Gson();
				mResponse = g.fromJson(result, JSONResponse.class);
				
				if (mResponse.statusCode.equals("200"))
					mCallback.onSuccess(mResponse.getURL());
				else mCallback.onError(mResponse.statusTXT);
				
			} catch (IOException e) {
				mCallback.onError(e.getMessage());
			}
    	}
    	
		@SuppressWarnings("unused")
		public class JSONResponse {
			
			@SerializedName("status_code")
			public String statusCode;
			
			@SerializedName("data")
			public Data data = new Data();
			
			public String getURL() { return data.URL; }
			
			@SerializedName("status_txt")
			public String statusTXT;
			
			public class Data {
				
				@SerializedName("url")
				public String URL;
				
				@SerializedName("hash")
				public String hash;
				
				@SerializedName("global_hash")
				public String globalHash;
				
				@SerializedName("long_url")
				public String longURL;
				
				@SerializedName("new_hash")
				public String newHash;
			}
		}
    }
    
    public static abstract class BitlyzerCallback {
    	public abstract void onSuccess(String shortUrl);
    	public abstract void onError(String reason);
    }
}