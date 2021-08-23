package com.filescanner.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.filescanner.application.FileScannerApplication;
import com.filescanner.properties.FileScannerProps;
import com.filescanner.utils.Utills;

public class FileWriterImpl implements FileWriter {
	String savePath = FileScannerProps.savePath;
	
	
	@Override
	public boolean fileWrite(List<File> readFiles) {
		boolean result = false;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		for(int f=0; f<readFiles.size(); f++) {
			File fileOne = readFiles.get(f);			
			try {
				//System.out.println(String.format(">> ���� ���縦 �����մϴ�, ��� ���� : [%s]",fileOne.getPath()));
				fis = new FileInputStream(fileOne);				
				boolean existCheckResult = Utills.checkPathisExist(savePath);
				if(!existCheckResult) {
					savePath = "C:/oneDriveSyncDir/";
					boolean existCheckResult2 = Utills.checkPathisExist(savePath);
					if(!existCheckResult2) {
						File newOutPath = new File(savePath);
						newOutPath.mkdirs();
					}					
				}
				fos = new FileOutputStream(savePath+fileOne.getName());
				bis = new BufferedInputStream(fis, 52428800);		// 50MB
				bos = new BufferedOutputStream(fos, 52428800);		// 50MB
				
				while(true) {
					int data = bis.read();
					if(data == -1) break;
					//fos.write(data);
					bos.write(data);
				}
				
				FileScannerApplication.logText.append(String.format("[%s] >> ���� ���縦 �Ϸ��߽��ϴ�, ���� ����[%s] : [%s]\n", Utills.getTodateTime(), (f+1), savePath+fileOne.getName()));
				System.out.println(String.format("[%s] >> ���� ���縦 �Ϸ��߽��ϴ�, ���� ����[%s] : [%s]", Utills.getTodateTime(), (f+1), savePath+fileOne.getName()));
			} catch (FileNotFoundException e) {
				FileScannerApplication.logText.append(String.format("[%s] >> ������ �о���̴� �� ������ �߻��߽��ϴ�, ������ �������� �ʽ��ϴ�. **** [%s - %s]\n", Utills.getTodateTime(), "FileNotFoundException", e.getLocalizedMessage()));
				System.out.println(String.format("[%s] >> ������ �о���̴� �� ������ �߻��߽��ϴ�, ������ �������� �ʽ��ϴ�. **** [%s - %s]", Utills.getTodateTime(), "FileNotFoundException", e.getLocalizedMessage()));
				e.printStackTrace();				
				return result;
			} catch (IOException e) {
				FileScannerApplication.logText.append(String.format("[%s] >> ������ �д� �߿� ������ �߻��߽��ϴ�. **** [%s - %s]\n", Utills.getTodateTime(), "IOException", e.getLocalizedMessage()));
				System.out.println(String.format("[%s] >> ������ �д� �߿� ������ �߻��߽��ϴ�. **** [%s - %s]", Utills.getTodateTime(), "IOException", e.getLocalizedMessage()));
				e.printStackTrace();
				return result;
			}finally {
				try {
					if(bos != null) bos.close();
					if(fos != null) fos.close();
					if(bis != null) bis.close();
					if(fis != null) fis.close();
					
				}catch(IOException e) {
					FileScannerApplication.logText.append(String.format(">> Stream close �� ������ �߻��߽��ϴ�. **** [%s - %s]\n", "IOException", e.getLocalizedMessage()));
					System.out.println(String.format(">> Stream close �� ������ �߻��߽��ϴ�. **** [%s - %s]", "IOException", e.getLocalizedMessage()));
				}
			}
			
		}
		
		result = true;
		
		return result;
	}

}
