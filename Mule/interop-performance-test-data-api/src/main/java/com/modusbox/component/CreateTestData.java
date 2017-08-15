package com.modusbox.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transport.PropertyScope;

import com.modusbox.bean.UserBean;

public class CreateTestData implements Callable {
	
	private Integer numberOfUsersToCreate;
	private String phoneNameRoot;
	private int testRunSequence;
	private String userRoleType;

		/*
		 * 
		 * Note about Phone Name/Account.
		 * 
		 * There are really 4 parts to the naming convention
		 * 
		 * 1.  phoneNameRoot : this should be 3 digits.  One convention that can be used is month/day e.g. 719 for July 19th.  What is important is to follow a convention that ensures we always have a unique number.
		 * 2.  test run sequence : this number should start with 1 (when we have a new phoneNameRoot value) and should increment by 1 for each run with the same phoneNameRoot.
		 * 3.  sequence of the number for a user account.  If the value for NumberOfusersToCreate is say 10, this number will start with 001, and increment all the way up to 010.
		 * 4.  The dfsp the user account was created for.
		 * 
		 * 
		 * 
		 */


	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();
		
		//
		// get all the values needed to create the test data
		//
		numberOfUsersToCreate = message.getProperty("numberOfUsersToCreate", PropertyScope.SESSION, 1);
		phoneNameRoot = message.getProperty("phoneNameRoot", PropertyScope.SESSION);
		testRunSequence = message.getProperty("testRunSequence", PropertyScope.SESSION, 1);
		userRoleType = message.getProperty("userRoleType", PropertyScope.SESSION);

		List<UserBean> users = createData(1);
		message.setProperty("dfsp1_users", users, PropertyScope.SESSION);
		dumpUserData(users);
		
		users = createData(2);
		message.setProperty("dfsp2_users", users, PropertyScope.SESSION);
		dumpUserData(users);
		
		return "done";
	}

	
	private List<UserBean> createData(int dfsp) {
		List<UserBean> users = new ArrayList<UserBean>();
		UserBean user = null;
		String phoneNameAndAccount = null;
		String sequenceNumberLPad = null;
		
		for (int i=1; i<=numberOfUsersToCreate; i++)  {
			user = new UserBean();

			// This left pads the sequence number with zeros up to 3 characters.  This is so we can keep the length to 8 (assuming the phoneNameRoot is 3 characters).
			sequenceNumberLPad = StringUtils.leftPad(Integer.toString(i), 3, "0");
			
			phoneNameAndAccount = phoneNameRoot + testRunSequence + sequenceNumberLPad + dfsp;
			user.setPhoneName(phoneNameAndAccount);
			user.setAccountName(phoneNameAndAccount);
			user.setUserRoleType(userRoleType);
			users.add(user);
		}
		
		return users;
	}
	
	
	private void dumpUserData(List<UserBean> users) {
		System.out.println("********************************************** dumping user data **********************************************");
		for (UserBean userBean : users) {
			System.out.println(userBean);
		}
	}

}
