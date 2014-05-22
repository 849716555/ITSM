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
	public static String DownloadURL="http://172.16.40.104:8081/update.xml";//��������
	public static int[] VehrecappArray;
	public static String DeviceId = "";
	public static String RFIDUID = "";
	public static String teamId = "";//��ǰ����ά����
	public static HashMap<String, String> AppConfig;
	public static SystemConfigMDL SystemConfig;
	public static Equ_EquipmentMDL equipmentMDL; 
	public static Mai_FaultReportMDL faultReportMDL;
	public static Mai_RecoveryMainMDL recoveryMainMDL;
	public static Mai_RecoveryDetailMDL recoverDeatil;//����ά����ϸ 
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
	public static List<Syst_LocationMDL> tollStation;//�շ�վ��Ϣ
	public static List<Syst_RoadMDL> roadMDLs;//·����Ϣ
	public static List<Mai_FaultReportMDL> faulList;//���ϱ����б�
	public static List<StockMDL> stockList;//�ֿ��б�
	
	public static Mai_InspRecordMDL inspRecord;//Ѳ���¼
	public static List<Mai_InspRecordDetailMDL> inspRecordList;//Ѳ���¼��ϸ�б�
	public static List<Mai_InspEquMDL> inspEquList;//Ѳ���豸�б�s
	
	

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
		mapEquStatus.put("����", "true");
		mapEquStatus.put("�쳣", "false");
		return mapEquStatus;
	}
	
	/**
	 * ����ϵͳ
	 */
	public static String systemClass="119";
	
	/**
	 * ����״̬
	 */
	public static String reportStatus="121";
	/**
	 * ��Ʒ����
	 */
	public static String shareType="128";

}
