package com.goldsunny.itsm.model;

/** 
 * 仓库查询实体类
 * @author yangwy       
 * @version 1.0     
 * @created 2014-5-15 下午4:21:08
 */
public class StoreMDL {

	public String OID;
	public String ThingClassID;
	public String ThingClassName;
	public String Name;
	public String Code;
	public String BrandName;
	public String Model;
	public String UnitName;
	public String ThingID;
	public String ShapeType;
	public int StockNumber;
	public int Amount;
	private int pageTotalCount;
	private int TotalPage;
	
	public String getOID() {
		return OID;
	}
	public void setOID(String oID) {
		OID = oID;
	}
	public String getThingClassID() {
		return ThingClassID;
	}
	public void setThingClassID(String thingClassID) {
		ThingClassID = thingClassID;
	}
	public String getThingClassName() {
		return ThingClassName;
	}
	public void setThingClassName(String thingClassName) {
		ThingClassName = thingClassName;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getBrandName() {
		return BrandName;
	}
	public void setBrandName(String brandName) {
		BrandName = brandName;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getUnitName() {
		return UnitName;
	}
	public void setUnitName(String unitName) {
		UnitName = unitName;
	}
	public String getThingID() {
		return ThingID;
	}
	public void setThingID(String thingID) {
		ThingID = thingID;
	}
	public String getShapeType() {
		return ShapeType;
	}
	public void setShapeType(String shapeType) {
		ShapeType = shapeType;
	}
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}
	public int getTotalPage() {
		return TotalPage;
	}
	public void setTotalPage(int totalPage) {
		TotalPage = totalPage;
	}
	public int getStockNumber() {
		return StockNumber;
	}
	public void setStockNumber(int stockNumber) {
		StockNumber = stockNumber;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	
	
	
	
	
}
