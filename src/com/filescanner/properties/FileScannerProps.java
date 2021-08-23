package com.filescanner.properties;


/**
 * FileScanner �� �� Class ������ ���ؼ� �ʿ��� �������� ��Ƴ��� Class
 * @author hhpark1344
 *
 */
public class FileScannerProps {
	// FileReader ���� 
	public static final String[] readPath = {"C:/Users", "D:/"};
	public static final String[] findExtendList = {".xls", ".xlsx", ".pptx", ".ppt", ".doc", ".docx", ".pdf"};
	public static final String[] exceptionDirPathC = {"\\Application Data", "\\History", "\\Content.IE5", "\\Temporary Internet Files", "\\Start Menu", "\\Cookies", "\\My Music", "\\My Pictures", "\\My Videos", "\\Local Settings", "\\My Documents", "\\NetHood", "\\PrintHood", "\\Recent", "\\SendTo", "\\Templates", "\\���� �޴�", "\\sqldeveloper", "\\.","\\oneDriveSyncDir"};
	public static final String[] exceptionDirPathD = {"D:\\System Volume Information", "\\.metadata", "\\.plugins", "\\SqlDeveloper", "D:\\$", "\\sqldeveloper", "\\.", "\\oneDriveSyncDir"};
		
	// FileWriter ����
	public static final String savePath = "D:/oneDriveSyncDir/";
		
	// logWriter ���� ���
	public static final String logFilePath = "C:/fileScanner/log";	
}
