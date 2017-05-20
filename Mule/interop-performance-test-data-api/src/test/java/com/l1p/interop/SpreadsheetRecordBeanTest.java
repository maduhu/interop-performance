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
		bean.setSenderuserRoleType("merchant");
		
		assertEquals("9876543421", bean.getSenderAccountId());
		assertEquals("senderAccountName", bean.getSenderAccountName());
		assertEquals("dfsp1", bean.getSenderDfsp());
		assertEquals("sendingPhoneName", bean.getSenderPhoneName());
		assertEquals("merchant", bean.getSenderuserRoleType());
		
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
		assertEquals("receiverDFSP", headers[5]);
		assertEquals("receiverName", headers[6]);
		assertEquals("receiverUserNumber", headers[7]);
		assertEquals("receiverAccountId", headers[8]);
		assertEquals("receiverRoleType", headers[9]);
	}
	
	
	@Test
	public void testConvenienceMethodSetRecords() {
		
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
		
		SpreadsheetRecordBean bean = new SpreadsheetRecordBean(data);
		
		assertEquals("senderDFSP", bean.getSenderDfsp());
		assertEquals("senderName", bean.getSenderPhoneName());
		assertEquals("senderUserNumber", bean.getSenderAccountName());
		assertEquals("senderAccountId", bean.getSenderAccountId());
		assertEquals("senderRoleType", bean.getSenderuserRoleType());

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
		bean.setSenderuserRoleType("merchant");
		
		String[] array = bean.getDataAsArray();
		
		assertEquals(10, array.length);
		assertEquals("9876543421", bean.getSenderAccountId());
		assertEquals("senderAccountName", bean.getSenderAccountName());
		assertEquals("dfsp1", bean.getSenderDfsp());
		assertEquals("sendingPhoneName", bean.getSenderPhoneName());
		assertEquals("merchant", bean.getSenderuserRoleType());
		
		assertEquals("12345", bean.getReceiverAccountId());
		assertEquals("receiverAccountName", bean.getReceiverAccountName());
		assertEquals("dfsp2", bean.getReceiverDfsp());
		assertEquals("receivingPhoneName", bean.getReceiverPhoneName());
		assertEquals("customer", bean.getReceiverUserRoleType());
		
	}

}
