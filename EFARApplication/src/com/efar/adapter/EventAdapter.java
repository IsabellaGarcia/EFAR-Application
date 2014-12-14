/** 
* File name: ContactAdapter.java
* @author Xinyi HUANG
* Created Date: 26/11/2014
* Description: Adapter for EventActivity.java
*/

package com.efar.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.efar.database.DatabaseConstants;
import com.efar.database.DatabaseHelper;
import com.efar.datamodel.EventModel;
import com.example.efar.R;

public class EventAdapter extends BaseAdapter{
	private List<EventModel> mData;
	private Context mContext;
	private DatabaseHelper mDbHelper;
	
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

	//public 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(mContext, R.layout.event_list_item, null);
		EventModel event = mData.get(position);	
		//Initialize view
		TextView event_phone = (TextView)view.findViewById(R.id.event_phone);	
		TextView event_time = (TextView)view.findViewById(R.id.event_time);	
		//Dynamically bind data to view
		event_phone.setText(event.getAddress_tag());
		event_time.setText(event.getTime());
		//return the final view
		return view;
	}

}
