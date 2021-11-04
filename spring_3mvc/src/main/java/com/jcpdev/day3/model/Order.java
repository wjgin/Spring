package com.jcpdev.day3.model;

import java.sql.Date;

public class Order {
	private String id;
	private int amount;
	private Date devDate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getDevDate() {
		return devDate;
	}
	public void setDevDate(Date devDate) {
		this.devDate = devDate;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", amount=" + amount + ", devDate=" + devDate + "]";
	}
}
