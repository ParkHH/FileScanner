package com.filescanner.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.filescanner.application.FileScannerApplication;
import com.filescanner.properties.FileScannerProps;
import com.filescanner.utils.Utills;

public class FileReaderImpl implements FileReader {
	String[] readPath = FileScannerProps.readPath;
	String[] findExtendList = FileScannerProps.findExtendList;
	String[] exceptionDirPathC = FileScannerProps.exceptionDirPathC;
	String[] exceptionDirPathD = FileScannerProps.exceptionDirPathD;
	String localName = "";
	List<File> findFileList = new ArrayList<File>();	
	
	
	
	
	@Override
	public List<File> fileRead() {	
		try {			
			findFileList.clear();
			localName = Utills.getLocalUserName();									// ���� ������ ����� ������ ��������			
		} catch (Exception e) {
			FileScannerApplication.logText.append(String.format("[%s] >> host �̸��� �˾Ƴ��µ� �����߽��ϴ�.\n", Utills.getTodateTime()));
			System.out.println(String.format("[%s] >> host �̸��� �˾Ƴ��µ� �����߽��ϴ�.", Utills.getTodateTime()));
			return null;
		}
		for(int i=0; i<readPath.length; i++) {										// ��ĵ ��� ��θ� ���������� ��ȸ
			String readPathOne = readPath[i];
			if(readPathOne != null) {				
				if(!readPathOne.equals("") && readPathOne.equals("C:/Users")) { 	// C ����̺��� ��� ����� ������ ���� ������ ����Ǵ� ��ġ�� �����ϹǷ� ������ ���� ��θ� ��ĵ�ϵ�����.
					readPathOne += "/"+localName;
				}
			}
			fileRead(readPathOne);													// ������ ��ο� ���� ��ĵ ����
		}
		
		// ã�� ���ϸ� Ȯ��
		for(int result=0; result<findFileList.size(); result++) {
			String findFileName = findFileList.get(result).getPath();
			FileScannerApplication.logText.append(String.format("[%s] >> ���� ��� ��ȸ ��� ����[%s] : [%s]\n", Utills.getTodateTime(), result+1, findFileName));
			System.out.println(String.format("[%s] >> ���� ��� ��ȸ ��� ����[%s] : [%s]", Utills.getTodateTime(), result+1, findFileName));
		}
		
		return findFileList;
	}
	
	
	
	
	
	
	/**
	 * ������ ��� ���� ������ �о�
	 * 1) ������ ��� ��Ī ���
	 * 2) ���丮�� ��� ���ȣ��
	 * �Ѵ�.
	 * @param strDirPath
	 */
	public void fileRead(String strDirPath) {
		File files = new File(strDirPath);
		if(files != null){
			File[] fileList = files.listFiles();																										// ��� ���� ���� ����Ʈ�� �����´�.
			if(fileList != null) {
				try {
					for(int a=0; a<fileList.length; a++) {																									// ������ ���� ����Ʈ�� �ϳ��� �����鼭 �������� ���丮���� Ȯ��
						File oneFile = fileList[a];
						if(oneFile != null) {
							//System.out.println(String.format(">>> �о���� ������ �̸� : %s", oneFile.getPath()));						
							String fileName = oneFile.getName();					
							if(oneFile.isFile()) {																											// ���� ������ ���� ���¶��
								try {
									String fileExtend = Utills.getFileExtension(fileName);
									for(int e=0; e<findExtendList.length; e++) {
										if(fileExtend.equals(findExtendList[e])) {
											//System.out.println(String.format(">>> �о���� ������ �̸� : %s", oneFile.getPath()));
											findFileList.add(oneFile);
										}
									}
								}catch(StringIndexOutOfBoundsException e) {
									//System.out.println(String.format(">> ���� Ȯ���� ���� �� ������ �߻��߽��ϴ�. ���� �߻� ������ [%s] �Դϴ�.", fileName));
									continue;
								}
							}else if(oneFile.isDirectory()) {																								// ���� ������ ���丮���
								String dirPath = oneFile.getPath();
								String [] exceptionDirPath = null;
								if(dirPath != null) {
									if(!dirPath.equals("")) {								
										boolean continueFlag = false;
										if(dirPath.contains("C:\\")) {																						// ���丮 ��ΰ� C ����̺����� D ����̺����� �����Ͽ� ��ĵ ���� ���丮�� �����Ѵ�.
											exceptionDirPath = exceptionDirPathC;
										}else if(dirPath.contains("D:\\")) {
											exceptionDirPath = exceptionDirPathD;
										}
										for(int j=0; j<exceptionDirPath.length; j++) {
											if(dirPath.contains(exceptionDirPath[j])) {
												//System.out.println(String.format(">> ��ĵ ���� ����Դϴ�. [%s]", exceptionDirPath[j]));
												continueFlag = true;
												break;
											}								
										}
										if(continueFlag) {
											continue;
										}else {
											fileRead(oneFile.getPath());																					// ��� ȣ���� ���� ���丮�� ���� ���� �׸��� Ž�� �� ��ĵ			
										}	
									}
								}
							}
						}
					}
				}catch(NullPointerException e) {						
					FileScannerApplication.logText.append(String.format("[%s] >> ���� ��θ� �о���̴� �� ������ �߻��߽��ϴ�. ���� �߻� ��δ� [%s] �Դϴ�.(%s - %s)\n", Utills.getTodateTime(), strDirPath, "NullPointerException", e.getLocalizedMessage()));
					System.out.println(String.format("[%s] >> ���� ��θ� �о���̴� �� ������ �߻��߽��ϴ�. ���� �߻� ��δ� [%s] �Դϴ�.(%s - %s)", Utills.getTodateTime(), strDirPath, "NullPointerException", e.getLocalizedMessage()));
					//e.printStackTrace();			
				}
			
			}
		}
	}
}
