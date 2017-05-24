package com.l1p.interop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mule.DefaultMuleEventContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;
import org.mule.component.SimpleCallableJavaComponentTestCase;

import com.modusbox.bean.SpreadsheetRecordBean;
import com.modusbox.component.WriteUserAccounts;
import com.opencsv.CSVReader;

public class WriteUserAccountsTest extends SimpleCallableJavaComponentTestCase {

	@Test
	public void test() throws Exception {
		WriteUserAccounts component = new WriteUserAccounts();
		List<SpreadsheetRecordBean> data = new ArrayList<SpreadsheetRecordBean>();
		SpreadsheetRecordBean bean = null;
		String roleType = "customer";
		
		bean = new SpreadsheetRecordBean();
		
		bean.setReceiverAccountId("12345");
		bean.setReceiverAccountName("receiverAccountName");
		bean.setReceiverDfsp("dfsp2");
		bean.setReceiverPhoneName("receivingPhoneName");
		bean.setReceiverUserRoleType(roleType);
		bean.setReceiverUUID("1234");
		
		bean.setSenderAccountId("9876543421");
		bean.setSenderAccountName("senderAccountName");
		bean.setSenderDfsp("dfsp1");
		bean.setSenderPhoneName("sendingPhoneName");
		bean.setSenderUserRoleType(roleType);
		bean.setSenderUUID("9876");
		
		data.add(bean);
		
        MuleEvent event = getTestEvent(data, muleContext);
        MuleMessage message = event.getMessage();
        message.setProperty("userRoleType", roleType, PropertyScope.SESSION);
        
        String response = (String) component.onCall(new DefaultMuleEventContext(event));
        
        assertTrue(response.contains("Completed writing data to"));
        
        String filename = response.replace("Completed writing data to ", "");
        System.out.println("filename return: " + filename);
        
        
        CSVReader reader = new CSVReader(new FileReader(filename));
	    String [] nextLine;
	    
	    int i = 0;
	    String[] row = null;
	    
	    while ((nextLine = reader.readNext()) != null) {
	       // nextLine[] is an array of values from the line
	    	i++;
	    	System.out.println(nextLine[0] + nextLine[1] + "etc...");
	       
	    	if (i==2) {
	    		row = nextLine;  // we want the second line as line 1 is the header fields.
	    		break;
	    	}
	    }
	    
	    reader.close();
	    
	    SpreadsheetRecordBean record = new SpreadsheetRecordBean(row);
	    
	    assertEquals("dfsp1", record.getSenderDfsp());
	    assertEquals("9876543421", record.getSenderAccountId());
	    assertEquals("senderAccountName", record.getSenderAccountName());
	    assertEquals("sendingPhoneName", record.getSenderPhoneName());
	    assertEquals(roleType, record.getSenderUserRoleType());
	    assertEquals("9876", record.getSenderUUID());
	    
	    assertEquals("dfsp2", record.getReceiverDfsp());
	    assertEquals("12345", record.getReceiverAccountId());
	    assertEquals("receiverAccountName", record.getReceiverAccountName());
	    assertEquals("receivingPhoneName", record.getReceiverPhoneName());
	    assertEquals(roleType, record.getReceiverUserRoleType());
	    assertEquals("1234", record.getReceiverUUID());
	    
        
	}

}
