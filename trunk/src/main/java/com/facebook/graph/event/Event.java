package com.facebook.graph.event;

public class Event {
	private String id;
	private String name;
	private String description;
	private String location;
	private String privacy;
	private String start_time;
	private String end_time;
	private String updated_time;
	private String rsvp_status;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
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
	public String getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(String updatedTime) {
		updated_time = updatedTime;
	}
	public String getRsvp_status() {
		return rsvp_status;
	}
	public void setRsvp_status(String rsvpStatus) {
		rsvp_status = rsvpStatus;
	}	
}
