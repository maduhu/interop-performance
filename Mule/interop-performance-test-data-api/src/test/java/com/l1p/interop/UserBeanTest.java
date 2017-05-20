package com.l1p.interop;

import org.junit.Test;
import static org.junit.Assert.*;

import com.modusbox.bean.UserBean;

public class UserBeanTest {

	@Test
	public void test() {
		
		UserBean bean = new UserBean();
		
		bean.setAccountId("1234");
		bean.setAccountName("Easter");
		bean.setPhoneName("Bob");
		bean.setUserRoleType("customer");
		
		assertEquals("1234", bean.getAccountId());
		assertEquals("Easter", bean.getAccountName());
		assertEquals("Bob", bean.getPhoneName());
		assertEquals("customer", bean.getUserRoleType());
		
	}

}
