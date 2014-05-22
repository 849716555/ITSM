package com.goldsunny.itsm.view.tools;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.model.Sys_FileMDL;
import com.goldsunny.itsm.util.AsyncBitmapLoader;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.GlobalData;

/** 
 * π ’œ±®–ﬁÕº∆¨¡–±Ì  ≈‰∆˜
 * @author yangwy       
 * @version 1.0     
 * @created 2014-5-13 œ¬ŒÁ4:19:37
 */
public class FaultImageAdapter extends BaseAdapter{

	public List<Sys_FileMDL> imageList;
	private LayoutInflater inflater;
    private AsyncBitmapLoader asyncBitmapLoader;
	
	public FaultImageAdapter(List<Sys_FileMDL> imagelist,Context context)
	{
		imageList=imagelist;
		inflater= LayoutInflater.from(context);
		asyncBitmapLoader=new AsyncBitmapLoader();
	}

	@Override
	public int getCount() {
		return imageList.size();
	}

	@Override
	public Object getItem(int position) {
		return imageList.get(position);
	}

	@Override
	public long getItemId(int position) { 
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) { 
		if(convertView==null)
		{ 
			convertView=inflater.inflate(R.layout.fault_image_item, null); 
		}
		ImageView disclosureImg=(ImageView)convertView.findViewById(R.id.fault_item_image);
		Sys_FileMDL file=imageList.get(position);
		/*
		 */
		String imageURL=GlobalData.SystemConfig.getServerWeb()+file.getFilePath(); 
		String filePath=asyncBitmapLoader.loadBitmap(disclosureImg, imageURL, new AsyncBitmapLoader.ImageCallBack(){

			@Override
			public void imageLoad(ImageView imageView, Bitmap bitmap) {
				// TODO Auto-generated method stub  
				imageView.setImageBitmap(ThumbnailUtils.extractThumbnail(bitmap, 70, 120));
			}});
		
		if(CommonClass.isNullorEmpty(filePath))  
        {  
			disclosureImg.setImageResource(R.drawable.load);  
        }  
        else  
        {   
        	disclosureImg.setImageBitmap(ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(filePath ), 70, 120)); 
        }    
		return convertView;
	}
	
	
	static class ViewHolder {
		ImageView disclosureImg; 
	}
	
}
