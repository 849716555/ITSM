package com.goldsunny.itsm.businesslogic;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.model.Mai_RecoveryDetailMDL;
import com.goldsunny.itsm.model.Mai_RecoveryMainMDL;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.Json2EntitiesUtil;
import com.goldsunny.itsm.webservicebll.SystemServiceDate;

/**
 * ����ά��
 * 
 * @author yangwy
 * @version 1.0
 * @created 2014-4-23 ����10:03:45
 */
public class RecoveryMainBo {

	List<Mai_RecoveryMainMDL> recovList;
	SystemServiceDate systemServiceDate;
	HashMap<String, String> parmas;

	public RecoveryMainBo() {
		systemServiceDate = new SystemServiceDate();
	}

	/**
	 * ����: ��ȡ����ά���б�
	 * 
	 * @param status
	 *            ״̬
	 * @return
	 * @throws Exception
	 */
	public List<Mai_RecoveryMainMDL> recoveryMainList(String status, String faultID) throws Exception {
		try {
			parmas = new HashMap<String, String>();
			parmas.put("userid", GlobalData.employeeMDL.getID());
			parmas.put("bustatus", status);
			String result = systemServiceDate.HandeMenthod(parmas, "GetRecoveryMains");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				recovList = Json2EntitiesUtil.GetRecoveryMains(jsonArray);
			}
		} catch (Exception e) {
			throw e;
		}
		return recovList;
	}

	/**
	 * ����: ��ȡ����ά���б�
	 * 
	 * @param status
	 *            ״̬
	 * @param pageIndex
	 *            ҳ
	 * @return
	 * @throws Exception
	 */
	public List<Mai_FaultReportMDL> getRecoverDoList(String status, int pageIndex) throws Exception {
		List<Mai_FaultReportMDL> faulList = null;
		try {
			parmas = new HashMap<String, String>();
			String sql = " 1=1 and MTeamID in (select OID from Syst_MaintainTeam where OID in (select MTeamID from Syst_MTeamPerson where EmployeeID='"
					+ GlobalData.employeeMDL.getID() + "' and canMT=1 )) ";
			if (!CommonClass.isNullorEmpty(status)) {
				if ("12102".equals(status)) {
					// ������
					sql = sql + " and  BuStatus in('" + status + "','12111')";
				}
			}
			parmas.put("keywords", sql);
			parmas.put("pageindex", String.valueOf(pageIndex));
			String result = systemServiceDate.HandeMenthod(parmas, "GetRecoveryList");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				faulList = Json2EntitiesUtil.GetFaultReports(jsonArray);
			}
		} catch (Exception e) {
			throw e;
		}
		return faulList;
	}

	public List<Mai_FaultReportMDL> queryRecovery(HashMap<String, String> query, int pageIndex) throws Exception {
		List<Mai_FaultReportMDL> faulList = null;
		try {
			parmas = new HashMap<String, String>();
			String sql = " 1=1 ";
			String metamsql=" and MTeamID in (select OID from Syst_MaintainTeam where OID in (select MTeamID from Syst_MTeamPerson where EmployeeID='"
					+ GlobalData.employeeMDL.getID() + "' and canMT=1 ))";
			if (query != null) {
				if (query.containsKey("status")) {
					String status = query.get("status");
					if (!CommonClass.isNullorEmpty(status)) {
						if ("12102".equals(status)) {
							// ������
							sql = sql + " and  BuStatus in('" + status + "','12111')";
							sql=sql+metamsql;
						}
						else if ("12103".equals(status)) {
							//������
							sql = sql + " and  BuStatus ='" + status + "' ";
							sql=sql+metamsql;
						}
						else if ("12105".equals(status)) {
							//������
							sql = sql + " and  BuStatus ='" + status + "' ";
						}
						else if ("12104".equals(status)) {
							//���˻�
							sql = sql + " and oid in (select FaultReportID from Mai_RecoveryMain where (BuStatus='" + status + "' OR BuStatus='12106' OR BuStatus='12108')";
							sql=sql+metamsql;
						}
						else if ("12105".equals(status)) {
							//��ת��
							sql = sql + " and  BuStatus ='" + status + "' ";
						}
					}
				}
				if (query.containsKey("key")) {
					String key = query.get("key");
					if (!CommonClass.isNullorEmpty(key))
						sql = sql + " and   FaultDesc like '%" + key + "%'";
				}
				if (query.containsKey("beginDate")) {
					String beginDate = query.get("beginDate");
					if (!CommonClass.isNullorEmpty(beginDate))
						sql = sql + " and   ReportTime >=  '" + beginDate + "'";
				}
				if (query.containsKey("endDate")) {
					String endDate = query.get("endDate");
					if (!CommonClass.isNullorEmpty(endDate))
						sql = sql + " and   ReportTime <=  '" + endDate + "'";
				}
				if (query.containsKey("place")) {
					String place = query.get("place");
					if (!CommonClass.isNullorEmpty(place))
						sql = sql + " and LocationID in (select OID from GetLocationChild(  '" + place + "'))";
				}
				
			}
			parmas.put("keywords", sql);
			parmas.put("pageindex", String.valueOf(pageIndex));
			String result = systemServiceDate.HandeMenthod(parmas, "GetRecoveryList");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				faulList = Json2EntitiesUtil.GetFaultReports(jsonArray);
			}
		} catch (Exception e) {
			throw e;
		}
		return faulList;
	}

	/**
	 * ����: ����faultId ��ȡ���ϴ���
	 * 
	 * @param faultID
	 *            ���ϱ���ID
	 * @return
	 * @throws Exception
	 */
	public Mai_RecoveryMainMDL GetRecoveryMainInfoByReportID(String faultID) throws Exception {
		Mai_RecoveryMainMDL recoveMain = null;
		try {
			parmas = new HashMap<String, String>();
			parmas.put("ReportListID", faultID);
			String result = systemServiceDate.HandeMenthod(parmas, "GetRecoveryMainInfoByReportID");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				recovList = Json2EntitiesUtil.GetRecoveryMains(jsonArray);
				if (recovList != null && recovList.size() > 0) {
					recoveMain = recovList.get(0);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return recoveMain;
	}

	/**
	 * ����: �������ϴ���
	 * 
	 * @param recoverMain
	 * @return
	 * @throws Exception
	 */
	public boolean addRecoveryMain(Mai_RecoveryMainMDL recoverMain) throws Exception {
		boolean bl = false;
		try {
			String json = Json2EntitiesUtil.GetRecoveryMain2Json(recoverMain);
			json = "[" + json + "]";
			parmas = new HashMap<String, String>();
			parmas.put("json", json);
			String result = systemServiceDate.HandeMenthod(parmas, "AddRecoveryMain");
			if ("true".equals(result))
				bl = true;
		} catch (Exception e) {
			throw e;
		}
		return bl;
	}

	/**
	 * ����: ���¹��ϴ���
	 * 
	 * @param recoverMain
	 * @return
	 * @throws Exception
	 */
	public boolean updateRecoveryMain(Mai_RecoveryMainMDL recoverMain) throws Exception {
		boolean bl = false;
		try {
			String json = Json2EntitiesUtil.GetRecoveryMain2Json(recoverMain);
			json = "[" + json + "]";
			parmas = new HashMap<String, String>();
			parmas.put("json", json);
			String result = systemServiceDate.HandeMenthod(parmas, "UpdateRecoveryMain");
			if ("true".equals(result))
				bl = true;
		} catch (Exception e) {
			throw e;
		}
		return bl;
	}

	/**
	 * ����: ��ȡ����ά����ϸ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Mai_RecoveryDetailMDL> getRecoverDeatalList(String recoverMainId) throws Exception {
		List<Mai_RecoveryDetailMDL> detailList = null;
		try {
			parmas = new HashMap<String, String>();
			parmas.put("RecoveryMainID", recoverMainId);
			String result = systemServiceDate.HandeMenthod(parmas, "GetRecoveryDetails");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				detailList = Json2EntitiesUtil.GetRecoveryDetails(jsonArray);
			}
		} catch (Exception e) {
			throw e;
		}
		return detailList;
	}

	/**
	 * ����: ��������ά����ϸ
	 * 
	 * @param recoveryDetailMDL
	 * @return
	 */
	public boolean addRecoverDetail(Mai_RecoveryDetailMDL recoveryDetailMDL) {
		boolean bl = false;
		try {
			String json = Json2EntitiesUtil.GetRecoveryDetail2Json(recoveryDetailMDL);
			json = "[" + json + "]";
			parmas = new HashMap<String, String>();
			parmas.put("json", json);
			String result = systemServiceDate.HandeMenthod(parmas, "AddRecoveryDetail");
			if ("true".equals(result))
				bl = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bl;
	}

	/**
	 * ����:���¹�����ϸ
	 * 
	 * @param recoveryDetailMDL
	 * @return
	 */
	public boolean updateRecoverDetail(Mai_RecoveryDetailMDL recoveryDetailMDL) {
		boolean bl = false;
		try {
			String json = Json2EntitiesUtil.GetRecoveryDetail2Json(recoveryDetailMDL);
			json = "[" + json + "]";
			parmas = new HashMap<String, String>();
			parmas.put("json", json);
			String result = systemServiceDate.HandeMenthod(parmas, "UpdateRecoveryDetail");
			if ("true".equals(result))
				bl = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bl;
	}

}
