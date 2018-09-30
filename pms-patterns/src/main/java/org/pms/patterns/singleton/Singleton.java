package org.pms.patterns.singleton;

/**
 * 单例模式
 * @author ike
 *
 */
public class Singleton {
	//使用volatile修饰static变量，避免dcl(double-checked loking)双重锁定失败
	private volatile static Singleton instance = null;
	private Singleton(){}
	
	public static Singleton getInstance(){
		if(instance==null) {
			instance = new Singleton();
		}
		return instance;
	}
	
	//内部内创建单例模式
	private static class SingletonInner{
		private static Singleton innerInstance =new Singleton();
	}
	public static Singleton getInstanceInner() {
		return SingletonInner.innerInstance;
	}
}
