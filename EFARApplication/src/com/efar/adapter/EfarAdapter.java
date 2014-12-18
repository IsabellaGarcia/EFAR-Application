/**
 * 
 */
package com.efar.adapter;

import java.util.List;

import com.efar.datamodel.EfarModel;
import com.efar.datamodel.EventModel;
import com.example.efar.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


/**CSIT 6000B
 * @author Xinyi HUANG
 * Student Name: HUANG Xinyi   Student ID:20222719   
 * Email: xhuangap@connect.ust.hk
 */
public class EfarAdapter extends BaseAdapter {
	private List<EfarModel> mData;
	private Context mContext;
	
	public EfarAdapter(Context context, List data) {
		this.mData = data;
		this.mContext = context;
		// TODO Auto-generated constructor stub
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
		View view = View.inflate(mContext, R.layout.efar_list_item, null);
		EfarModel volunteer = mData.get(position);
		TextView volunteer_name = (TextView)view.findViewById(R.id.efar_list_name);
		volunteer_name.setText(volunteer.getName());
		TextView volunteer_address = (TextView)view.findViewById(R.id.efar_list_address_tag);
		volunteer_address.setText(volunteer.getAddressTag());
		
		return view;
	}
	
}  