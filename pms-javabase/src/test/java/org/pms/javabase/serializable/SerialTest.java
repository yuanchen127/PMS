package org.pms.javabase.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class SerialTest {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		String filePath = "person.txt";
		serialTest(filePath);
		deSerialTest(filePath);
		System.out.println(this.getClass().getClassLoader().getResource(""));
	}
	
	public void serialTest(String filePath) throws IOException{
		Person person = new Person();
		person.setId(1);
		person.setName("ike");
		person.setAge(24);
		FileOutputStream fos = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(person);
		oos.flush();
		oos.close();
		System.out.println("person 对象序列化完成!");
	}
	
	public void deSerialTest(String filePath) throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream(filePath);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Person person = (Person)ois.readObject();
		System.out.println(person.toString());
		ois.close();
	}
}
