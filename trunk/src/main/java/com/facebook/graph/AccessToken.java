package com.facebook.graph;

public class AccessToken {
	private String token;
	private int expires;
	public AccessToken( String token, int expires ) {
		setToken( token );
		setExpires( expires );
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpires() {
		return expires;
	}
	public void setExpires(int expires) {
		this.expires = expires;
	}
}
