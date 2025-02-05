package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable with additional bitwise operations
 */
public class Binary {
	private String number = "0";  // string containing the binary value '0' or '1'

	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored. Trailing zeros will be excluded and empty string will be considered as zero.
	 */
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0";
			return;
		}

		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0";
				return;
			}
		}

		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}

		this.number = (beg == number.length()) ? "0" : number.substring(beg);

		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}

	public String getValue() {
		return this.number;
	}

	public static Binary add(Binary num1, Binary num2) {
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		int carry = 0;
		String num3 = "";

		while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
			int sum = carry;
			if (ind1 >= 0) {
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
				ind1--;
			}
			if (ind2 >= 0) {
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
				ind2--;
			}
			carry = sum / 2;
			sum = sum % 2;
			num3 = ((sum == 0) ? "0" : "1") + num3;
		}

		return new Binary(num3);
	}

	/**
	 * Bitwise OR operation between two binary numbers.
	 */
	public static Binary or(Binary num1, Binary num2) {
		int maxLength = Math.max(num1.number.length(), num2.number.length());
		StringBuilder result = new StringBuilder();

		String n1 = String.format("%" + maxLength + "s", num1.number).replace(' ', '0');
		String n2 = String.format("%" + maxLength + "s", num2.number).replace(' ', '0');

		for (int i = 0; i < maxLength; i++) {
			result.append((n1.charAt(i) == '1' || n2.charAt(i) == '1') ? '1' : '0');
		}

		return new Binary(result.toString());
	}

	/**
	 * Bitwise AND operation between two binary numbers.
	 */
	public static Binary and(Binary num1, Binary num2) {
		StringBuilder result = new StringBuilder();
	
		// Pad the shorter number with leading zeros
		String n1 = num1.number;
		String n2 = num2.number;
		int lengthDifference = n1.length() - n2.length();
	
		if (lengthDifference > 0) {
			n2 = "0".repeat(lengthDifference) + n2;
		} else if (lengthDifference < 0) {
			n1 = "0".repeat(-lengthDifference) + n1;
		}
	
		// Perform bitwise AND
		for (int i = 0; i < n1.length(); i++) {
			result.append((n1.charAt(i) == '1' && n2.charAt(i) == '1') ? '1' : '0');
		}
	
		return new Binary(result.toString());
	}

	/**
	 * Multiply two binary numbers using repeated addition.
	 */
	public static Binary multiply(Binary num1, Binary num2) {
		Binary result = new Binary("0");
		for (int i = num2.number.length() - 1; i >= 0; i--) {
			if (num2.number.charAt(i) == '1') {
				String shifted = num1.number + "0".repeat(num2.number.length() - 1 - i);
				result = Binary.add(result, new Binary(shifted));
			}
		}
		return result;
	}
}
