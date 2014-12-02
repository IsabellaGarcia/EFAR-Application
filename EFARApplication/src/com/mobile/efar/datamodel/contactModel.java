package com.mobile.efar.datamodel;

import static android.provider.BaseColumns._ID;
import android.database.Cursor;
import static com.mobile.efar.database.DatabaseConstants.*;

public class ContactModel {

	private int contactId;
	private String displayName;
	private String phoneNum;
	private String sortKey;
	private Long photoId;
	private String lookUpKey;
	private int selected = 0;
	private String formattedNumber;
	private String pinyin;

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public String getLookUpKey() {
		return lookUpKey;
	}

	public void setLookUpKey(String lookUpKey) {
		this.lookUpKey = lookUpKey;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public String getFormattedNumber() {
		return formattedNumber;
	}

	public void setFormattedNumber(String formattedNumber) {
		this.formattedNumber = formattedNumber;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	/**
	 * Reduct from a cursor to build a class of ContactModel.
	 * @param cursor:Cursor
	 * @return result:ContactModel
	 */
	public static ContactModel reductContact(Cursor cursor) {
		ContactModel result = new ContactModel();
		if(cursor != null && cursor.moveToFirst()){  
            int _id = cursor.getInt(cursor.getColumnIndex(_ID));  
            String name = cursor.getString(cursor.getColumnIndex(NAME));  
            result.setContactId(_id);  
            result.setDisplayName(name);  
        }  
        return result;  
	}
}
