package com.loa.tw.model;

import java.util.ArrayList;

import com.loa.tw.exception.InvalidNumberException;

public interface IConvertor {
	//To validate if the given Numerals is a valid one
	public boolean validate();
	
	//To convert the current Numberals object and update the equivalent value of it's reference Numerals
	public void convert() throws InvalidNumberException;

	//To evaluate the current Numberals object's value based on the predefined rules in the current Numerals
	public float evaluate(String resultUnit, String baseUnit) throws InvalidNumberException;
	
	//To return the rules of the current Numberals
	public ArrayList<Rule> getRules();
}
