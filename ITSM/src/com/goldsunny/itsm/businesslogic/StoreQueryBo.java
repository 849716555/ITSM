package com.goldsunny.itsm.businesslogic;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

import com.goldsunny.itsm.model.Equ_EquipmentMDL;
import com.goldsunny.itsm.model.StoreMDL;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.Json2EntitiesUtil;
import com.goldsunny.itsm.webservicebll.SystemServiceDate;

/**
 * 库存查询    
 * @author yangwy       
 * @version 1.0     
 * @created 2014-5-15 下午5:17:15
 */
public class StoreQueryBo {

	SystemServiceDate systemServiceDate;
	HashMap<String, String> parmas;
	public StoreQueryBo()
	{
		systemServiceDate=new SystemServiceDate();
	}
	
	/** 
	 * 描述: 根据查询条件获取 
	 * @param query 查询参数 
	 * @param pageIndex 页数
	 * @return
	 */
	public List<StoreMDL> getStoreList(HashMap<String,String> query,int pageIndex)
	{
		List<StoreMDL> stroeList=null;
		try {
			parmas = new HashMap<String, String>();
			String key="";
			String name="";
			String brand="";
			String store="";
			String type="";
			if(query.containsKey("key")) 
				key=query.get("key");  
			if(query.containsKey("name")) 
				name=query.get("name");  
			if(query.containsKey("brand")) 
				brand=query.get("brand");  
			if(query.containsKey("store")) 
				store=query.get("store");  
			if(query.containsKey("type")) 
				type=query.get("type");
			parmas.put("key", key);
			parmas.put("name", name);
			parmas.put("brand", brand);
			parmas.put("store", store);
			parmas.put("type", type);
			parmas.put("pageindex", String.valueOf(pageIndex));
			String result = systemServiceDate.HandeMenthod(parmas, "GetStockList");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				stroeList = Json2EntitiesUtil.GetStoreList(jsonArray);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return stroeList;
	}
	
	public List<Equ_EquipmentMDL> getEqmentListByThingId(String thingId,int pageIndex)
	{
		List<Equ_EquipmentMDL> equList=null;
		try {
			parmas = new HashMap<String, String>();
			parmas.put("thingOID", thingId);
			parmas.put("pageindex", String.valueOf(pageIndex));
			String result = systemServiceDate.HandeMenthod(parmas, "GetStockInDetailInfo");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				equList = Json2EntitiesUtil.GetEquipments(jsonArray);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equList;
	}
}
