package com.loa.tw.model;

import java.util.ArrayList;


public class IntergalacticNumerals extends ArabicNumerals {
	private static ArrayList<Rule> rules = new ArrayList<Rule>();
	
	static {
		init();
	}
	  
	public static void init(){
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	  
	  public IntergalacticNumerals(){
		  setReference(new RomanNumerals());
	  }

	@Override
	public ArrayList<Rule> getRules() {
		return rules;
	}
	@Override
	public boolean validate() {
		return true;
		
	}
	
	public String getValue(){
		StringBuffer sb = new StringBuffer();
		for(String symbol : getSymbols()) {
			sb.append(symbol +" ");
		}
		return sb.toString().trim();
	}
}
