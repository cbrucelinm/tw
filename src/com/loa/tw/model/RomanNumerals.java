package com.loa.tw.model;

import java.util.ArrayList;


public class RomanNumerals extends ArabicNumerals {
	private static ArrayList<Rule> rules = new ArrayList<Rule>();
	
	static {
		init();
	}
	  
	public static void init(){
		try {
			addRule(rules, "I", "1");
			addRule(rules, "V", "5");
			addRule(rules, "X", "10");
			addRule(rules, "L", "50");
			addRule(rules, "C", "100");
			addRule(rules, "D", "500");
			addRule(rules, "M", "1000");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

 
	  
	  public RomanNumerals(){
		  setReference(new ArabicNumerals());
	  }

	@Override
	public ArrayList<Rule> getRules() {
		return rules;
	}
	
	@Override
	public boolean validate() {
		try {
			ArrayList<Integer> symbolsInNumber = new ArrayList<Integer>();
			for(String symbol : getSymbols()) {
				String currentStr = getValue(symbol).getNumerals().getSymbols().get(0);
				int current = Integer.parseInt(currentStr);
				if( current < 0 ) {
					return false;
				}
				symbolsInNumber.add(current);
			}
			int size = getSymbols().size();
			for(int i = 0 ; i < size ; i++) {
				//"I", "X", "C", and "M" can be repeated three times in succession, others not allowed
				if(getSymbols().get(i).matches("[IXCM]") && i+3 < size && (getSymbols().get(i).equals(getSymbols().get(i+1)) && getSymbols().get(i).equals(getSymbols().get(i+2)) && getSymbols().get(i).equals(getSymbols().get(i+3)) )) {
					 return false;
				}
				if(getSymbols().get(i).matches("[DLV]") && i+1 < size && (getSymbols().get(i).equals(getSymbols().get(i+1))  )) {
				 return false;
				}
				if(!getSymbols().get(i).matches("[IXCMDLV]")) {
				 return false;
				}
				//"I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only. "C" can be subtracted from "D" and "M" only. "V", "L", and "D" can never be subtracted.
				if(getSymbols().get(i).matches("[I]") && i+1 < size && symbolsInNumber.get(i) < symbolsInNumber.get(i+1) && !(getSymbols().get(i+1).equals("V") || getSymbols().get(i+1).equals("X")  )) {
					return false;
				}
				if(getSymbols().get(i).matches("[X]") && i+1 < size && symbolsInNumber.get(i) < symbolsInNumber.get(i+1) && !(getSymbols().get(i+1).equals("L") || getSymbols().get(i+1).equals("C")  )) {
					return false;
				}
				if(getSymbols().get(i).matches("[C]") && i+1 < size && symbolsInNumber.get(i) < symbolsInNumber.get(i+1) && !(getSymbols().get(i+1).equals("D") || getSymbols().get(i+1).equals("M")  )) {
					return false;
				}
				if(getSymbols().get(i).matches("[VLD]") && i+1 < size && symbolsInNumber.get(i) < symbolsInNumber.get(i+1) ) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
