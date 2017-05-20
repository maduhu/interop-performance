package com.modusbox.component;

import java.util.ArrayList;
import java.util.List;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transport.PropertyScope;

import com.modusbox.bean.UserBean;

public class CreateTestData implements Callable {
	
	private Integer numberOfUsersToCreate;
	private String phoneNameRoot;
	private String accountNameRoot;
	private int accountNameSequence;
	private int testRunSequence;
	private String userRoleType;

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();
		
		//
		// get all the values needed to create the test data
		//
		numberOfUsersToCreate = message.getProperty("numberOfUsersToCreate", PropertyScope.SESSION, 1);
		phoneNameRoot = message.getProperty("phoneNameRoot", PropertyScope.SESSION);
		accountNameRoot = message.getProperty("accountNameRoot", PropertyScope.SESSION);
		testRunSequence = (int) message.getProperty("testRunSequence", PropertyScope.SESSION, 1);
		userRoleType = message.getProperty("userRoleType", PropertyScope.SESSION);

		message.setProperty("dfsp1_users", createData("1"), PropertyScope.SESSION);
		message.setProperty("dfsp2_users", createData("2"), PropertyScope.SESSION);
		
		return "done";
	}
	
	
	private List<UserBean> createData(String dfsp) {
		List<UserBean> users = new ArrayList<UserBean>();
		UserBean user = null;
		
		for (int i=accountNameSequence; i<numberOfUsersToCreate+accountNameSequence; i++)  {
			user = new UserBean();
			user.setPhoneName(phoneNameRoot + "-" + testRunSequence + "-" + i + dfsp);
			user.setAccountName(accountNameRoot + "-" + testRunSequence + "-" + i + dfsp);
			user.setUserRoleType(userRoleType);
			users.add(user);
		}
		
		return users;
	}

}
