package utils;

import java.util.List;

import domain.Operator;

public class Validator {
	private static final int ONE = 1;
	private static final int ZERO = 0;

	public static void checkIsBlank(String input) {
		if (input.isEmpty()) {
			throw new IllegalArgumentException("입력값이 없습니다.");
		}
	}

	public static void checkDivideByZero(String operator, double number) {
		if (Operator.isDivide(operator) && number == ZERO) {
			throw new ArithmeticException("0으로 나눌 수는 없습니다.");
		}
	}

	public static void checkIsNumber(String term) {
		if (!isNumber(term)) {
			throw new IllegalArgumentException("유효한 숫자가 아닙니다.");
		}
	}

	public static void checkIsValidExpression(List<String> tokens) {
		if (!isValidSize(tokens) || !isNumber(tokens.get(ZERO))) {
			throw new IllegalArgumentException("유효한 식이 아닙니다.");
		}
	}

	private static boolean isValidSize(List<String> tokens) {
		return (tokens.size() & ONE) == ONE;
	}

	private static boolean isNumber(String term) {
		int firstIndex = ZERO;

		if (isFirstNumberMinus(term)) {
			firstIndex = ONE;
		}

		for (int i = firstIndex; i < term.length(); i++) {
			if (!Character.isDigit(term.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private static boolean isFirstNumberMinus(String term) {
		return (Operator.isMinus(term) && term.length() > ONE);
	}
}
