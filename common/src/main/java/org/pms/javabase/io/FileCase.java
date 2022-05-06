package org.pms.javabase.io;

import java.io.File;

public class FileCase {
	public void listFiles(String dirPath) {
		File dir = new File(dirPath);
		for(File file:dir.listFiles()) {
			System.out.println(file.getName());
			
		}
	}
}
