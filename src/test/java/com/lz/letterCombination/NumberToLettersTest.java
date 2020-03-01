package com.lz.letterCombination;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.lz.letterCombination.num2letters.NumberToLetters;

import junit.framework.TestCase;

public class NumberToLettersTest extends TestCase {
	private String separator = System.getProperty("line.separator");

	@Test
	public void testCombinations() throws Exception {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream out = System.out;
		System.setOut(new PrintStream(bytes));
		int[] testArr = new int[] { 2, 3 };
		String expected = "ad ae af bd be bf cd ce cf" + separator;
		NumberToLetters.combine(testArr);
		String actual = bytes.toString();
		assertEquals(expected, actual);
		System.setOut(out);
		
		bytes = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bytes));
		testArr = new int[] {0};
		expected = "" + separator;
		NumberToLetters.combine(testArr);
		actual = bytes.toString();
		assertEquals(expected, actual);
		
		bytes = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bytes));
		testArr = new int[] {0, 1, 2, 3, 1};
		expected = "ad ae af bd be bf cd ce cf" + separator;
		NumberToLetters.combine(testArr);
		actual = bytes.toString();
		assertEquals(expected, actual);
		
		bytes = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bytes));
		testArr = new int[] {7, 8, 5};
		expected = "ptj ptk ptl puj puk pul pvj pvk pvl qtj qtk qtl quj quk qul "
				+ "qvj qvk qvl rtj rtk rtl ruj ruk rul rvj rvk rvl stj stk stl "
				+ "suj suk sul svj svk svl" + separator;
		NumberToLetters.combine(testArr);
		actual = bytes.toString();
		assertEquals(expected, actual);
	}
}
