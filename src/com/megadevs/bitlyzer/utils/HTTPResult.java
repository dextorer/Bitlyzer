package com.megadevs.bitlyzer.utils;

import java.util.List;
import java.util.Map;

/**
 * HTTP response result class.
 */
public class HTTPResult {

	/** result data */
	private String data;
	
	/** response header */
	private Map<String, List<String>> header;
	
	
	public HTTPResult(){
		data=null;
		header=null;
	}
	
	/**
	 * Build an HTTPResult with only response data
	 * @param data response data
	 */
	public HTTPResult(String _data){
		data=_data;
		header=null;
	}
	  
	/**
	 * Build an HTTPResult with response data and response header
	 * @param data response data
	 * @param header response header
	 */
	public HTTPResult(String _data, Map<String, List<String>> _header){
		data=_data;
		header = _header;
	}

	
	
	//***  SET & GET  ***//
	
	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setHeader(Map<String, List<String>> header) {
		this.header = header;
	}

	public Map<String, List<String>> getHeader() {
		return header;
	}
	
}
