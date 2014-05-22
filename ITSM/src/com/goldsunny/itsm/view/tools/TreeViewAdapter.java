package com.goldsunny.itsm.view.tools;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.goldsunny.itsm.R;

/**
 * TreeViewAdapter
 * 
 * @author carrey
 * 
 */
public class TreeViewAdapter extends BaseAdapter {
	/** 元素数据源 */
	private ArrayList<Element> elementsData;
	/** 树中元素 */
	private ArrayList<Element> elements;
	/** LayoutInflater */
	private LayoutInflater inflater;
	/** item的行首缩进基数 */
	private int indentionBase;
	private HashMap<String, Boolean> states = new HashMap<String, Boolean>();

	public TreeViewAdapter(ArrayList<Element> elements, ArrayList<Element> elementsData, LayoutInflater inflater) {
		this.elements = elements;
		this.elementsData = elementsData;
		this.inflater = inflater;
		indentionBase = 50;
	}

	public ArrayList<Element> getElements() {
		return elements;
	}

	public ArrayList<Element> getElementsData() {
		return elementsData;
	} 

	public HashMap<String, Boolean> getStates() {
		return states;
	}

	@Override
	public int getCount() {
		return elements.size();
	}

	@Override
	public Object getItem(int position) {
		return elements.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.treeview_item, null);
			holder.disclosureImg = (ImageView) convertView.findViewById(R.id.disclosureImg);
			holder.contentText = (TextView) convertView.findViewById(R.id.contentText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final RadioButton radio = (RadioButton) convertView.findViewById(R.id.checkTree);
		holder.disCheckBox = radio;
		holder.disCheckBox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (String key : states.keySet()) {
					states.put(key, false);
				}
				states.put(String.valueOf(position), radio.isChecked());
				TreeViewAdapter.this.notifyDataSetChanged();
			}
		});
		boolean res = false;
		if (states.get(String.valueOf(position)) == null || states.get(String.valueOf(position)) == false) {
			res = false;
			states.put(String.valueOf(position), false);
		} else
			res = true;

		holder.disCheckBox.setChecked(res);
		Element element = elements.get(position);
		int level = element.getLevel();
		holder.disclosureImg.setPadding(indentionBase * (level + 1), holder.disclosureImg.getPaddingTop(),
				holder.disclosureImg.getPaddingRight(), holder.disclosureImg.getPaddingBottom());
		holder.contentText.setText(element.getContentText());
		if (element.isHasChildren() && !element.isExpanded()) {
			holder.disclosureImg.setImageResource(R.drawable.close);
			// 这里要主动设置一下icon可见，因为convertView有可能是重用了"设置了不可见"的view，下同。
			holder.disclosureImg.setVisibility(View.VISIBLE);
			holder.disCheckBox.setVisibility(View.GONE);
		} else if (element.isHasChildren() && element.isExpanded()) {
			holder.disclosureImg.setImageResource(R.drawable.open);
			holder.disclosureImg.setVisibility(View.VISIBLE);
			holder.disCheckBox.setVisibility(View.GONE);
		} else if (!element.isHasChildren()) {
			holder.disclosureImg.setImageResource(R.drawable.close);
			holder.disclosureImg.setVisibility(View.INVISIBLE);
			holder.disCheckBox.setVisibility(View.VISIBLE);
		}
		return convertView;
	}

	/**
	 * 优化Holder
	 * 
	 * @author carrey
	 * 
	 */
	static class ViewHolder {
		ImageView disclosureImg;
		RadioButton disCheckBox;
		TextView contentText;
	}
 
}
