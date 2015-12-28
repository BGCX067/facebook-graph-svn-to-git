package com.facebook.graph.user;

public class Event {
	private String id;
	private String name;
	private String location;
	private String rsvp_status;
	private String start_time;
	private String end_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRsvp_status() {
		return rsvp_status;
	}
	public void setRsvp_status(String rsvpStatus) {
		rsvp_status = rsvpStatus;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String startTime) {
		start_time = startTime;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String endTime) {
		end_time = endTime;
	}	
}
