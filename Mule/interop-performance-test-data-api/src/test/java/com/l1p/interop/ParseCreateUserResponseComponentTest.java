package com.l1p.interop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mule.DefaultMuleEventContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;
import org.mule.component.SimpleCallableJavaComponentTestCase;
import static org.junit.Assert.assertEquals;

import com.modusbox.bean.SpreadsheetRecordBean;
import com.modusbox.bean.UserBean;
import com.modusbox.component.UserAccountPreparationComponent;

public class ParseCreateUserResponseComponentTest extends SimpleCallableJavaComponentTestCase {

	@Test
	public void test() throws Exception {
		String payload = "no payload needed";
		MuleEvent event = getTestEvent(payload, muleContext);
		MuleMessage message = event.getMessage();
		
		List<UserBean> dfsp1Users = new ArrayList<UserBean>();
		List<UserBean> dfsp2Users = new ArrayList<UserBean>();
		
		UserBean bean = null;
		
		/*
		 * Create sender user
		 */
		bean = new UserBean();
		bean.setAccountId("123");
		bean.setAccountName("Bob1");
		bean.setPhoneName("bobPhone1");
		bean.setUserRoleType("customer");
		
		dfsp1Users.add(bean);
		
		/*
		 * Create receiver user
		 */
		bean = new UserBean();
		bean.setAccountId("789");
		bean.setAccountName("Alice2");
		bean.setPhoneName("AlicePhone2");
		bean.setUserRoleType("customer");
		
		dfsp2Users.add(bean);
		
		message.setProperty("dfsp1_users", dfsp1Users, PropertyScope.SESSION);
		message.setProperty("dfsp2_users", dfsp2Users, PropertyScope.SESSION);
		
		
		UserAccountPreparationComponent component = new UserAccountPreparationComponent();
		List<SpreadsheetRecordBean> data = (List<SpreadsheetRecordBean>) component.onCall(new DefaultMuleEventContext(event));
		
		assertEquals("Size matches", 2, data.size());
		
		SpreadsheetRecordBean ssrb = null;
		
		/*
		 * Test first combination
		 */
		ssrb = data.get(0);
		assertEquals("123", ssrb.getSenderAccountId());
		assertEquals("Bob1", ssrb.getSenderAccountName());
		assertEquals("bobPhone1", ssrb.getSenderPhoneName());
		assertEquals("customer", ssrb.getSenderuserRoleType());
		
		assertEquals("789", ssrb.getReceiverAccountId());
		assertEquals("Alice2", ssrb.getReceiverAccountName());
		assertEquals("AlicePhone2", ssrb.getReceiverPhoneName());
		assertEquals("customer", ssrb.getReceiverUserRoleType());
		
		/*
		 * Test second combination
		 */
		ssrb = data.get(1);
		assertEquals("123", ssrb.getReceiverAccountId());
		assertEquals("Bob1", ssrb.getReceiverAccountName());
		assertEquals("bobPhone1", ssrb.getReceiverPhoneName());
		assertEquals("customer", ssrb.getReceiverUserRoleType());
		
		assertEquals("789", ssrb.getSenderAccountId());
		assertEquals("Alice2", ssrb.getSenderAccountName());
		assertEquals("AlicePhone2", ssrb.getSenderPhoneName());
		assertEquals("customer", ssrb.getSenderuserRoleType());
	}
	
	
	@Test
	public void testErrorWithData() throws Exception {
		String payload = "no payload needed";
		MuleEvent event = getTestEvent(payload, muleContext);
		MuleMessage message = event.getMessage();
		
		List<UserBean> dfsp1Users = new ArrayList<UserBean>();
		List<UserBean> dfsp2Users = new ArrayList<UserBean>();
		
		UserBean bean = null;
		
		/*
		 * Create sender user
		 */
		bean = new UserBean();
		bean.setAccountId("123");
		bean.setAccountName("Bob1");
		bean.setPhoneName("bobPhone1");
		bean.setUserRoleType("customer");
		
		dfsp1Users.add(bean);
		
		/*
		 * Create receiver user
		 */
		bean = new UserBean();
		bean.setAccountId(null);
		bean.setAccountName("Alice2");
		bean.setPhoneName("AlicePhone2");
		bean.setUserRoleType("customer");
		
		dfsp2Users.add(bean);
		
		message.setProperty("dfsp1_users", dfsp1Users, PropertyScope.SESSION);
		message.setProperty("dfsp2_users", dfsp2Users, PropertyScope.SESSION);
		

		UserAccountPreparationComponent component = new UserAccountPreparationComponent();
		List<SpreadsheetRecordBean> data = (List<SpreadsheetRecordBean>) component.onCall(new DefaultMuleEventContext(event));
		
		assertEquals("Size matches", 0, data.size());
	}

}
