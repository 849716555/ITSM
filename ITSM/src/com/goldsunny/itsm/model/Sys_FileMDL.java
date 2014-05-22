package com.goldsunny.itsm.model;

/** 
 *  附件实体类
 * @author yangwy       
 * @version 1.0     
 * @created 2014-5-13 下午4:13:20
 */
public class Sys_FileMDL {
	private String OID;
	private String BizOID;
	private String FileName;
	private String File_Type;
	private String FileExtName;
	private double FileSize;
	private String SaveType;
	private String FileContent;
	private String FilePath;
	private String Operate;//Add  新增  Del 删除

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getBizOID() {
		return BizOID;
	}

	public void setBizOID(String bizOID) {
		BizOID = bizOID;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public String getFile_Type() {
		return File_Type;
	}

	public void setFile_Type(String file_Type) {
		File_Type = file_Type;
	}

	public String getFileExtName() {
		return FileExtName;
	}

	public void setFileExtName(String fileExtName) {
		FileExtName = fileExtName;
	}

	public double getFileSize() {
		return FileSize;
	}

	public void setFileSize(double fileSize) {
		FileSize = fileSize;
	}

	public String getSaveType() {
		return SaveType;
	}

	public void setSaveType(String saveType) {
		SaveType = saveType;
	}

	public String getFileContent() {
		return FileContent;
	}

	public void setFileContent(String fileContent) {
		FileContent = fileContent;
	}

	public String getFilePath() {
		return FilePath;
	}

	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

	public String getOperate() {
		return Operate;
	}

	public void setOperate(String operate) {
		Operate = operate;
	}
	
	
	
}
