package com.filescanner.core;

import java.io.File;
import java.util.List;

public interface FileReader {
	/**
	 * 사용자의 C/D 드라이브 하위에 있는 항목들을 모두 Scan 해서 ms office, pdf 파일의 정보를 반환한다.
	 * @return
	 */
	public List<File> fileRead();
}
