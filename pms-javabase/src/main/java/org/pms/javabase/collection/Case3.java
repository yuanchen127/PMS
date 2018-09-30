package org.pms.javabase.collection;

/**
 * 
 * @author ike
 *创建一个生成器类，在每次调用其next()方法是，产生由你最喜欢的敌营的字符构成的名字，在字符列表的电影名用完之后，循环到这个字符列表的开始出
 */
public class Case3 {
	private static int index=0;
	private String[] arr;
	public String[] getArr() {
		return arr;
	}
	public void setArr(String[] arr) {
		this.arr = arr;
	}

	public String next() {
		String s = null;
		if(arr==null || arr.length<=0) {
			return null;
		}
		if(index>arr.length-1) {
			index=0;
		}
		s = (String)arr[index];
		index++;
		return s;
	}
}
