/** 
* @author Xinyi HUANG
* Created Date: 25/11/2014
* Description: Data model for EventActivity.java & EventAdapter
*/

package com.efar.datamodel;

import java.util.Vector;

public class EventModel {
	//Emergency name
	private int id;
	private String phone;
	private String time;
	private String address_tag;
	private String description;
	private Vector<String> relatedEfarsVector;
	private String send_list;  //send out SMS contacts list
	
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
 
}
