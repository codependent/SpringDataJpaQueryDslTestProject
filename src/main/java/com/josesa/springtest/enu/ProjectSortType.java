package com.josesa.springtest.enu;

public enum ProjectSortType {

	NAME("name"),
	CREATION_DATE("creationDate");
	
	String type;
	
	private ProjectSortType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
