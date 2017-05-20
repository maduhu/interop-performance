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
	        
		message.setProperty("numberOfUsersToCreate", "1", PropertyScope.SESSION);
		message.setProperty("phoneNameRoot", "rootPhoneName", PropertyScope.SESSION);
		message.setProperty("accountNameRoot", "perfuser", PropertyScope.SESSION);
		message.setProperty("testRunSequence", "1", PropertyScope.SESSION);
		message.setProperty("userRoleType", "customer", PropertyScope.SESSION);
		
		CreateTestData createData = new CreateTestData();
		String response = (String) createData.onCall(new DefaultMuleEventContext(event));
		
		assertEquals("done", response);
		
		List<UserBean> users1 = message.getProperty("dfsp1_users", PropertyScope.SESSION);
		List<UserBean> users2 = message.getProperty("dfsp2_users", PropertyScope.SESSION);
		
		
		assertEquals(1, users1.size());
		assertEquals(1, users2.size());
		
		
		UserBean user1Bean = users1.get(0);
		UserBean user2Bean = users2.get(0);
		
		assertEquals(null, user1Bean.getAccountId());
		assertEquals("perfuser-1-01", user1Bean.getAccountName());
		assertEquals("rootPhoneName-1-01", user1Bean.getPhoneName());
		assertEquals("customer", user1Bean.getUserRoleType());
		
		assertEquals(null, user2Bean.getAccountId());
		assertEquals("perfuser-1-02", user2Bean.getAccountName());
		assertEquals("rootPhoneName-1-02", user2Bean.getPhoneName());
		assertEquals("customer", user2Bean.getUserRoleType());
		
	}

}
