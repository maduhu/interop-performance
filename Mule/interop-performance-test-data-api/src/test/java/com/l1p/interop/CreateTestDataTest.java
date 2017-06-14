package com.l1p.interop;

import org.junit.Test;
import org.mule.DefaultMuleEventContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;
import org.mule.component.SimpleCallableJavaComponentTestCase;
import static org.junit.Assert.assertEquals;

import java.util.List;

import com.modusbox.bean.UserBean;
import com.modusbox.component.CreateTestData;

public class CreateTestDataTest extends SimpleCallableJavaComponentTestCase {

	@Test
	public void testCreateData() throws Exception {
		
		String payload = "no payload needed";
		MuleEvent event = getTestEvent(payload, muleContext);
		MuleMessage message = event.getMessage();
		
		String testrunSequence = "4";
	        
		message.setProperty("numberOfUsersToCreate", "3", PropertyScope.SESSION);
		message.setProperty("phoneNameRoot", "rootPhoneName", PropertyScope.SESSION);
		message.setProperty("testRunSequence", testrunSequence, PropertyScope.SESSION);
		message.setProperty("userRoleType", "customer", PropertyScope.SESSION);
		
		CreateTestData createData = new CreateTestData();
		String response = (String) createData.onCall(new DefaultMuleEventContext(event));
		
		assertEquals("done", response);
		
		List<UserBean> users1 = message.getProperty("dfsp1_users", PropertyScope.SESSION);
		List<UserBean> users2 = message.getProperty("dfsp2_users", PropertyScope.SESSION);
		
		
		assertEquals(3, users1.size());
		assertEquals(3, users2.size());
		
		
		UserBean user1Bean = users1.get(0);
		UserBean user2Bean = users2.get(0);
		
		/*
		 * Name pattern:
		 * 
		 * [phoneNameRoot]-[testRunSequence]-[i]-[dfsp]
		 * 
		 * with testRunSequence = 4 and we want three dfsp1 users, the data should look like
		 * 
		 * rootPhoneName-4-1-1
		 * rootPhoneName-4-2-1
		 * rootPhoneName-4-3-1
		 * 
		 * and for dfsp2: 
		 * 
		 * rootPhoneName-4-1-2
		 * rootPhoneName-4-2-2
		 * rootPhoneName-4-3-2
		 */
		
		assertEquals(null, user1Bean.getAccountId());
		assertEquals("PT-rootPhoneName-"+testrunSequence+"-1-1", user1Bean.getAccountName());
		assertEquals("rootPhoneName-"+testrunSequence+"-1-1", user1Bean.getPhoneName());
		assertEquals("customer", user1Bean.getUserRoleType());
		
		assertEquals(null, user2Bean.getAccountId());
		assertEquals("PT-rootPhoneName-"+testrunSequence+"-1-2", user2Bean.getAccountName());
		assertEquals("rootPhoneName-"+testrunSequence+"-1-2", user2Bean.getPhoneName());
		assertEquals("customer", user2Bean.getUserRoleType());
		
		/*
		 * 
		 * Test the second user
		 * 
		 */
		user1Bean = users1.get(1);
		user2Bean = users2.get(1);
		
		assertEquals(null, user1Bean.getAccountId());
		assertEquals("PT-rootPhoneName-"+testrunSequence+"-2-1", user1Bean.getAccountName());
		assertEquals("rootPhoneName-"+testrunSequence+"-2-1", user1Bean.getPhoneName());
		assertEquals("customer", user1Bean.getUserRoleType());
		
		assertEquals(null, user2Bean.getAccountId());
		assertEquals("PT-rootPhoneName-"+testrunSequence+"-2-2", user2Bean.getAccountName());
		assertEquals("rootPhoneName-"+testrunSequence+"-2-2", user2Bean.getPhoneName());
		assertEquals("customer", user2Bean.getUserRoleType());
		
	}

}
