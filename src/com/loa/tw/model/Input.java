package com.loa.tw.model;

public class Input extends Rule {
	public static final int CONVERTION = 1;
	public static final int EVALUATION = 2;
	private int type = CONVERTION;
	private boolean isQuery = false;
	
	public boolean isQuery() {
		return isQuery;
	}
	public void setQuery(boolean isQuery) {
		this.isQuery = isQuery;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
