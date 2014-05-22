package com.goldsunny.itsm.businesslogic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;

import com.goldsunny.itsm.R.string;
import com.goldsunny.itsm.model.Equ_EquipmentMDL;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.Json2EntitiesUtil;
import com.goldsunny.itsm.webservicebll.SystemService;
import com.goldsunny.itsm.webservicebll.SystemServiceDate;

public class EquipmentBO {

	List<Equ_EquipmentMDL> equList;
	Equ_EquipmentMDL equiMentMDL;
	SystemService systemService;
	SystemServiceDate systemServiceDate;
	HashMap<String, String> parmas;
	HashMap<String, string> paramdddd;
	
	public EquipmentBO()
	{
		systemServiceDate=new SystemServiceDate();
	}
	
	public Equ_EquipmentMDL getEquMentByCode(String code)  {
		try {
			parmas = new HashMap<String, String>();  
			parmas.put("code", code); 
			String result = systemServiceDate.HandeMenthod(parmas, "GetEquByEquCode");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				equList = Json2EntitiesUtil.GetEquipments(jsonArray);
				if(equList!=null&&equList.size()>0)
				{
					equiMentMDL=equList.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return equiMentMDL;
	}
	
}
