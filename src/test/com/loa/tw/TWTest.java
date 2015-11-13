package test.com.loa.tw;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.loa.tw.model.ArabicNumerals;
import com.loa.tw.model.Input;
import com.loa.tw.model.IntergalacticNumerals;
import com.loa.tw.exception.InvalidNumberException;
import com.loa.tw.model.RomanNumerals;
import com.loa.tw.storage.file.InputParser;

public class TWTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testRomanNumerals() {
		RomanNumerals rn = new RomanNumerals();
		rn.getSymbols().add("X");
		rn.getSymbols().add("X");
		rn.getSymbols().add("X");
		rn.getSymbols().add("I");
		rn.getSymbols().add("X");
		try {
			rn.convert();
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
		assertEquals("39", rn.getReference().getValue());
	}

	@Test
	public void testIntergalacticNumerals() {
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

	@Test
	public void testSilverIntergalacticNumerals() {
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
		in = new IntergalacticNumerals();
		in.getSymbols().add("glob");
		in.getSymbols().add("prok");
		try {
			in.convert();
			in.getReference().convert();
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
		ArabicNumerals.addRule(an.getRules(), in.getReference().getReference()
				.getValue(), "Gold", "57800", "Credits");
		in = new IntergalacticNumerals();
		in.getSymbols().add("glob");
		in.getSymbols().add("prok");
		try {
			in.convert();
			in.getReference().convert();
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
		an.setSymbols(in.getReference().getReference().getSymbols());
		try {
			assertEquals(68, (int) an.evaluate("Credits", "Silver"));
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGoldIntergalacticNumerals() {
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

		in = new IntergalacticNumerals();
		in.getSymbols().add("glob");
		in.getSymbols().add("prok");
		// in.getSymbols().add("pish");
		// in.getSymbols().add("pish");
		try {
			in.convert();

			in.getReference().convert();
		} catch (InvalidNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArabicNumerals.addRule(an.getRules(), in.getReference().getReference()
				.getValue(), "Gold", "57800", "Credits");

		in = new IntergalacticNumerals();
		in.getSymbols().add("glob");
		in.getSymbols().add("prok");
		try {
			in.convert();

			in.getReference().convert();
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
		an.setSymbols(in.getReference().getReference().getSymbols());
		try {
			assertEquals(57800, (int) an.evaluate("Credits", "Gold"));
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIronIntergalacticNumerals() {
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
		in = new IntergalacticNumerals();
		in.getSymbols().add("glob");
		in.getSymbols().add("prok");
		try {
			in.convert();
			in.getReference().convert();
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
		ArabicNumerals.addRule(an.getRules(), in.getReference().getReference()
				.getValue(), "Gold", "57800", "Credits");
		in = new IntergalacticNumerals();
		in.getSymbols().add("pish");
		in.getSymbols().add("pish");
		try {
			in.convert();
			in.getReference().convert();
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
		ArabicNumerals.addRule(an.getRules(), in.getReference().getReference()
				.getValue(), "Iron", "3910", "Credits");
		in = new IntergalacticNumerals();
		in.getSymbols().add("glob");
		in.getSymbols().add("prok");
		try {
			in.convert();
			in.getReference().convert();
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
		an.setSymbols(in.getReference().getReference().getSymbols());
		try {
			assertEquals(782, (int) an.evaluate("Credits", "Iron"));
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIronExceptionIntergalacticNumerals() {
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
		in = new IntergalacticNumerals();
		in.getSymbols().add("glob");
		in.getSymbols().add("prok");
		try {
			in.convert();
			in.getReference().convert();
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
		ArabicNumerals.addRule(an.getRules(), in.getReference().getReference()
				.getValue(), "Gold", "57800", "Credits");
		in = new IntergalacticNumerals();
		in.getSymbols().add("glob");
		in.getSymbols().add("prok");
		try {
			in.convert();
			in.getReference().convert();
		} catch (InvalidNumberException e) {
			e.printStackTrace();
		}
		an.setSymbols(in.getReference().getReference().getSymbols());
		try {
			an.evaluate("Credits", "Iron");
		} catch (InvalidNumberException e) {
			assertEquals(
					"No reference found for base unit : Iron with resulting unit : Credits",
					e.getMessage());
		}
	}

	@Test
	public void testInputFileIntergalacticNumerals() {
		StringBuffer sb = new StringBuffer();
		try {
			InputParser parser = new InputParser("data/input_1.txt");
			ArrayList<Input> inputs = parser.parse();
			ArabicNumerals an = new ArabicNumerals();
			Input tempinput = null;
			int size = inputs.size();
			for (int i = 0; i < size; i++) {
				tempinput = inputs.get(i);
				if (tempinput.isQuery()) {
					try {
						((ArabicNumerals) tempinput.getVariable().getNumerals())
								.convert();
						((ArabicNumerals) tempinput.getVariable().getNumerals())
								.getReference().convert();
						if (tempinput.getType() == Input.CONVERTION) {
							sb.append(tempinput.getVariable().getNumerals()
									.getValue()
									+ " is "
									+ ((ArabicNumerals) tempinput.getVariable()
											.getNumerals()).getReference()
											.getReference().getValue() + "\r");
						} else {
							an.setSymbols(((ArabicNumerals) tempinput
									.getVariable().getNumerals())
									.getReference().getReference().getSymbols());
							sb.append(tempinput.getVariable().getNumerals()
									.getValue()
									+ " "
									+ tempinput.getVariable().getUnitName()
									+ " is "
									+ (int) an.evaluate(tempinput.getValue()
											.getUnitName(), tempinput
											.getVariable().getUnitName())
									+ " "
									+ tempinput.getValue().getUnitName() + "\r");
						}
					} catch (InvalidNumberException e) {
						sb.append("I have no idea what you are talking about, on input line with text \""
								+ tempinput.getVariable().getNumerals()
										.getValue() + "\"");
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(
				"pish tegj glob glob is 42\r"
						+ "glob prok Silver is 68 Credits\r"
						+ "glob prok Gold is 57800 Credits\r"
						+ "glob prok Iron is 782 Credits\r"
						+ "I have no idea what you are talking about, on input line with text \"could a woodchuck chuck if a woodchuck could chuck wood\"",
				sb.toString());
	}

}
