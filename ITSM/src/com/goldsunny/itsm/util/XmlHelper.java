package com.goldsunny.itsm.util;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.util.Xml;

import com.goldsunny.itsm.model.MaintainTeamMDL;
import com.goldsunny.itsm.model.Sys_DictListMDL;

/**
 * @ClassName：XmlHelper
 * @Description：XML文件处理类
 * @author：yangwy
 * @date 2014-4-17
 */
public class XmlHelper {

	/** 
	 * 描述:  根据PID获取数据字典详细
	 * @param c
	 * @param dictPid 数据字典
	 * @return
	 * @throws Exception
	 */
	public static List<Sys_DictListMDL> readDictXml(Context c, String dictPid) throws Exception {
		List<Sys_DictListMDL> dictList = null;
		FileInputStream in = null;
		try {
			Sys_DictListMDL dict = null;
			// 由android.util.Xml创建一个XmlPullParser实例
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
			in = c.openFileInput("dict.xml");
			// 设置输入流 并指明编码方式
			parser.setInput(in, "UTF-8");
			// 产生第一个事件
			int eventType = parser.getEventType();
			String sPid = "";
			String oid = "";
			String name = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				// 判断当前事件是否为文档开始事件
				case XmlPullParser.START_DOCUMENT:
					dictList = new ArrayList<Sys_DictListMDL>(); // 初始化books集合
					break;
				// 判断当前事件是否为标签元素开始事件
				case XmlPullParser.START_TAG:
					if ("dict".equals(parser.getName())) {
						sPid = parser.getAttributeValue("", "Sys_DictOID");
						if (dictPid.equals(sPid)) {
							dict = new Sys_DictListMDL();
							oid = parser.getAttributeValue("", "OID");
							name = parser.getAttributeValue("", "ListName");
							dict.setSys_DictOID(sPid);
							dict.setOID(oid);
							dict.setListName(name);
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("dict")) {
						if (dictPid.equals(sPid)) {
							dictList.add(dict);
						}
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		} finally {
			if (in != null)
				in.close();
		}

		return dictList;
	}

	public static Sys_DictListMDL getDictByID(String id, Context context) throws Exception {
		Sys_DictListMDL dictMDL = null;
		FileInputStream in = null;
		try {
			// 由android.util.Xml创建一个XmlPullParser实例
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
			in = context.openFileInput("dict.xml");
			// 设置输入流 并指明编码方式
			parser.setInput(in, "UTF-8");
			// 产生第一个事件
			int eventType = parser.getEventType();
			String sPid = "";
			String oid = "";
			String name = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				// 判断当前事件是否为文档开始事件
				case XmlPullParser.START_DOCUMENT:
					break;
				// 判断当前事件是否为标签元素开始事件
				case XmlPullParser.START_TAG:
					if ("dict".equals(parser.getName())) {
						sPid = parser.getAttributeValue("", "OID");
						if (id.equals(sPid)) {
							dictMDL = new Sys_DictListMDL();
							oid = parser.getAttributeValue("", "OID");
							name = parser.getAttributeValue("", "ListName");
							dictMDL.setSys_DictOID(sPid);
							dictMDL.setOID(oid);
							dictMDL.setListName(name);
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("dict")) {
						if (id.equals(sPid)) {
							return dictMDL;
						}
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		} finally {
			if (in != null)
				in.close();
		}
		return dictMDL;
	}

	/**
	 * 描述 创建数据字典XML
	 * 
	 * @param dictList
	 * @return
	 * @throws Exception
	 */
	public static String createDicXml(List<Sys_DictListMDL> dictList) throws Exception {
		try {
			// 由android.util.Xml创建一个XmlSerializer实例
			XmlSerializer serializer = Xml.newSerializer();
			StringWriter writer = new StringWriter();
			// 设置输出方向为writer
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			if (dictList != null) {
				for (Sys_DictListMDL dict : dictList) {
					serializer.startTag("", "dict");
					serializer.attribute("", "Sys_DictOID", dict.getSys_DictOID());
					serializer.attribute("", "OID", dict.getOID());
					serializer.attribute("", "ListName", dict.getListName());
					serializer.endTag("", "dict");
				}
			}
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 描述： 讲维护队写入XML文件
	 * 
	 * @param mainTeamList
	 *            维护队
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public static Boolean writeMeaTeam(List<MaintainTeamMDL> mainTeamList, Context c) throws Exception {
		boolean result = false;
		OutputStream outStream = null;
		OutputStreamWriter outStreamWriter = null;
		try {
			if (mainTeamList != null && mainTeamList.size() > 0) {
				XmlSerializer serializer = Xml.newSerializer();
				StringWriter writer = new StringWriter();
				serializer.setOutput(writer);
				serializer.startDocument("UTF-8", true);
				for (MaintainTeamMDL mainTeam : mainTeamList) {
					serializer.startTag("", "mainteam");
					serializer.attribute("", "OID", mainTeam.getOID());
					serializer.attribute("", "Name", mainTeam.getName());
					serializer.attribute("", "staionId", mainTeam.getStaionId());
					serializer.attribute("", "staionName", mainTeam.getStaionName());
					serializer.endTag("", "mainteam");
				}
				serializer.endDocument();
				outStream = c.openFileOutput("mainteam.xml", c.MODE_PRIVATE);
				outStreamWriter = new OutputStreamWriter(outStream);
				outStreamWriter.write(writer.toString());
				outStreamWriter.close();
				outStream.close();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (outStreamWriter != null)
				outStreamWriter.close();
			if (outStream != null)
				outStream.close();
		}
		return result;
	}

	/**
	 * 描述 :根据收费站名称获取维护队信息
	 * 
	 * @param sationName
	 *            //收费站名称
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public static List<MaintainTeamMDL> getMainTeam(String sationName, Context c) throws Exception {
		List<MaintainTeamMDL> mainTeamList = null;
		FileInputStream in = null;
		try {
			MaintainTeamMDL mainTeamMDL = null;
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
			in = c.openFileInput("mainteam.xml");
			parser.setInput(in, "UTF-8");
			int eventType = parser.getEventType();
			String stName = "";
			String oid = "";
			String name = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				// 判断当前事件是否为文档开始事件
				case XmlPullParser.START_DOCUMENT:
					mainTeamList = new ArrayList<MaintainTeamMDL>(); // 初始化books集合
					break;
				// 判断当前事件是否为标签元素开始事件
				case XmlPullParser.START_TAG:
					if ("mainteam".equals(parser.getName())) {
						stName = parser.getAttributeValue("", "staionName");
						if (sationName.equals(stName)) {
							mainTeamMDL = new MaintainTeamMDL();
							oid = parser.getAttributeValue("", "OID");
							name = parser.getAttributeValue("", "Name");
							mainTeamMDL.setOID(oid);
							mainTeamMDL.setName(name);
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("mainteam")) {
						if (sationName.equals(stName)) {
							mainTeamList.add(mainTeamMDL);
						}
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (in != null)
				in.close();
		}
		return mainTeamList;
	}
}
