/** 
* File name: ContactAdapter.java
* Created by Xinyi HUANG
* Created Date: 26/11/2014
* Description: Adapter for EventActivity.java
*/

package com.mobile.efar.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.efar.R;
import com.mobile.efar.datamodel.eventModel;

public class EventAdapter extends BaseAdapter{
	private List<eventModel> mData;
	private Context mContext;
	
	public EventAdapter(Context context, List data){
		this.mData = data;
		this.mContext = context;
	}
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(mContext, R.layout.event_list_item, null);
		eventModel event = mData.get(position);
		
		//Initialize view
		TextView event_name = (TextView)view.findViewById(R.id.event_name);	
		//Dynamically bind data to view
		event_name.setText(event.getEvent_name());
		//return the final view
		return view;
	}

}
