package com.filescanner.core;

import java.io.File;
import java.util.List;

public interface FileWriter {
	/**
	 * �о���� ������ ��ο� ������
	 * savePath : ���ϸ��� ������ ���� ���
	 * @return
	 */
	public boolean fileWrite(List<File> readFiles);
}
