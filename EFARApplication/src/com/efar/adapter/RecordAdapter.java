
package com.efar.adapter;

import java.util.List;

import com.efar.datamodel.RecordModel;
import com.example.efar.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * @author Michyo
 * Adapter for Record selection.
 */
public class RecordAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	// private Bitmap mPic; 
	private List<RecordModel> records;  
	
	public RecordAdapter(Context context, List<RecordModel> Records){  
		mInflater = LayoutInflater.from(context);  
		records = Records;  
	}  
    
	public int getCount(){  
		return records.size();  
	}  
	public Object getItem(int position){  
		return records.get(position);  
	}      
	public long getItemId(int position){  
		return position;  
	}      
	
	@SuppressLint("InflateParams")
	public View getView(int position,View convertView,ViewGroup parent){  
		ViewHolder holder;      
		if(convertView == null){  
			convertView = mInflater.inflate(R.layout.record_list_item, null);  
			holder = new ViewHolder();  
			holder.name = (TextView) convertView.findViewById(R.id.record_list_name);  
			holder.efars = (TextView) convertView.findViewById(R.id.record_list_efars);
			holder.detail = (TextView) convertView.findViewById(R.id.record_list_detail); 
			convertView.setTag(holder);  
		}  
		else{  
			holder = (ViewHolder) convertView.getTag();  
		}  
		
		RecordModel this_record = records.get(position);
		holder.name.setText(this_record.getEventName().toString().trim());
		holder.efars.setText(this_record.getRelatedEfars().toString().trim());
		holder.detail.setText(this_record.getEventDetail().toString().trim());

		return convertView;  
	}  
	
	private class ViewHolder{  
		TextView name;  
		TextView efars;
		TextView detail;
	}
}  