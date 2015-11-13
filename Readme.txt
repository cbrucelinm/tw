Readme for "Problem One: Merchant's Guide to the Galaxy"

I) Instructions to run the application
---------------------------------------
 1) Extract the zip file MerchantGuide.zip into a folder. ( source code git location athttps://github.com/cbrucelinm/tw.git)
 2) Create a Java Project in eclipse IDE ( with Java 1.8)
 3) Import all files from extracted folder into the eclipse java project
 4) To run from JUnit Test on eclipse, right click on file src/test/com/loa/tw/TWTest.java ->Run As -> JUnit Test
 5) To run a java main program for testing, right click on file src/com/loa/tw/cli/TWMain.java ->Run As -> Java Application ( output will be shown on eclipse console)
 6) To build a jar using ant,  right click on file build.xml ->Run As -> Ant Build, a new jar file with the current date will be created ( example dist/TW-20151113.jar (2015 Nov 13th build)), JUnit test results will be in test_results folder.
 7) Copy the new jar file into a computer with java installed and run the following command line to verify the result.
    D:\LOA\projects\thoughtwork>java -jar TW-20151113.jar D:\LOA\luna1-j2ee\MerchantGuide\data\input_1.txt
 8) Sample input file is in eclipse data/input_1.txt
 
II) High level Design:
------------------------- 
  Package Summary:
  -------------------
	com.loa.tw.model - for all model objects
	com.loa.tw.exception - for all application specific exception classes
	com.loa.tw.cli - for all main programs and command line interfaces
	com.loa.tw.storage.file - for all file operations including parsing input file
	
  Objects:
  -------------
  Rule object will store one condition including a variable and a value( example : "V", "5" or "tegj", "L" or "2 Silver" , "34 Credits")
  Input Object extends Rule object with additional property "type" and "isQuery", each line in input file will be represented in Input object, each question with isQuery property set to true and type with "CONVESION" for conversions("how much is pish tegj glob glob ?") or type with "EVLUATION" for evaluations("how many Credits is glob prok Iron ?"), Similarly each mapping with isQuery property set to false and type with "CONVESION" for mapping/rules(tegj is L) or type with "EVLUATION" for evaluation rules(2 Silver is 34 Credits).
  
  There are three Numerals objects each with a reference object to another Numberals object to which a conversion would be needed. They are     
  1) ArabicNumberals with a reference object to another ArabicNumberals object
  2) RomanNumerals with a reference object to a ArabicNumberals object
  3) IntergalacticNumerals with a reference object to a RomanNumerals object
  Each of these class implements the folowing methods of IConvertor interface
  
  	//To validate if the given Numerals is a valid one
	public boolean validate();
	
	//To convert the current Numberals object and update the equivalent value of it's reference Numerals
	public void convert() throws InvalidNumberException;

	//To evaluate the current Numberals object's value based on the predefined rules in the current Numerals
	public float evaluate(String resultUnit, String baseUnit) throws InvalidNumberException;
	
	//To return the rules of the current Numberals
	public ArrayList<Rule> getRules();
  
  All predefined rules will be added to the corresponding Numberals as shown below.
  For RomanNumerals, the following static rules are added by default
	addRule(rules, "I", "1");
	addRule(rules, "V", "5");
	addRule(rules, "X", "10");
	addRule(rules, "L", "50");
	addRule(rules, "C", "100");
	addRule(rules, "D", "500");
	addRule(rules, "M", "1000");
	
  For IntergalacticNumerals, the following dynamic input rules are added as needed
	IntergalacticNumerals in = new IntergalacticNumerals();
	IntergalacticNumerals.addRule(in.getRules(), "glob", "I");
	IntergalacticNumerals.addRule(in.getRules(), "prok", "V");
	IntergalacticNumerals.addRule(in.getRules(), "pish", "X");
	IntergalacticNumerals.addRule(in.getRules(), "tegj", "L");
	in.getSymbols().add("glob");
	in.getSymbols().add("glob");
	try {
		in.convert();
		in.getReference().convert();
	} catch (InvalidNumberException e) {
		e.printStackTrace();
	}
	ArabicNumerals an = new ArabicNumerals();
	ArabicNumerals.addRule(an.getRules(), in.getReference().getReference()
				.getValue(), "Silver", "34", "Credits");
	
  Example conversion:
   To convert IntergalacticNumerals to ArabicNumerals, IntergalacticNumerals's convert method will be called which will fill it's reference object RomanNumerals with it's equivalent value. Now the reference object's ( RomanNumerals) convert method will be called which will fill it's reference object ArabicNumerals with it's equivalent value, Now print the ArabicNumerals reference object will return the value in ArabicNumerals format, the following sample code shows a sample conversion.
   
		IntergalacticNumerals in = new IntergalacticNumerals();
		IntergalacticNumerals.addRule(in.getRules(), "glob", "I");
		IntergalacticNumerals.addRule(in.getRules(), "prok", "V");
		IntergalacticNumerals.addRule(in.getRules(), "pish", "X");
		IntergalacticNumerals.addRule(in.getRules(), "tegj", "L");
		in.getSymbols().add("tegj");
		in.getSymbols().add("pish");
		in.getSymbols().add("pish");
		in.getSymbols().add("pish");
		try {
			in.convert();
			in.getReference().convert();
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
		assertEquals("80", in.getReference().getReference().getValue());
		}
	
   Example evaluation:
      All query which starts with "how many" and ends with "?" are considered evaluation, and after converting the value from IntergalacticNumerals to ArabicNumerals, evaluate method will be called to get the result as shown below.
	  
	    in = new IntergalacticNumerals();
		in.getSymbols().add("glob");
		in.getSymbols().add("prok");
		try {
			in.convert();
			in.getReference().convert();
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
		
		ArabicNumerals an = new ArabicNumerals();
		an.setSymbols(in.getReference().getReference().getSymbols());
		try {
			assertEquals(68, (int) an.evaluate("Credits", "Silver"));
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
   
		
  
