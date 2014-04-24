package calc;

import java.util.Stack;

public class OppPow extends Opperator{

	@Override
	public Value evaluate(Stack<Element> stack) {
		double value = Math.pow(stack.pop().evaluate(stack).getValue(), stack.pop().evaluate(stack).getValue());
		return new Value(value);
	}

}
