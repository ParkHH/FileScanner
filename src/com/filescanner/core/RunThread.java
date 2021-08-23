package com.filescanner.core;

import java.io.File;
import java.util.List;

import com.filescanner.application.FileScannerApplication;
import com.filescanner.utils.LogWriter;
import com.filescanner.utils.Utills;

/**
 * fileScanner �� Thread ������ �����ϴ� �κ�
 * �ʳ� Ȯ�强�� ����ؼ� Thread �� ������.
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
			FileScannerApplication.logText.append(String.format("[%s] >> Thread ���� �� ���� �߻�! (%s - %s)\n", Utills.getTodateTime(), e.getMessage(), e.getLocalizedMessage()));
			System.out.println(String.format("[%s] >> Thread ���� �� ���� �߻�! (%s - %s)", Utills.getTodateTime(), e.getMessage(), e.getLocalizedMessage()));
		}
		
	}

	/**
	 * fileScanner ���� �κ� ��
	 */
	public void runFileScanner() {
		FileReader fileReader = new FileReaderImpl();
		List<File> findFileList = fileReader.fileRead();
		FileWriter fileWriter = new FileWriterImpl();
		boolean result = fileWriter.fileWrite(findFileList);
		if(result) {
			FileScannerApplication.logText.append(String.format("[%s] >> ��� ���� ���縦 ���������� �Ϸ��Ͽ����ϴ�.\n", Utills.getTodateTime()));
			System.out.println(String.format("[%s] >> ��� ���� ���縦 ���������� �Ϸ��Ͽ����ϴ�.\n", Utills.getTodateTime()));
		}else {
			FileScannerApplication.logText.append(String.format("[%s] >> ��� ���� ���翡 �����Ͽ����ϴ�.", Utills.getTodateTime()));
			System.out.println(String.format("[%s] >> ��� ���� ���翡 �����Ͽ����ϴ�.", Utills.getTodateTime()));
		}				
	}
}
