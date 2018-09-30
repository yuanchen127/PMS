package org.pms.es.index;

import java.util.Date;

public class Index {
	private String name;
	private Date date;

	public Index() {
	}

	public Index(String name) {
		this.name = name;
	}

	public Index(String name, Date date) {
		this.name = name;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
