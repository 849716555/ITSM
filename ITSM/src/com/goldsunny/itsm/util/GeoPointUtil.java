package com.goldsunny.itsm.util;

import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;

/**
 * 地理坐标工具类
 * 
 * @author yangwy
 * @version 1.0
 * @created 2014-4-21 下午4:27:30
 */
public class GeoPointUtil {

	/**
	 * 描述 故障列表获取地理位置信息
	 * 
	 * @param faulList
	 * @return
	 */
	public static HashMap<String, Integer> faultGeoPoint(List<Mai_FaultReportMDL> faulList) {
		HashMap<String, Integer> geoMap = new HashMap<String, Integer>();
		try {
			if (faulList != null && faulList.size() > 0) {
				Mai_FaultReportMDL faulMdl = null;
				Log.i("location", "loca:" + faulList.size());
				for (int i = 0; i < faulList.size(); i++) {
					faulMdl = faulList.get(i);
					double x = faulMdl.getX();
					double y = faulMdl.getY();
					Log.i("location", "loca:" + x+y);
					if (x != 0 && y != 0) {
						String geo = faulMdl.getX() + ";" + faulMdl.getY();
						Log.i("location", "loca:+11111" + geo);
						if (!geoMap.containsKey(geo))
							geoMap.put(geo, 1);
						else {
							int count = geoMap.get(geo);
							geoMap.remove(geo);
							geoMap.put(geo, count + 1);
							Log.i("location", "loca:+11111");
						}
					} 
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return geoMap;
	}
	
	/** 
	 * 描述:  地图标记点数量
	 * @param count 数量
	 * @return 图片资源ID
	 */
	public static int LocationImage(int count){
		if(count==1){
			return R.drawable.icon_marka;
		}else if(count==2){
			return R.drawable.icon_markb;
		}else if(count==3){
			return R.drawable.icon_markc;
		}else if(count==4){
			return R.drawable.icon_markd;
		}else if(count==5){
			return R.drawable.icon_marke;
		}else if(count==6){
			return R.drawable.icon_markf;
		}else if(count==7){
			return R.drawable.icon_markg;
		}else if(count==8){
			return R.drawable.icon_markh;
		}else if(count==9){
			return R.drawable.icon_marki;
		}else if(count==10){
			return R.drawable.icon_markj;
		} 
		return R.drawable.icon_marka;
	}
}
