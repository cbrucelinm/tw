package com.loa.tw.model;

import java.util.ArrayList;

public class Numerals {
	private String name = "Numerals";
	private ArrayList<String> symbols = new ArrayList<String>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getSymbols() {
		return symbols;
	}
	public void setSymbols(ArrayList<String> symbols) {
		this.symbols = symbols;
	}
 
	public String getValue(){
		StringBuffer sb = new StringBuffer();
		for(String symbol : getSymbols()) {
			sb.append(symbol);
		}
		return sb.toString();
	}
}
