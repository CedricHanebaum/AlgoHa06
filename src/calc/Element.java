package calc;

import java.util.Stack;

public abstract class Element {

	/**
	 * Evaluates the Element. In case of an Operator, performs the operation. In case of a value, returns the value.
	 * @param stack the stack to work with.
	 * @return value of the evaluation.
	 */
	public abstract Value evaluate(Stack<Element> stack);

}
