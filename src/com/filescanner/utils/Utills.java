package com.filescanner.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utills {
	
	/**
	 * windows 로그인 사용자 계정 정보 가져오기
	 * @return
	 */
	public static String getLocalUserName() {
		//NTSystem ntsys = new NTSystem();
		//String localName = ntsys.getName();
		String localName = System.getProperty("user.home");
		localName = localName.substring(localName.lastIndexOf("\\")+1);
		System.out.println(String.format(">> host 이름 : [%s]", localName));
		
		return localName;
	}
	
	/**
	 * 파일 확장자 가져오기
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
		
		return fileExtension;
	}
	
	
	/**
	 * 오늘 날짜 구하기 yyyy-MM-dd
	 */
	public static String getTodate() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String convertFormatToday = sdf.format(today);

		return convertFormatToday; 
	}
	
	/**
	 * 오늘 날짜 구하기 yyyy-MM-dd HH:mm:ss
	 */
	public static String getTodateTime() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String convertFormatToday = sdf.format(today);

		return convertFormatToday; 
	}
	
	
	/**
	 * 해당 경로가 존재하는지 확인
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
