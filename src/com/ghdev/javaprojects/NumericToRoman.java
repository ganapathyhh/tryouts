package com.ghdev.javaprojects;

public class NumericToRoman {
	// 0, 1, 2, 3, 4, 5, 6
	private static final String[] USEFUL_ROMANS = { "I", "V", "X", "L", "C", "D", "M" };

	/**
	 * @param num
	 * @return
	 */
	private String convertNumericToRoman(int num) {
		if (num < 1) {
			return "Cannot convert " + num + " to Roman!";
		}

		StringBuilder stringBuilder = new StringBuilder();
		String numStr = String.valueOf(num);
		int numLen = numStr.length();

		for (int pos = 0; pos < numLen; pos++) {
			if (numStr.charAt(pos) == '0') {
				continue;
			}
			num = Integer.parseInt(numStr.substring(pos));
			stringBuilder.append(conditionalNumToRoman(num, numLen - pos));
		}

		return stringBuilder.toString();
	}

	/**
	 * @param num
	 * @return
	 */
	private String conditionalNumToRoman(int num, int pow) {
		StringBuilder tmpBuilder = new StringBuilder();
		if (num >= 1000) {
			tmpBuilder.append(replaceToRomans((int) (num / Math.pow(10, pow - 1)), USEFUL_ROMANS[6], null, null));
		} else if (num >= 100) {
			tmpBuilder.append(replaceToRomans(num / (int) Math.pow(10, pow - 1), USEFUL_ROMANS[4], USEFUL_ROMANS[5],
					USEFUL_ROMANS[6]));
		} else if (num >= 10) {
			tmpBuilder.append(replaceToRomans(num / 10, USEFUL_ROMANS[2], USEFUL_ROMANS[3], USEFUL_ROMANS[4]));
		} else {
			tmpBuilder.append(replaceToRomans(num, USEFUL_ROMANS[0], USEFUL_ROMANS[1], USEFUL_ROMANS[2]));
		}

		return tmpBuilder.toString();
	}

	/**
	 * @param num
	 * @param romanStringPrevSeries
	 * @param romanStringCurrSeries
	 * @param romanStringNextSeries
	 * @return
	 */
	private String replaceToRomans(int num, String romanStringPrevSeries, String romanStringCurrSeries,
			String romanStringNextSeries) {
		StringBuilder tmpBuilder = new StringBuilder();

		if (num == 4) {
			tmpBuilder.append(romanStringPrevSeries);
			tmpBuilder.append(romanStringCurrSeries);
		} else if (num == 9) {
			tmpBuilder.append(romanStringPrevSeries);
			tmpBuilder.append(romanStringNextSeries);
		} else {
			if (num >= 5) {
				tmpBuilder.append(romanStringCurrSeries);
			}

			num = num % 5;
			for (int pos = 0; pos < num; pos++) {
				tmpBuilder.append(romanStringPrevSeries);
			}
		}

		return tmpBuilder.toString();
	}

	public static void main(String[] args) {
		// I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
		NumericToRoman numToRoman = new NumericToRoman();
		int itr = 3;
//		for (int itr = 1; itr < 200; itr++) {
		String output = numToRoman.convertNumericToRoman(itr);
		System.out.println(itr + " - " + output);
//		}
	}
}
