/**
 * Created by Michyo SONG
 * Created Date: 03/12/2014
 * Description: Model of Efars.
 */

package com.efar.datamodel;

import static android.provider.BaseColumns._ID;
import static com.efar.database.DatabaseConstants.*;
import android.database.Cursor;

public class EfarModel {
	
	private int id;
	private String name;
	private String phone;
	private String address_tag;
	private String time_available;
	private String skill_available;

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
	public String getAddress_tag() {
		return address_tag;
	}
	/**
	 * @param address_tag the address_tag to set
	 */
	public void setAddress_tag(String address_tag) {
		this.address_tag = address_tag;
	}
	/**
	 * @return the time_available
	 */
	public String getTime_available() {
		return time_available;
	}
	/**
	 * @param time_available the time_available to set
	 */
	public void setTime_available(String time_available) {
		this.time_available = time_available;
	}
	/**
	 * @return the skill_available
	 */
	public String getSkill_available() {
		return skill_available;
	}
	/**
	 * @param skill_available the skill_available to set
	 */
	public void setSkill_available(String skill_available) {
		this.skill_available = skill_available;
	}
	
	/**
	 * Reduct from a cursor to build a class of ContactModel.
	 * @param cursor:Cursor
	 * @return result:ContactModel
	 */
	public static EfarModel reductEfar(Cursor cursor) {
		EfarModel result = new EfarModel();
		if(cursor != null && cursor.moveToFirst()){   
            result.setId(cursor.getInt(cursor.getColumnIndex(_ID)));  
            result.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            result.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            result.setAddress_tag(cursor.getString(cursor.getColumnIndex(ADDRESS_TAG)));
            result.setTime_available(cursor.getString(cursor.getColumnIndex(TIME_AVAILABLE)));
            result.setSkill_available(cursor.getString(cursor.getColumnIndex(SKILL_AVAILABLE)));
        }  
        return result;  
	}

}
