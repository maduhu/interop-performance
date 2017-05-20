package com.l1p.interop;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mule.DefaultMuleEventContext;
import org.mule.api.MuleEvent;
import org.mule.component.SimpleCallableJavaComponentTestCase;

import com.modusbox.component.ParseCreateUserResponseComponent;


public class ParseCreateUserResponseTest extends SimpleCallableJavaComponentTestCase {


	@Test
	public void test() throws Exception  {

        // setup payload of actual expected message from USSD create user 
        StringBuilder sb = new StringBuilder();
        sb.append("<UssdResponse version=\"1.0\"><Status code=\"0\"></Status><Message>Account created!");
        sb.append('\n');
        sb.append("Your user number is: 29894189");
        sb.append('\n');
        sb.append("0. Home</Message><DefaultCode>*123#</DefaultCode><PhoneNumber></PhoneNumber></UssdResponse>");
        
//        
//        
//        <UssdResponse version="1.0"><Status code="0"></Status><Message>Account created! 
//        		Your user number is: 29894189
//
//        		0. Home</Message><DefaultCode>*123#</DefaultCode><PhoneNumber></PhoneNumber></UssdResponse>
//        
//        
        
        
        ParseCreateUserResponseComponent component = new ParseCreateUserResponseComponent();
 
        MuleEvent event = getTestEvent(sb.toString(), muleContext);
        String response = (String) component.onCall(new DefaultMuleEventContext(event));
        
        System.out.println("component response = " + response);
        
        // Assert test data
        assertEquals("Expecting 29894189 to be returned", "29894189", response);
        
        
	}
        
}
