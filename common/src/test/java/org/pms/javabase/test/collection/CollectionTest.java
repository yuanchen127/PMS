package org.pms.javabase.test.collection;

import org.junit.jupiter.api.Test;
import org.pms.javabase.collection.Case3;

/**
 * collection练习测试
 * @author ike
 *
 */
public class CollectionTest {
	
	@Test
	public void test() {
		case3Test();
	}
	
	//测试case3类
	public static void case3Test() {
		String[] videos=new String[] {"a","b",null};
		Case3 case3 = new Case3();
		case3.setArr(videos);
		System.out.println(case3.next());
		System.out.println(case3.next());
		System.out.println(case3.next());
		System.out.println(case3.next());
		System.out.println(case3.next());
	}

}
