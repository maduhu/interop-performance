package com.modusbox.component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transport.PropertyScope;

import com.modusbox.bean.SpreadsheetRecordBean;
import com.opencsv.CSVWriter;


/**
 * 
 * @author 		Brian Price
 * 
 * Date:		May 2017
 *  
 * Purpose: 	The purpose of this class is to write the data from the SpreadsheetRecordBean to a comma separated file.
 *  			By using this class allows us to run this Mule application on CE ratherand not on EE.  Otherwise it makes better
 *  			sense to use Data Weave instead of this. 
 *
 */
public class WriteUserAccounts implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();

		String userRoleType = message.getProperty("userRoleType", PropertyScope.SESSION);
		String filename = userRoleType + "TestingData.csv";
		String temporaryDir = System.getProperty("java.io.tmpdir");
		String qualifiedFilename = temporaryDir + filename;
				
		List<SpreadsheetRecordBean> data = ((List<SpreadsheetRecordBean>) message.getPayload());
		writeDataToFile(data, qualifiedFilename);
		System.out.println("Writing performance data CSV file: " + qualifiedFilename);
		
		return "Completed writing data to " + qualifiedFilename;
	}
	
	
	public void writeDataToFile(List<SpreadsheetRecordBean> data, String fileaLocation) throws IOException {
		
		/*
		 * Need to write file without quotes around the fields.
		 */
		
		CSVWriter writer = new CSVWriter(new FileWriter(fileaLocation), ',');
		
		// Write the header row
		writer.writeNext(SpreadsheetRecordBean.getHeaderFieldsAsArray());
		
		for (SpreadsheetRecordBean spreadsheetRecordBean : data) {
			writer.writeNext(spreadsheetRecordBean.getDataAsArray());	
		}
		
		writer.flush();
		writer.close();
		 
		 
	}

}
