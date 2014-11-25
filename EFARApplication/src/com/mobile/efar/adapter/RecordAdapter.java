/** 
* File name: ContactAdapter.java
* Created by Xinyi HUANG
* Created Date: 26/11/2014
* Description: Adapter for RecordActivity.java
*/

package com.mobile.efar.adapter;

import java.util.List;



import com.example.efar.R;
import com.mobile.efar.datamodel.recordModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RecordAdapter extends BaseAdapter{
	private List<recordModel> mData;
	private Context mContext;
	
	public RecordAdapter(Context context, List data){
		this.mData = data;
		this.mContext = context;
	}
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(mContext, R.layout.item_record, null);
		recordModel record = mData.get(position);
		
		//Initialize view
		TextView record_name = (TextView)view.findViewById(R.id.record_name);	
		//Dynamically bind data to view
		record_name.setText(record.getRecord_name());
		//return the final view
		return view;
	}
	

}
