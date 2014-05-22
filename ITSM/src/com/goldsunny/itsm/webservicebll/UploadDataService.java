package com.goldsunny.itsm.webservicebll;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.goldsunny.itsm.dataaccess.SoapObjectHelper;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.model.Mai_InspEquMDL;
import com.goldsunny.itsm.model.Mai_InspRecordDetailMDL;
import com.goldsunny.itsm.model.Mai_InspRecordMDL;
import com.goldsunny.itsm.model.Mai_RecoveryDetailMDL;
import com.goldsunny.itsm.model.Mai_RecoveryMainMDL;
import com.goldsunny.itsm.model.MaintainRecordDetailMDL;
import com.goldsunny.itsm.model.MaintainRecordMDL;
import com.goldsunny.itsm.model.RealMaintainEQUMDL;
import com.goldsunny.itsm.model.Sys_FileMDL;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.Json2EntitiesUtil;
import com.goldsunny.itsm.util.MyAndroidHttpTransport;

public class UploadDataService extends ServiceBase {

	public String UploadFaultReport(List<Mai_FaultReportMDL> faultReportMDLs) {
		String methodName = "AddFaultReport";
		Object result = "";
		try {
			String jsonString = "[";

			for (Mai_FaultReportMDL faultReportMDL : faultReportMDLs) {
				jsonString += Json2EntitiesUtil
						.GetFaultReport2Json(faultReportMDL) + ",";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 1);
			jsonString += "]";
			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("json", jsonString);
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(
					GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
			return SoapObjectHelper.parseNullString(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public String UploadRecoveryMain(List<Mai_RecoveryMainMDL> recoveryMainMDLs) {
		String methodName = "AddRecoveryMain";
		Object result = "";
		try {
			String jsonString = "[";

			for (Mai_RecoveryMainMDL recoveryMainMDL : recoveryMainMDLs) {
				jsonString += Json2EntitiesUtil
						.GetRecoveryMain2Json(recoveryMainMDL) + ",";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 1);
			jsonString += "]";
			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("json", jsonString);
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(
					GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
			return SoapObjectHelper.parseNullString(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
	public String UploadRecoveryDetail(List<Mai_RecoveryDetailMDL> recoveryDetailMDLs) {
		String methodName = "AddRecoveryDetail";
		Object result = "";
		try {
			String jsonString = "[";

			for (Mai_RecoveryDetailMDL recoveryMainMDL : recoveryDetailMDLs) {
				jsonString += Json2EntitiesUtil
						.GetRecoveryDetail2Json(recoveryMainMDL) + ",";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 1);
			jsonString += "]";
			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("json", jsonString);
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(
					GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
			return SoapObjectHelper.parseNullString(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public String UploadInspRecord(List<Mai_InspRecordMDL> inspRecordMDLs) {
		String methodName = "AddInspRecord";
		Object result = "";
		try {
			String jsonString = "[";

			for (Mai_InspRecordMDL recoveryMainMDL : inspRecordMDLs) {
				jsonString += Json2EntitiesUtil
						.GetInspRecord2Json(recoveryMainMDL) + ",";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 1);
			jsonString += "]";
			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("json", jsonString);
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(
					GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
			return SoapObjectHelper.parseNullString(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public String UploadInspRecordDetail(
			List<Mai_InspRecordDetailMDL> inspRecordDetailMDLs) {
		String methodName = "AddInspRecordDetail";
		Object result = "";
		try {
			String jsonString = "[";

			for (Mai_InspRecordDetailMDL recoveryMainMDL : inspRecordDetailMDLs) {
				jsonString += Json2EntitiesUtil
						.GetInspRecordDetail2Json(recoveryMainMDL) + ",";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 1);
			jsonString += "]";
			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("json", jsonString);
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(
					GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
			return SoapObjectHelper.parseNullString(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public String UploadInspEqu(List<Mai_InspEquMDL> inspEquMDLs) {
		String methodName = "AddInspEqu";
		Object result = "";
		try {
			String jsonString = "[";

			for (Mai_InspEquMDL recoveryMainMDL : inspEquMDLs) {
				jsonString += Json2EntitiesUtil
						.GetInspEqu2Json(recoveryMainMDL) + ",";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 1);
			jsonString += "]";
			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("json", jsonString);
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(
					GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
			return SoapObjectHelper.parseNullString(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public String UploadMTRecord(List<MaintainRecordMDL> mtRecordMDLs) {
		String methodName = "AddMTRecord";
		Object result = "";
		try {
			String jsonString = "[";

			for (MaintainRecordMDL recoveryMainMDL : mtRecordMDLs) {
				jsonString += Json2EntitiesUtil
						.GetMTRecord2Json(recoveryMainMDL) + ",";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 1);
			jsonString += "]";
			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("json", jsonString);
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(
					GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
			return SoapObjectHelper.parseNullString(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public String UploadMTRecordDetail(
			List<MaintainRecordDetailMDL> mtRecordMDLs) {
		String methodName = "AddMTRecordDetail";
		Object result = "";
		try {
			String jsonString = "[";

			for (MaintainRecordDetailMDL recoveryMainMDL : mtRecordMDLs) {
				jsonString += Json2EntitiesUtil
						.GetMTRecordDetail2Json(recoveryMainMDL) + ",";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 1);
			jsonString += "]";
			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("json", jsonString);
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(
					GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
			return SoapObjectHelper.parseNullString(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public String UploadRealMaintainEQU(List<RealMaintainEQUMDL> mtRecordMDLs) {
		String methodName = "AddRealMaintainEQU";
		Object result = "";
		try {
			String jsonString = "[";

			for (RealMaintainEQUMDL recoveryMainMDL : mtRecordMDLs) {
				jsonString += Json2EntitiesUtil.GetMTEqu2Json(recoveryMainMDL)
						+ ",";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 1);
			jsonString += "]";
			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("json", jsonString);
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(
					GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
			return SoapObjectHelper.parseNullString(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public String UploadSysFile(List<Sys_FileMDL> sys_FileMDLs) {
		String methodName = "AddSysFile";
		Object result = "";
		try {
			String jsonString = "[";

			for (Sys_FileMDL recoveryMainMDL : sys_FileMDLs) {
				jsonString += Json2EntitiesUtil
						.GetSysFile2Json(recoveryMainMDL) + ",";
			}
			jsonString = jsonString.substring(0, jsonString.length() - 1);
			jsonString += "]";
			String sopaAction = serviceNameSpace + methodName;
			SoapObject request = new SoapObject(serviceNameSpace, methodName);
			request.addProperty("json", jsonString);
			SoapSerializationEnvelope envelope = GetEnvelope(request);
			MyAndroidHttpTransport ht = new MyAndroidHttpTransport(
					GetServiceUrl());
			ht.call(sopaAction, envelope);
			result = (Object) envelope.getResponse();
			return SoapObjectHelper.parseNullString(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public String UploadImgFile(List<Sys_FileMDL> sys_FileMDLs) {
		String result = "";
		String str = "";
		boolean isSuccess = false;
		try {
			for (Sys_FileMDL i : sys_FileMDLs) {
				try {
					str = "";
					if (i.getFilePath().indexOf(".") > 0) {
						str = GetImgBuffer(GlobalData.PhotoPath
								+ i.getFilePath());
						isSuccess = UploadIamge(str, i.getFilePath(),
								i.getFileName());
					}
					if (isSuccess == true) {
						result += i.getOID() + ",";
						File file=new File(GlobalData.PhotoPath+i.getFilePath());
						if(file.exists())
							file.delete();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
		result = result.equals("") ? "" : result.substring(0,
				result.lastIndexOf(",") - 1);
		return result;
	}

	private String GetImgBuffer(String srcUrl) {
		try {
			FileInputStream fis = new FileInputStream(srcUrl);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = fis.read(buffer)) >= 0) {
				baos.write(buffer, 0, count);
			}
			String uploadBuffer = new String(Base64.encode(baos.toByteArray()));
			return uploadBuffer;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	private boolean UploadIamge(String uploadBuffer, String filePath,
			String fileName) throws IOException {
		String methodName = "FileUploadImage";
		SoapObject soapObject = new SoapObject(serviceNameSpace, methodName);
		soapObject.addProperty("filename", fileName);
		soapObject.addProperty("filepath", filePath);
		soapObject.addProperty("bytestr", uploadBuffer);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soapObject);
		envelope.bodyOut = soapObject;
		envelope.dotNet = true;
		// envelope.encodingStyle = SoapSerializationEnvelope.ENC;
		HttpTransportSE httpTransportSE = new HttpTransportSE(GetServiceUrl());
		try {
			httpTransportSE.call(serviceNameSpace + methodName, envelope);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}
}
