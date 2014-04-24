package calc;

import java.util.Stack;

public class OppDivision extends Opperator{

	@Override
	public Value evaluate(Stack<Element> stack) {
		double value = stack.pop().evaluate(stack).getValue() / stack.pop().evaluate(stack).getValue();
		return new Value(value);
	}

}
