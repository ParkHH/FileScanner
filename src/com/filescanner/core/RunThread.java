package com.filescanner.core;

import java.io.File;
import java.util.List;

import com.filescanner.application.FileScannerApplication;
import com.filescanner.utils.LogWriter;
import com.filescanner.utils.Utills;

/**
 * fileScanner 의 Thread 동작을 제어하는 부분
 * 훗날 확장성을 고려해서 Thread 로 구성함.
 * @author hhpark1344
 *
 */
public class RunThread implements Runnable {

	@Override
	public void run() {
		try {
			while(true) {
				FileScannerApplication.logText.setLength(0);
				runFileScanner();
				LogWriter logWriter = new LogWriter();
				logWriter.writeLog();
				Thread.sleep(30000);
			}
		}catch (Exception e) {
			FileScannerApplication.logText.append(String.format("[%s] >> Thread 실행 중 에러 발생! (%s - %s)\n", Utills.getTodateTime(), e.getMessage(), e.getLocalizedMessage()));
			System.out.println(String.format("[%s] >> Thread 실행 중 에러 발생! (%s - %s)", Utills.getTodateTime(), e.getMessage(), e.getLocalizedMessage()));
		}
		
	}

	/**
	 * fileScanner 동작 부분 명세
	 */
	public void runFileScanner() {
		FileReader fileReader = new FileReaderImpl();
		List<File> findFileList = fileReader.fileRead();
		FileWriter fileWriter = new FileWriterImpl();
		boolean result = fileWriter.fileWrite(findFileList);
		if(result) {
			FileScannerApplication.logText.append(String.format("[%s] >> 대상 파일 복사를 최종적으로 완료하였습니다.\n", Utills.getTodateTime()));
			System.out.println(String.format("[%s] >> 대상 파일 복사를 최종적으로 완료하였습니다.\n", Utills.getTodateTime()));
		}else {
			FileScannerApplication.logText.append(String.format("[%s] >> 대상 파일 복사에 실패하였습니다.", Utills.getTodateTime()));
			System.out.println(String.format("[%s] >> 대상 파일 복사에 실패하였습니다.", Utills.getTodateTime()));
		}				
	}
}
