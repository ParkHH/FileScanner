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
			localName = Utills.getLocalUserName();									// 현재 윈도우 사용자 계정명 가져오기			
		} catch (Exception e) {
			FileScannerApplication.logText.append(String.format("[%s] >> host 이름을 알아내는데 실패했습니다.\n", Utills.getTodateTime()));
			System.out.println(String.format("[%s] >> host 이름을 알아내는데 실패했습니다.", Utills.getTodateTime()));
			return null;
		}
		for(int i=0; i<readPath.length; i++) {										// 스캔 대상 경로를 순차적으로 조회
			String readPathOne = readPath[i];
			if(readPathOne != null) {				
				if(!readPathOne.equals("") && readPathOne.equals("C:/Users")) { 	// C 드라이브의 경우 사용자 계정에 따라 파일이 저장되는 위치가 상이하므로 계정명 하위 경로를 스캔하도록함.
					readPathOne += "/"+localName;
				}
			}
			fileRead(readPathOne);													// 구성한 경로에 대해 스캔 시작
		}
		
		// 찾은 파일명 확인
		for(int result=0; result<findFileList.size(); result++) {
			String findFileName = findFileList.get(result).getPath();
			FileScannerApplication.logText.append(String.format("[%s] >> 복사 대상 조회 결과 파일[%s] : [%s]\n", Utills.getTodateTime(), result+1, findFileName));
			System.out.println(String.format("[%s] >> 복사 대상 조회 결과 파일[%s] : [%s]", Utills.getTodateTime(), result+1, findFileName));
		}
		
		return findFileList;
	}
	
	
	
	
	
	
	/**
	 * 지정된 경로 내부 내용을 읽어
	 * 1) 파일일 경우 명칭 출력
	 * 2) 디렉토리일 경우 재귀호출
	 * 한다.
	 * @param strDirPath
	 */
	public void fileRead(String strDirPath) {
		File files = new File(strDirPath);
		if(files != null){
			File[] fileList = files.listFiles();																										// 경로 내부 파일 리스트를 가져온다.
			if(fileList != null) {
				try {
					for(int a=0; a<fileList.length; a++) {																									// 가져온 파일 리스트를 하나씩 꺼내면서 파일인지 디렉토리인지 확인
						File oneFile = fileList[a];
						if(oneFile != null) {
							//System.out.println(String.format(">>> 읽어들인 파일의 이름 : %s", oneFile.getPath()));						
							String fileName = oneFile.getName();					
							if(oneFile.isFile()) {																											// 꺼낸 내용이 파일 형태라면
								try {
									String fileExtend = Utills.getFileExtension(fileName);
									for(int e=0; e<findExtendList.length; e++) {
										if(fileExtend.equals(findExtendList[e])) {
											//System.out.println(String.format(">>> 읽어들인 파일의 이름 : %s", oneFile.getPath()));
											findFileList.add(oneFile);
										}
									}
								}catch(StringIndexOutOfBoundsException e) {
									//System.out.println(String.format(">> 파일 확장자 추출 중 문제가 발생했습니다. 문제 발생 파일은 [%s] 입니다.", fileName));
									continue;
								}
							}else if(oneFile.isDirectory()) {																								// 꺼낸 내용이 디렉토리라면
								String dirPath = oneFile.getPath();
								String [] exceptionDirPath = null;
								if(dirPath != null) {
									if(!dirPath.equals("")) {								
										boolean continueFlag = false;
										if(dirPath.contains("C:\\")) {																						// 디렉토리 경로가 C 드라이브인지 D 드라이브인지 구분하여 스캔 예외 디렉토리를 지정한다.
											exceptionDirPath = exceptionDirPathC;
										}else if(dirPath.contains("D:\\")) {
											exceptionDirPath = exceptionDirPathD;
										}
										for(int j=0; j<exceptionDirPath.length; j++) {
											if(dirPath.contains(exceptionDirPath[j])) {
												//System.out.println(String.format(">> 스캔 예외 경로입니다. [%s]", exceptionDirPath[j]));
												continueFlag = true;
												break;
											}								
										}
										if(continueFlag) {
											continue;
										}else {
											fileRead(oneFile.getPath());																					// 재귀 호출을 통해 디렉토리를 열어 하위 항목을 탐색 및 스캔			
										}	
									}
								}
							}
						}
					}
				}catch(NullPointerException e) {						
					FileScannerApplication.logText.append(String.format("[%s] >> 파일 경로를 읽어들이던 중 문제가 발생했습니다. 문제 발생 경로는 [%s] 입니다.(%s - %s)\n", Utills.getTodateTime(), strDirPath, "NullPointerException", e.getLocalizedMessage()));
					System.out.println(String.format("[%s] >> 파일 경로를 읽어들이던 중 문제가 발생했습니다. 문제 발생 경로는 [%s] 입니다.(%s - %s)", Utills.getTodateTime(), strDirPath, "NullPointerException", e.getLocalizedMessage()));
					//e.printStackTrace();			
				}
			
			}
		}
	}
}
