package com.goldsunny.itsm.view.tools;
/**
 * Element��
 * @author carrey
 *
 */
public class Element {
	/** �������� */
	private String contentText;
	/** ��tree�еĲ㼶 */
	private int level;
	/** Ԫ�ص�id */
	private String id;
	/** ��Ԫ�ص�id */
	private String parendId;
	/** �Ƿ�����Ԫ�� */
	private boolean hasChildren;
	/** item�Ƿ�չ�� */
	private boolean isExpanded;
	private String remark;
	
	/** ��ʾ�ýڵ�û�и�Ԫ�أ�Ҳ����levelΪ0�Ľڵ� */
	public static final int NO_PARENT = -1;
	/** ��ʾ��Ԫ��λ�����Ĳ㼶 */
	public static final int TOP_LEVEL = 0;
	
	public Element(String contentText, int level, String id, String parendId,
			boolean hasChildren, boolean isExpanded,String Remark) {
		super();
		this.contentText = contentText;
		this.level = level;
		this.id = id;
		this.parendId = parendId;
		this.hasChildren = hasChildren;
		this.isExpanded = isExpanded;
		remark=Remark;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParendId() {
		return parendId;
	}

	public void setParendId(String parendId) {
		this.parendId = parendId;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
