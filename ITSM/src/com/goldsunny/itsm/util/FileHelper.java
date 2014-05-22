package com.goldsunny.itsm.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class FileHelper {

	/**
	 * ɾ���ļ���
	 * 
	 * @param filePathAndName
	 *            String �ļ���·�������� ��c:/fqf
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // ɾ����������������
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // ɾ�����ļ���

		} catch (Exception e) {
			System.out.println("ɾ���ļ��в�������");
			e.printStackTrace();

		}
	}

	/**
	 * ɾ���ļ�������������ļ�
	 * 
	 * @param path
	 *            String �ļ���·�� �� c:/fqf
	 */
	public static void delAllFile(String path) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				return;
			}
			if (!file.isDirectory()) {
				return;
			}
			String[] tempList = file.list();
			File temp = null;
			for (int i = 0; i < tempList.length; i++) {
				try {
					if (path.endsWith(File.separator)) {
						temp = new File(path + tempList[i]);
					} else {
						temp = new File(path + File.separator + tempList[i]);
					}
					if (temp.isFile()) {
						temp.delete();
					}
					if (temp.isDirectory()) {
						delAllFile(path + "/" + tempList[i]);// ��ɾ���ļ���������ļ�
						delFolder(path + "/" + tempList[i]);// ��ɾ�����ļ���
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static String[] GetDirsByDate(String path,Date date) {
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		if (!file.isDirectory()) {
			return null;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date);
		String[] tempList = file.list();
		List<String> nameList=new LinkedList<String>();
		for (int i = 0; i < tempList.length; i++) {
			String nameString = tempList[i];
			if (nameString.length() == 8 && ObjectHelper.isNumeric(nameString)) {
				
				Date tmpDate = ObjectHelper.Convert2Date(nameString.substring(0,4)+"-"+nameString.substring(4,6)+"-"+nameString.substring(6,8)+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
				c2.setTime(tmpDate);
				int ii=c1.compareTo(c2);
				if(ii>=0)
				{
					nameList.add(nameString);
				}
				
			} else {
				break;
			}
		}
		return (String[])nameList.toArray(new String[nameList.size()]);
		//return tempList;
	}

}
