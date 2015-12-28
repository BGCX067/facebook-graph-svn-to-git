package com.facebook.graph;

public enum Scope {
	PUBLISH_STREAM( "publish_stream" ),
	CREATE_EVENT( "create_event" ),
	RSVP_EVENT( "rsvp_event" ),
	SMS( "sms" ),
	OFFLINE_ACCESS( "offline_access" ),
	EMAIL( "email" ),
	READ_INSIGHTS( "read_insights" ),
	READ_STREAM( "read_stream" ),
	USER_ABOUT_ME( "user_about_me" ),
	USER_ACTIVITIES( "user_activities" ),
	USER_BIRTHDAY( "user_birthday" ),
	USER_EDUCATION_HISTORY( "user_education_history" ),
	USER_EVENTS( "user_events" ),
	USER_GROUPS( "user_groups" ),
	USER_HOMETOWN( "user_hometown" ),
	USER_INTERESTS( "user_interests" ),
	USER_LIKES( "user_likes" ),
	USER_LOCATION( "user_location" ),
	USER_NOTES( "user_notes" ),
	USER_ONLINE_PRESENCE( "user_online_presence" ),
	USER_PHOTO_VIDEO_TAGS( "user_photo_video_tags" ),
	USER_PHOTOS( "user_photos" ),
	USER_RELATIONSHIPS( "user_relationships" ),
	USER_RELIGION_POLITICS( "user_religion_politics" ),
	USER_STATUS( "user_status" ),
	USER_VIDEOS( "user_videos" ),
	USER_WEBSITE( "user_website" ),
	USER_WORK_HISTORY( "user_work_history" ),
	FRIENDS_ABOUT_ME( "friends_about_me" ),
	FRIENDS_ACTIVITIES( "friends_activities" ),
	FRIENDS_BIRTHDAY( "friends_birthday" ),
	FRIENDS_EDUCATION_HISTORY( "friends_education_history" ),
	FRIENDS_EVENTS( "friends_events" ),
	FRIENDS_GROUPS( "friends_groups" ),
	FRIENDS_HOMETOWN( "friends_hometown" ),
	FRIENDS_INTERESTS( "friends_interests" ),
	FRIENDS_LIKES( "friends_likes" ),
	FRIENDS_LOCATION( "friends_location" ),
	FRIENDS_NOTES( "friends_notes" ),
	FRIENDS_ONLINE_PRESENCE( "friends_online_presence" ),
	FRIENDS_PHOTO_VIDEO_TAGS( "friends_photo_video_tags" ),
	FRIENDS_PHOTOS( "friends_photos" ),
	FRIENDS_RELATIONSHIPS( "friends_relationships" ),
	FRIENDS_RELIGION_POLITICS( "friends_religion_politics" ),
	FRIENDS_STATUS( "friends_status" ),
	FRIENDS_VIDEOS( "friends_videos" ),
	FRIENDS_WEBSITE( "friends_website" ),
	FRIENDS_WORK_HISTORY( "friends_work_history" ),	
	READ_FRIENDLISTS( "read_friendlists" ),
	READ_REQUESTS( "read_requests" );
	
	private final String scope;
	
	Scope( String scope ) {
		this.scope = scope;
	}
	
	public String toString() { return scope; }
}
