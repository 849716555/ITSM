package com.goldsunny.itsm.businesslogic;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

import com.goldsunny.itsm.model.Mai_InspPLanDetailMDL;
import com.goldsunny.itsm.model.Mai_InspPlanMDL;
import com.goldsunny.itsm.model.Mai_InspRecordDetailMDL;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.Json2EntitiesUtil;
import com.goldsunny.itsm.webservicebll.SystemServiceDate;

/** 
 * 巡检计划 
 * @author yangwy       
 * @version 1.0     
 * @created 2014-5-20 下午4:32:22
 */
public class InspPlanBo {

	SystemServiceDate systemServiceDate;
	HashMap<String, String> parmas;
	public InspPlanBo()
	{
		systemServiceDate = new SystemServiceDate();
	}
	
	/** 
	 * 描述:  获取巡检计划
	 * @return
	 */
	public List<Mai_InspPlanMDL> getInspPlanMainList(int pageIndex)
	{
		List<Mai_InspPlanMDL> inspList=null;
		try {
			parmas = new HashMap<String, String>();
			parmas.put("userid", GlobalData.employeeMDL.getID());
			String result = systemServiceDate.HandeMenthod(parmas, "GetInspPlan");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				inspList = Json2EntitiesUtil.GetInspPlans(jsonArray);
			}
		} catch (Exception e) {
			
		}
		return inspList;
	}
	
	/**
	 * 描述:根据巡检计划OID获取巡明细  
	 * @param inspPlanId 巡检计划OID
	 * @param pageIndex  页数
	 * @return 巡检明细列表
	 */
	public List<Mai_InspRecordDetailMDL> getInspPlanDetalList(String inspPlanId,int pageIndex){
		List<Mai_InspRecordDetailMDL> inspPLanDetail=null;
		try { 
			parmas = new HashMap<String, String>();
			parmas.put("userid", GlobalData.employeeMDL.getID());
			String result = systemServiceDate.HandeMenthod(parmas, "GetInspPlan");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				//inspPLanDetail = Json2EntitiesUtil.getins(jsonArray);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return inspPLanDetail;
	}
}
