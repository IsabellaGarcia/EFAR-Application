
package com.efar.datamodel;

import java.util.Vector;

/**CSIT 6000B
 * @author Xinyi HUANG
 * Student Name: HUANG Xinyi   Student ID:20222719   
 * Email: xhuangap@connect.ust.hk
 * Description: Model of Record.
 */
public class RecordModel {
	private int id;
	private String eventName;
	private String eventDetail;
	private String send_list;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDetail() {
		return eventDetail;
	}
	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
	}
	public String getSend_list() {
		return send_list;
	}
	public void setSend_list(String send_list) {
		this.send_list = send_list;
	}
	

 
}
