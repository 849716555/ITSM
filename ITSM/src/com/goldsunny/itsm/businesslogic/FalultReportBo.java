package com.goldsunny.itsm.businesslogic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.kobjects.base64.Base64;

import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.model.Sys_FileMDL;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.Json2EntitiesUtil;
import com.goldsunny.itsm.webservicebll.SystemService;
import com.goldsunny.itsm.webservicebll.SystemServiceDate;

/**
 * 故障报修
 * 
 * @author yangwy
 * @version 1.0
 * @created 2014-4-19 下午3:46:58
 */
public class FalultReportBo {

	List<Mai_FaultReportMDL> faultList;
	SystemService systemService;
	SystemServiceDate systemServiceDate;
	HashMap<String, String> parmas;

	public FalultReportBo() {
		systemService = new SystemService();
		systemServiceDate = new SystemServiceDate();
	}

	/**
	 * @Title: getFalReportList
	 * @Description: 获取故障列表
	 * @return: List<Mai_FaultReportMDL>
	 */
	public List<Mai_FaultReportMDL> getFalReportList(String UserId, String status, int pageIndex)
			throws Exception {
		try {
			parmas = new HashMap<String, String>();
			parmas.put("userid", UserId);
			parmas.put("status", status);
			parmas.put("pageindex", String.valueOf(pageIndex));
			String result = systemServiceDate.HandeMenthod(parmas, "GetFaultReports");
			if (!CommonClass.isNullorEmpty(result)) {
				JSONArray jsonArray = new JSONArray(result);
				faultList = Json2EntitiesUtil.GetFaultReports(jsonArray);
			}
		} catch (Exception e) {
			throw e;
		}
		return faultList;
	}

	/**
	 * 描述: 保存故障上报，上传图片附件
	 * 
	 * @param faultReport
	 *            故障
	 * @param oldImageList
	 *            图片列表
	 * @param newImageList
	 *            新图片列表
	 * @return
	 */
	public String saveFaultReport(Mai_FaultReportMDL faultReport, List<Sys_FileMDL> oldImageList,
			List<Sys_FileMDL> newImageList) {
		String result = "";
		try {
			String json = Json2EntitiesUtil.GetFaultReport2Json(faultReport);
			json = "[" + json + "]";
			String imageJson = "["; 
			for (Sys_FileMDL flle : oldImageList) {
				if (!newImageList.contains(flle)) {
					flle.setOperate("del");
					imageJson = imageJson + Json2EntitiesUtil.GetSysFile2Json(flle) + ",";
				}
			}
			for (Sys_FileMDL file : newImageList) {
				if ("add".equals(file.getOperate())) {
					imageJson=imageJson+filePaht(file)+",";
				}
			}
			if(imageJson.length()>2) 
				imageJson=imageJson.substring(0,imageJson.lastIndexOf(","));
			imageJson=imageJson+"]";
			parmas = new HashMap<String, String>();
			parmas.put("json", json);
			parmas.put("images", imageJson);
			result = systemServiceDate.HandeMenthod(parmas, "AddFaultReports");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String filePaht(Sys_FileMDL imageFile)
	{
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		try {
			File file = new File(imageFile.getFilePath());
			fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = fis.read(buffer)) >= 0) {
				baos.write(buffer, 0, count);
			}
			String uploadBuffer = new String(Base64.encode(baos.toByteArray())); // 进行Base64编码 
			String jsonString = "{";
			jsonString += "\"OID\":\""+imageFile.getOID()+"\",";
			jsonString += "\"BizOID\":\""+""+"\",";
			jsonString += "\"FileName\":\""+imageFile.getFileName()+"\",";
			jsonString += "\"File_Type\":\"" + imageFile.getFile_Type() + "\",";
			jsonString += "\"FileExtName\":\"" + "" + "\",";
			jsonString += "\"FileSize\":\""+""+"\",";
			jsonString += "\"SaveType\":\""+""+"\",";
			jsonString += "\"FileContent\":\"" + uploadBuffer + "\",";
			jsonString += "\"Operate\":\"" + imageFile.getOperate() + "\",";
			jsonString += "\"FilePath\":\"" +imageFile.getFilePath() + "\"";
			jsonString += "}";    
			return jsonString;
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
		return "";
	}

	/**
	 * 描述 :新增故障报修
	 * 
	 * @return
	 */
	public Boolean addFalutReprot(Mai_FaultReportMDL faultReport) throws Exception {
		Boolean result = false;
		try {
			String json = Json2EntitiesUtil.GetFaultReport2Json(faultReport);
			json = "[" + json + "]";
			parmas = new HashMap<String, String>();
			parmas.put("json", json);
			String re = systemServiceDate.HandeMenthod(parmas, "AddFaultReport");
			if ("true".equals(re))
				result = true;
			else
				result = false;
		} catch (Exception e) {
			result = false;
			throw e;
		}
		return result;
	}

	/**
	 * 描述 :更新故障报修
	 * 
	 * @return
	 */
	public Boolean updateFalutReprot(Mai_FaultReportMDL faultReport) throws Exception {
		Boolean result = false;
		try {
			String json = Json2EntitiesUtil.GetFaultReport2Json(faultReport);
			json = "[" + json + "]";
			parmas = new HashMap<String, String>();
			parmas.put("json", json);
			String re = systemServiceDate.HandeMenthod(parmas, "UpdateFaultReport");
			if ("true".equals(re))
				result = true;
			else
				result = false;
		} catch (Exception e) {
			result = false;
			throw e;
		}
		return result;
	}

	/**
	 * 描述 删除故障报修
	 * 
	 * @return
	 * @throws Exception
	 */
	public Boolean delFalutReprot(String oids) throws Exception {
		Boolean result = false;
		try {
			parmas = new HashMap<String, String>();
			parmas.put("oids", oids);
			String re = systemServiceDate.HandeMenthod(parmas, "DelFaultReport");
			if ("true".equals(re))
				result = true;
			else
				result = false;
		} catch (Exception e) {

			throw e;
		}
		return result;
	}

}
