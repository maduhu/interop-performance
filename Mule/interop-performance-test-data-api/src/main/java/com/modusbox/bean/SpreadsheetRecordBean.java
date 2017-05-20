package com.modusbox.bean;

public class SpreadsheetRecordBean {

	private String senderDfsp;
	private String senderPhoneName;
	private String senderAccountName;
	private String senderuserRoleType;
	private String senderAccountId;

	private String receiverDfsp;
	private String receiverPhoneName;
	private String receiverAccountName;
	private String receiverUserRoleType;
	private String receiverAccountId;
	
	
	
	public SpreadsheetRecordBean() {
		super();
	}
	
	public SpreadsheetRecordBean(String[] record) {
		super();
		this.setRecord(record);
	}
	

	public String getSenderPhoneName() {
		return senderPhoneName;
	}
	
	public void setSenderPhoneName(String senderPhoneName) {
		this.senderPhoneName = senderPhoneName;
	}
	
	public String getSenderAccountName() {
		return senderAccountName;
	}
	
	public void setSenderAccountName(String senderaccountName) {
		this.senderAccountName = senderaccountName;
	}
	
	public String getSenderuserRoleType() {
		return senderuserRoleType;
	}
	
	public void setSenderuserRoleType(String senderuserRoleType) {
		this.senderuserRoleType = senderuserRoleType;
	}
	
	public String getSenderAccountId() {
		return senderAccountId;
	}
	
	public void setSenderAccountId(String senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	
	public String getReceiverPhoneName() {
		return receiverPhoneName;
	}
	
	public void setReceiverPhoneName(String receiverPhoneName) {
		this.receiverPhoneName = receiverPhoneName;
	}
	
	public String getReceiverAccountName() {
		return receiverAccountName;
	}
	
	public void setReceiverAccountName(String receiverAccountName) {
		this.receiverAccountName = receiverAccountName;
	}
	
	public String getReceiverUserRoleType() {
		return receiverUserRoleType;
	}
	
	public void setReceiverUserRoleType(String receiverUserRoleType) {
		this.receiverUserRoleType = receiverUserRoleType;
	}
	
	public String getReceiverAccountId() {
		return receiverAccountId;
	}
	
	public void setReceiverAccountId(String receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}
	
	
	public String[] getDataAsArray() {
		String[] data = new String[10];
		data[0] = this.getSenderDfsp();
		data[1] = this.getSenderPhoneName();
		data[2] = this.getSenderAccountName();
		data[3] = this.getSenderAccountId();
		data[4] = this.getSenderuserRoleType();
		
		data[5] = this.getReceiverDfsp();
		data[6] = this.getReceiverPhoneName();
		data[7] = this.getReceiverAccountName();
		data[8] = this.getReceiverAccountId();
		data[9] = this.getReceiverUserRoleType();
		
		return data;
	}
	
	
	public static String[] getHeaderFieldsAsArray() {
		String[] data = new String[10];
		
		data[0] = "senderDFSP";  		//this.getSenderDfsp();
		data[1] = "senderName";  		//this.getSenderPhoneName();
		data[2] = "senderUserNumber";  	//this.getSenderAccountName();
		data[3] = "senderAccountId";  	//this.getSenderAccountId();
		data[4] = "senderRoleType";  	//this.getSenderuserRoleType();
		
		data[5] = "receiverDFSP";  		//this.getReceiverDfsp();
		data[6] = "receiverName";  		//this.getReceiverPhoneName();
		data[7] = "receiverUserNumber";	//this.getReceiverAccountName();
		data[8] = "receiverAccountId";  //this.getReceiverAccountId();
		data[9] = "receiverRoleType";	//this.getReceiverUserRoleType();
		
		return data;
	}

	public String getSenderDfsp() {
		return senderDfsp;
	}

	public void setSenderDfsp(String senderDfsp) {
		this.senderDfsp = senderDfsp;
	}

	public String getReceiverDfsp() {
		return receiverDfsp;
	}

	public void setReceiverDfsp(String receiverDfsp) {
		this.receiverDfsp = receiverDfsp;
	}
	
	
	/*
	 * Convenience method to set all the fields. 
	 */
	public void setRecord(String[] values) {
//		String[] values = record.split(",");
		
		this.senderDfsp = values[0];
		this.senderPhoneName = values[1];
		this.senderAccountName = values[2];
		this.senderAccountId = values[3];
		this.senderuserRoleType = values[4];
		
		this.receiverDfsp = values[5];
		this.receiverPhoneName = values[6];
		this.receiverAccountName = values[7];
		this.receiverAccountId = values[8];
		this.receiverUserRoleType = values[9];
	}
}
