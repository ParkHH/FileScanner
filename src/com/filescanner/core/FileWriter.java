package com.filescanner.core;

import java.io.File;
import java.util.List;

public interface FileWriter {
	/**
	 * 읽어들인 파일을 경로에 저장함
	 * savePath : 파일명을 제외한 순수 경로
	 * @return
	 */
	public boolean fileWrite(List<File> readFiles);
}
