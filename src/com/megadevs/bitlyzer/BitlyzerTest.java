package com.megadevs.bitlyzer;

import com.megadevs.bitlyzer.Bitlyzer.BitlyzerCallback;

public class BitlyzerTest {
    public static void main(String args[]) {
    	Bitlyzer b = new Bitlyzer("bitly_login","bitly_api_key");
    	b.shorten("http://www.google.com", new BitlyzerCallback() {
			
			@Override
			public void onSuccess(String shortUrl) {
				System.out.println("success");
				System.out.println(shortUrl);
			}
			
			@Override
			public void onError(String reason) {
				System.out.println("error");
				System.out.println(reason);
			}
		});
    }

}