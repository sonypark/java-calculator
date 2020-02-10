package domain;

import java.util.Arrays;
import java.util.List;

import utils.Validator;

public class Calculator {
	private static final String BLANK = " ";
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int FIRST_OPERATOR = 1;
	private static final int NEXT_OPERATOR = 2;

	public static Double calculate(String expression) {
		List<String> tokens = Arrays.asList(expression.split(BLANK));
		Validator.checkIsValidExpression(tokens);

		double accumulatedNumber = Double.parseDouble(tokens.get(ZERO));
		for (int i = FIRST_OPERATOR; i < tokens.size(); i += NEXT_OPERATOR) {
			String operator = tokens.get(i);
			String nextNumber = tokens.get(i + ONE);

			Validator.checkIsNumber(nextNumber);
			Validator.checkDivideByZero(operator, Double.parseDouble(nextNumber));
			accumulatedNumber = Operator.isEqual(operator).operate(accumulatedNumber, Double.parseDouble(nextNumber));
		}
		return accumulatedNumber;
	}

}
