package com.megadevs.bitlyzer.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

/** Network utils class */
public class Utils {
	
	public static final int BUFFER_SIZE = 2*1024;

	public static byte[] getBytesFromUrl(String URL) throws IOException{
		URL url = new URL(URL);
		ByteArrayOutputStream bais = new ByteArrayOutputStream();
		InputStream is = null;
		try {
		  is = url.openStream ();
		  byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
		  int n;

		  while ( (n = is.read(byteChunk)) > 0 ) {
		    bais.write(byteChunk, 0, n);
		  }
		}
		catch (IOException e) {
		  System.err.printf ("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
		  e.printStackTrace ();
		  // Perform any other exception handling that's appropriate.
		}
		finally {
		  if (is != null) { is.close(); }
		  
		}
		return bais.toByteArray();
		
		
	}
	
	/**<b><br>ATTENZIONE,  PREFERIBILE USARE IL METODO executeHTTPUrlPost</b><br><br>
	 * Read data from  HTTP url
	 * @param myurl 
	 * @return response data
	 * @throws URISyntaxException bad URL
	 * @throws IOException 
	 * @throws FakeConnectivityException 
	 */
	public static HTTPResult executeHTTPUrl(String myurl) throws IOException, URISyntaxException {

		HTTPResult ris = new HTTPResult();

		//Result data
		String data="";

		//Prepare url
		String composeUrl="//"+myurl;
		URLEncoder.encode(composeUrl, "UTF-8");
		URI	tmpUri = new URI("http",composeUrl,null);

		//creo URI per quotare i caratteri speciali
		composeUrl = tmpUri.toString(); //estraggo come stringa
		System.out.println("compose url: "+composeUrl);
		URL url = new URL(composeUrl); //creo URL da aprire

		//Apro connessione
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);

		data=readInputStreamAsString(connection.getInputStream());
		if(data==null)data="";


		ris.setData(data);
		ris.setHeader(connection.getHeaderFields());

		return ris;
	}


	/**
	 * Read data from an HTTP post request.
	 * @param targetURL the target page
	 * @param urlParameters the post parameters
	 * @return HTTP response
	 * @throws IOException 
	 * @throws FakeConnectivityException 
	 * @throws IOException 
	 * @throws URISyntaxException 
	 * */
	public static HTTPResult executeHTTPUrlPost(String targetURL, HTTPPostParameters params, Map<String,String> cookies) throws IOException {
		HTTPResult ris = new HTTPResult();
		try{
			ClientHttpRequest client = new ClientHttpRequest(targetURL);

			//Set cookies
			if(cookies!=null){
				client.setCookies(cookies);
			}

			InputStream is;

			if(params == null)
				is = client.post();
			else if(params.getParamCount()>0){
				is = client.post(params.getParams()); 	
			}
			else 
				is = client.post();

			String data=readInputStreamAsString(is);
			
			//save result
			ris.setData(data);
			//System.out.println(ris.getData());
			ris.setHeader(client.getConnection().getHeaderFields());
			System.out.println("End network");
		}catch(IOException e){
			e.printStackTrace();
		}
		return ris;
	}

	
	public static String readInputStreamAsString(InputStream is) throws IOException{
		final char[] buffer = new char[BUFFER_SIZE];
		StringBuilder out = new StringBuilder(BUFFER_SIZE);
		Reader in = new InputStreamReader(is, "UTF-8");
		int read;
		do {
		  read = in.read(buffer, 0, buffer.length);
		  if (read>0) {
		    out.append(buffer, 0, read);
		  }
		} while (read>=0);
		return out.toString();
	}

}
