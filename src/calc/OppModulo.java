package calc;

import java.util.Stack;

public class OppModulo extends Opperator{

	@Override
	public Value evaluate(Stack<Element> stack) {
		double value = stack.pop().evaluate(stack).getValue() % stack.pop().evaluate(stack).getValue();
		return new Value(value);
	}

}
