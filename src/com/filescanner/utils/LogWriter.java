package com.filescanner.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.filescanner.application.FileScannerApplication;
import com.filescanner.properties.FileScannerProps;

public class LogWriter {	
	/**
	 * 
	 */
	public void writeLog() {
		String logFileName = Utills.getTodate()+".log";			
		FileWriter fw = null;
		try {		
			File logFilePath = new File(FileScannerProps.logFilePath);
			if(logFilePath != null) {
				logFilePath.mkdirs();
			}
			fw = new FileWriter(FileScannerProps.logFilePath+"/"+logFileName, true);			
			fw.write(FileScannerApplication.logText.toString());			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		} finally {
			try {
				if(fw!=null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
