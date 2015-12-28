package com.facebook.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.facebook.graph.user.Event;
import com.facebook.graph.user.EventType;
import com.facebook.graph.user.Friend;
import com.facebook.graph.user.User;
import com.google.gson.Gson;

public class GraphClient {
	private Log log = LogFactory.getLog( this.getClass() );
	
	private HttpClient httpClient;
	private String baseURL = "https://graph.facebook.com";
	private String authorizeURL = "https://graph.facebook.com/oauth/authorize";
	private String accessTokenURL = "https://graph.facebook.com/oauth/access_token";
	private String clientId;
	private String clientSecret;
	
	public GraphClient( String clientId, String clientSecret ) {
		setHttpClient( new HttpClient() );
		setClientId( clientId );
		setClientSecret( clientSecret );		
	}
	
	public GraphClient( HttpClient httpClient, String clientId, String clientSecret ) {
		setHttpClient( httpClient );
		setClientId( clientId );
		setClientSecret( clientSecret );
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public HttpClient getHttpClient() {
		return httpClient;
	}
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	public String getBaseURL() {
		return baseURL;
	}
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	public String getAuthorizeURL() {
		return authorizeURL;
	}
	public void setAuthorizeURL(String authorizeURL) {
		this.authorizeURL = authorizeURL;
	}
	public String getAccessTokenURL() {
		return accessTokenURL;
	}
	public void setAccessTokenURL(String accessTokenURL) {
		this.accessTokenURL = accessTokenURL;
	}
	
	public String getAuthorizeURL( String redirectURI, List<Scope> scopeList ) {
		
		StringBuilder b = new StringBuilder();
		URLCodec codec = new URLCodec();
		b.append( getAuthorizeURL() );
		b.append( "?client_id=" ).append( getClientId() );
		try {
			b.append( "&redirect_uri=" ).append( codec.encode( redirectURI ) );
		} catch (EncoderException e) {
			log.error( "Unable to URL encode redirect_uri: " + redirectURI, e );
		}
		if( scopeList != null && scopeList.size() != 0 ) {
			StringBuffer scope = new StringBuffer();
			for( Scope s : scopeList ) {
				if( scope.length() != 0 ) {
					scope.append( "," );
				}
				scope.append( s );
			}
			b.append( "&scope=" ).append( scope );
		}
		
		return b.toString();
	}
	
	public AccessToken getAccessToken( String redirectURI, String code ) {

		List<NameValuePair> pairs = new ArrayList<NameValuePair>(4);
		pairs.add( new NameValuePair( "client_id", getClientId() ) );
		pairs.add( new NameValuePair( "redirect_uri", redirectURI ) );
		pairs.add( new NameValuePair( "client_secret", getClientSecret() ) );
		pairs.add( new NameValuePair( "code", code ) );
		GetMethod get = new GetMethod( getAccessTokenURL() );
		get.setQueryString( pairs.toArray( new NameValuePair[4] ) );
		
		AccessToken accessToken = null;
		try {
			int status = getHttpClient().executeMethod(get);
			if( status == 200 ) {
				BufferedReader reader = new BufferedReader( new InputStreamReader( get.getResponseBodyAsStream(), "UTF-8" ) );
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				reader.close();
				int expires = 0;
				String token = null;
				String params[] = sb.toString().split( "&" );
				for( String param : params ) {
					String pair[] = param.split( "=" );
					if( pair[0].equals("access_token" ) ) {
						token = pair[1];
					} else if( pair[0].equals( "expires" ) ) {
						expires = NumberUtils.toInt(pair[1]);
					}
				}
				
				if( !StringUtils.isEmpty(token) ) {
					accessToken = new AccessToken( token, expires );
				}
			} else {
				log.error( "status: " + status );
			}
		} catch (HttpException e) {
			log.error( e );
		} catch (IOException e) {
			log.error( e );
		} finally {
			get.releaseConnection();
		}
		
		return accessToken;
	}
	
	private JSONObject get( String action, Map<String, String> parameters, AccessToken accessToken ) {
		JSONObject retval = null;
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		
		if( parameters != null ) {
			for( Entry<String, String> entry: parameters.entrySet() ) {
				pairs.add( new NameValuePair( entry.getKey(), entry.getValue() ) );
			}
		}
		
		pairs.add( new NameValuePair( "access_token", accessToken.getToken() ) );
		
		GetMethod get = new GetMethod( getBaseURL() + action );
		get.setQueryString( pairs.toArray( new NameValuePair[pairs.size()] ) );
		
		try {
			int status = getHttpClient().executeMethod(get);
			if( status == 200 ) {
				BufferedReader reader = new BufferedReader( new InputStreamReader( get.getResponseBodyAsStream(), "UTF-8" ) );
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
				reader.close();
				log.info( sb.toString() );
				retval = JSONObject.fromObject( sb.toString() );
			} else {
				log.error( "status: " + status );
			}
		} catch (HttpException e) {
			log.error( e );
		} catch (IOException e) {
			log.error( e );
		} finally {
			get.releaseConnection();
		}
		
		return retval;
	}
	
	public User getUser( AccessToken accessToken ) {
		return getUser( "me", accessToken );
	}

	public User getUser( String userId, AccessToken accessToken ) {
		JSONObject json = get( "/" + userId, null, accessToken );
        User user = new Gson().fromJson(json.toString(), User.class);		
		return user;
	}

	public List<Friend> getFriends( AccessToken accessToken ) {
		JSONObject json = get( "/me/friends", null, accessToken );
		List<Friend> friendList = (List<Friend>)JSONArray.toList( json.getJSONArray("data"), Friend.class );		
		return friendList;
	}

	public List<Friend> getFriends( String userId, AccessToken accessToken ) {
		JSONObject json = get( "/" + userId + "/friends", null, accessToken );	
		List<Friend> friendList = (List<Friend>)JSONArray.toList( json.getJSONArray("data"), Friend.class );		
		return friendList;
	}

	public List<Event> getEvents( AccessToken accessToken, EventType type, Date since, int limit ) {
		return getEvents( accessToken, type.toString(),  since, limit );
	}
	
	public List<Event> getEvents( AccessToken accessToken, String type, Date since, int limit ) {
		Map<String, String> params = new HashMap<String, String>();
		if( since != null ) {
			// 2010-07-24T05:00:00+0000
			SimpleDateFormat sdfDate = new SimpleDateFormat( "yyyy-MM-dd" );
			SimpleDateFormat sdfTime = new SimpleDateFormat( "HH:mm:ss" );
			params.put( "since", sdfDate.format(since) + "T" + sdfTime.format(since) + "+0000" );
		}
		if( limit != 0 ) {
			params.put( "limit", Integer.toString( limit ) ); 
		}
		JSONObject json = get( "/me/events/" + (StringUtils.isBlank(type)?"":type), params, accessToken );	
		List<Event> eventList = (List<Event>)JSONArray.toList( json.getJSONArray("data"), Event.class );		
		return eventList;
	}
	
	public com.facebook.graph.event.Event getEvent( AccessToken accessToken, String id ) {
		JSONObject json = get( "/" + id, null, accessToken );	
		com.facebook.graph.event.Event event = (com.facebook.graph.event.Event) JSONObject.toBean(json, com.facebook.graph.event.Event.class);
		return event;
	}
}
