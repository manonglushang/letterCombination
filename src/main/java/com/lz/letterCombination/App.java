package com.lz.letterCombination;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lz.letterCombination.num2letters.NumberToLetters;



/**
 * Program entrance
 *
 */
public class App {
    public static void main( String[] args ) {
    	// Read the input from console
    	Scanner input = new Scanner(System.in);
        String line = null;
        System.out.println( "Please Enter integers to get possible combinations,e.g., 2 3 7,\r\n"
        		+ "(enter 00 to exit this program):" );
        while (true) {
        	
			line = input.nextLine();
			if (line != null && line.trim().length() > 0) {
				if ("00".equals(line)) {
					break;
				}
				Pattern pattern = Pattern.compile("^[ 0-9]+$");
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					List<Integer> numList = new ArrayList<Integer>();
					String[] splitStrings = line.split("\\s+");
					for (int i = 0; i < splitStrings.length; i++) {
						String split = splitStrings[i];
						if (split.length() > 9) {
							for (int j = 0; j < split.length(); j++) {
								numList.add(split.charAt(j) - '0');
							}
						} else if (split.length() > 0) {
							numList.add(Integer.parseInt(split));
						}
					}
					NumberToLetters.combine(numList);
				} else {
					System.out.println("Only integer accept!");
				}
			}
		}
        input.close();
    }
}
