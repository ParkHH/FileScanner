package com.filescanner.properties;


/**
 * FileScanner 의 각 Class 동작을 위해서 필요한 변수들을 모아놓은 Class
 * @author hhpark1344
 *
 */
public class FileScannerProps {
	// FileReader 변수 
	public static final String[] readPath = {"C:/Users", "D:/"};
	public static final String[] findExtendList = {".xls", ".xlsx", ".pptx", ".ppt", ".doc", ".docx", ".pdf"};
	public static final String[] exceptionDirPathC = {"\\Application Data", "\\History", "\\Content.IE5", "\\Temporary Internet Files", "\\Start Menu", "\\Cookies", "\\My Music", "\\My Pictures", "\\My Videos", "\\Local Settings", "\\My Documents", "\\NetHood", "\\PrintHood", "\\Recent", "\\SendTo", "\\Templates", "\\시작 메뉴", "\\sqldeveloper", "\\.","\\oneDriveSyncDir"};
	public static final String[] exceptionDirPathD = {"D:\\System Volume Information", "\\.metadata", "\\.plugins", "\\SqlDeveloper", "D:\\$", "\\sqldeveloper", "\\.", "\\oneDriveSyncDir"};
		
	// FileWriter 변수
	public static final String savePath = "D:/oneDriveSyncDir/";
		
	// logWriter 저장 경로
	public static final String logFilePath = "C:/fileScanner/log";	
}
