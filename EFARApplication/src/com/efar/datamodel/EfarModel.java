/**
 * Created by Michyo SONG
 * Created Date: 03/12/2014
 * Description: Model of Efars.
 */

package com.efar.datamodel;

/**
 * @author HUANG Xinyi
 */

public class EfarModel {
	
	private int id;
	private String name;
	private String phone;
	private String addressTag;
	private String timeAvailable;
	private String skillAvailable;

	public EfarModel() {
	}
	
	public EfarModel(String name, String phone, String addressTag, String timeAvailable, String skillAvailable) {
		this.name = name;
		this.phone = phone;
		this.addressTag = addressTag;
		this.timeAvailable = timeAvailable;
		this.skillAvailable = skillAvailable;
	}
	
	public EfarModel(int id, String name, String phone, String addressTag, String timeAvailable, String skillAvailable) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.addressTag = addressTag;
		this.timeAvailable = timeAvailable;
		this.skillAvailable = skillAvailable;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the address_tag
	 */
	public String getAddressTag() {
		return addressTag;
	}
	/**
	 * @param address_tag the address_tag to set
	 */
	public void setAddressTag(String address_tag) {
		this.addressTag = address_tag;
	}
	/**
	 * @return the time_available
	 */
	public String getTimeAvailable() {
		return timeAvailable;
	}
	/**
	 * @param time_available the time_available to set
	 */
	public void setTimeAvailable(String time_available) {
		this.timeAvailable = time_available;
	}
	/**
	 * @return the skill_available
	 */
	public String getSkillAvailable() {
		return skillAvailable;
	}
	/**
	 * @param skill_available the skill_available to set
	 */
	public void setSkillAvailable(String skill_available) {
		this.skillAvailable = skill_available;
	}
}
