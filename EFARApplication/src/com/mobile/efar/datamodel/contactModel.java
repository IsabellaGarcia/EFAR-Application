/** 
* Created by Xinyi HUANG
* Created Date: 25/11/2014
* Description: Data model for ContactActivity.java & ContactAdapter
*/
package com.mobile.efar.datamodel;

public class contactModel {
	//Contact name
	private String name;
	//Contact phone number
	private String number;
	//Contact block number
	private String block_num;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBlock_num() {
		return block_num;
	}
	public void setBlock_num(String block_num) {
		this.block_num = block_num;
	}
}
