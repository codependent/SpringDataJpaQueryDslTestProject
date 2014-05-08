package com.josesa.springtest.test.dto;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class SortBean {

	private List<Order> orders;
	private Direction direction;
	private String property;
	private boolean ignoreCase;
	private boolean ascending;
	
	public SortBean(){}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public String getProperty() {
		return property;
	}
	
	public void setProperty(String property) {
		this.property = property;
	}
	
	public boolean isIgnoreCase() {
		return ignoreCase;
	}
	
	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public boolean isAscending() {
		return ascending;
	}
	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
}


