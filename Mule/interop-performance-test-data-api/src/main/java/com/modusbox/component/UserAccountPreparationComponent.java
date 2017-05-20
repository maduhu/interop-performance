package com.modusbox.component;

import java.util.ArrayList;
import java.util.List;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transport.PropertyScope;

import com.modusbox.bean.SpreadsheetRecordBean;
import com.modusbox.bean.UserBean;

public class UserAccountPreparationComponent implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();
		
		List<UserBean> dfsp1Users = message.getProperty("dfsp1_users", PropertyScope.SESSION);
		List<UserBean> dfsp2Users = message.getProperty("dfsp2_users", PropertyScope.SESSION);
		
		List<SpreadsheetRecordBean> data = new ArrayList<SpreadsheetRecordBean>();
		SpreadsheetRecordBean csvEntry = null;
		
		int numAccountNotCreated = 0;
		
		System.out.println(" ** CSV file preparation -- Start");
		
		UserBean dfsp1UserBean = null;
		UserBean dfsp2UserBean = null;
		
		for (int i=0; i<dfsp1Users.size(); i++) {
			
			csvEntry = new SpreadsheetRecordBean();
			
			dfsp1UserBean = dfsp1Users.get(i);
			dfsp2UserBean = dfsp2Users.get(i);
			
			//
			// dfsp1 is the sender in this situation
			//
			csvEntry.setSenderDfsp("dfsp1");
			csvEntry.setSenderAccountId(dfsp1UserBean.getAccountId());
			csvEntry.setSenderAccountName(dfsp1UserBean.getAccountName());
			csvEntry.setSenderPhoneName(dfsp1UserBean.getPhoneName());
			csvEntry.setSenderuserRoleType(dfsp1UserBean.getUserRoleType());

			csvEntry.setReceiverDfsp("dfsp2");
			csvEntry.setReceiverAccountId(dfsp2UserBean.getAccountId());
			csvEntry.setReceiverAccountName(dfsp2UserBean.getAccountName());
			csvEntry.setReceiverPhoneName(dfsp2UserBean.getPhoneName());
			csvEntry.setReceiverUserRoleType(dfsp2UserBean.getUserRoleType());
			
			if (dfsp1UserBean.getAccountId() != null && dfsp1UserBean.getAccountId().length() >= 0 && dfsp2UserBean.getAccountId() != null && dfsp2UserBean.getAccountId().length() >= 0) {
				System.out.println(" ** CSV file preparation -- " + dfsp1UserBean.getPhoneName() + " We have an account ID, so adding it to csv array");
				data.add(csvEntry);					
			} else {
				numAccountNotCreated++;
				System.out.println(" ** CSV file preparation -- " + dfsp1UserBean.getPhoneName() + " No Account ID set in bean,");
			}
			
			
			//
			// dfsp2 is the sender and dfsp1 is the receiver
			//
			csvEntry = new SpreadsheetRecordBean();
			
			csvEntry.setSenderDfsp("dfsp2");
			csvEntry.setSenderAccountId(dfsp2UserBean.getAccountId());
			csvEntry.setSenderAccountName(dfsp2UserBean.getAccountName());
			csvEntry.setSenderPhoneName(dfsp2UserBean.getPhoneName());
			csvEntry.setSenderuserRoleType(dfsp2UserBean.getUserRoleType());

			csvEntry.setReceiverDfsp("dfsp1");
			csvEntry.setReceiverAccountId(dfsp1UserBean.getAccountId());
			csvEntry.setReceiverAccountName(dfsp1UserBean.getAccountName());
			csvEntry.setReceiverPhoneName(dfsp1UserBean.getPhoneName());
			csvEntry.setReceiverUserRoleType(dfsp1UserBean.getUserRoleType());
			
			
			if (dfsp1UserBean.getAccountId() != null && dfsp1UserBean.getAccountId().length() >= 0 && dfsp2UserBean.getAccountId() != null && dfsp2UserBean.getAccountId().length() >= 0) {
				data.add(csvEntry);					
			}
			
		}
		
		if (numAccountNotCreated > 0) {
			message.setProperty("NewAccountsCreated", "false", PropertyScope.INVOCATION);
		}
		else 
			message.setProperty("NewAccountsCreated", "true", PropertyScope.INVOCATION);
		
		return data;
	}

}
