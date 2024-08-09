package com.renanCompany.dioBanck.enums;

public enum AccountStatus {
	
	OPEN("The account is acctive"),
	CLOSED("The account is closed"),
	BLOQUED("The account is bloqued");
	
	private String description;
	AccountStatus(String description) {
		this.description = description;
		
	}
	
	public String getDescription() {
		return description;
	}
}
