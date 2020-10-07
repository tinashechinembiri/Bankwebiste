package com.cognizant.AsyncResponsemainclass;

public interface AsyncResponse {
	 boolean resume(Object response);
	 boolean resume(Throwable response);
}
