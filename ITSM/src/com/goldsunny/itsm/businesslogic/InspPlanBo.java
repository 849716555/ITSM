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
 * Ѳ��ƻ� 
 * @author yangwy       
 * @version 1.0     
 * @created 2014-5-20 ����4:32:22
 */
public class InspPlanBo {

	SystemServiceDate systemServiceDate;
	HashMap<String, String> parmas;
	public InspPlanBo()
	{
		systemServiceDate = new SystemServiceDate();
	}
	
	/** 
	 * ����:  ��ȡѲ��ƻ�
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
	 * ����:����Ѳ��ƻ�OID��ȡѲ��ϸ  
	 * @param inspPlanId Ѳ��ƻ�OID
	 * @param pageIndex  ҳ��
	 * @return Ѳ����ϸ�б�
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
