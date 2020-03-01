package com.lz.letterCombination.num2letters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberToLetters {
	/** A single digit maps to a list of letters, e.g.,3 is mapping d, e, f */
    private static List<List<String>> singleDigitLetters = new ArrayList<>();
    /** A double digit maps to a list of letters, e.g.,23 is mapping ad ae af bd be bf cd ce cf */
    private static Map<Integer, List<String>> doubleDigitLetters = new HashMap<>();

    /**
     * initialize the double digits mapping letter list
     */
    static {
        singleDigitLetters.add(null);
        singleDigitLetters.add(null);
        singleDigitLetters.add(Arrays.asList("a", "b", "c"));
        singleDigitLetters.add(Arrays.asList("d", "e", "f"));
        singleDigitLetters.add(Arrays.asList("g", "h", "i"));
        singleDigitLetters.add(Arrays.asList("j", "k", "l"));
        singleDigitLetters.add(Arrays.asList("m", "n", "o"));
        singleDigitLetters.add(Arrays.asList("p", "q", "r", "s"));
        singleDigitLetters.add(Arrays.asList("t", "u", "v"));
        singleDigitLetters.add(Arrays.asList("w", "x", "y", "z"));

        for (int i = 0; i < singleDigitLetters.size(); i++) {
            for (int j = 0; j < singleDigitLetters.size(); j++) {
                List<String> newList = new ArrayList<>();
                List<String> iList = singleDigitLetters.get(i);
                List<String> jList = singleDigitLetters.get(j);
                if (iList != null && jList != null){
                    for (String iString: iList){
                        for (String jString: jList){
                            newList.add(iString + jString);
                        }
                    }
                }else if (iList != null){
                    for (String iString: iList){
                        newList.add(iString);
                    }
                } else if (jList != null){
                    for (String jString: jList){
                        newList.add(jString);
                    }
                }
                doubleDigitLetters.put(i * 10 + j, newList);
            }
        }

    }

    /**
     * Print all possible letter combinations that the numbers could represent
     * @param numbers the integer array
     */
    public static void combine(int[] numbers){
        System.out.println(getCombinationString(numbers));
    }
    /**
     * Print all possible letter combinations that the numbers could represent
     * @param numbers the integer list
     */
    public static void combine(List<Integer> numList){
        System.out.println(getCombinationString(numList));
    }
    
    /**
     * Get all possible letter combinations that the numbers could represent
     * @param numbers the integer array
     * @return all possible letter combinations as a string
     */
    private static String getCombinationString(int[] numbers){
        List<String> combineList = getCombinations(numbers);
        StringBuilder sb = new StringBuilder();
        for (String com: combineList) {
        	if (sb.length() > 0) {
        		sb.append(" ").append(com);
			} else {
				sb.append(com);
			}
		}
        return sb.toString();
    }
    /**
     * Get all possible letter combinations that the numbers could represent
     * @param numbers the integer array
     * @return all possible letter combinations as a string
     */
    private static String getCombinationString(List<Integer> numList){
        List<String> combineList = getCombinations(numList);
        StringBuilder sb = new StringBuilder();
        for (String com: combineList) {
        	if (sb.length() > 0) {
        		sb.append(" ").append(com);
			} else {
				sb.append(com);
			}
		}
        return sb.toString();
    }
    /**
     * Get all possible letter combinations that the numbers could represent
     * @param numbers the integer array
     * @return all possible letter combinations
     */
    public static List<String> getCombinations(int[] numbers){
    	List<Integer> numList = new ArrayList<Integer>();
    	for (int i = 0; i < numbers.length; i++) {
			numList.add(numbers[i]);
		}
        return getCombinations(numList);
    }
    /**
     * Get all possible letter combinations that the numbers could represent
     * @param numbers the integer array
     * @return all possible letter combinations
     */
    public static List<String> getCombinations(List<Integer> numList){
        List<Integer> singleDigitList = getSingleDigitList(numList);
        List<Integer> doubleDigitList = getDoubleDigitList(singleDigitList);
        return getCombinationList(doubleDigitList);
    }

    /**
     * Get all possible letter combinations that the numbers could represent
     * @param numList the integer list
     * @return all possible letter combinations
     */
    private static List<String> getCombinationList(List<Integer> numList){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < numList.size(); i++) {
            List<String> letterList = doubleDigitLetters.get(numList.get(i));
            if (letterList == null){
                continue;
            }
            if (result.size() == 0){
                result.addAll(letterList);
            } else {
                List<String> tempList = new ArrayList<>();
                for (int j = 0; j < result.size(); j++) {
                    for (int k = 0; k < letterList.size(); k++) {
                        tempList.add(result.get(j) + letterList.get(k));
                    }
                }
                result = tempList;
            }
        }
        return result;
    }

    /**
     * Transfer the number in the array to a single digit list
     * @param numbers the integer array
     * @return a single digit list
     */
    private static List<Integer> getSingleDigitList(List<Integer> numList){
        List<Integer> result = new ArrayList<>(numList.size());
        for (int i = 0; i < numList.size(); i++) {
            int num = numList.get(i);
            // split the number to single digit if the number is larger than 9
            if (num > 9){
                List<Integer> tempList = new ArrayList<>();
                while (num > 0){
                    int temp = num % 10;
                    if (temp > 1){  // 0 and 1 do not map to any letters
                        tempList.add(temp);
                    }
                    num = num / 10;
                }
                Collections.reverse(tempList);
                result.addAll(tempList);
            } else if (num > 1){  // 0 and 1 do not map to any letters
                result.add(num);
            }
        }
        return result;
    }

    /**
     * Transfer the single digits to double digits
     * The more numbers input, the more efficient this method is, but up to 14 single digit
     * @param numbers the single digit list 
     * @return double digit list
     */
    private static List<Integer> getDoubleDigitList(List<Integer> numbers){
        List<Integer> result = new ArrayList<>();
        int size = numbers.size();
        for (int i = 0; i < size; i++) {
            int tens = numbers.get(i);
            if (i < size - 1){
                int units = numbers.get(++i);
                result.add(tens * 10 + units);
            } else {
                result.add(tens);
            }
        }
        return result;
    }
}

