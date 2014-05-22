package com.goldsunny.itsm.model;

import java.util.Date;

import android.R.integer;

public class Equ_LifeLogMDL {
	private String OID;
	private String EquipmentID;
	private Date OccuDate;
	private String ReceiptType;
	private String ReceiptMainID;
	private String ReceiptDetailID;
	private String EquipmentStatus;
	private String LiftDesc;
	private String KeyDesc;
	private Date InTime;
	private int Seq;
	private int Timestamp;
	
	public String getOID() {
		return OID;
	}
	public void setOID(String oID) {
		OID = oID;
	}
	public String getEquipmentID() {
		return EquipmentID;
	}
	public void setEquipmentID(String equipmentID) {
		EquipmentID = equipmentID;
	}
	public Date getOccuDate() {
		return OccuDate;
	}
	public void setOccuDate(Date occuDate) {
		OccuDate = occuDate;
	}
	public String getReceiptType() {
		return ReceiptType;
	}
	public void setReceiptType(String receiptType) {
		ReceiptType = receiptType;
	}
	public String getReceiptMainID() {
		return ReceiptMainID;
	}
	public void setReceiptMainID(String receiptMainID) {
		ReceiptMainID = receiptMainID;
	}
	public String getReceiptDetailID() {
		return ReceiptDetailID;
	}
	public void setReceiptDetailID(String receiptDetailID) {
		ReceiptDetailID = receiptDetailID;
	}
	public String getEquipmentStatus() {
		return EquipmentStatus;
	}
	public void setEquipmentStatus(String equipmentStatus) {
		EquipmentStatus = equipmentStatus;
	}
	public String getLiftDesc() {
		return LiftDesc;
	}
	public void setLiftDesc(String liftDesc) {
		LiftDesc = liftDesc;
	}
	public String getKeyDesc() {
		return KeyDesc;
	}
	public void setKeyDesc(String keyDesc) {
		KeyDesc = keyDesc;
	}
	public Date getInTime() {
		return InTime;
	}
	public void setInTime(Date inTime) {
		InTime = inTime;
	}
	public int getSeq() {
		return Seq;
	}
	public void setSeq(int seq) {
		Seq = seq;
	}
	public int getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(int timestamp) {
		Timestamp = timestamp;
	}
}
