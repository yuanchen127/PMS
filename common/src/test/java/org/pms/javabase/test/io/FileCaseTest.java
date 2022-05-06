package org.pms.javabase.test.io;

import org.junit.Test;
import org.pms.javabase.io.FileCase;

public class FileCaseTest {

	@Test
	public void test() {
		FileCase fileCase = new FileCase();
		fileCase.listFiles("c:\\");
	}

}
