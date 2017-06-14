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
		testRunSequence = message.getProperty("testRunSequence", PropertyScope.SESSION, 1);
		userRoleType = message.getProperty("userRoleType", PropertyScope.SESSION);

		List<UserBean> users = createData("1");
		message.setProperty("dfsp1_users", users, PropertyScope.SESSION);
		dumpUserData(users);
		
		users = createData("2");
		message.setProperty("dfsp2_users", users, PropertyScope.SESSION);
		dumpUserData(users);
		
		return "done";
	}
	
	
	private List<UserBean> createData(String dfsp) {
		List<UserBean> users = new ArrayList<UserBean>();
		UserBean user = null;
		String phoneNameAndAccount = null;
		
		for (int i=1; i<=numberOfUsersToCreate; i++)  {
			user = new UserBean();
			phoneNameAndAccount = phoneNameRoot + "-" + testRunSequence + "-" + i + "-" + dfsp;
			System.out.println("phoneNameAndAccount = " + phoneNameAndAccount);
			user.setPhoneName(phoneNameAndAccount);
			user.setAccountName("PT-" + phoneNameAndAccount);
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
