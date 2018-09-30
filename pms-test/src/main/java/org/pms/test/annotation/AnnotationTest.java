package org.pms.test.annotation;

import org.junit.Test;

import junit.framework.TestCase;

public class AnnotationTest extends TestCase {

	@Test
	public void test() {
		ProviderAnnotationHandler.getInfo(Ike.class);
	}
}
