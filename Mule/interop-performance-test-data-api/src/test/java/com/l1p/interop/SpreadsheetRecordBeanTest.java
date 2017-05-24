package com.l1p.interop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.modusbox.bean.SpreadsheetRecordBean;

public class SpreadsheetRecordBeanTest {

	@Test
	public void testGetterAndSetters() {
		
		SpreadsheetRecordBean bean = new SpreadsheetRecordBean();
		
		bean.setReceiverAccountId("12345");
		bean.setReceiverAccountName("receiverAccountName");
		bean.setReceiverDfsp("dfsp2");
		bean.setReceiverPhoneName("receivingPhoneName");
		bean.setReceiverUserRoleType("customer");
		
		bean.setSenderAccountId("9876543421");
		bean.setSenderAccountName("senderAccountName");
		bean.setSenderDfsp("dfsp1");
		bean.setSenderPhoneName("sendingPhoneName");
		bean.setSenderUserRoleType("merchant");
		
		assertEquals("9876543421", bean.getSenderAccountId());
		assertEquals("senderAccountName", bean.getSenderAccountName());
		assertEquals("dfsp1", bean.getSenderDfsp());
		assertEquals("sendingPhoneName", bean.getSenderPhoneName());
		assertEquals("merchant", bean.getSenderUserRoleType());
		
		assertEquals("12345", bean.getReceiverAccountId());
		assertEquals("receiverAccountName", bean.getReceiverAccountName());
		assertEquals("dfsp2", bean.getReceiverDfsp());
		assertEquals("receivingPhoneName", bean.getReceiverPhoneName());
		assertEquals("customer", bean.getReceiverUserRoleType());
	}
	
	
	@Test
	public void testConvenienceMethodGetHeaderFieldsArray() {
		
		String[] headers = SpreadsheetRecordBean.getHeaderFieldsAsArray();
		
		assertEquals("senderDFSP", headers[0]);
		assertEquals("senderName", headers[1]);
		assertEquals("senderUserNumber", headers[2]);
		assertEquals("senderAccountId", headers[3]);
		assertEquals("senderRoleType", headers[4]);
		assertEquals("senderUUID", headers[5]);
		
		assertEquals("receiverDFSP", headers[6]);
		assertEquals("receiverName", headers[7]);
		assertEquals("receiverUserNumber", headers[8]);
		assertEquals("receiverAccountId", headers[9]);
		assertEquals("receiverRoleType", headers[10]);
		assertEquals("receiverUUID", headers[11]);
	}
	
	
	@Test
	public void testConvenienceMethodSetRecords() {
		
		String[] data = new String[12];
		
		data[0] = "senderDFSP";  		//this.getSenderDfsp();
		data[1] = "senderName";  		//this.getSenderPhoneName();
		data[2] = "senderUserNumber";  	//this.getSenderAccountName();
		data[3] = "senderAccountId";  	//this.getSenderAccountId();
		data[4] = "senderRoleType";  	//this.getSenderuserRoleType();
		data[5] = "senderUUID";		  	//this.getSenderUUID();
		
		data[6] = "receiverDFSP";  		//this.getReceiverDfsp();
		data[7] = "receiverName";  		//this.getReceiverPhoneName();
		data[8] = "receiverUserNumber";	//this.getReceiverAccountName();
		data[9] = "receiverAccountId";  //this.getReceiverAccountId();
		data[10] = "receiverRoleType";	//this.getReceiverUserRoleType();
		data[11] = "receiverUUID";		//this.getReceiverUUID();
		
		SpreadsheetRecordBean bean = new SpreadsheetRecordBean(data);
		
		assertEquals("senderDFSP", bean.getSenderDfsp());
		assertEquals("senderName", bean.getSenderPhoneName());
		assertEquals("senderUserNumber", bean.getSenderAccountName());
		assertEquals("senderAccountId", bean.getSenderAccountId());
		assertEquals("senderRoleType", bean.getSenderUserRoleType());

		assertEquals("receiverDFSP", bean.getReceiverDfsp());
		assertEquals("receiverName", bean.getReceiverPhoneName());
		assertEquals("receiverUserNumber", bean.getReceiverAccountName());
		assertEquals("receiverAccountId", bean.getReceiverAccountId());
		assertEquals("receiverRoleType", bean.getReceiverUserRoleType());
		
		
		
		
	}
	
	
	@Test
	public void testConvenienceMethodGetDataAsArray() {
		SpreadsheetRecordBean bean = new SpreadsheetRecordBean();
		
		bean.setReceiverAccountId("12345");
		bean.setReceiverAccountName("receiverAccountName");
		bean.setReceiverDfsp("dfsp2");
		bean.setReceiverPhoneName("receivingPhoneName");
		bean.setReceiverUserRoleType("customer");
		
		bean.setSenderAccountId("9876543421");
		bean.setSenderAccountName("senderAccountName");
		bean.setSenderDfsp("dfsp1");
		bean.setSenderPhoneName("sendingPhoneName");
		bean.setSenderUserRoleType("merchant");
		
		String[] array = bean.getDataAsArray();
		
		assertEquals(12, array.length);
		assertEquals("9876543421", bean.getSenderAccountId());
		assertEquals("senderAccountName", bean.getSenderAccountName());
		assertEquals("dfsp1", bean.getSenderDfsp());
		assertEquals("sendingPhoneName", bean.getSenderPhoneName());
		assertEquals("merchant", bean.getSenderUserRoleType());
		
		assertEquals("12345", bean.getReceiverAccountId());
		assertEquals("receiverAccountName", bean.getReceiverAccountName());
		assertEquals("dfsp2", bean.getReceiverDfsp());
		assertEquals("receivingPhoneName", bean.getReceiverPhoneName());
		assertEquals("customer", bean.getReceiverUserRoleType());
		
	}

}
