<<<<<<< HEAD
=======
/** 
* @author Xinyi HUANG
* Created Date: 25/11/2014
* Description: Data model for RecordActivity.java & RecordAdapter
*/
>>>>>>> FETCH_HEAD
package com.efar.datamodel;

import java.util.Vector;

/**
 * @author Michyo
 * Model of Record.
 */
public class RecordModel {
	private int id;
	private String eventName;
	private String eventDetail;
	private Vector<String> relatedEfarsVector;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String record_name) {
		this.eventName = record_name;
	}

	/**
	 * @return the eventDetail
	 */
	public String getEventDetail() {
		return eventDetail;
	}

	/**
	 * @param eventDetail the eventDetail to set
	 */
	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
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
 
}
