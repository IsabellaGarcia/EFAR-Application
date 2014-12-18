/** 
*@author Xinyi HUANG
* Student Name: HUANG Xinyi   Student ID:20222719   
* Email: xhuangap@connect.ust.hk
* Description: Data model for EventActivity.java & EventAdapter
*/

package com.efar.datamodel;

import java.io.Serializable;
import java.util.Vector;

import android.R.integer;
import android.app.Application;

public class EventModel implements Serializable {
	//Emergency name
	private int id;
	private String phone;
	private String time;
	private String address_tag;
	private String description;
	private Vector<String> relatedEfarsVector;
	private String send_list;  //send out SMS contacts list
    private String weekday;
    private int index;
    private int position;
	
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
	

	/**
	 * @return the relatedEfars
	 */
	public String getRelatedEfars() {
		return getRelatedEfars(relatedEfarsVector);
	}

	/**
	 * @param relatedEfars the relatedEfars to set
	 */
	public void setRelatedEfars(String relatedEfars) {
		setRelatedEfarsVector(relatedEfars);
	}
	
	
	public String getRelatedEfars(Vector<String> v) {
		String result = "";
		for(int i = 0; i < v.size()-1; i++) {
			result += v.get(i) + ",";
		}
		result += v.get(v.size()-1);
		return result;
	}

	/**
	 * @return the relatedEfarsVector
	 */
	public Vector<String> getRelatedEfarsVector() {
		return relatedEfarsVector;
	}

	/**
	 * @param relatedEfarsVector the relatedEfarsVector to set
	 */
	public void setRelatedEfarsVector(Vector<String> relatedEfarsVector) {
		this.relatedEfarsVector = relatedEfarsVector;
	}
	
	public void setRelatedEfarsVector(String efars) {
		String[] separate_efars = efars.split(",");
		Vector<String> results = new Vector<String>();
		for(int i = 0; i < separate_efars.length; i++) {
			results.add(separate_efars[i]);
		}
		this.relatedEfarsVector = results;
	}
	
	public void addEfar(EfarModel efar) {
		this.relatedEfarsVector.add(efar.getName());
	}
	
	public void deleteEfar(EfarModel efar) {
		this.relatedEfarsVector.remove(efar.getName());
	}
	
	public String generateDetail() {
		String result = "From: " + phone + "\n"
				+ "When: " + time + "\n" 
				+ "At:" + address_tag + "\n"
				+ "Description:" + description;
		return result;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String info) {
		this.weekday = info;
	}

	public String getSend_list() {
		return send_list;
	}

	public void setSend_list(String send_list) {
		this.send_list = send_list;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
 
}
