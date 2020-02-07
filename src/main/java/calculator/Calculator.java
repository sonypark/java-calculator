package calculator;

import java.util.Arrays;
import java.util.List;

public class Calculator {
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE = "/";
	private static final String BLANK = " ";
	private static final int ONE = 1;
	private static final int ZERO = 0;

	static public String calculate(String expression) {
		List<String> tokens = Arrays.asList(expression.split(BLANK));
		checkIsValidExpression(tokens);

		double accumulatedNumber = Integer.parseInt(tokens.get(ZERO));
		for (int i = 1; i < tokens.size(); i += 2) {
			checkIsNumber(tokens.get(i + 1));
			accumulatedNumber = operate(accumulatedNumber, tokens.get(i), Integer.parseInt(tokens.get(i + 1)));
		}
		return Double.toString(accumulatedNumber);
	}

	private static double operate(double previousNumber, String operator, double nextNumber) {
		if (operator.equals(PLUS)) {
			return previousNumber + nextNumber;
		}
		if (operator.equals(MINUS)) {
			return previousNumber - nextNumber;
		}
		if (operator.equals(MULTIPLY)) {
			return previousNumber * nextNumber;
		}
		if (operator.equals(DIVIDE)) {
			checkDivideByZero(nextNumber);
			return previousNumber / nextNumber;
		}
		throw new IllegalArgumentException("올바른 연산자가 아닙니다.");
	}

	private static void checkIsNumber(String term) {
		if (!isNumber(term)) {
			throw new IllegalArgumentException("유효한 식이 아닙니다.");
		}
	}

	private static void checkIsValidExpression(List<String> tokens) {
		if (!isValidSize(tokens) || !isNumber(tokens.get(ZERO))) {
			throw new IllegalArgumentException("유효한 식이 아닙니다.");
		}
	}

	private static void checkDivideByZero(double number) {
		if (number == ZERO) {
			throw new ArithmeticException("0으로 나눌 수는 없습니다.");
		}
	}

	private static boolean isValidSize(List<String> tokens) {
		if ((tokens.size() & ONE) == ONE) {
			return true;
		}
		return false;
	}

	private static boolean isNumber(String term) {
		int firstIndex = ZERO;

		if (isFirstNumberMinus(term)) {
			firstIndex = ONE;
		}

		for (int i = firstIndex; i < term.length(); i++) {
			if (Character.isDigit(term.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	private static boolean isFirstNumberMinus(String term) {
		if (term.startsWith(MINUS) && term.length() > ONE) {
			return true;
		}
		return false;
	}
}
