package com.loa.tw.model;

import java.util.ArrayList;

import com.loa.tw.exception.InvalidNumberException;

public class ArabicNumerals extends Numerals  implements IConvertor {
	private static ArrayList<Rule> rules = new ArrayList<Rule>();
	private ArabicNumerals reference;
	public ArabicNumerals getReference() {
		return reference;
	}
	public void setReference(ArabicNumerals reference) {
		this.reference = reference;
	}
	
	@Override
	public void convert() throws InvalidNumberException {
		if ( !validate() )  throw new InvalidNumberException("Invalid number : " + getValue());
		for(String symbol : getSymbols()) {
			String current = getValue(symbol).getNumerals().getSymbols().get(0);
			reference.getSymbols().add(current);
		}
		if( !(reference instanceof RomanNumerals || reference instanceof IntergalacticNumerals)) {
			int result = 0;
			int previous = -1;
			for(String symbol : reference.getSymbols()) {
				int current = Integer.parseInt(symbol);
				if( previous != -1 && previous < current ) {
					result -= previous;
					result += (current - previous );
				} else {
					result += current;
				}
				previous = current;
			}	
			reference.getSymbols().clear();
			reference.getSymbols().add(""+result);
		}
	}
	
	public ArrayList<Rule> getRules() {
		return rules;
	}
	
	
	
	public Item getValue(String symbol) throws InvalidNumberException {
		for(Rule rule : getRules()) {
			if(rule.getVariable().getNumerals().getSymbols().get(0).equals(symbol)) {
				return rule.getValue();
			}
		}
		throw new InvalidNumberException("No mapping found for symbol : " + symbol + " on input : " + getValue());
	}
	@Override
	public boolean validate() {
		try {
			for(String symbol : getSymbols()) {
				int current = Integer.parseInt(symbol);
				if( current < 0 ) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	  public static void addRule(ArrayList<Rule> rules, String variableStr, String valueStr){
		  	Rule rule = new Rule();
		  	updateRule(rule,variableStr, valueStr);
			rules.add(rule);
	  }
	  
 
	  
	  public static void updateRule( Rule  rule, String variableStr, String valueStr){
			Item variable = new Item();
			Numerals rn = new Numerals();
			rn.getSymbols().add(variableStr);
			variable.setNumerals(rn);
			Item value = new Item();
			Numerals rn1 = new Numerals();
			rn1.getSymbols().add(valueStr);
			value.setNumerals(rn1);
			rule.setVariable(variable);
			rule.setValue(value);
	  }
	  
	  public static void addRule(ArrayList<Rule> rules, String variableStr, String variableUnit, String valueStr, String valueUnit){
		  Rule rule = new Rule();
		  	updateRule(rule,variableStr, variableUnit, valueStr , valueUnit);
			rules.add(rule);
	  }
	  
	  public static void updateRule( Rule rule, String variableStr, String variableUnit, String valueStr, String valueUnit){
		  	 Item variable = new Item();
			Numerals rn = new Numerals();
			rn.getSymbols().add(variableStr);
			variable.setNumerals(rn);
			variable.setUnitName(variableUnit);
			Item value = new Item();
			Numerals rn1 = new Numerals();
			rn1.getSymbols().add(valueStr);
			value.setNumerals(rn1);
			value.setUnitName(valueUnit);
			rule.setVariable(variable);
			rule.setValue(value);
	  }
	  
	@Override
	public float evaluate(String resultUnit, String baseUnit) throws InvalidNumberException {
			 if( !validate()) throw new InvalidNumberException("Invalid number : " + getValue());
			 Rule rule = getBaseRule(resultUnit, baseUnit);
			 if( rule == null) throw new InvalidNumberException("No reference found for base unit : " + baseUnit + " with resulting unit : " + resultUnit );
		 try {
			 return Float.parseFloat(getValue())*Integer.parseInt(rule.getValue().getNumerals().getValue())/Integer.parseInt(rule.getVariable().getNumerals().getValue());
		 } catch (Exception e ){
			 e.printStackTrace();
			 throw new InvalidNumberException("Unable to evaluate : " + getValue() + " with rules " + getRules());
		 }
	}
	
	private Rule getBaseRule(String resultUnit, String baseUnit) {
		for(Rule rule : getRules()) {
			if(rule.getVariable().getUnitName().equals(baseUnit) && rule.getValue().getUnitName().equals(resultUnit)) {
				return rule;
			}
		}
		return null;
	}
	  
}
