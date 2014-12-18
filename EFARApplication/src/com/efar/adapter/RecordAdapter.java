
package com.efar.adapter;

import java.util.List;

import com.efar.datamodel.EventModel;
import com.efar.datamodel.RecordModel;
import com.example.efar.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**CSIT 6000B
 * @author Xinyi HUANG
 * Student Name: HUANG Xinyi   Student ID:20222719   
 * Email: xhuangap@connect.ust.hk 
 */
public class RecordAdapter extends BaseAdapter {
	private List<RecordModel> mData;
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
		View view = View.inflate(mContext, R.layout.record_list_item, null);
		RecordModel record = mData.get(position);	
		//Initialize view
		TextView record_list_name = (TextView)view.findViewById(R.id.record_list_name);	
		TextView record_list_efars = (TextView)view.findViewById(R.id.record_list_efars);	
		TextView record_list_detail = (TextView)view.findViewById(R.id.record_list_detail);
		//Dynamically bind data to view
		record_list_name.setText(record.getEventName());
		record_list_efars.setText(record.getSend_list());
		record_list_detail.setText(record.getEventDetail());
		//return the final view
		return view;
	}
	
}  