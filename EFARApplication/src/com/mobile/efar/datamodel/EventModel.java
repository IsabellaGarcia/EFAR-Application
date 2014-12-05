/** 
* Created by Xinyi HUANG
* Created Date: 25/11/2014
* Description: Data model for EventActivity.java & EventAdapter
*/

package com.mobile.efar.datamodel;

public class EventModel {
	//Emergency name
	private int id;
	private String event_name;
	private int[] efars;
	
	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

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
}
