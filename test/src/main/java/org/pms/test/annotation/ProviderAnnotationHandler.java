package org.pms.test.annotation;

import java.lang.reflect.Field;

public class ProviderAnnotationHandler {
	
	public static void getInfo(Class<?> clazz) {
		String strProvider = " 供应商：";
		
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field:fields) {
			if(field.isAnnotationPresent(Provider.class)) {
				Provider provider = (Provider)field.getAnnotation(Provider.class);
				strProvider  = "供应商编号："+provider.id() +" 供应商姓名：" +provider.name() + " 供应商年限：" + provider.age();
				System.out.println(strProvider);
			}
		}
	}

}
