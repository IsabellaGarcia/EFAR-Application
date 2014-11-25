/** 
* File name: ContactAdapter.java
* Created by Xinyi HUANG
* Created Date: 25/11/2014
* Description: Adapter for ContactActivity.java
*/ 

package com.mobile.efar.adapter;

import java.util.List;

import com.example.efar.R;
import com.mobile.efar.datamodel.contactModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter{
	private List<contactModel> mData;
	private Context mContext;
	//Construct function
	public ContactAdapter(Context context, List data){
		this.mData = data;
		this.mContext = context;
	}
	//determine the number of items
	@Override
	public int getCount() {
		return mData.size();
	}
	
	//based on the position, get the corresponding data of item
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	//based on the position, get the corresponding Id of item
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	//Generate the item view -- 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(mContext, R.layout.item_contact, null);
		contactModel contact = mData.get(position);
		
		//Initialize view
		TextView cname = (TextView)view.findViewById(R.id.contact_name);
		TextView cnum = (TextView)view.findViewById(R.id.contact_number);	
		TextView bnum = (TextView)view.findViewById(R.id.contact_block_number);	
		//Dynamically bind data cto view
		cname.setText(contact.getName());
		cnum.setText(contact.getNumber());
		bnum.setText(contact.getBlock_num());
		//return the final view
		return view;
	}
}
