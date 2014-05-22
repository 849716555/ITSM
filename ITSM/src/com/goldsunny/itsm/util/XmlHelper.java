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
 * @ClassName��XmlHelper
 * @Description��XML�ļ�������
 * @author��yangwy
 * @date 2014-4-17
 */
public class XmlHelper {

	/** 
	 * ����:  ����PID��ȡ�����ֵ���ϸ
	 * @param c
	 * @param dictPid �����ֵ�
	 * @return
	 * @throws Exception
	 */
	public static List<Sys_DictListMDL> readDictXml(Context c, String dictPid) throws Exception {
		List<Sys_DictListMDL> dictList = null;
		FileInputStream in = null;
		try {
			Sys_DictListMDL dict = null;
			// ��android.util.Xml����һ��XmlPullParserʵ��
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
			in = c.openFileInput("dict.xml");
			// ���������� ��ָ�����뷽ʽ
			parser.setInput(in, "UTF-8");
			// ������һ���¼�
			int eventType = parser.getEventType();
			String sPid = "";
			String oid = "";
			String name = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				// �жϵ�ǰ�¼��Ƿ�Ϊ�ĵ���ʼ�¼�
				case XmlPullParser.START_DOCUMENT:
					dictList = new ArrayList<Sys_DictListMDL>(); // ��ʼ��books����
					break;
				// �жϵ�ǰ�¼��Ƿ�Ϊ��ǩԪ�ؿ�ʼ�¼�
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
			// ��android.util.Xml����һ��XmlPullParserʵ��
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
			in = context.openFileInput("dict.xml");
			// ���������� ��ָ�����뷽ʽ
			parser.setInput(in, "UTF-8");
			// ������һ���¼�
			int eventType = parser.getEventType();
			String sPid = "";
			String oid = "";
			String name = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				// �жϵ�ǰ�¼��Ƿ�Ϊ�ĵ���ʼ�¼�
				case XmlPullParser.START_DOCUMENT:
					break;
				// �жϵ�ǰ�¼��Ƿ�Ϊ��ǩԪ�ؿ�ʼ�¼�
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
	 * ���� ���������ֵ�XML
	 * 
	 * @param dictList
	 * @return
	 * @throws Exception
	 */
	public static String createDicXml(List<Sys_DictListMDL> dictList) throws Exception {
		try {
			// ��android.util.Xml����һ��XmlSerializerʵ��
			XmlSerializer serializer = Xml.newSerializer();
			StringWriter writer = new StringWriter();
			// �����������Ϊwriter
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
	 * ������ ��ά����д��XML�ļ�
	 * 
	 * @param mainTeamList
	 *            ά����
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
	 * ���� :�����շ�վ���ƻ�ȡά������Ϣ
	 * 
	 * @param sationName
	 *            //�շ�վ����
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
				// �жϵ�ǰ�¼��Ƿ�Ϊ�ĵ���ʼ�¼�
				case XmlPullParser.START_DOCUMENT:
					mainTeamList = new ArrayList<MaintainTeamMDL>(); // ��ʼ��books����
					break;
				// �жϵ�ǰ�¼��Ƿ�Ϊ��ǩԪ�ؿ�ʼ�¼�
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
