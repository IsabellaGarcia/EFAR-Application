/**
 * 
 */
package com.efar.adapter;

import java.util.List;

import com.efar.datamodel.EfarModel;
import com.example.efar.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


/**
 * @author Michyo
 * Adapter for Efar selection.
 */
public class EfarAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	// private Bitmap mPic; 
	private List<EfarModel> efars;  
	
	public EfarAdapter(Context context, List<EfarModel> EFARs){  
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
			convertView = mInflater.inflate(R.layout.efar_list_item, null);  
			holder = new ViewHolder();  
			holder.name = (TextView) convertView.findViewById(R.id.efar_list_name);  
			holder.address = (TextView) convertView.findViewById(R.id.efar_list_address_tag); 
			holder.skill = (TextView) convertView.findViewById(R.id.efar_list_skill_tag); 
			holder.select = (CheckBox) convertView.findViewById(R.id.select_this_efar);
			// holder.icon = (ImageView) convertView.findViewById(R.id.icon);  
        
			convertView.setTag(holder);  
		}  
		else{  
			holder = (ViewHolder) convertView.getTag();  
		}  
		
		/*
		 * Picture processing. 
		BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 50;//图片宽高都为原来的五十分之一，即图片为原来的二千五百分之一 
		mPic = BitmapFactory.decodeFile(items.get(position).toString().trim(), opts);    
        holder.icon.setImageBitmap(mPic);  
        //mPic.recycle();
         */
		EfarModel this_efar = efars.get(position);
		holder.name.setText(this_efar.getName().toString().trim());
		holder.address.setText(this_efar.getAddress_tag().toString().trim());
		holder.skill.setText(this_efar.getSkill_available().toString().trim());
		holder.select.setChecked(false);

		return convertView;  
	}  
	
	private class ViewHolder{  
		TextView name;  
		TextView address;
		TextView skill;
		CheckBox select;
		// ImageView icon;  
	}
}  