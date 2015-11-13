package com.loa.tw.storage.file;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import com.loa.tw.exception.InvalidInputException;
import com.loa.tw.exception.InvalidNumberException;
import com.loa.tw.model.ArabicNumerals;
import com.loa.tw.model.Input;
import com.loa.tw.model.IntergalacticNumerals;
import com.loa.tw.model.Item;


public class InputParser {
	private RandomAccessFile rf ;
	public InputParser(String fileName) throws FileNotFoundException {
		rf = new RandomAccessFile(fileName, "rw");
	}
	
	public ArrayList<Input> parse() throws InvalidInputException  {
		ArrayList<Input> result = new ArrayList<Input>();
		try {
			String line = rf.readLine();
			while ( line != null ) {
				parse(result, line);
				line = rf.readLine();
			}
			if( result.size() > 0 ) {
				return result;
			}
		} catch (Exception e) {
			throw new InvalidInputException(e.getMessage());
		}
		throw new InvalidInputException("Empty Input file");
	}
	
	private void parse(ArrayList<Input> result, String line) {
		 try {
			 line = line.trim();
			 String[] words = line.split(" ");
			 Input input = new Input();
			 IntergalacticNumerals in = new IntergalacticNumerals();
			 // Conversion rule
			 if( words.length == 3 && words[1].equals("is")) {
				 ArabicNumerals.updateRule( input, words[0], words[2]);
				 in.getRules().add(input);
			 }
			 
			 // Evaluation rule
			 if( words.length > 3 && words[words.length-3].equals("is")) {
				 input.setType(Input.EVALUATION);
				 for(int i = 0 ; i < words.length-4; i++ ) {
					 in.getSymbols().add(words[i]);
				 }
				 try {
					// System.out.println(in.getValue());
			     		in.convert();
			 			in.getReference().convert();
				 } catch (InvalidNumberException e) {
					 e.printStackTrace();
			 	  }
				  ArabicNumerals.updateRule( input,in.getReference().getReference().getValue(), words[words.length-4],  words[words.length-2],  words[words.length-1]);
				  ArabicNumerals an = new ArabicNumerals();
				  an.getRules().add(input);
			 }
			 
			// query
			 if( words.length > 3 && words[0].equals("how") && words[words.length-1].equals("?")) {
				 input.setQuery(true);
				 in = new IntergalacticNumerals();
				 Item query = new Item();
				 query.setNumerals(in);
				 if( words[1].equals("much") ) {
					 //conversion
					 for(int i = 3 ; i < words.length-1; i++ ) {
						 in.getSymbols().add(words[i]);
					 }
				 } else if( words[1].equals("many") ) {
					 //evaluation
					 input.setType(Input.EVALUATION);
					 for(int i = 4 ; i < words.length-2	; i++ ) {
						 in.getSymbols().add(words[i]);
					 }
					 query.setUnitName(words[words.length - 2]);
					 Item queryResult = new Item();
					 queryResult.setUnitName(words[2]);
					 input.setValue(queryResult);
				 }
				 
				 input.setVariable(query);
			 }
			 if( input.getVariable() != null ) {
				 result.add(input);
			 }
		 } catch (Exception e ) {
			 e.printStackTrace();
		 }
	}
}
