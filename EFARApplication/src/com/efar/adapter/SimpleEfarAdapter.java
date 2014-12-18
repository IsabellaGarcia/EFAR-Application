/**
 * 
 */
package com.efar.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.efar.datamodel.EfarModel;
import com.example.efar.R;

/**CSIT 6000B
 * @author Michyo
 *
 */
public class SimpleEfarAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	// private Bitmap mPic; 
	private List<EfarModel> efars;  
	
	public SimpleEfarAdapter(Context context, List<EfarModel> EFARs){  
		mInflater = LayoutInflater.from(context);  
		efars = EFARs;  
	}  
    
	public int getCount(){  
		return efars.size();  
	}  
	public Object getItem(int position){  
		return efars.get(position);  
	}      
	public long getItemId(int position){  
		return position;  
	}      
	
	@SuppressLint("InflateParams")
	public View getView(int position,View convertView,ViewGroup parent){  
		ViewHolder holder;      
		if(convertView == null){  
			convertView = mInflater.inflate(R.layout.efar_simple_list_item, null);  
			holder = new ViewHolder();  
			holder.name = (TextView) convertView.findViewById(R.id.efar_simple_list_name);  
			holder.address = (TextView) convertView.findViewById(R.id.efar_simple_list_address_tag); 
			holder.skill = (TextView) convertView.findViewById(R.id.efar_simple_list_skill_tag); 
			convertView.setTag(holder);  
		}  
		else{  
			holder = (ViewHolder) convertView.getTag();  
		}  
		
		EfarModel this_efar = efars.get(position);
		holder.name.setText(this_efar.getName().toString().trim());
		holder.address.setText(this_efar.getAddressTag().toString().trim());
		holder.skill.setText(this_efar.getSkillAvailable().toString().trim());

		return convertView;  
	}  
	
	private class ViewHolder{  
		TextView name;  
		TextView address;
		TextView skill;
		// ImageView icon;  
	}
}
