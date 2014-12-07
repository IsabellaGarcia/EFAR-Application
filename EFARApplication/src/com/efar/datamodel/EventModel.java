/** 
* Created by Xinyi HUANG
* Created Date: 25/11/2014
* Description: Data model for EventActivity.java & EventAdapter
*/

package com.efar.datamodel;

public class EventModel {
	//Emergency name
	private int id;
	private String phone;
	private String time;
	private String address_tag;
	private String description;
	
	//private String event_name;
	private int[] efars;
	

	/**
	 * @return the efars
	 */
	public int[] getEfars() {
		return efars;
	}

	/**
	 * @param efars the efars to set
	 */
	public void setEfars(int[] efars) {
		this.efars = efars;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getAddress_tag() {
		return address_tag;
	}

	public void setAddress_tag(String address_tag) {
		this.address_tag = address_tag;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
