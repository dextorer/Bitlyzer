Bitlyzer
========

Android library for Bit.ly URL shortening.


What is it?
-----------

It's just a very (very very ..) simple Android library that comes in hand when you need to shorten your URLs via the Bit.ly service.


How can I use it?
-----------------

If you already have an account on Bit.ly, you just need to import this library into your Android Project.

First, call the constructor passing your login and API key provided by Bit.ly:

	`Bitlyzer b = new Bitlyzer("bitly_login","bitly_api_key");`

Now call the shorten() method passing the URL you want to shorten (of course) and a callback object, which will be used to return the result:

	`b.shorten("http://www.google.com", new BitlyzerCallback() {
		@Override
		public void onSuccess(String shortUrl) {}
			
		@Override
		public void onError(String reason) {}
	});
	
And you're done!


Some useful links
-----------------

[.MegaDevs official website](http://megadevs.com/)
[.Bit.ly](http://bit.ly/)
