package com.filescanner.core;

import java.io.File;
import java.util.List;

public interface FileReader {
	/**
	 * ������� C/D ����̺� ������ �ִ� �׸���� ��� Scan �ؼ� ms office, pdf ������ ������ ��ȯ�Ѵ�.
	 * @return
	 */
	public List<File> fileRead();
}
