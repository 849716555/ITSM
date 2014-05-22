package com.goldsunny.itsm.businesslogic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.kobjects.base64.Base64;

import android.util.Log;

import com.goldsunny.itsm.model.MaintainTeamMDL;
import com.goldsunny.itsm.model.StockMDL;
import com.goldsunny.itsm.model.Sys_DictListMDL;
import com.goldsunny.itsm.model.Sys_FileMDL;
import com.goldsunny.itsm.model.Syst_LocationMDL;
import com.goldsunny.itsm.model.Syst_RoadMDL;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.Json2EntitiesUtil;
import com.goldsunny.itsm.util.JsonHelper;
import com.goldsunny.itsm.webservicebll.SystemServiceDate;

/**
 * @ClassName��DataInitBo
 * @Description����ʼ����������
 * @author��yangwy
 * @date 2014-4-15
 */
public class BaseDataBo {

	SystemServiceDate systemServiceDate;
	HashMap<String, String> parmas;

	public BaseDataBo() {
		systemServiceDate = new SystemServiceDate();
	}

	/**
	 * 
	 * ����:  ��ȡ������ʱ��
	 * @return
	 */
	public Date getSystemDate()
	{
		Date date=null;
		try {
			parmas = new HashMap<String, String>();
			String result = systemServiceDate.HandeMenthod(parmas, "GetSysDateTime");
			if (!CommonClass.isNullorEmpty(result)) 
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date=sdf.parse(result);
			}
		} catch (Exception e) {
			Log.i("base", "base"+e.getMessage());
		}
		return date;
	}
	
	/**
	 * @Title: getRoad
	 * @Description: ��ȡ·����Ϣ
	 * @return
	 * @return: List<Syst_RoadMDL>
	 */
	public List<Syst_RoadMDL> getRoad() throws Exception {
		List<Syst_RoadMDL> roadList = new ArrayList<Syst_RoadMDL>();
		try {
			parmas = new HashMap<String, String>();
			parmas.put("userid", GlobalData.employeeMDL.getID());
			String result = systemServiceDate.HandeMenthod(parmas, "GetSyst_Road");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				roadList = Json2EntitiesUtil.GetRoads(jsonArray);
			}
		} catch (Exception e) {
			throw e;
		}
		return roadList;
	}

	/**
	 * @Title: getAllLoaction
	 * @Description: ��ȡȫ������λ����Ϣ(ûд��)
	 * @param roadId
	 *            ·������
	 * @return
	 * @throws Exception
	 * @return: List<Syst_LocationMDL>
	 */
	public List<Syst_LocationMDL> getAllLoaction(String roadId) throws Exception {
		List<Syst_LocationMDL> locationList = new ArrayList<Syst_LocationMDL>();
		try {
			parmas = new HashMap<String, String>();
			parmas.put("roadid", roadId);
			parmas.put("userid", GlobalData.employeeMDL.getID());
			String result = systemServiceDate.HandeMenthod(parmas, "GetSyst_Road");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				locationList = Json2EntitiesUtil.GetLocations(jsonArray);
			}
		} catch (Exception e) {
			throw e;
		}
		return locationList;
	}

	/**
	 * @Title: getTollStation
	 * @Description: ��ȡ�շ�վ�б�
	 * @return
	 * @throws Exception
	 * @return: List<Syst_LocationMDL>
	 */
	public List<Syst_LocationMDL> getTollStation(String roadId) throws Exception {
		List<Syst_LocationMDL> tollStation = new ArrayList<Syst_LocationMDL>();
		try {
			parmas = new HashMap<String, String>();
			parmas.put("roadid", roadId);
			parmas.put("userid", GlobalData.employeeMDL.getID());
			String result = systemServiceDate.HandeMenthod(parmas, "GetTollStation");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				tollStation = Json2EntitiesUtil.GetLocations(jsonArray);
			}
		} catch (Exception e) {
			throw e;
		}
		return tollStation;
	}

	/**
	 * ����: ��ȡά������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<MaintainTeamMDL> getTeanMDL() throws Exception {
		List<MaintainTeamMDL> maintainTeamList = null;
		try {
			parmas = new HashMap<String, String>();
			parmas.put("userid", GlobalData.employeeMDL.getID());
			String result = systemServiceDate.HandeMenthod(parmas, "GetMaintainTeam");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				maintainTeamList = Json2EntitiesUtil.GetMaintainTeams(jsonArray);
			}
		} catch (Exception e) {
			throw e;
		}
		return maintainTeamList;
	}

	/**
	 * @Title: getDictList
	 * @Description: ��ȡ�����ֵ��б�
	 * @return
	 * @throws Exception
	 * @return: List<Sys_DictListMDL>
	 */
	public List<Sys_DictListMDL> getDictList() throws Exception {
		List<Sys_DictListMDL> dictList = null;
		try {
			parmas = new HashMap<String, String>();
			parmas.put("num", "");
			String result = systemServiceDate.HandeMenthod(parmas, "GetDictList");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				dictList = Json2EntitiesUtil.GetDictLists(jsonArray);
			}
		} catch (Exception e) {
			throw e;
		}
		return dictList;
	}

	/**
	 * ����: ����PID ��ȡ����λ���б�
	 * 
	 * @param pid
	 * @return
	 */
	public List<Syst_LocationMDL> getLocationByPid(String pid) {
		List<Syst_LocationMDL> locationList = null;
		try {
			parmas = new HashMap<String, String>();
			parmas.put("id", pid);
			String result = systemServiceDate.HandeMenthod(parmas, "GetLocationsByParentID");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				locationList = Json2EntitiesUtil.GetLocations(jsonArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locationList;
	}

	/**
	 * ����: ��ȡϵͳ�汾
	 * 
	 * @return
	 */
	public String getVersionNo() {
		String version = "";
		try {
			parmas = new HashMap<String, String>();
			version = systemServiceDate.HandeMenthod(parmas, "GetSysVerNo");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return version;
	}
	
	public List<StockMDL> getStockList()
	{
		List<StockMDL> list=null;
		try {
			parmas = new HashMap<String, String>();
			parmas.put("userid", GlobalData.employeeMDL.getID());
			parmas.put("stockRoomID", ""); 
			String result = systemServiceDate.HandeMenthod(parmas, "GetStockRoom");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				list = Json2EntitiesUtil.GetStockList(jsonArray);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	/**
	 * ����: �ϴ��ļ���������
	 * 
	 * @param path
	 *            �ļ�·��
	 * @param oid
	 *            ҵ������
	 * @return
	 */
	public Boolean upLoadImageServer(String path, String oid) {
		boolean result = false;
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		try {
			File file = new File(path);
			fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = fis.read(buffer)) >= 0) {
				baos.write(buffer, 0, count);
			}
			String uploadBuffer = new String(Base64.encode(baos.toByteArray())); // ����Base64����  
			parmas = new HashMap<String, String>();
			parmas.put("bizoid", oid);
			parmas.put("filename", file.getName());
			parmas.put("image", uploadBuffer); 
			String d = systemServiceDate.HandeMenthod(parmas, "uploadImages");
			if ("true".equals(d))
				result = true;
			else {
				result = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ����: ����ҵ��ID��ȡ�����б�
	 * 
	 * @param bid
	 *            ҵ������
	 * @return
	 */
	public List<Sys_FileMDL> getFileByBizOid(String bid) {
		List<Sys_FileMDL> fileList = null;
		try {
			parmas = new HashMap<String, String>();
			parmas.put("BID", bid);
			String result = systemServiceDate.HandeMenthod(parmas, "GetFileByBizOid");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				fileList = Json2EntitiesUtil.GetFileList(jsonArray);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fileList;
	}

	/**
	 * ����: ���Ӹ���
	 * 
	 * @param fileList
	 *            �����б�
	 * @return
	 */
	public Boolean addFile(List<Sys_FileMDL> fileList) {
		boolean result = false;
		try {
			if (fileList != null && fileList.size() > 0) {
				String json = "[";
				for (Sys_FileMDL file : fileList) {
					json = json + Json2EntitiesUtil.GetSysFile2Json(file) + ",";
				}
				json = json.substring(0, json.lastIndexOf(",")) + "]";
				parmas = new HashMap<String, String>();
				parmas.put("json", json);
				String re = systemServiceDate.HandeMenthod(parmas, "AddSysFile");
				if ("true".equals(re))
					result = true;
				else
					result = false;
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * ����: ����IDɾ������
	 * 
	 * @param ids
	 * @return
	 */
	public boolean delFile(String ids) {
		boolean result = false;
		try {
			parmas = new HashMap<String, String>();
			parmas.put("OID", ids);
			String re = systemServiceDate.HandeMenthod(parmas, "DeleteFileByOID");
			if ("true".equals(re))
				result = true;
			else
				result = false;
		} catch (Exception e) {

		}
		return result;
	}
	
	/**
	 * ��ȡ��ǰ�û�����ά������Ϣ
	 * ����:  
	 * @return
	 */
	public String GetMaintainTeam()
	{
		String teamId="";
		try {
			parmas = new HashMap<String, String>();
			parmas.put("userid", GlobalData.employeeMDL.getID());
			String result = systemServiceDate.HandeMenthod(parmas, "GetMaintainTeamByUserID");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				JSONObject jsonObject2 = (JSONObject) jsonArray.opt(0);
				teamId=JsonHelper.GetString(jsonObject2, "OID");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teamId;
	}

}
