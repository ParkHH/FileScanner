package com.filescanner.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utills {
	
	/**
	 * windows �α��� ����� ���� ���� ��������
	 * @return
	 */
	public static String getLocalUserName() {
		//NTSystem ntsys = new NTSystem();
		//String localName = ntsys.getName();
		String localName = System.getProperty("user.home");
		localName = localName.substring(localName.lastIndexOf("\\")+1);
		System.out.println(String.format(">> host �̸� : [%s]", localName));
		
		return localName;
	}
	
	/**
	 * ���� Ȯ���� ��������
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
		
		return fileExtension;
	}
	
	
	/**
	 * ���� ��¥ ���ϱ� yyyy-MM-dd
	 */
	public static String getTodate() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String convertFormatToday = sdf.format(today);

		return convertFormatToday; 
	}
	
	/**
	 * ���� ��¥ ���ϱ� yyyy-MM-dd HH:mm:ss
	 */
	public static String getTodateTime() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String convertFormatToday = sdf.format(today);

		return convertFormatToday; 
	}
	
	
	/**
	 * �ش� ��ΰ� �����ϴ��� Ȯ��
	 */
	public static boolean checkPathisExist(String path) {				
		boolean result = false;
		
		File file = new File(path);
		if(file != null) {	
			File[] fileArr = file.listFiles();
			if(fileArr != null) {
				result = true;
			}
		}
		
		return result;
	}
}
