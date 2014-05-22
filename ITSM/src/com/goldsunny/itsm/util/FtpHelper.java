package com.goldsunny.itsm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpHelper {
	private String ftpServiceIp = GlobalData.FtpServiceIp;
	private String ftpUserName = GlobalData.FtpUserName;
	private String ftpPassword = GlobalData.FtpPassword;
	private FTPClient ftpClient;

	public FtpHelper() {
		ftpClient = new FTPClient();
	}

	public String GetFtpServiceIp() {
		return GlobalData.SystemConfig.getServerIp();
	}

	public FtpHelper(String ip, String userName, String password) {
		ftpServiceIp = ip;
		ftpUserName = userName;
		ftpPassword = password;
		ftpClient = new FTPClient();
	}

	private void MakeDirs(String paths) {
		String[] arrayPath = paths.split("/");
		String pathedString = "";
		for (int i = 0; i < arrayPath.length; i++) {
			if (!arrayPath[i].equals("")) {
				try {
					boolean aa = ftpClient.makeDirectory(pathedString + "/" + arrayPath[i]);
					pathedString = pathedString + "/" + arrayPath[i];
					// if(ftpClient.makeDirectory(pathedString+"/"+arrayPath[i]))
					// pathedString=pathedString+"/"+arrayPath[i];
					// else {
					// throw new IOException("�½��ļ���ʧ��");
					// }
					// Log.e("a", aa+"");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public String uploadFile(InputStream stream, String serverPath, String serverName) {
		String result = "";
		// ftpClient.setDefaultTimeout(30000);
		ftpClient.setDefaultTimeout(30000);
		try {
			ftpClient.connect(GetFtpServiceIp());
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				result = "FTP����ʧ��";
			} else {
				if (ftpClient.login(ftpUserName, ftpPassword)) {
					ftpClient.enterLocalPassiveMode();
					MakeDirs(serverPath);
					ftpClient.changeWorkingDirectory(serverPath);
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
					// FileInputStream fisFileInputStream =new
					// FileInputStream(new File(fileName));
					if (!ftpClient.storeFile(serverName, stream))
						result = "FTP�Ҳ���Ŀ¼·��";

				}
				ftpClient.logout();

			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			result = "��������ʧ��;";

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			result = "û���ҵ��ļ�";

		} catch (Exception e) {
			// TODO: handle exception
			result = e.getMessage() + " ;";
		}
		return result;

	}

	public String uploadFile(String fileName, String serverPath, String serverName) {
		String result = "";
		// ftpClient.setDefaultTimeout(30000);
		ftpClient.setDefaultTimeout(30000);
		try {
			ftpClient.connect(GetFtpServiceIp());
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				result = "FTP����ʧ��";
			} else {
				if (ftpClient.login(ftpUserName, ftpPassword)) {
					ftpClient.enterLocalPassiveMode();
					MakeDirs(serverPath);
					ftpClient.changeWorkingDirectory(serverPath);
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
					FileInputStream fisFileInputStream = new FileInputStream(new File(fileName));
					if (!ftpClient.storeFile(serverName, fisFileInputStream))
						result = "FTP�Ҳ���Ŀ¼·��";
					// ftpClient.storeFile(serverName, fisFileInputStream);

				}
				ftpClient.logout();

			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			result = "��������ʧ��";

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			result = "û���ҵ��ļ�" + fileName;

		} catch (Exception e) {
			// TODO: handle exception

		}
		return result;

	}
}
