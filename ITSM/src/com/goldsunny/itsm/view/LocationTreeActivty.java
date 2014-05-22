package com.goldsunny.itsm.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.goldsunny.itsm.R;
import com.goldsunny.itsm.businesslogic.BaseDataBo;
import com.goldsunny.itsm.model.Syst_LocationMDL;
import com.goldsunny.itsm.util.CommonClass;
import com.goldsunny.itsm.util.DialogHelper;
import com.goldsunny.itsm.util.SysApplication;
import com.goldsunny.itsm.view.tools.Element;
import com.goldsunny.itsm.view.tools.TreeViewAdapter;
import com.goldsunny.itsm.view.tools.TreeViewItemClickListener;

public class LocationTreeActivty extends Activity implements OnItemClickListener{

	/** 树中的元素集合 */
	private ArrayList<Element> elements;
	/** 数据源元素集合 */
	private ArrayList<Element> elementsData;
	TreeViewAdapter treeViewAdapter;
	Button btnLoctationToTop ,btnSave;
	ProgressBar location_tree_progresbar;
	private String pid;
	public final int LOCATION_RESULT_OK=2000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.location_tree);
		SysApplication.getInstance().addActivity(this);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		btnLoctationToTop=(Button)findViewById(R.id.btnLoctationToTop);
		btnSave=(Button)findViewById(R.id.btnSave);
		location_tree_progresbar=(ProgressBar)findViewById(R.id.location_tree_progresbar);
		btnSave.setOnClickListener(onClickListener);
		btnLoctationToTop.setOnClickListener(onClickListener);
		elements=new ArrayList<Element>();
		elementsData=new ArrayList<Element>();
		ListView treeview = (ListView) findViewById(R.id.treeview);
		treeViewAdapter = new TreeViewAdapter(
				elements, elementsData, inflater);
		TreeViewItemClickListener treeViewItemClickListener = new TreeViewItemClickListener(treeViewAdapter);
		treeview.setAdapter(treeViewAdapter);
		treeview.setOnItemClickListener(this); 
		pid=getIntent().getStringExtra("pid");
		locationAsyncTask locationAsyncTask=new locationAsyncTask();
		locationAsyncTask.execute(null);
		
	}
	
	private OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnLoctationToTop:
				finish();
				break;
			case R.id.btnSave:
				HashMap<String, Boolean> states=treeViewAdapter.getStates();
				int pos=-1;
				for (String key : states.keySet()) {
					if(states.get(key))
					{
						pos=Integer.valueOf(key);
						break;
					} 
				}
				if(pos==-1){
					DialogHelper.showDialog(LocationTreeActivty.this, "请选择位置");
					return;
				}
				Intent data=new Intent();
				data.putExtra("oid", treeViewAdapter.getElements().get(pos).getId());
				data.putExtra("name", treeViewAdapter.getElements().get(pos).getRemark()); 
				setResult(2000, data); 
				finish();
				break;
			default:
				break;
			}
		}
	};
	
	 String oid,name;
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		//点击的item代表的元素
				Element element = (Element) treeViewAdapter.getItem(position); 
				//树中的元素
				ArrayList<Element> elements = treeViewAdapter.getElements();
				//元素的数据源
				ArrayList<Element> elementsData = treeViewAdapter.getElementsData();
				
				//点击没有子项的item直接返回
				if (!element.isHasChildren()) {
					RadioButton checkBox=(RadioButton)arg1.findViewById(R.id.checkTree);
					checkBox.toggle();
					if(checkBox.isChecked())
					{
						oid=element.getId();
						name=element.getRemark();//地理位置全称
					}
					return;
				} 
				if (element.isExpanded()) {
					element.setExpanded(false);
					//删除节点内部对应子节点数据，包括子节点的子节点...
					ArrayList<Element> elementsToDel = new ArrayList<Element>();
					for (int i = position + 1; i < elements.size(); i++) {
						if (element.getLevel() >= elements.get(i).getLevel())
							break;
						elementsToDel.add(elements.get(i));
					}
					elements.removeAll(elementsToDel);
					treeViewAdapter.notifyDataSetChanged();
				} else {
					element.setExpanded(true);
					//从数据源中提取子节点数据添加进树，注意这里只是添加了下一级子节点，为了简化逻辑
					int i = 1;//注意这里的计数器放在for外面才能保证计数有效
					for (Element e : elementsData) {
						if (e.getParendId() .equals(element.getId())) {
							e.setExpanded(false);
							elements.add(position + i, e);
							i ++;
						}
					}
					treeViewAdapter.notifyDataSetChanged();
				}
	}

 
	
	private class locationAsyncTask extends AsyncTask<String, String , Boolean>
	{ 
		@Override
		protected Boolean doInBackground(String... params) {
			BaseDataBo baseDataBo=new BaseDataBo();
			if(CommonClass.isNullorEmpty(pid))
				pid="0";
		    List<Syst_LocationMDL> locationList=baseDataBo.getLocationByPid(pid);
		    if(locationList!=null && locationList.size()>0)
		    {  
		    	boolean isChildNode=false;
			    for (Syst_LocationMDL location : locationList) {
			    	if(location.getLevel()==0 || location.getLevel()==1)
			    	{
			    		boolean  isExpanded=location.getLevel()==0?true:false;
			    		isChildNode=location.getSons()==0 ? false:true; 
					    Element e1 = new Element(location.getName(), location.getLevel(), location.getOID(), location.getPID(), isChildNode, isExpanded,location.getLocationDetail()); 
					    elements.add(e1);
			    	}
			    	isChildNode=location.getSons()==0 ? false:true;
			    	Element e = new Element(location.getName(),location.getLevel() , location.getOID(), location.getPID(), isChildNode, false,location.getLocationDetail());
			    	
			    	elementsData.add(e);
				} 
		    } 
			return true;
		} 

		@Override
		protected void onPostExecute(Boolean result) { 
			location_tree_progresbar.setVisibility(View.GONE);
			treeViewAdapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		
	}
 
}
