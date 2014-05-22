package com.goldsunny.itsm.util;

import java.util.HashMap;
import java.util.List;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.goldsunny.itsm.model.EmployeeMDL;
import com.goldsunny.itsm.model.Equ_EquipmentMDL;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.model.Mai_InspEquMDL;
import com.goldsunny.itsm.model.Mai_InspPLanDetailMDL;
import com.goldsunny.itsm.model.Mai_InspRecordDetailMDL;
import com.goldsunny.itsm.model.Mai_InspRecordMDL;
import com.goldsunny.itsm.model.Mai_RecoveryDetailMDL;
import com.goldsunny.itsm.model.Mai_RecoveryMainMDL;
import com.goldsunny.itsm.model.StockMDL;
import com.goldsunny.itsm.model.Syst_LocationMDL;
import com.goldsunny.itsm.model.Syst_RoadMDL;
import com.goldsunny.itsm.model.SystemConfigMDL;

public class GlobalData {
	public static String DataBasePath = "/sdcard/ITSM/DB/";
	public static String PhotoPath = "/sdcard/ITSM/Photo/";
	public static String DataBaseFileName = "ITSM.db";
	public static String downloadDir="app/download/";
	public static String FtpServiceIp = "172.16.60.110";
	public static String FtpUserName = "ITSM";
	public static String FtpPassword = "ITSM123";
	public static String ClientVersion = "1.0.0"; 
	public static int serverVersion = 0;
	public static int localVersion = 0;
	public static String DownloadURL="http://172.16.40.104:8081/update.xml";//更新链接
	public static int[] VehrecappArray;
	public static String DeviceId = "";
	public static String RFIDUID = "";
	public static String teamId = "";//当前所在维护队
	public static HashMap<String, String> AppConfig;
	public static SystemConfigMDL SystemConfig;
	public static Equ_EquipmentMDL equipmentMDL; 
	public static Mai_FaultReportMDL faultReportMDL;
	public static Mai_RecoveryMainMDL recoveryMainMDL;
	public static Mai_RecoveryDetailMDL recoverDeatil;//故障维修明细 
	public static String ClientIp = "";
	public static String BizID = "";
	public static BluetoothSocket BTSocket;
	public static BluetoothDevice BTDevice;
	public static ServiceHelper serviceHelper;
	public static Object threadDBLock = "";
	public static boolean isLogined = false;
	public static int CurEquTabID = 0;
	public static EmployeeMDL employeeMDL;
	public static HashMap<String, String> mapEquStatus;
	public static String BizType = "";
	public static int CurTypeIndex = 0;
	public static int CurItemIndex = 0;
	public static List<Syst_LocationMDL> tollStation;//收费站信息
	public static List<Syst_RoadMDL> roadMDLs;//路段信息
	public static List<Mai_FaultReportMDL> faulList;//故障报修列表
	public static List<StockMDL> stockList;//仓库列表
	
	public static Mai_InspRecordMDL inspRecord;//巡检记录
	public static List<Mai_InspRecordDetailMDL> inspRecordList;//巡检记录详细列表
	public static List<Mai_InspEquMDL> inspEquList;//巡检设备列表s
	
	

	private static final int FLING_MIN_DISTANCE = 50;
	private static final int FLING_MIN_VELOCITY = 0;

	public static int getFlingMinDistance() {
		return FLING_MIN_DISTANCE;
	}

	public static int getFlingMinVelocity() {
		return FLING_MIN_VELOCITY;
	}

	public static HashMap<String, String> GetMapEquStatus() {
		mapEquStatus = new HashMap<String, String>();
		mapEquStatus.put("正常", "true");
		mapEquStatus.put("异常", "false");
		return mapEquStatus;
	}
	
	/**
	 * 所属系统
	 */
	public static String systemClass="119";
	
	/**
	 * 保修状态
	 */
	public static String reportStatus="121";
	/**
	 * 物品类型
	 */
	public static String shareType="128";

}
