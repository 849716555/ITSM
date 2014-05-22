package com.goldsunny.itsm.model;

public class MaintainStepMDL {
	private String OID;
	private String MaintainItemID;
	private String MaintainStep;
	private double Seq;

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getMaintainItemID() {
		return MaintainItemID;
	}

	public void setMaintainItemID(String maintainItemID) {
		MaintainItemID = maintainItemID;
	}

	public String getMaintainStep() {
		return MaintainStep;
	}

	public void setMaintainStep(String maintainStep) {
		MaintainStep = maintainStep;
	}

	public double getSeq() {
		return Seq;
	}

	public void setSeq(double seq) {
		Seq = seq;
	}
}
