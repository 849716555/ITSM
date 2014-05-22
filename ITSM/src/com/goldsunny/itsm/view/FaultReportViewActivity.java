package com.goldsunny.itsm.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.BaseDataBo;
import com.goldsunny.itsm.model.Mai_FaultReportMDL;
import com.goldsunny.itsm.model.Sys_FileMDL;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.GlobalData;
import com.goldsunny.itsm.util.ObjectHelper;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.util.XmlHelper;
import com.goldsunny.itsm.view.tools.FaultImageAdapter;

public class FaultReportViewActivity extends Activity implements OnClickListener {

	private TextView tbFault_ReportTime, tbFault_Reportor, tbFault_Device, tbFault_Lane, tbFault_Remark, tbFault_Road,
			tbFault_Level, tbFault_Source, tbFault_Type, tbFault_systemClass;
	private Button btnFaultViewToTop, btnFault_Cancel;
	private Gallery iamgeGridView;
	private FaultImageAdapter adepter;
	private List<Sys_FileMDL> imageList = new ArrayList<Sys_FileMDL>();// 图片数据
	private Mai_FaultReportMDL faultReportMDL;
	private BaseDataBo baseDataBo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.faultdetailview);
		SysApplication.getInstance().addActivity(this);
		btnFaultViewToTop = (Button) findViewById(R.id.btnFaultViewToTop);
		btnFault_Cancel = (Button) findViewById(R.id.btnFault_Cancel);
		tbFault_Device = (TextView) findViewById(R.id.tbFault_Device);
		tbFault_Lane = (TextView) findViewById(R.id.tbFault_Lane);
		tbFault_Level = (TextView) findViewById(R.id.tbFault_Level);
		tbFault_Remark = (TextView) findViewById(R.id.tbFault_Remark);
		tbFault_Reportor = (TextView) findViewById(R.id.tbFault_Reportor);
		tbFault_ReportTime = (TextView) findViewById(R.id.tbFault_ReportTime);
		tbFault_Road = (TextView) findViewById(R.id.tbFault_Road);
		tbFault_Source = (TextView) findViewById(R.id.tbFault_Source);
		tbFault_Type = (TextView) findViewById(R.id.tbFault_Type);
		tbFault_systemClass = (TextView) findViewById(R.id.tbFault_systemClass);
		btnFault_Cancel.setOnClickListener(this);
		btnFaultViewToTop.setOnClickListener(this);
		initView();
		adepter = new FaultImageAdapter(imageList, this);
		iamgeGridView = (Gallery) findViewById(R.id.gridviewFaultDetail);
		iamgeGridView.setAdapter(adepter);
		iamgeGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				if (!CommonClass.isNullorEmpty(imageList.get(arg2).getFilePath())) {
					Intent ImageShow = new Intent(FaultReportViewActivity.this, ShowImageActivty.class);
					Bundle bundle = new Bundle();
					String fullPath = imageList.get(arg2).getFilePath();
					fullPath = GlobalData.PhotoPath + fullPath.substring(fullPath.lastIndexOf("/") + 1);
					bundle.putString("path", fullPath);
					bundle.putString("canDel", "false");
					ImageShow.putExtras(bundle);
					startActivity(ImageShow);
				}
			}
		});
		baseDataBo = new BaseDataBo();
		FaluDetalAsyncTask faluDetalAsyncTask = new FaluDetalAsyncTask();
		faluDetalAsyncTask.execute(null);
	}

	private void initView() {
		if (GlobalData.faultReportMDL != null) {
			faultReportMDL = GlobalData.faultReportMDL;
			String deviceName=faultReportMDL.getDeviceName() == null ? "" : faultReportMDL.getDeviceName();
			if(faultReportMDL.getDeviceCode()!=null && !"".equals(faultReportMDL.getDeviceCode()))
			{
				if(!"".equals(deviceName))
				{
					deviceName=deviceName+"("+faultReportMDL.getDeviceCode()+")";
				}else {
					deviceName=faultReportMDL.getDeviceCode();
				}
				
			}
			tbFault_Device.setText(deviceName);
			tbFault_Lane.setText(faultReportMDL.getLocationName());
			tbFault_Remark.setText(faultReportMDL.getFaultDesc());
			tbFault_Reportor.setText(faultReportMDL.getReportor());
			tbFault_ReportTime.setText(ObjectHelper.Convert2String(faultReportMDL.getReportTime(), "yyyy-MM-dd"));
			tbFault_Road.setText(faultReportMDL.getRoadName());
			try {
				tbFault_Type.setText(XmlHelper.getDictByID(faultReportMDL.getFaultType(), getApplicationContext())
						.getListName());
				String faulLeve = faultReportMDL.getFaultLevel();
				tbFault_Level.setText(XmlHelper.getDictByID(faulLeve, getApplicationContext()).getListName());
				String faulSource = faultReportMDL.getFaultSource();
				tbFault_Source.setText(XmlHelper.getDictByID(faulSource, getApplicationContext()).getListName());
				String systmenClass = faultReportMDL.getSystemClass();
				tbFault_systemClass.setText(XmlHelper.getDictByID(systmenClass, getApplicationContext()).getListName());

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnFault_Cancel:
			finish();
			break;
		case R.id.btnFaultViewToTop:
			finish();
			break;
		default:
			break;
		}

	}

	/**
	 * @author 异步加载图片列表
	 * @version 1.0
	 * @created 2014-5-14 下午2:29:38
	 */
	private class FaluDetalAsyncTask extends AsyncTask<String, String, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {
			try {
				if (faultReportMDL != null) {
					List<Sys_FileMDL> fileList = baseDataBo.getFileByBizOid(faultReportMDL.getOID());
					if (fileList != null && fileList.size() > 0) {
						imageList.addAll(fileList);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			adepter.notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}

}
