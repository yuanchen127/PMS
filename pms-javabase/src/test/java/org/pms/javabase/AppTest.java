package org.pms.javabase;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Date;

/**
 * Unit test for simple App.
 */


public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
//		long start = new Date().getTime();
		System.out.println(tailfib(10, 1, 1));
//		long end = new Date().getTime();
//		System.out.println("time: " + (end - start));

//		long before = new Date().getTime();
//		System.out.println(fib(100));;
//		long after = new Date().getTime();
//		System.out.println("time: " + (after - before));
		
	}
	public boolean isEmpty(String s){
		if (s==null || s.length()==0 ||s.trim().length()==0){
			return true;
		}
		return false;
	}

	public static int tailfib(int n, int var1, int var2) {
		if (n < 2) {
			return 1;
		}
		return tailfib(n - 1, var2, var1 + var2);
	}

	public static int fib(int n) {
		if (n <= 2) {
			return 1;
		}
		return fib(n - 1) + fib(n - 2);
	}
}
