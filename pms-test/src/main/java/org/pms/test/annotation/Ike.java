package org.pms.test.annotation;

public class Ike {
	
	@Provider(id=1,age=24,name="ike")
	private String dsc;

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	

}
