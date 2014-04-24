package calc;

import java.util.Stack;

public abstract class Element {

	public abstract Value evaluate(Stack<Element> stack);

}
