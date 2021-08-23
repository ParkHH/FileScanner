package com.filescanner.application;

import com.filescanner.core.RunThread;
import com.filescanner.utils.CreateTrayIcon;

public class FileScannerApplication {
	public static StringBuilder logText = new StringBuilder();	
	
	public static void main(String[] args) {		
		CreateTrayIcon trayIcon = new CreateTrayIcon();
		trayIcon.createTrayIcon();
		RunThread runThread = new RunThread();
		Thread t = new Thread(runThread, "FileScanner Run by Thread");			
		t.start();				
	}
}
