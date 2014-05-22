package com.goldsunny.itsm.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldsunny.itsm.R;

public class FalultMainItem extends BaseAdapter {
	private LayoutInflater inflater;
	private List<HashMap<String, String>> list; 
	public FalultMainItem(Context context){ 
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list = new ArrayList<HashMap<String, String>>();
	}
	public FalultMainItem(Context context,List<HashMap<String, String>> list){
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list = list;
	}
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView nameView = null;
		TextView phoneView = null;
		ImageView imageView=null;
		if(convertView==null){
			convertView = inflater.inflate(R.layout.recove_list_item, null);//生成条目界面对象 
			imageView=(ImageView)convertView.findViewById(R.id.main_item_image);
			nameView = (TextView) convertView.findViewById(R.id.item_text);
			phoneView = (TextView) convertView.findViewById(R.id.item_text_describe);  
			ViewCache cache = new ViewCache();
			cache.imageView=imageView;
			cache.nameView = nameView;
			cache.phoneView = phoneView;
			convertView.setTag(cache);
			 
		}else{
			ViewCache cache = (ViewCache) convertView.getTag();
			imageView=cache.imageView;
			nameView = cache.nameView;
			phoneView = cache.phoneView;
		}
		HashMap<String, String> map = list.get(position); 
		imageView.setImageResource(R.drawable.login_zy);
		
		nameView.setText(map.get("title"));
		phoneView.setText(map.get("describe")); 
		
		return convertView;
	}
	
	private final class ViewCache{
		private ImageView imageView;
		public TextView nameView;
		public TextView phoneView;
	
	}


}
