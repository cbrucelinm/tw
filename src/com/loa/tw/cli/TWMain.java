package com.loa.tw.cli;



import java.util.ArrayList;

import com.loa.tw.exception.InvalidNumberException;
import com.loa.tw.model.ArabicNumerals;
import com.loa.tw.model.Input;
import com.loa.tw.storage.file.InputParser;

public class TWMain {
	public static void main(String[] args) {
		try {
			if( args.length < 1 ) {
				usage("No input file found");
			}
			InputParser parser = new InputParser(args[0]);
			ArrayList<Input> inputs = parser.parse();
			ArabicNumerals an = new ArabicNumerals();
			Input tempinput = null;
			int size = inputs.size();
			for(int i = 0 ; i < size; i++ ) {
				tempinput = inputs.get(i);
				if(tempinput.isQuery()) {
					try {
						((ArabicNumerals) tempinput.getVariable().getNumerals()).convert();
						((ArabicNumerals) tempinput.getVariable().getNumerals()).getReference().convert();
						if( tempinput.getType() == Input.CONVERTION) {
							System.out.println(  tempinput.getVariable().getNumerals().getValue()  + " is " + ((ArabicNumerals) tempinput.getVariable().getNumerals()).getReference().getReference().getValue());
						} else {
							 an.setSymbols(((ArabicNumerals) tempinput.getVariable().getNumerals()).getReference().getReference().getSymbols());
					        System.out.println(  tempinput.getVariable().getNumerals().getValue()  + " " +   tempinput.getVariable().getUnitName() + " is " +  (int)an.evaluate(tempinput.getValue().getUnitName(), tempinput.getVariable().getUnitName()) + " " + tempinput.getValue().getUnitName() );
						}
					} catch (InvalidNumberException e) {
						System.out.println("I have no idea what you are talking about, on input line with text \"" + tempinput.getVariable().getNumerals().getValue() + "\"");
						continue;
					}
				}
			}
			
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}
	
	public static void usage(String msg) {
		System.out.println(msg);
		System.out.println("Usage : TWMain <input_file_name>" );
	}
}
