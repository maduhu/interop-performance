package com.modusbox.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

public class ParseCreateUserResponseComponent implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();
		
		Object oPayload = message.getPayload();
		String accountId = "<nothing>";
		String payload = null;
		
		/*
		 * Only look for number that have at least 5 digits.  This is because the standard 
		 * response from USSD create has multiple digits in the response due to XML version
		 * and other values in the response.  The account number is currently 8 digits
		 */
		Pattern p = Pattern.compile("[0-9]{7,}");
		Matcher m = null;
		
		if (oPayload instanceof String) {
			System.out.println("about to parse string response from create user in USSD");
			payload = (String) oPayload;
			
			System.out.println("..parsing payload :: " + payload);
			
			m = p.matcher(payload);
			
			while (m.find()) {
			    accountId = m.group();
			    System.out.println("found a parsed number: " + accountId);
			}
			
		}
		
		return accountId;
	}

}
