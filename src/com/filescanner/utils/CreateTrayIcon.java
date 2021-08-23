package com.filescanner.utils;

import java.awt.Toolkit;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CreateTrayIcon {
	
	/**
	 * TrayIcon 을 생성합니다.
	 */
	public void createTrayIcon() {
		URL url = this.getClass().getClassLoader().getResource("searchIcon.png");
		//System.out.println(url);
		TrayIconHandler.registerTrayIcon(
				Toolkit.getDefaultToolkit().getImage(url)
				,"FileScanner For DocumentManagement to OneDrive",
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Open your application here.
					}
				}
			);
			TrayIconHandler.addItem("Exit", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			TrayIconHandler.displayMessage("HHsoft", "Benefit the myself!", MessageType.INFO);		
	}
}
