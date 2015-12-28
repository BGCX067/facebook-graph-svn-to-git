package com.facebook.graph.user;


public class User {
	private String id;
	private String email;
	private String name;
	private String first_name;
	private String last_name;
	private String link;
	private String about;
	private String birthday;
	private String gender;
	private String locale;
	private String updated_time;
	private Location location;
	private String website;
	private int timezone;
	private boolean verified;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String firstName) {
		first_name = firstName;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String lastName) {
		last_name = lastName;
	}
	public String getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(String updatedTime) {
		updated_time = updatedTime;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}	
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public int getTimezone() {
		return timezone;
	}
	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
}
